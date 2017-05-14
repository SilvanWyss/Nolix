//package declaration
package ch.nolix.coreTest.utilTest;

//own imports
import ch.nolix.core.test2.ZetaTest;
import ch.nolix.element.basic.Time;

//test class
/**
 * This class is a test class for the time class.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 80
 */
public final class TimeTest extends ZetaTest {
	
	//loop test method
	public void loopTest_constructor_1() {
		
		//main loop
		for (int y = 1600; y <= 1600; y++) {	
			for (int m = 1; m <= 12; m++) {
				final int dayCount = m != 2 ? 30 : 28;		
				for (int d = 1; d <= dayCount; d++) {
					
					//execution
					final Time time = new Time(y, m, d);
					
					//verification
					expectThat(time.getYear()).equals(y);
					expectThat(time.getMonthOfYear()).equals(m);
					expectThat(time.getDayOfMonth()).equals(d);
					expectThat(time.getHourOfDay()).equals(Time.DEFAULT_HOUR_OF_DAY);
					expectThat(time.getMinuteOfHour()).equals(Time.DEFAULT_MINUTE_OF_HOUR);
					expectThat(time.getSecondOfMinute()).equals(Time.DEFAULT_SECOND_OF_MINUTE);
					expectThat(time.getMillisecondOfSecond()).equals(Time.DEFAULT_MILLISECOND_OF_SECOND);
				}
			}
		}
	}
	
	//loop test method
	public void loopTest_constructor_2() {
				
		//main loop
		for (int h = 0; h <= 23; h++) {		
			for (int m = 0; m <= 59; m++) {
					
					//execution
					final Time time
					= new Time(
						Time.DEFAULT_YEAR,
						Time.DEFAULT_MONTH_OF_YEAR,
						Time.DEFAULT_DAY_OF_MONTH,
						h,
						m
					);
					
					//verification
					expectThat(time.getYear()).equals(Time.DEFAULT_YEAR);
					expectThat(time.getMonthOfYear()).equals(Time.DEFAULT_MONTH_OF_YEAR);
					expectThat(time.getDayOfMonth()).equals(Time.DEFAULT_DAY_OF_MONTH);
					expectThat(time.getHourOfDay()).equals(h);
					expectThat(time.getMinuteOfHour()).equals(m);
					expectThat(time.getSecondOfMinute()).equals(Time.DEFAULT_SECOND_OF_MINUTE);
					expectThat(time.getMillisecondOfSecond()).equals(Time.DEFAULT_MILLISECOND_OF_SECOND);
			}
		}
	}
	
	//test method
	public void test_constructor() {
		
		//execution
		final Time time = new Time();
			
		//verification
		expectThat(time.getYear()).equals(Time.DEFAULT_YEAR);
		expectThat(time.getMonthOfYear()).equals(Time.DEFAULT_MONTH_OF_YEAR);
		expectThat(time.getDayOfMonth()).equals(Time.DEFAULT_DAY_OF_MONTH);
		expectThat(time.getHourOfDay()).equals(Time.DEFAULT_HOUR_OF_DAY);
		expectThat(time.getMinuteOfHour()).equals(Time.DEFAULT_MINUTE_OF_HOUR);
		expectThat(time.getSecondOfMinute()).equals(Time.DEFAULT_SECOND_OF_MINUTE);
		expectThat(time.getMillisecondOfSecond()).equals(Time.DEFAULT_MILLISECOND_OF_SECOND);
	}
}
