//package declaration
package ch.nolix.element.finance;

//Java imports
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import ch.nolix.core.containers.List;
import ch.nolix.element.time.Time;

//class
/**
 * Provides methods to get finance data.
 * To get the data a web service from Quandl is used.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 70
 */
public final class QuandlDataProvider {

	//method
	/**
	 * @param productSymbol
	 * @param startDate
	 * @param endDate
	 * @return the candle sticks per day
	 * of the product with the given product symbol
	 * from the given start date
	 * to the given end date.
	 * @throws RuntimeException if an error occurs.
	 */
	public List<VolumeCandlestick> getCandleSticksPerDay(
		final String productSymbol,
		final Time startDate,
		final Time endDate
	) {
		try {
			
			final List<VolumeCandlestick> dailyCandleSticks = new List<>();
			
			final String URLString = "https://www.quandl.com/api/v3/datasets/WIKI/"
				+ productSymbol
				+ "/data.csv";
			
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(URLString).openStream()));
			String line;
			bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {

				final String[] stringArray = line.split(",");
				
				final Time time = new Time(stringArray[0]);
				
				if (time.isBefore(startDate)) {
					break;
				}
				
				dailyCandleSticks.addAtBegin(
					new VolumeCandlestick(
						time,
						Integer.valueOf(stringArray[5].substring(0, stringArray[5].length() - 3)),
						Double.valueOf(stringArray[1]),
						Double.valueOf(stringArray[4]),
						Double.valueOf(stringArray[3]),
						Double.valueOf(stringArray[2])
					)	
				);
			}
			
			return dailyCandleSticks;
			
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
