//package declaration
package ch.nolix.elementTest.timeTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.element.time.Time;

//test class
/**
 * A {@link TimeTest} is a test for {@link Time}s.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 90
 */
public final class TimeTest extends Test {
	
	//loop test case
	public void loopTestCase_creation() {
		
		//main loop
		for (int y = 1600; y <= 1600; y++) {
			for (int m = 1; m <= 12; m++) {
				final int dayCount = m != 2 ? 30 : 28;
				for (int d = 1; d <= dayCount; d++) {
					
					//execution
					final Time time = new Time(y, m, d);
					
					//verification
					expect(time.getYearAsInt()).isEqualTo(y);
					expect(time.getMonthOfYear()).isEqualTo(m);
					expect(time.getDayOfMonth()).isEqualTo(d);
				}
			}
		}
	}
	
	//loop test case
	public void loopTestCase_creation_2() {
				
		//main loop
		for (int h = 0; h <= 23; h++) {
			for (int m = 0; m <= 59; m++) {
					
				//execution
				final Time time	= new Time(2000, 1,	1,	h,	m);
				
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
	public void testCase_creation() {
		
		//execution
		final Time time = new Time();
			
		//verification
		expect(time.getYearAsInt()).isEqualTo(Time.DEFAULT_YEAR);
		expect(time.getMonthOfYear()).isEqualTo(Time.DEFAULT_MONTH_OF_YEAR);
		expect(time.getDayOfMonth()).isEqualTo(Time.DEFAULT_DAY_OF_MONTH);
		expect(time.getHourOfDay()).isEqualTo(Time.DEFAULT_HOUR_OF_DAY);
		expect(time.getMinuteOfHour()).isEqualTo(Time.DEFAULT_MINUTE_OF_HOUR);
		expect(time.getSecondOfMinute()).isEqualTo(Time.DEFAULT_SECOND_OF_MINUTE);
		expect(time.getMillisecondOfSecond()).isEqualTo(Time.DEFAULT_MILLISECOND_OF_SECOND);
	}
	
	//method
	@TestCase
	public void testCase_getDay() {
		
		//setup
		final Time time = new Time(2010, 10, 10, 10, 10, 10, 100);
		
		//execution
		final Time day = time.getDay();
		
		//verification
		expect(day.getYearAsInt()).isEqualTo(2010);
		expect(day.getMonthOfYear()).isEqualTo(10);
		expect(day.getDayOfMonth()).isEqualTo(10);
		expect(day.getHourOfDay()).isZero();
		expect(day.getMinuteOfHour()).isZero();
		expect(day.getSecondOfMinute()).isZero();
		expect(day.getMillisecondOfSecond()).isZero();
	}
}
