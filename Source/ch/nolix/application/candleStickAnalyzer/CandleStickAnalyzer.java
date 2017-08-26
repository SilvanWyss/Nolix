//package declaration
package ch.nolix.application.candleStickAnalyzer;

//own imports
import ch.nolix.element.basic.Time;
import ch.nolix.system.client.StandardApplication;
import ch.nolix.system.consoleClient.ConsoleBackClient;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 40
 */
public class CandleStickAnalyzer extends StandardApplication<ConsoleBackClient> {

	//name
	public static final String NAME = "Candlestick Analyzer";
	
	//static method
	/**
	 * @return the title of a candle stick analyzer.
	 */
	public static String getTitle() {
		
		final Time currentTime = Time.createCurrentTime();
		
		return
		NAME
		+ " "
		+ currentTime.getYear()
		+ "-"
		+ currentTime.getMonthOfYear()
		+ "-"
		+ currentTime.getDayOfMonth();
	}
	
	//constructor
	/**
	 * Creates new candle stick analyzer.
	 */
	public CandleStickAnalyzer() {
		
		//Calls constructor of the base class.
		super(NAME, MainSession.class);
	}
}
