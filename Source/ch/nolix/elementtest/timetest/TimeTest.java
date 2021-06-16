//package declaration
package ch.nolix.elementtest.timetest;

//own imports
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.element.time.base.Time;

//class
/**
 * A {@link TimeTest} is a test for {@link Time}s.
 * 
 * @author Silvan Wyss
 * @date 2017-02-04
 * @lines 90
 */
public final class TimeTest extends Test {
	
	//loop test case
	public void loopTestCase_creation_1A() {
		
		//main loop
		for (int y = 1600; y <= 1600; y++) {
			for (int m = 1; m <= 12; m++) {
				
				final int dayCount;
				if (m == 2) {
					dayCount = 28;
				} else {
					dayCount = 30;
				}
				
				for (int d = 1; d <= dayCount; d++) {
					
					//execution
					final Time time = Time.withYearAndMonthOfYearAndDayOfMonth(y, m, d);
					
					//verification
					expect(time.getYearAsInt()).isEqualTo(y);
					expect(time.getMonthOfYear()).isEqualTo(m);
					expect(time.getDayOfMonth()).isEqualTo(d);
				}
			}
		}
	}
	
	//loop test case
	public void loopTestCase_creation_1B() {
				
		//main loop
		for (int h = 0; h <= 23; h++) {
			for (int m = 0; m <= 59; m++) {
					
				//execution
				final Time time	=
				Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour(2000, 1, 1, h, m);
				
				//verification
				expect(time.getYearAsInt()).isEqualTo(2000);
				expect(time.getMonthOfYear()).isEqualTo(1);
				expect(time.getDayOfMonth()).isEqualTo(1);
				expect(time.getHourOfDay()).isEqualTo(h);
				expect(time.getMinuteOfHour()).isEqualTo(m);
				expect(time.getSecondOfMinute()).isEqualTo(0);
				expect(time.getMillisecondOfSecond()).isEqualTo(0);
			}
		}
	}
	
	//method
	@TestCase
	public void testCase_getDay() {
		
		//setup
		final Time time =
		Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
			2010,
			10,
			10,
			10,
			10,
			10,
			100
		);
		
		//execution
		final Time day = time.getDay();
		
		//verification
		expect(day.getYearAsInt()).isEqualTo(2010);
		expect(day.getMonthOfYear()).isEqualTo(10);
		expect(day.getDayOfMonth()).isEqualTo(10);
		expect(day.getHourOfDay()).isEqualTo(0);
		expect(day.getMinuteOfHour()).isEqualTo(0);
		expect(day.getSecondOfMinute()).isEqualTo(0);
		expect(day.getMillisecondOfSecond()).isEqualTo(0);
	}
}
