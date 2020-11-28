package ch.nolix.elementtutorial.financetutorial;

import ch.nolix.element.finance.NYSEProductSymbolCatalogue;
import ch.nolix.element.finance.QuandlDataProvider;
import ch.nolix.element.time.Time;

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
	 * Avoids that an instance of the {@link QuandlDataProvider} can be created.
	 */
	private DataProviderTutorial() {}
}
