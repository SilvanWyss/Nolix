//package declaration
package ch.nolix.application.candleStickAnalyzer;

//own import
import ch.nolix.system.consoleClient.ConsoleFrontClient;

//class
/**
 * This class provides a main method to launch a candlestick analyzer.
 * Of this class no instance can be created.
 * 
 * @author Silvan
 * @month 2017-08
 * @lines 30
 */
public final class Launcher {

	//main method
	/**
	 * Creates a candle stick analyzer and a console front client that connects to it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a candle stick analyzer.
		final CandleStickAnalyzer candleStickAnalyzer = new CandleStickAnalyzer();
		
		//Creates a console front client that connects to the candle stick analyzer.
		new ConsoleFrontClient(candleStickAnalyzer);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
