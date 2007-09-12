package net.sourceforge.fenixedu.domain.candidacy;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sourceforge.fenixedu.domain.Person;
import net.sourceforge.fenixedu.domain.RootDomainObject;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.domain.student.PrecedentDegreeInformation;
import net.sourceforge.fenixedu.domain.util.workflow.Operation;

import org.apache.commons.beanutils.BeanComparator;
import org.joda.time.YearMonthDay;

public abstract class Candidacy extends Candidacy_Base {

    protected Candidacy() {
	super();
	setNumber(createCandidacyNumber());
	setRootDomainObject(RootDomainObject.getInstance());
	setStartDate(new YearMonthDay());
    }

    public Candidacy(CandidacySituation candidacySituation) {
	this();
	if (candidacySituation == null) {
	    throw new DomainException("candidacy situation cannot be null");
	}
	this.addCandidacySituations(candidacySituation);
    }

    public final Integer createCandidacyNumber() {
	if (RootDomainObject.getInstance().getCandidaciesCount() == 0) {
	    return Integer.valueOf(1);
	}
	Candidacy candidacy = (Candidacy) Collections.max(RootDomainObject.getInstance()
		.getCandidaciesSet(), new BeanComparator("number"));
	return candidacy.getNumber() + 1;
    }

    public CandidacySituation getActiveCandidacySituation() {
	return Collections.max(getCandidacySituations(), CandidacySituation.DATE_COMPARATOR);
    }

    private CandidacySituation getFirstCandidacySituation() {
	return Collections.min(getCandidacySituations(), CandidacySituation.DATE_COMPARATOR);
    }

    // static methods

    public static Candidacy readByCandidacyNumber(Integer candidacyNumber) {
	for (Candidacy candidacy : RootDomainObject.getInstance().getCandidacies()) {
	    if (candidacy.getNumber().equals(candidacyNumber)) {
		return candidacy;
	    }
	}
	return null;
    }

    public static Set<Candidacy> readCandidaciesBetween(final Integer from, final Integer to) {
	final Set<Candidacy> result = new HashSet<Candidacy>();
	for (final Candidacy candidacy : RootDomainObject.getInstance().getCandidaciesSet()) {
	    if (candidacy.getNumber() >= from && candidacy.getNumber() <= to) {
		result.add(candidacy);
	    }
	}
	return result;
    }

    public static Set<Candidacy> readDegreeCandidaciesBetween(final Integer from, final Integer to) {
	final Set<Candidacy> result = new HashSet<Candidacy>();
	for (final Candidacy candidacy : RootDomainObject.getInstance().getCandidaciesSet()) {
	    if (candidacy instanceof DegreeCandidacy) {
		if (candidacy.getNumber() >= from && candidacy.getNumber() <= to) {
		    result.add(candidacy);
		}
	    }
	}
	return result;
    }

    public static Set<Candidacy> readDFACandidaciesBetween(final Integer from, final Integer to) {
	final Set<Candidacy> result = new HashSet<Candidacy>();
	for (final Candidacy candidacy : RootDomainObject.getInstance().getCandidaciesSet()) {
	    if (candidacy instanceof DFACandidacy) {
		if (candidacy.getNumber() >= from && candidacy.getNumber() <= to) {
		    result.add(candidacy);
		}
	    }
	}
	return result;
    }

    public abstract String getDescription();

    abstract Set<Operation> getOperations(CandidacySituation candidacySituation);

    abstract void moveToNextState(CandidacyOperationType candidacyOperationType, Person person);

    public abstract boolean isConcluded();

    @Override
    public YearMonthDay getStartDate() {
	return super.getStartDate() != null ? super.getStartDate() : getFirstCandidacySituation()
		.getSituationDate().toYearMonthDay();
    }

    public void delete() {

	removePerson();

	for (; !getCandidacySituationsSet().isEmpty(); getCandidacySituationsSet().iterator().next()
		.delete())
	    ;

	removeRootDomainObject();
	deleteDomainObject();
    }

    public abstract Map<String, Set<String>> getStateMapping();

    public abstract String getDefaultState();

    public void nextState(String nextState) {
	CandidacySituationType situationType = CandidacySituationType.valueOf(nextState);

	if (!situationType.equals(this.getActiveCandidacySituation().getCandidacySituationType())
		&& getValidNextStates().contains(nextState)) {

	    switch (situationType) {
	    case CANCELLED:
		new CancelledCandidacySituation(this);
		break;
	    case PRE_CANDIDACY:
		new PreCandidacySituation(this);
		break;
	    case STAND_BY:
		new StandByCandidacySituation(this);
		break;
	    case STAND_BY_CONFIRMED_DATA:
		new StandByConfirmedDataCandidacySituation(this);
		break;
	    case STAND_BY_FILLED_DATA:
		new StandByFilledDataCandidacySituation(this);
		break;
	    case REGISTERED:
		new RegisteredCandidacySituation(this);
		break;
	    case ADMITTED:
		new AdmittedCandidacySituation(this);
		break;
	    case NOT_ADMITTED:
		new NotAdmittedCandidacySituation(this);
		break;
	    case SUBSTITUTE:
		new SubstituteCandidacySituation(this);
		break;
	    default:
		break;
	    }
	}
    }


    public void nextState() {
	nextState(getDefaultState());
    }
    
    public void checkConditionsToForward() {
	checkConditionsToForward(getDefaultState());
    }

    public void checkConditionsToForward(String nextState) {

	switch (this.getActiveCandidacySituation().getCandidacySituationType()) {

	case CANCELLED:
	    throw new DomainException("error.impossible.to.forward.from.cancelled");
	case REGISTERED:
	    throw new DomainException("error.impossible.to.forward.from.registered");
	case ADMITTED:
	    if (this instanceof PHDProgramCandidacy) {
		if(((PHDProgramCandidacy)this).getPhdCandidacyEvent().isInDebt()) {
		    throw new DomainException("error.student.needs.to.pay.candidacy.fee");
		}
	    }
	    break;	    
	case STAND_BY:
	    if (!checkIfDataIsFilled()) {
		throw new DomainException("error.mandatory.data.not.filled.yet");
	    }
	    if (this instanceof DFACandidacy) {
		if (!checkIfPrecedenceDataIsFilled()) {
		    throw new DomainException("error.mandatory.data.not.filled.yet");
		}
	    }
	    break;
	default:
	    break;
	}
    }


    public Set<String> getValidNextStates() {
	return getStateMapping().get(
		getActiveCandidacySituation().getCandidacySituationType().toString());
    }

    private boolean checkIfPrecedenceDataIsFilled() {
	if(!(this instanceof DFACandidacy)) {
	    return false;
	}
	PrecedentDegreeInformation precedentDegreeInformation =  ((DFACandidacy)this).getPrecedentDegreeInformation();
	return (precedentDegreeInformation.getConclusionGrade() != null
		&& precedentDegreeInformation.getConclusionYear() != null
		&& precedentDegreeInformation.getCountry() != null
		&& precedentDegreeInformation.getDegreeDesignation() != null && precedentDegreeInformation
		.getInstitution() != null);
    }

    private boolean checkIfDataIsFilled() {
	Person person = getPerson();
	return (person.getGender() != null && person.getEmissionDateOfDocumentIdYearMonthDay() != null
		&& person.getEmissionLocationOfDocumentId() != null
		&& person.getExpirationDateOfDocumentIdYearMonthDay() != null
		&& person.getSocialSecurityNumber() != null && person.getProfession() != null
		&& person.getMaritalStatus() != null && person.getDateOfBirthYearMonthDay() != null
		&& person.getCountry() != null && person.getParishOfBirth() != null
		&& person.getDistrictSubdivisionOfBirth() != null && person.getDistrictOfBirth() != null
		&& person.getCountryOfBirth() != null && person.getNameOfFather() != null
		&& person.getNameOfMother() != null && person.hasDefaultPhysicalAddress() && person
		.getEmail() != null);
    }

}
