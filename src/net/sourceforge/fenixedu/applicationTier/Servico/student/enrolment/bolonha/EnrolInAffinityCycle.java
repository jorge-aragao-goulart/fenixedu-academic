package net.sourceforge.fenixedu.applicationTier.Servico.student.enrolment.bolonha;

import net.sourceforge.fenixedu.applicationTier.FenixService;
import net.sourceforge.fenixedu.applicationTier.Servico.exceptions.FenixServiceException;
import net.sourceforge.fenixedu.dataTransferObject.student.enrollment.bolonha.CycleEnrolmentBean;
import net.sourceforge.fenixedu.domain.ExecutionSemester;
import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.StudentCurricularPlan;
import net.sourceforge.fenixedu.domain.candidacy.Ingression;
import net.sourceforge.fenixedu.domain.candidacy.MDCandidacy;
import net.sourceforge.fenixedu.domain.degreeStructure.CycleCourseGroup;
import net.sourceforge.fenixedu.domain.student.AffinityCyclesManagement;
import net.sourceforge.fenixedu.domain.student.Registration;
import net.sourceforge.fenixedu.domain.student.RegistrationAgreement;
import net.sourceforge.fenixedu.domain.student.Student;
import net.sourceforge.fenixedu.domain.student.registrationStates.RegistrationState;
import net.sourceforge.fenixedu.domain.student.registrationStates.RegistrationStateType;
import net.sourceforge.fenixedu.domain.student.registrationStates.RegistrationState.RegistrationStateCreator;
import net.sourceforge.fenixedu.domain.studentCurriculum.CycleCurriculumGroup;

import org.joda.time.DateTime;

import pt.ist.fenixWebFramework.services.Service;

public class EnrolInAffinityCycle extends FenixService {

    /**
     * This method is used when student is enroling second cycle without
     * conclude first cycle
     * 
     */
    @Service
    public static void run(final Person person, final CycleEnrolmentBean cycleBean) {
	final StudentCurricularPlan studentCurricularPlan = cycleBean.getStudentCurricularPlan();
	studentCurricularPlan.enrolInAffinityCycle(cycleBean.getCycleCourseGroupToEnrol(), cycleBean.getExecutionPeriod());
    }

    /**
     * This method is used to create new registrations based on a new cycle. If
     * second cycle belongs to the same DegreeCurricularPlan then we use
     * studentCurricularPlan.enrolInAffinityCycle(cycleCourseGroupToEnrol,
     * executionPeriod). Else we create a new empty registration or we separate
     * the old second cycle that exists in previous StudentCurricularPlan to a
     * new registration
     * 
     * 
     */
    @Service
    public static Registration run(final Person person, final StudentCurricularPlan studentCurricularPlan,
	    final CycleCourseGroup cycleCourseGroupToEnrol, final ExecutionSemester executionSemester)
	    throws FenixServiceException {

	/*
	 * TODO: refactor this code, should be more generic and moved to
	 * AffinityCyclesManagement, while refactoring
	 * SeparationCyclesManagement
	 */

	checkConditionsToEnrol(studentCurricularPlan, executionSemester);

	final CycleCurriculumGroup secondCycle = studentCurricularPlan.getSecondCycle();
	if (secondCycle == null) {

	    if (studentCurricularPlanAllowAffinityCycle(studentCurricularPlan, cycleCourseGroupToEnrol)) {
		studentCurricularPlan.enrolInAffinityCycle(cycleCourseGroupToEnrol, executionSemester);
		return studentCurricularPlan.getRegistration();

	    } else {

		final Student student = studentCurricularPlan.getRegistration().getStudent();
		if (student.hasActiveRegistrationFor(cycleCourseGroupToEnrol.getParentDegreeCurricularPlan())) {
		    throw new FenixServiceException("error");
		}

		final MDCandidacy candidacy = createMDCandidacy(student, cycleCourseGroupToEnrol, executionSemester);
		final Registration newRegistration = new Registration(student.getPerson(), cycleCourseGroupToEnrol
			.getParentDegreeCurricularPlan(), candidacy, RegistrationAgreement.NORMAL, cycleCourseGroupToEnrol
			.getCycleType());

		newRegistration.setSourceRegistration(studentCurricularPlan.getRegistration());
		newRegistration.getActiveState().setResponsiblePerson(null);
		newRegistration.setIngression(Ingression.DA1C);

		markOldRegistrationWithConcludedState(studentCurricularPlan);

		return newRegistration;
	    }

	} else if (secondCycle.isExternal()) {
	    return new AffinityCyclesManagement(studentCurricularPlan).enrol(cycleCourseGroupToEnrol);
	} else {
	    throw new FenixServiceException("error");
	}
    }

    private static void markOldRegistrationWithConcludedState(final StudentCurricularPlan studentCurricularPlan) {
	if (studentCurricularPlan.getRegistration().hasState(RegistrationStateType.CONCLUDED)) {
	    return;
	}

	final Registration registration = studentCurricularPlan.getRegistration();
	final RegistrationState state = RegistrationStateCreator.createState(registration, null, new DateTime(),
		RegistrationStateType.CONCLUDED);
	state.setResponsiblePerson(null);
    }

    private static boolean studentCurricularPlanAllowAffinityCycle(final StudentCurricularPlan studentCurricularPlan,
	    final CycleCourseGroup cycleCourseGroupToEnrol) {
	return studentCurricularPlan.getCycleTypes().contains(cycleCourseGroupToEnrol.getCycleType())
		&& studentCurricularPlan.getDegreeCurricularPlan() == cycleCourseGroupToEnrol.getParentDegreeCurricularPlan();
    }

    private static MDCandidacy createMDCandidacy(final Student student, final CycleCourseGroup cycleCourseGroupToEnrol,
	    final ExecutionSemester executionSemester) {
	return new MDCandidacy(student.getPerson(), cycleCourseGroupToEnrol.getParentDegreeCurricularPlan()
		.getExecutionDegreeByYear(executionSemester.getExecutionYear()));
    }

    /*
     * Refactor this (similar code already exists in
     * StudentCurricularPlanEnrolment) ?
     */
    private static void checkConditionsToEnrol(final StudentCurricularPlan studentCurricularPlan,
	    final ExecutionSemester executionSemester) throws FenixServiceException {

	if (executionSemester.isFirstOfYear() && hasSpecialSeason(studentCurricularPlan, executionSemester)) {
	    if (studentCurricularPlan.getDegreeCurricularPlan().getActualEnrolmentPeriodInCurricularCoursesSpecialSeason() == null) {
		throw new FenixServiceException("error.out.of.enrolment.period");
	    }
	} else if (executionSemester.isFirstOfYear()
		&& studentCurricularPlan.getRegistration().hasFlunkedState(executionSemester.getExecutionYear())
		&& studentCurricularPlan.getRegistration().hasRegisteredActiveState()) {
	    if (studentCurricularPlan.getDegreeCurricularPlan().getActualEnrolmentPeriodInCurricularCoursesSpecialSeason() == null) {
		throw new FenixServiceException("error.out.of.enrolment.period");
	    }
	} else {
	    if (!studentCurricularPlan.getDegreeCurricularPlan().hasActualEnrolmentPeriodInCurricularCourses()) {
		throw new FenixServiceException("error.out.of.enrolment.period");
	    }
	}

	if (studentCurricularPlan.getRegistration().getStudent().isAnyGratuityOrAdministrativeOfficeFeeAndInsuranceInDebt()) {
	    throw new FenixServiceException("error.message.debts.from.past.years.not.payed");
	}

	if (studentCurricularPlan.getPerson().hasAnyResidencePaymentsInDebt()) {
	    throw new FenixServiceException("error.StudentCurricularPlan.cannot.enrol.with.residence.debts");
	}
    }

    private static boolean hasSpecialSeason(final StudentCurricularPlan studentCurricularPlan,
	    final ExecutionSemester executionSemester) {
	if (studentCurricularPlan.hasSpecialSeasonFor(executionSemester)) {
	    return true;
	}

	final Registration registration = studentCurricularPlan.getRegistration();

	return registration.hasSourceRegistration()
		&& registration.getSourceRegistration().getLastStudentCurricularPlan().hasSpecialSeasonFor(executionSemester);
    }

}