//package declaration
package ch.nolix.systemtest.timetest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.time.base.Time;
import ch.nolix.systemapi.timeapi.timestructure.Weekday;

//class
/**
 * A {@link TimeTest} is a test for {@link Time}s.
 * 
 * @author Silvan Wyss
 * @date 2017-02-04
 */
public final class TimeTest extends Test {
	
	//method
	@TestCase
	public void testCase_getDay() {
		
		//setup
		final var testUnit =
		Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
			2020,
			1,
			2,
			10,
			15,
			30,
			500
		);
		
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
	@TestCase
	public void testCase_getWeekday_whenIs2020_01_01() {
		
		//setup
		final var testUnit = Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 1);
		
		//execution
		final var result = testUnit.getWeekday();
		
		//verification
		expect(result).isEqualTo(Weekday.WEDNESDAY);
	}
	
	//method
	@TestCase
	public void testCase_getWeekday_whenIs2020_01_02() {
		
		//setup
		final var testUnit = Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 2);
		
		//execution
		final var result = testUnit.getWeekday();
		
		//verification
		expect(result).isEqualTo(Weekday.THURSDAY);
	}
	
	//method
	@TestCase
	public void testCase_getWeekday_whenIs2020_01_03() {
		
		//setup
		final var testUnit = Time.withYearAndMonthOfYearAndDayOfMonth(2020, 1, 3);
		
		//execution
		final var result = testUnit.getWeekday();
		
		//verification
		expect(result).isEqualTo(Weekday.FRIDAY);
	}
	
	//method
	@TestCase
	public void testCase_withYearAndMonthOfYearAndDayOfMonth() {
		for (var y = 1600; y <= 3000; y++) {
			for (var m = 1; m <= 12; m++) {
				
				final int dayCount;
				if (m == 2) {
					dayCount = 28;
				} else {
					dayCount = 30;
				}
				
				for (int d = 1; d <= dayCount; d++) {
					
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
	@TestCase
	public void testCase_withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour() {
				
		//main loop
		for (var h = 0; h <= 23; h++) {
			for (var m = 0; m <= 59; m++) {
					
				//execution
				final var result =
				Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour(2000, 1, 1, h, m);
				
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
