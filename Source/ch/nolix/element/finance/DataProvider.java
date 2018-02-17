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
 * This class provides methods to get finance data from the web.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 210
 */
public final class DataProvider {

	//static method
	/**
	 * This method uses a web service from Alphabet Inc. as data source.
	 * 
	 * @param productSymbol
	 * @param startDate
	 * @param endDate
	 * @return the candle sticks per day of the product with the given product symbol from the given start date to the given end date.
	 */
	public List<VolumeCandlestick> getCandleSticksPerDay(
		final String productSymbol,
		final Time startDate,
		final Time endDate
	) {
		return getCandleSticks(productSymbol, startDate, endDate, 24 * 60 * 60);
	}
	
	//static method
	/**
	 * This method uses a web service from Yahoo Inc. as data source.
	 * 
	 * @param productSymbol
	 * @param startDate
	 * @param endDate
	 * @return the candle sticks per day from the given start date to the given end date of the product with the given product symbol.
	 * @throws Exception if an error occurs
	 */
	public List<VolumeCandlestick> getCandleSticksPerDay2(
		final String productSymbol,
		final Time startDate,
		final Time endDate
	) {
		try {
			
			final List<VolumeCandlestick> dailyCandleSticks = new List<VolumeCandlestick>();
			
			final String URLString = "http://finance.yahoo.com/table.csv"
				+ "?s=" + productSymbol
				+ "&amp;a=" + (startDate.getMonthOfYear() - 1)
				+ "&amp;b=" + startDate.getDayOfMonth()
				+ "&amp;c=" + startDate.getYear()
				+ "&amp;d=" + (endDate.getMonthOfYear() - 1)
				+ "&amp;e=" + endDate.getDayOfMonth()
				+ "&amp;f=" + endDate.getYear()
				+ "&amp;g=d"
				+ "&amp;ignore=.csv";
			
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(URLString).openStream()));
			String line;
			bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {

				final String[] stringArray = line.split(",");
				
				dailyCandleSticks.addAtBegin(
					new VolumeCandlestick(
						new Time(stringArray[0]),
						Integer.valueOf(stringArray[5]),
						Double.valueOf(stringArray[1]),
						Double.valueOf(stringArray[4]),
						Double.valueOf(stringArray[3]),
						Double.valueOf(stringArray[2])
					)	
				);
			}
			
			return dailyCandleSticks;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//static method
	/**
	 * This method uses a web service from Alphabet Inc. as data source.
	 * 
	 * @param productSymbol
	 * @param startTime
	 * @param endTime
	 * @return the candle sticks per hour of the product with the given product symbol from the given start time to the given end time.
	 */
	public List<VolumeCandlestick> getCandleSticksPerHour(
		final String productSymbol,
		final Time startTime,
		final Time endTime
	) {
		return getCandleSticks(productSymbol, startTime, endTime, 60 * 60);
	}
	
	//static method
	/**
	 * This method uses a web service from Alphabet Inc. as data source.
	 * 
	 * @param productSymbol
	 * @param startTime
	 * @param endTime
	 * @return the candle sticks per minute of the product with the given product symbol from the given start time to the given end time.
	 */
	public List<VolumeCandlestick> getCandleSticksPerMinute(
		final String productSymbol,
		final Time startTime,
		final Time endTime
	) {
		return getCandleSticks(productSymbol, startTime, endTime, 60);
	}
	
	//static method
	/**
	 * This method uses a web service from Alphabet Inc. as data source.
	 * 
	 * @param productSymbol
	 * @param startTime
	 * @param endTime
	 * @param intervalInSeconds
	 * @return the candle sticks per the given interval in seconds of the product with the given product symbol from the given start time to the given end time.
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
							
				if (lineIndex > 7) {
					
					final String[] array = line.split(",");
					
					if (array[0].startsWith("a")) {
						timeStamp = Time.createFromUnixTimeStamp(Long.valueOf(array[0].substring(1)));
						currentTime = timeStamp;
					}
					else {
						currentTime = timeStamp.getTimeWithAddedSeconds(Integer.valueOf(array[0]) * intervalInSeconds);
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
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return intraDayCandleSticks.removeAll(
			idcs -> idcs.getRefTime().isBefore(startTime) || idcs.getRefTime().isAfter(endTime)
		);
	}
}
