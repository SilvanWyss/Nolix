//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.system.client.Client;

//class
/**
 * A console is a client that provides a console.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 130
 */
public final class ConsoleClient extends Client<ConsoleClient> {
	
	//commands
	static final String WRITE_NEXT_LINE_TO_CONSOLE_COMMAND = "WriteNextLineToConsole";
	static final String CLEAR_CONSOLE_COMMAND = "ClearConsole";
	static final String WRITE_NEXT_LINE_TO_INFO_PANEL_COMMAND = "WriteNextLineToInfoPanel";
	static final String CLEAR_INFO_PANEL_COMMAND = "ClearInfoPanel";
	
	//requests
	static final String NEXT_LINE_OF_CONSOLE_REQUEST = "NextLineOfConsole";
	static final String NEXT_CHARACTER_OF_CONSOLE_REQUEST = "NextCharacterOfConsole";
	static final String NEXT_ENTER_OF_CONSOLE_REQUEST = "NextEnterOfConsole";
	static final String LINES_OF_CONSOLE_REQUEST = "LinesOfConsole";
	
	//constructor
	/**
	 * Creates new console client with the given duplex controller.
	 * 
	 * @param duplexController
	 * @throws NullArgumentException if the given duplex controller is null.
	 */
	public ConsoleClient(final DuplexController duplexController) {
		
		//Calls constructor of the base class.
		super(duplexController);
	}
	
	//method
	/**
	 * Lets this console client clear the console.
	 */
	public void clearConsole() {
		internal_getRefDuplexController().run(CLEAR_CONSOLE_COMMAND);
	}
	
	//method
	/**
	 * Lets this console client clear the info panel.
	 */
	public void clearInfoPanel() {
		internal_getRefDuplexController().run(CLEAR_INFO_PANEL_COMMAND);
	}
	
	//method
	/**
	 * Lets this console client read the next character of the console.
	 * 
	 * @return the next character of the console.
	 */
	public char readNextCharacterOfConsole() {
		return internal_getRefDuplexController().getData(NEXT_CHARACTER_OF_CONSOLE_REQUEST).toString().charAt(0);
	}
	
	//method
	/**
	 * Lets this console client read the next enter of the console.
	 */
	public void readNextEnterOfConsole() {
		internal_getRefDuplexController().getData(NEXT_ENTER_OF_CONSOLE_REQUEST);
	}
	
	//method
	/**
	 * Lets this console client read the next line of the console.
	 * 
	 * @return the next line of the console.
	 */
	public String readLineFromConsole() {
		return internal_getRefDuplexController().getData(NEXT_LINE_OF_CONSOLE_REQUEST).toString();
	}
	
	//method
	/**
	 * Lets this console client write the given line to the console.
	 * 
	 * @param line
	 * @throws NullArgumentException if the given line is null.
	 */
	public void writeLineToConsole(final String line) {
		
		//Checks if the given line is not null.
		Validator.supposeThat(line).thatIsNamed("line").isNotNull();
		
		internal_getRefDuplexController().run(new Specification(WRITE_NEXT_LINE_TO_CONSOLE_COMMAND, line).toString());
	}
	
	//method
	/**
	 * Writes the given lines to the console.
	 * 
	 * @param lines
     * @throws NullArgumentException if one of the givne line is null.
	 */
	public void writeLineToCosnole(final String... lines) {
		
		//Checks if no one of the given lines is null.
		//TODO: Add are not null method to zeta validator.
		//ZetaValidator.supposeThat(lines).areNotNull();
		
		//Iterates the given lines.
		for (final String l : lines) {
			internal_getRefDuplexController().appendCommand(new Specification(WRITE_NEXT_LINE_TO_CONSOLE_COMMAND, l).toString());
		}
		
		internal_getRefDuplexController().runAppendedCommands();
	}

	//method
	/**
	 * Finishes the initialization of the session of this console client.
	 */
	protected void internal_finishSessionInitialization() {}
}
