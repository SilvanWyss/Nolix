//package declaration
package ch.nolix.systemtest.timetest.momenttest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.timeapi.timestructureapi.Weekday;

//class
final class TimeTest extends StandardTest {

  //method
  @Test
  void testCase_getDay() {

    //setup
    final var testUnit = Time
      .withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
        2020,
        1,
        2,
        10,
        15,
        30,
        500);

    //execution
    final Time result = testUnit.getDay();

    //verification
    expect(result.getYearAsInt()).isEqualTo(2020);
    expect(result.getMonthOfYearAsInt()).isEqualTo(1);
    expect(result.getDayOfMonth()).isEqualTo(2);
    expect(result.getHourOfDay()).isEqualTo(0);
    expect(result.getMinuteOfHour()).isEqualTo(0);
    expect(result.getSecondOfMinute()).isEqualTo(0);
    expect(result.getMillisecondOfSecond()).isEqualTo(0);
  }

  //method
  @Test
  void testCase_getWeekday_whenIs2020_01_01() {

    //setup
    final var testUnit = Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1);

    //execution
    final var result = testUnit.getWeekday();

    //verification
    expect(result).isEqualTo(Weekday.WEDNESDAY);
  }

  //method
  @Test
  void testCase_getWeekday_whenIs2020_01_02() {

    //setup
    final var testUnit = Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 2);

    //execution
    final var result = testUnit.getWeekday();

    //verification
    expect(result).isEqualTo(Weekday.THURSDAY);
  }

  //method
  @Test
  void testCase_getWeekday_whenIs2020_01_03() {

    //setup
    final var testUnit = Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 3);

    //execution
    final var result = testUnit.getWeekday();

    //verification
    expect(result).isEqualTo(Weekday.FRIDAY);
  }

  //method
  @Test
  void testCase_isAfter_whenTheGivenTimeIsBefore() {

    //setup
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      01,
      00,
      00,
      00,
      000);

    //execution
    final var result = testUnit.isAfter(time);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isAfter_whenTheGivenTimeEquals() {

    //setup
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);

    //execution
    final var result = testUnit.isAfter(time);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isAfter_whenTheGivenTimeIsAfter() {

    //setup
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      03,
      00,
      00,
      00,
      000);

    //execution
    final var result = testUnit.isAfter(time);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isBefore_whenTheGivenTimeIsBefore() {

    //setup
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      01,
      00,
      00,
      00,
      000);
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      01,
      00,
      00,
      00,
      000);

    //execution
    final var result = testUnit.isBefore(time);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isBefore_whenTheGivenTimeEquals() {

    //setup
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);

    //execution
    final var result = testUnit.isBefore(time);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isBefore_whenTheGivenTimeIsAfter() {

    //setup
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      2030,
      06,
      03,
      00,
      00,
      00,
      000);

    //execution
    final var result = testUnit.isBefore(time);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_withYearAndMonthOfYearAndDayOfMonth() {
    for (var y = 1600; y <= 3000; y++) {
      for (var m = 1; m <= 12; m++) {

        final int dayCount;
        if (m == 2) {
          dayCount = 28;
        } else {
          dayCount = 30;
        }

        for (var d = 1; d <= dayCount; d++) {

          //execution
          final var result = Time.withYearAndMonthOfYearAndDayOfMonth(y, m, d);

          //verification
          expect(result.getYearAsInt()).isEqualTo(y);
          expect(result.getMonthOfYearAsInt()).isEqualTo(m);
          expect(result.getDayOfMonth()).isEqualTo(d);
        }
      }
    }
  }

  //method
  @Test
  void testCase_withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour() {

    //main loop
    for (var h = 0; h <= 23; h++) {
      for (var m = 0; m <= 59; m++) {

        //execution
        final var result = Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour(2000, 1, 1, h, m);

        //verification
        expect(result.getYearAsInt()).isEqualTo(2000);
        expect(result.getMonthOfYearAsInt()).isEqualTo(1);
        expect(result.getDayOfMonth()).isEqualTo(1);
        expect(result.getHourOfDay()).isEqualTo(h);
        expect(result.getMinuteOfHour()).isEqualTo(m);
        expect(result.getSecondOfMinute()).isEqualTo(0);
        expect(result.getMillisecondOfSecond()).isEqualTo(0);
      }
    }
  }
}
