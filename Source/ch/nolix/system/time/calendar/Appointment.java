//package declaration
package ch.nolix.system.time.calendar;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.property.MutableValue;
import ch.nolix.system.time.moment.Time;
import ch.nolix.system.time.timevalidator.TimeValidator;
import ch.nolix.systemapi.timeapi.calendarapi.IMutableAppointment;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
public final class Appointment extends MutableElement implements IMutableAppointment<Appointment> {

  //constant
  public static final String DEFAULT_TITLE = PascalCaseCatalogue.APPOINTMENT;

  //constant
  private static final Time DEFAULT_START_TIME = Time
      .withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour(2020, 1, 1, 10, 0);

  private static final Time DEFAULT_END_TIME = Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour(2020,
      1, 1, 11, 0);

  //constant
  private static final String TITLE_HEADER = PascalCaseCatalogue.TITLE;

  //constant
  private static final String START_TIME_HEADER = PascalCaseCatalogue.START_TIME;

  //constant
  private static final String END_TIME_HEADER = PascalCaseCatalogue.END_TIME;

  //attribute
  private final MutableValue<String> title = MutableValue.forString(TITLE_HEADER, DEFAULT_TITLE,
      this::setTitle);

  //attribute
  private final MutableValue<ITime> startTime = new MutableValue<>(
      START_TIME_HEADER,
      DEFAULT_START_TIME,
      this::setStartTime,
      Time::fromSpecification,
      ITime::getSpecification);

  //attribute
  private final MutableValue<ITime> endTime = new MutableValue<>(
      END_TIME_HEADER,
      DEFAULT_END_TIME,
      this::setEndTime,
      Time::fromSpecification,
      ITime::getSpecification);

  //method
  @Override
  public ITime getEndTime() {
    return endTime.getValue();
  }

  //method
  @Override
  public ITime getStartTime() {
    return startTime.getValue();
  }

  //method
  @Override
  public String getTitle() {
    return title.getValue();
  }

  //method
  @Override
  public void reset() {
    setTitle(DEFAULT_TITLE);
  }

  //method
  @Override
  public Appointment setEndTime(final ITime endTime) {

    TimeValidator.assertThat(endTime).thatIsNamed(LowerCaseCatalogue.END_TIME).isBefore(getEndTime());

    this.endTime.setValue(endTime);

    return this;
  }

  //method
  @Override
  public Appointment setStartTime(final ITime startTime) {

    TimeValidator.assertThat(startTime).thatIsNamed(LowerCaseCatalogue.START_TIME).isBefore(getEndTime());

    this.startTime.setValue(startTime);

    return this;
  }

  //method
  @Override
  public Appointment setTitle(final String title) {

    GlobalValidator.assertThat(title).thatIsNamed(LowerCaseCatalogue.TITLE).isNotBlank();

    this.title.setValue(title);

    return this;
  }
}
