//package declaration
package ch.nolix.element.finance;

//own import
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import ch.nolix.core.container.List;
import ch.nolix.element.core.Time;

//class
/**
 * Provides methods to get finance data.
 * To get the data a web service from Yahoo! Inc. is used.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 70
 */
public final class YahooDataProvider {

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
		}
		catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
