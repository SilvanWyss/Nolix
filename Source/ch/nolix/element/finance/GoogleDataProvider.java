//package declaration
package ch.nolix.element.finance;

//Java imports
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.core.Time;

//class
/**
 * Provides methods to get finance data.
 * To get the data a web service from Alphabet Inc. is used.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 150
 */
public final class GoogleDataProvider {

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
		return getCandleSticks(productSymbol, startDate, endDate, 24 * 60 * 60);
	}
	
	//method
	/**
	 * @param productSymbol
	 * @param startTime
	 * @param endTime
	 * @return the candle sticks per hour
	 * of the product with the given product symbol
	 * from the given start time
	 * to the given end time.
	 * @throws RuntimeException if an error occurs.
	 */
	public List<VolumeCandlestick> getCandleSticksPerHour(
		final String productSymbol,
		final Time startTime,
		final Time endTime
	) {
		return getCandleSticks(productSymbol, startTime, endTime, 60 * 60);
	}
	
	//method
	/**
	 * @param productSymbol
	 * @param startTime
	 * @param endTime
	 * @return the candle sticks per minute
	 * of the product with the given product symbol
	 * from the given start time
	 * to the given end time.
	 * @throws RuntimeException if an error occurs.
	 */
	public List<VolumeCandlestick> getCandleSticksPerMinute(
		final String productSymbol,
		final Time startTime,
		final Time endTime
	) {
		return getCandleSticks(productSymbol, startTime, endTime, 60);
	}
	
	//method
	/**
	 * This method uses a web service from Alphabet Inc. as data source.
	 * 
	 * @param productSymbol
	 * @param startTime
	 * @param endTime
	 * @param intervalInSeconds
	 * @return the candle sticks per the given interval in seconds
	 * of the product with the given product symbol
	 * from the given start time
	 * to the given end time.
	 * @throws RuntimeException if an error occurs.
	 */
	private List<VolumeCandlestick> getCandleSticks(
		final String productSymbol,
		final Time startTime,
		final Time endTime,
		final int intervalInSeconds
	) {
		
		final List<VolumeCandlestick> intraDayCandleSticks = new List<VolumeCandlestick>();
		
		final int dayCount = startTime.getDaysTo(Time.createCurrentTime());
		
		//Creates URL.
		final String URL =
		"https://www.google.com/finance/getprices"
		+ "?i=" + intervalInSeconds
		+ "&p=" + dayCount + "d"
		+ "&f=d,c,h,l,o,v"
		+ "&q=" + productSymbol;
		
		try {
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(URL).openStream()));
			int lineIndex = 1;
			String line;
			Time timeStamp = null;
			Time currentTime = null;
			while ((line = bufferedReader.readLine()) != null) {
							
				if (lineIndex > 7 && !line.startsWith("TIMEZONE_OFFSET")) {
					
					final String[] array = line.split(",");
					
					if (array[0].startsWith("a")) {
						timeStamp = Time.createFromUnixTimeStamp(Long.valueOf(array[0].substring(1)));
						currentTime = timeStamp;
					}
					else {
						currentTime = timeStamp.getWithAddedOrSubtractedSeconds(Integer.valueOf(array[0]) * intervalInSeconds);
					}
					
					intraDayCandleSticks.addAtEnd(
						new VolumeCandlestick(
							currentTime,
							Integer.valueOf(array[5]),
							Double.valueOf(array[4]),
							Double.valueOf(array[1]),
							Double.valueOf(array[3]),
							Double.valueOf(array[2])
						)
					);
				}
				
				lineIndex++;
			}
		}
		catch (final Exception e) {
			throw new RuntimeException(e);
		}
		
		return intraDayCandleSticks.removeAll(
			idcs -> idcs.getRefTime().isBefore(startTime) || idcs.getRefTime().isAfter(endTime)
		);
	}
}
