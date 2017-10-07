//package declaration
package ch.nolix.application.candlestickAnalyzer;

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
	 * Creates a console front client that connects to a candle stick analyzer.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ConsoleFrontClient(new CandlestickAnalyzer());
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Launcher() {}
}
