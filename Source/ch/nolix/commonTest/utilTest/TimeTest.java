//package declaration
package ch.nolix.commonTest.utilTest;

//own imports
import ch.nolix.common.util.Time;
import ch.nolix.common.zetaTest.ZetaTest;

//test class
/**
 * This class is a test class for the time class.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 80
 */
public class TimeTest extends ZetaTest {

	//loop test method
	public void loopTestTimeYearMonthDayConstructor() {
		
		//main loop
		for (int y = 1600; y <= 1600; y++) {	
			for (int m = 1; m <= 12; m++) {
				final int dayCount = m != 2 ? 30 : 28;		
				for (int d = 1; d <= dayCount; d++) {
					
					//setup and execution
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
	public void loopTestTimeYearMonthDayHourMinuteConstructor() {
		
		//test parameters
		final int year = 2000;
		final int month = 1;
		final int day = 1;
		
		//main loop
		for (int h = 0; h <= 23; h++) {		
			for (int m = 0; m <= 59; m++) {
					
					//setup and execution
					final Time time = new Time(year, month, day, h, m);
					
					//verification
					expectThat(time.getYear()).equals(year);
					expectThat(time.getMonthOfYear()).equals(month);
					expectThat(time.getDayOfMonth()).equals(day);
					expectThat(time.getHourOfDay()).equals(h);
					expectThat(time.getMinuteOfHour()).equals(m);
					expectThat(time.getSecondOfMinute()).equals(Time.DEFAULT_SECOND_OF_MINUTE);
					expectThat(time.getMillisecondOfSecond()).equals(Time.DEFAULT_MILLISECOND_OF_SECOND);
			}
		}
	}
	
	//test method
	public void testDefaultConstructor() {
		
		//setup and execution
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
