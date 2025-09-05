package ch.nolix.systemtest.time.moment;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.time.timestructure.Weekday;

final class TimeTest extends StandardTest {
  @Test
  void testCase_getWeekday_whenIs2020_01_01() {
    //setup
    final var testUnit = Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1);

    //execution
    final var result = testUnit.getWeekday();

    //verification
    expect(result).isEqualTo(Weekday.WEDNESDAY);
  }

  @Test
  void testCase_getWeekday_whenIs2020_01_02() {
    //setup
    final var testUnit = Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 2);

    //execution
    final var result = testUnit.getWeekday();

    //verification
    expect(result).isEqualTo(Weekday.THURSDAY);
  }

  @Test
  void testCase_getWeekday_whenIs2020_01_03() {
    //setup
    final var testUnit = Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 3);

    //execution
    final var result = testUnit.getWeekday();

    //verification
    expect(result).isEqualTo(Weekday.FRIDAY);
  }

  @Test
  void testCase_isAfter_whenTheGivenTimeIsBefore() {
    //setup
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
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
    expect(result).isTrue();
  }

  @Test
  void testCase_isAfter_whenTheGivenTimeEquals() {
    //setup
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
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
    expect(result).isFalse();
  }

  @Test
  void testCase_isAfter_whenTheGivenTimeIsAfter() {
    //setup
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
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
    expect(result).isFalse();
  }

  @Test
  void testCase_isBefore_whenTheGivenTimeIsBefore() {
    //setup
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
      2030,
      06,
      01,
      00,
      00,
      00,
      000);
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
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
    expect(result).isFalse();
  }

  @Test
  void testCase_isBefore_whenTheGivenTimeEquals() {
    //setup
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
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
    expect(result).isFalse();
  }

  @Test
  void testCase_isBefore_whenTheGivenTimeIsAfter() {
    //setup
    final var testUnit = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
      2030,
      06,
      02,
      00,
      00,
      00,
      000);
    final var time = //
    Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
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
    expect(result).isTrue();
  }

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
          expect(result.getYear()).isEqualTo(y);
          expect(result.getMonthOfYearAsInt()).isEqualTo(m);
          expect(result.getDayOfMonth()).isEqualTo(d);
        }
      }
    }
  }

  @Test
  void testCase_withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHour() {
    //main loop
    for (var h = 0; h <= 23; h++) {
      for (var m = 0; m <= 59; m++) {
        //execution
        final var result = Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHour(2000, 1, 1, h, m);

        //verification
        expect(result.getYear()).isEqualTo(2000);
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
