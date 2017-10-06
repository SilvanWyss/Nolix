//package declaration
package ch.nolix.elementTest.coreTest;

//own imports
import ch.nolix.core.test2.Test;
import ch.nolix.element.core.Time;

//test class
/**
 * This class is a test class for the time class.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 70
 */
public final class TimeTest extends Test {
	
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
					expectThat(time.getYear()).isEqualTo(y);
					expectThat(time.getMonthOfYear()).isEqualTo(m);
					expectThat(time.getDayOfMonth()).isEqualTo(d);
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
				final Time time	= new Time(2000, 1,	1,	h,	m);
				
				//verification
				expectThat(time.getYear()).isEqualTo(2000);
				expectThat(time.getMonthOfYear()).isEqualTo(1);
				expectThat(time.getDayOfMonth()).isEqualTo(1);
				expectThat(time.getHourOfDay()).isEqualTo(h);
				expectThat(time.getMinuteOfHour()).isEqualTo(m);
				expectThat(time.getSecondOfMinute()).isEqualTo(0);
				expectThat(time.getMillisecondOfSecond()).isEqualTo(0);
			}
		}
	}
	
	//test method
	public void test_constructor() {
		
		//execution
		final Time time = new Time();
			
		//verification
		expectThat(time.getYear()).isEqualTo(Time.DEFAULT_YEAR);
		expectThat(time.getMonthOfYear()).isEqualTo(Time.DEFAULT_MONTH_OF_YEAR);
		expectThat(time.getDayOfMonth()).isEqualTo(Time.DEFAULT_DAY_OF_MONTH);
		expectThat(time.getHourOfDay()).isEqualTo(Time.DEFAULT_HOUR_OF_DAY);
		expectThat(time.getMinuteOfHour()).isEqualTo(Time.DEFAULT_MINUTE_OF_HOUR);
		expectThat(time.getSecondOfMinute()).isEqualTo(Time.DEFAULT_SECOND_OF_MINUTE);
		expectThat(time.getMillisecondOfSecond()).isEqualTo(Time.DEFAULT_MILLISECOND_OF_SECOND);
	}
}
