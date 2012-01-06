package net.sourceforge.fenixedu.domain.space;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.fenixedu.domain.ExecutionCourse;
import net.sourceforge.fenixedu.domain.FrequencyType;
import net.sourceforge.fenixedu.domain.Lesson;
import net.sourceforge.fenixedu.domain.LessonInstance;
import net.sourceforge.fenixedu.domain.accessControl.Group;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;
import net.sourceforge.fenixedu.domain.resource.ResourceAllocation;
import net.sourceforge.fenixedu.util.DiaSemana;
import net.sourceforge.fenixedu.util.HourMinuteSecond;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.TimeOfDay;
import org.joda.time.YearMonthDay;

import pt.ist.fenixWebFramework.security.accessControl.Checked;

public class LessonInstanceSpaceOccupation extends LessonInstanceSpaceOccupation_Base {

    @Checked("SpacePredicates.checkPermissionsToManageLessonInstanceSpaceOccupationsWithTeacherCheck")
    public LessonInstanceSpaceOccupation(AllocatableSpace allocatableSpace) {

	super();

	ResourceAllocation allocation = allocatableSpace
		.getFirstOccurrenceOfResourceAllocationByClass(LessonInstanceSpaceOccupation.class);
	if (allocation != null) {
	    throw new DomainException("error.LessonInstanceSpaceOccupation.occupation.for.this.space.already.exists");
	}

	setResource(allocatableSpace);
    }

    @Checked("SpacePredicates.checkPermissionsToManageLessonInstanceSpaceOccupationsWithTeacherCheck")
    public void edit(LessonInstance lessonInstance) {

	if (hasLessonInstances(lessonInstance)) {
	    removeLessonInstances(lessonInstance);
	}

	AllocatableSpace space = (AllocatableSpace) getResource();
	final ExecutionCourse executionCourse = lessonInstance.getLesson().getExecutionCourse();
	if (!space.isOccupiedByExecutionCourse(executionCourse, lessonInstance.getBeginDateTime(),
		lessonInstance.getEndDateTime())
		&& !space.isFree(lessonInstance.getDay(), lessonInstance.getDay(), lessonInstance.getStartTime(),
			lessonInstance.getEndTime(), lessonInstance.getDayOfweek(), null, null, null)) {

	    // TODO : remove this check after end of term.
	    final YearMonthDay day = lessonInstance.getDay();
	    if (!(day.getYear() == 2012 && day.getMonthOfYear() == 1 && (day.getDayOfMonth() > 1 && day.getDayOfMonth() < 7))) {
		throw new DomainException("error.LessonInstanceSpaceOccupation.room.is.not.free", space.getIdentification(),
			lessonInstance.getDay().toString("dd-MM-yy"));
	    }
	}

	addLessonInstances(lessonInstance);
    }

    @Checked("SpacePredicates.checkPermissionsToManageLessonInstanceSpaceOccupations")
    public void delete() {
	if (canBeDeleted()) {
	    super.delete();
	}
    }

    private boolean canBeDeleted() {
	return !hasAnyLessonInstances();
    }

    @Override
    public boolean isLessonInstanceSpaceOccupation() {
	return true;
    }

    @Override
    protected boolean intersects(YearMonthDay startDate, YearMonthDay endDate) {
	return true;
    }

    @Override
    public List<Interval> getEventSpaceOccupationIntervals(YearMonthDay startDateToSearch, YearMonthDay endDateToSearch) {

	List<Interval> result = new ArrayList<Interval>();
	List<LessonInstance> lessonInstances = getLessonInstances();

	DateTime startDateTime = startDateToSearch != null ? startDateToSearch.toDateTimeAtMidnight() : null;
	DateTime endDateTime = endDateToSearch != null ? endDateToSearch.toDateTime(new TimeOfDay(23, 59, 59)) : null;

	for (LessonInstance lessonInstance : lessonInstances) {
	    if (startDateTime == null
		    || (!lessonInstance.getEndDateTime().isBefore(startDateTime) && !lessonInstance.getBeginDateTime().isAfter(
			    endDateTime))) {

		result.add(new Interval(lessonInstance.getBeginDateTime(), lessonInstance.getEndDateTime()));
	    }
	}
	return result;
    }

    @Override
    public YearMonthDay getBeginDate() {
	return null;
    }

    @Override
    public YearMonthDay getEndDate() {
	return null;
    }

    @Override
    public HourMinuteSecond getStartTimeDateHourMinuteSecond() {
	return null;
    }

    @Override
    public HourMinuteSecond getEndTimeDateHourMinuteSecond() {
	return null;
    }

    @Override
    public FrequencyType getFrequency() {
	return null;
    }

    @Override
    public DiaSemana getDayOfWeek() {
	return null;
    }

    @Override
    public Boolean getDailyFrequencyMarkSaturday() {
	return null;
    }

    @Override
    public Boolean getDailyFrequencyMarkSunday() {
	return null;
    }

    @Override
    public Group getAccessGroup() {
	return null;
    }

    @Override
    public boolean isOccupiedByExecutionCourse(final ExecutionCourse executionCourse, final DateTime start, final DateTime end) {
	for (final LessonInstance lessonInstance : getLessonInstancesSet()) {
	    final Lesson lesson = lessonInstance.getLesson();
	    if (lesson.getExecutionCourse() == executionCourse && start.isBefore(lessonInstance.getEndDateTime())
		    && end.isAfter(lessonInstance.getBeginDateTime())) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public String getPresentationString() {
	if (hasAnyLessonInstances()) {
	    return getLessonInstances().get(0).getLesson().getShift().getExecutionCourse().getSigla();
	}
	return getClass().getName();
    }
}
