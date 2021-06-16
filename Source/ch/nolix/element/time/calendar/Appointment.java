//package declaration
package ch.nolix.element.time.calendar;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.base.MutableElement;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.time.base.Time;
import ch.nolix.element.time.calendarapi.IAppointment;
import ch.nolix.element.time.timevalidator.TimeValidator;

//class
public final class Appointment extends MutableElement<Appointment> implements IAppointment<Appointment> {
	
	//constants
	public static final String DEFAULT_SUBJECT = PascalCaseCatalogue.APPOINTMENT;
	
	//constant
	private static final Time DEFAULT_START_TIME =
	Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour(2020, 1, 1, 10, 0);
	
	private static final Time DEFAULT_END_TIME = 
	Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour(2020, 1, 1, 11, 0);
	
	//constants
	private static final String SUBJECT_HEADER = PascalCaseCatalogue.SUBJECT;
	private static final String START_TIME_HEADER = PascalCaseCatalogue.START_TIME;
	private static final String END_TIME_HEADER = PascalCaseCatalogue.END_TIME;
	
	//attribute
	private final MutableValue<String> subject =
	MutableValue.forString(SUBJECT_HEADER, DEFAULT_SUBJECT, this::setSubject);
	
	//attribute
	private final MutableValue<Time> startTime =
	new MutableValue<>(
		START_TIME_HEADER,
		DEFAULT_START_TIME,
		this::setStartTime,
		Time::fromSpecification,
		Time::getSpecification
	);
	
	//attribute
	private final MutableValue<Time> endTime =
	new MutableValue<>(
		END_TIME_HEADER,
		DEFAULT_END_TIME,
		this::setEndTime,
		Time::fromSpecification,
		Time::getSpecification
	);
	
	//method
	@Override
	public Time getEndTime() {
		return endTime.getValue();
	}
	
	//method
	@Override
	public Time getStartTime() {
		return startTime.getValue();
	}
	
	//method
	@Override
	public String getSubject() {
		return subject.getValue();
	}
	
	//method
	@Override
	public void reset() {
		setSubject(DEFAULT_SUBJECT);
	}
	
	//method
	@Override
	public Appointment setEndTime(final Time endTime) {
		
		TimeValidator.assertThat(endTime).thatIsNamed(LowerCaseCatalogue.END_TIME).isBefore(getEndTime());
		
		this.endTime.setValue(endTime);
		
		return this;
	}
	
	//method
	@Override
	public Appointment setStartTime(final Time startTime) {
		
		TimeValidator.assertThat(startTime).thatIsNamed(LowerCaseCatalogue.START_TIME).isBefore(getEndTime());
		
		this.startTime.setValue(startTime);
		
		return this;
	}
	
	//method
	@Override
	public Appointment setSubject(final String subject) {
		
		Validator.assertThat(subject).thatIsNamed(LowerCaseCatalogue.SUBJECT).isNotBlank();
		
		this.subject.setValue(subject);
		
		return this;
	}
}
