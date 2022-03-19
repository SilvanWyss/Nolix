//package declaration
package ch.nolix.element.trading;

//Java imports
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.element.time.base.Time;

//class
/**
 * A {@link QuandlDataProvider} provides methods to get finance data from the Quandl web service.
 * A {@link QuandlDataProvider} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-08-26
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
	public LinkedList<VolumeCandleStick> getCandleSticksPerDay(
		final String productSymbol,
		final Time startDate,
		final Time endDate
	) {
		
		final var dailyCandleSticks = new LinkedList<VolumeCandleStick>();
		final var URLString = "https://www.quandl.com/api/v3/datasets/WIKI/" + productSymbol + "/data.csv";
		
		try (final var bufferedReader = new BufferedReader(new InputStreamReader(new URL(URLString).openStream()))) {
			
			String line;
			bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				
				final var stringArray = line.split(",");
				final var time = Time.fromString(stringArray[0]);
				
				if (!time.isBefore(startDate) && !time.isAfter(endDate)) {
					dailyCandleSticks.addAtBegin(
						new VolumeCandleStick(
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
