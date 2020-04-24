//package declaration
package ch.nolix.element.finance;

//Java imports
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.wrapperException.WrapperException;
import ch.nolix.element.time.Time;

//class
/**
 * A {@link QuandlDataProvider} provides methods to get finance data from the Quandl web service.
 * A {@link QuandlDataProvider} is not mutable.
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
	 * @return the candle sticks per day of the product with the given productSymbol
	 * from the given startDate to the given endDate.
	 * @throws RuntimeException if an error occurs.
	 */
	public LinkedList<VolumeCandlestick> getCandleSticksPerDay(
		final String productSymbol,
		final Time startDate,
		final Time endDate
	) {
		
		final var dailyCandleSticks = new LinkedList<VolumeCandlestick>();
		final var URLString = "https://www.quandl.com/api/v3/datasets/WIKI/" + productSymbol + "/data.csv";
		
		try (final var bufferedReader = new BufferedReader(new InputStreamReader(new URL(URLString).openStream()))) {
			
			String line;
			bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				
				final var stringArray = line.split(",");
				final var time = new Time(stringArray[0]);
				
				if (!time.isBefore(startDate) && !time.isAfter(endDate)) {
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
			}
			
			return dailyCandleSticks;
		} catch (final Exception exception) {
			throw new WrapperException(exception);
		}
	}
}
