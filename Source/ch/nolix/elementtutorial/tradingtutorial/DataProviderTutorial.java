package ch.nolix.elementtutorial.tradingtutorial;

import ch.nolix.element.time.base.Time;
import ch.nolix.element.trading.NYSEProductSymbolCatalogue;
import ch.nolix.element.trading.QuandlDataProvider;

/**
 * The {@link DataProviderTutorial} is a tutorial for {@link QuandlDataProvider}.
 * 
 * @author Silvan Wyss
 * @date 2017-02-06
 * @lines 30
 */
public final class DataProviderTutorial {
	
	/**
	 * Loads and prints out to the console the candle sticks per day of BP from 2017-2-1 until 2017-2-3.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		new QuandlDataProvider()
		.getCandleSticksPerDay(
			NYSEProductSymbolCatalogue.BP,
			new Time(2017, 2, 1, 16, 0),
			new Time(2017, 2, 3, 22, 0)
		)
		.forEach(System.out::println);
	}
	
	/**
	 * Prevents that an instance of the {@link QuandlDataProvider} can be created.
	 */
	private DataProviderTutorial() {}
}
