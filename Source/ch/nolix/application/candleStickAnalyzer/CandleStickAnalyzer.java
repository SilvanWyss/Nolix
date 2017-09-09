//package declaration
package ch.nolix.application.candleStickAnalyzer;

//own imports
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
		return (NAME + " 2017-9-2");
	}
	
	//constructor
	/**
	 * Creates new candle stick analyzer.
	 */
	public CandleStickAnalyzer() {
		
		//Calls constructor of the base class.
		super(NAME, LoginSession.class);
	}
}
