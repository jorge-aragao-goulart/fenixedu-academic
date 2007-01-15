package net.sourceforge.fenixedu.domain.time.dateTimeFields;

import net.sourceforge.fenixedu.domain.time.chronologies.AcademicChronology;
import net.sourceforge.fenixedu.domain.time.durationFields.AcademicSemestersDurationFieldType;
import net.sourceforge.fenixedu.domain.time.durationFields.AcademicYearsDurationFieldType;

import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationFieldType;

public class AcademicSemesterOfAcademicYearDateTimeFieldType extends DateTimeFieldType {

    public static final AcademicSemesterOfAcademicYearDateTimeFieldType ACADEMIC_SEMESTER_OF_ACADEMIC_YEAR;

    static {
	ACADEMIC_SEMESTER_OF_ACADEMIC_YEAR = new AcademicSemesterOfAcademicYearDateTimeFieldType(
		"academicSemesterOfAcademicYear",
		AcademicSemestersDurationFieldType.ACADEMIC_SEMESTERS_TYPE,
		AcademicYearsDurationFieldType.ACADEMIC_YEARS_TYPE);
    }

    private AcademicSemesterOfAcademicYearDateTimeFieldType(String name, DurationFieldType unitType,
	    DurationFieldType rangeType) {
	super(name);
    }

    @Override
    public DateTimeField getField(Chronology chronology) {
	if(chronology instanceof AcademicChronology) {
	    return ((AcademicChronology)chronology).academicSemesterOfAcademicYear();
	}	
	return null;
    }


    @Override
    public DurationFieldType getDurationType() {
	return AcademicSemestersDurationFieldType.ACADEMIC_SEMESTERS_TYPE;
    }

    
    @Override
    public DurationFieldType getRangeDurationType() {
	return AcademicYearsDurationFieldType.ACADEMIC_YEARS_TYPE;
    }
}
