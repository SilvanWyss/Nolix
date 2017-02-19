//package declaration
package ch.nolix.commonTutorial.financeTutorial;

//own imports
import ch.nolix.element.basic.Time;
import ch.nolix.element.finance.DataProvider;
import ch.nolix.element.finance.NYSEProductSymbolManager;

//class
/**
 * This class provides a tutorial for the data provider class.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 30
 */
public final class DataProviderTutorial {
	
	//main method
	/**
	 * Loads and prints out to the console the candle sticks per hour of BP from 2017-2-1 until 2017-2-3.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		DataProvider.getCandleSticksPerHour(
			NYSEProductSymbolManager.BP,
			new Time(2017, 2, 1, 16, 0),
			new Time(2017, 2, 3, 22, 0)
		)
		.forEach(cs -> System.out.println(cs));
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private DataProviderTutorial() {}
}
