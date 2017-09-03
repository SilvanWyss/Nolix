//package declaration
package ch.nolix.application.candleStickAnalyzer;

//package-visible class
/**
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 30
 */
final class CommandManager {
	
	//input commands
	public static final String SELECT_RPODUCT_SYMBOL_COMMAND = "sps";
	public static final String SELECT_START_TIME_COMMAND = "sst";
	public static final String SELECT_END_TIME_COMMAND = "set";
	public static final String SELECT_RED_CANDLE_STICKS_BEFORE_HAMMER_COMMAND = "srcsbh";
	public static final String SELECT_GREEN_CANDLE_STICKS_AFTER_HAMMER_COMMAND = "sgcsah";
	public static final String SELECT_HAMMER_MINIMAL_LOWER_WICK_LENGTH_RATIO_COMMAND = "shmlwlr";
	public static final String SELECT_MAX_LOSS_RATIO_PER_DAY_COMMAND = "smlrpd";
	public static final String SELECT_MAX_KEEPING_DAYS_COMMAND = "smkd";
			
	//output commands
	public static final String SHOW_ANALYSIS_COMMAND = "sa";
	public static final String SAVE_PRODUCT_DATA_TO_FILE_COMMAND = "spdtf";
	public static final String SAVE_ANALYSIS_TO_FILE_COMMAND = "satf";
	public static final String SHOW_ALGORITHM_FACTS_COMMAND = "saf";

	//system commands
	public static final String SHOW_COMMANDS_COMMAND = "sc";
	public static final String QUIT_COMMAND = "q";
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private CommandManager() {}
}
