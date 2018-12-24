//package declaration
package ch.nolix.system.consoleClient;

//package-visible class
/**
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 40
 */
final class Protocol {
	
	//general commands
	public static final String QUIT_COMMAND = "Quit";
	public static final String SET_DESIGN_COMMAND = "SetDesign";
	public static final String SET_TITLE_COMMAND = "SetTitle";
	
	//console commands
	public static final String CLEAR_CONSOLE_COMMAND = "ClearConsole";
	public static final String READ_ENTER_FROM_CONSOLE_COMMAND = "ReadEnterFromConsole";
	public static final String WRITE_EMPTY_LINE_TO_CONSOLE_COMMAND = "WriteEmptyLineToConsole";
	public static final String WRITE_LINE_TO_CONSOLE_COMMAND = "WriteLineToConsole";
	public static final String WRITE_LINES_TO_CONSOLE_COMMAND = "WriteLinesToConsole";
	
	//info panel commands
	public static final String CLEAR_INFO_PANEL_COMMAND = "ClearInfoPanel";
	public static final String WRITE_EMPTY_LINE_TO_INFO_PANEL_COMMAND = "WriteEmptyLineToInfoPanel";
	public static final String WRITE_LINE_TO_INFO_PANEL_COMMAND = "WriteLineToInfoPanel";
	public static final String WRITE_LINES_TO_INFO_PANEL_COMMAND = "WriteLinesToInfoPanel";
		
	//console requests
	public static final String LINES_OF_CONSOLE_REQUEST = "LinesOfConsole";
	public static final String READ_CHARACTER_FROM_CONSOLE_REQUEST = "ReadtCharacterFromConsole";
	public static final String READ_LINE_FROM_CONSOLE_REQUEST = "ReadLineFromConsole";
	public static final String READ_NON_EMPTY_LINE_FROM_CONSOLE_REQUEST = "NonEmptyLineFromConsole";
	public static final String READ_SECRET_LINE_FROM_CONSOLE_REQUEST = "ReadSecretLineFromConsole";
	
	//info panel requests
	public static final String LINES_OF_INFO_PANEL_REQUEST = "LinesOfInfoPanel";

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Protocol() {}
}
