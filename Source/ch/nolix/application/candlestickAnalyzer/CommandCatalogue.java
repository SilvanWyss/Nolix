//package declaration
package ch.nolix.application.candlestickAnalyzer;

//class
/**
 * The command catalogue stores the commands of the candlestick analyzer.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 40
 */
public final class CommandCatalogue {
	
	//input commands
	public static final String SELECT_RPODUCT_SYMBOL_COMMAND = "s";
	public static final String SELECT_START_TIME_COMMAND = "ss";
	public static final String SELECT_END_TIME_COMMAND = "se";
	public static final String SELECT_RED_CANDLE_STICKS_BEFORE_HAMMER_COMMAND = "sr";
	public static final String SELECT_GREEN_CANDLE_STICKS_AFTER_HAMMER_COMMAND = "sg";
	public static final String SELECT_HAMMER_MINIMAL_LOWER_WICK_LENGTH_RATIO_COMMAND = "sh";
	public static final String SELECT_MAX_LOSS_RATIO_PER_DAY_COMMAND = "sm";
	public static final String SELECT_MAX_KEEPING_DAYS_COMMAND = "smk";
			
	//output commands
	public static final String SHOW_ANALYSIS_COMMAND = "sa";
	public static final String SAVE_PRODUCT_DATA_TO_FILE_COMMAND = "sp";
	public static final String SAVE_ANALYSIS_TO_FILE_COMMAND = "sat";
	public static final String SHOW_ALGORITHM_FACTS_COMMAND = "saf";

	//system commands
	public static final String SHOW_COMMANDS_COMMAND = "sc";
	public static final String QUIT_COMMAND = "q";
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private CommandCatalogue() {}
}
