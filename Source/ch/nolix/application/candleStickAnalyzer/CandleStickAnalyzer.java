//package declaration
package ch.nolix.application.candlestickAnalyzer;

//own imports
import ch.nolix.system.client.StandardApplication;
import ch.nolix.system.consoleClient.ConsoleBackClient;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 30
 */
public class CandlestickAnalyzer
extends StandardApplication<ConsoleBackClient> {

	//name
	public static final String NAME = "Candlestick Analyzer";
	
	//title
	public static final String TITLE = NAME + " 2018-1-12";
	
	//constructor
	/**
	 * Creates new candle stick analyzer.
	 */
	public CandlestickAnalyzer() {
		
		//Calls constructor of the base class.
		super(NAME, LoginSession.class);
	}
}
