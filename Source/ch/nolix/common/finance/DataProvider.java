/*
 * file:	FinanceDataProvider.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	180
 */

//package declaration
package ch.nolix.common.finance;

//Java imports
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.container.Pair;
import ch.nolix.common.util.Time;

//class
/**
 * This class provides methods to get finance data from the web.
 */
public class DataProvider {

	//static method
	/**
	 * @param productSymbol
	 * @param startDate
	 * @param endDate
	 * @return the daily closing prices from the given start date to the given end date of the product with the given product symbol
	 * @throws Exception if an error occurs
	 */
	public final static List<Pair<Time, Double>> getDailyClosingPrices(
		final String productSymbol,
		final Time startDate,
		final Time endDate) {
		try {
			
			final List<Pair<Time, Double>> dailyClosingPrices = new List<Pair<Time, Double>>();
			
			final String URLString = "http://chart.finance.yahoo.com/table.csv"
				+ "?s=" + productSymbol
				+ "&amp;a=" + (startDate.getMonth() - 1)
				+ "&amp;b=" + startDate.getDay()
				+ "&amp;c=" + startDate.getYear()
				+ "&amp;d=" + (endDate.getMonth() - 1)
				+ "&amp;e=" + endDate.getDay()
				+ "&amp;f=" + endDate.getYear()
				+ "&amp;g=d"
				+ "&amp;ignore=.csv";
			
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(URLString).openStream()));
			boolean begin = true;
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (begin) {
					begin = false;
				}
				else {
					String[] stringArray = line.split(",");
					dailyClosingPrices.addAtBegin(new Pair<Time, Double>(new Time(stringArray[0]), new Double(stringArray[4])));
				}
			}
			
			return dailyClosingPrices;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//static method
	/**
	 * @param productSymbol
	 * @param startDate
	 * @param endDate
	 * @return the daily data from the given start date to the given end date of the product with the given product symbol
	 * @throws Exception if an error occurs
	 */
	public final static List<VolumeCandleStick> getDailyCandleSticks(
		final String productSymbol,
		final Time startDate,
		final Time endDate
	) {
		try {
			
			final List<VolumeCandleStick> dailyCandleSticks = new List<VolumeCandleStick>();
			
			final String URLString = "http://chart.finance.yahoo.com/table.csv"
				+ "?s=" + productSymbol
				+ "&amp;a=" + (startDate.getMonth() - 1)
				+ "&amp;b=" + startDate.getDay()
				+ "&amp;c=" + startDate.getYear()
				+ "&amp;d=" + (endDate.getMonth() - 1)
				+ "&amp;e=" + endDate.getDay()
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
	 * @param productSymbol
	 * @param startDate
	 * @param endDate
	 * @return the daily opening prices from the given start date to the given end date of the product with the given product symbol
	 * @throws Exception if an error occurs
	 */
	public final static List<Pair<Time, Double>> getDailyOpeningPrices(
		final String productSymbol,
		final Time startDate,
		final Time endDate) {
		try {
			
			final List<Pair<Time, Double>> dailyOpeningPrices = new List<Pair<Time, Double>>();
			
			final String URLString = "http://chart.finance.yahoo.com/table.csv"
				+ "?s=" + productSymbol
				+ "&amp;a=" + (startDate.getMonth() - 1)
				+ "&amp;b=" + startDate.getDay()
				+ "&amp;c=" + startDate.getYear()
				+ "&amp;d=" + (endDate.getMonth() - 1)
				+ "&amp;e=" + endDate.getDay()
				+ "&amp;f=" + endDate.getYear()
				+ "&amp;g=d"
				+ "&amp;ignore=.csv";
			
			final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(URLString).openStream()));
			boolean begin = true;
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (begin) {
					begin = false;
				}
				else {
					String[] stringArray = line.split(",");
					dailyOpeningPrices.addAtBegin(new Pair<Time, Double>(new Time(stringArray[0]), new Double(stringArray[1])));
				}
			}
			
			return dailyOpeningPrices;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private DataProvider() {}
}
