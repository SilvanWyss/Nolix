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
 * @lines 50
 */
public class TimeTest extends ZetaTest {

	//loop test method
	public void loopTestTimeWithYearsMonthsDays() {
		
		//main loop
		for (int y = 1970; y <= 2000; y++) {
			for (int m = 1; m <= 12; m++) {
				
				final int daysCount = m != 2 ? 30 : 28;
				
				for (int d = 1; d <= daysCount; d++) {
					
					//setup and execution
					final Time time = new Time(y, m, d);
					
					//verification
					expectThat(time.getYear()).equals(y);
					expectThat(time.getMonth()).equals(m);
					expectThat(time.getDay()).equals(d);;
				}
			}
		}
	
	}
	
	//loop test method
	public void loopTestTimeWithHoursMinutesSeconds() {
		
		//test parameters
		final int year = 2000;
		final int month = 1;
		final int day = 1;
		
		//main loop
		for (int h = 0; h <= 23; h++) {
			for (int m = 0; m <= 59; m++) {
				for (int s = 0; s <= 3599; s++) {
					//TODO: Complete implementation.
				}
			}
		}
	}
}
