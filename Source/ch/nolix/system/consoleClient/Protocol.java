//package declaration
package ch.nolix.system.consoleClient;

//package-visible class
/**
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 30
 */
final class Protocol {
	
	//commands
	public static final String CLEAR_CONSOLE_COMMAND = "ClearConsole";
	public static final String CLEAR_INFO_PANEL_COMMAND = "ClearInfoPanel";
	public static final String OPEN_FILE_EXPLORER_COMMAND = "OpenFileExplorer";
	public static final String READ_NEXT_ENTER_FROM_CONSOLE_COMMAND = "ReadNextEnterFromConsole";
	public static final String SET_TITLE_COMMAND = "SetTitle";
	public static final String WRITE_NEXT_LINE_TO_CONSOLE_COMMAND = "WriteNextLineToConsole";
	public static final String WRITE_NEXT_LINE_TO_INFO_PANEL_COMMAND = "WriteNextLineToInfoPanel";
		
	//requests
	public static final String LINES_FROM_CONSOLE_REQUEST = "LinesOfConsole";
	public static final String LINES_FROM_INFO_PANEL_REQUEST = "LinesOfInfoPanel";
	public static final String NEXT_CHARACTER_FROM_CONSOLE_REQUEST = "NextCharacterFromConsole";	
	public static final String NEXT_LINE_FROM_CONSOLE_REQUEST = "NextLineFromConsole";
	public static final String NEXT_NON_EMPTY_LINE_FROM_CONSOLE_REQUEST = "NextNonEmptyLineFromConsole";	

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Protocol() {}
}
