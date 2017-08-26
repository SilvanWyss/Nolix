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
	public static final String SET_TITLE = "SetTitle";
	public static final String WRITE_NEXT_LINE_TO_CONSOLE_COMMAND = "WriteNextLineToConsole";
	public static final String CLEAR_CONSOLE_COMMAND = "ClearConsole";
	public static final String WRITE_NEXT_LINE_TO_INFO_PANEL_COMMAND = "WriteNextLineToInfoPanel";
	public static final String CLEAR_INFO_PANEL_COMMAND = "ClearInfoPanel";
	
	//requests
	public static final String NEXT_LINE_OF_CONSOLE_REQUEST = "NextLineOfConsole";
	public static final String NEXT_CHARACTER_OF_CONSOLE_REQUEST = "NextCharacterOfConsole";
	public static final String NEXT_ENTER_OF_CONSOLE_REQUEST = "NextEnterOfConsole";
	public static final String LINES_OF_CONSOLE_REQUEST = "LinesOfConsole";

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Protocol() {}
}
