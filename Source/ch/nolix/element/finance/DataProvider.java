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
	public static List<VolumeCandleStick> getCandleSticksPerDay(
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
	public static final List<VolumeCandleStick> getCandleSticksPerDay2(
		final String productSymbol,
		final Time startDate,
		final Time endDate
	) {
		try {
			
			final List<VolumeCandleStick> dailyCandleSticks = new List<VolumeCandleStick>();
			
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
					new VolumeCandleStick(
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
	public static List<VolumeCandleStick> getCandleSticksPerHour(
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
	public static List<VolumeCandleStick> getCandleSticksPerMinute(
		final String productSymbol,
		final Time startTime,
		final Time endTime
	) {
		return getCandleSticks(productSymbol, startTime, endTime, 60);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private DataProvider() {}
	
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
	private static List<VolumeCandleStick> getCandleSticks(
		final String productSymbol,
		final Time startTime,
		final Time endTime,
		final int intervalInSeconds
	) {
		
		final List<VolumeCandleStick> intraDayCandleSticks = new List<VolumeCandleStick>();
		
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
						timeStamp = Time.createTimeFromUnixTimeStamp(Long.valueOf(array[0].substring(1)));
						currentTime = timeStamp;
					}
					else {
						currentTime = timeStamp.getTimeWithAddedSeconds(Integer.valueOf(array[0]) * intervalInSeconds);
					}
					
					//System.out.println(currentTime);
					
					intraDayCandleSticks.addAtEnd(
						new VolumeCandleStick(
							currentTime,
							new Integer(array[5]),
							new Double(array[4]),
							new Double(array[1]),
							new Double(array[3]),
							new Double(array[2])
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
