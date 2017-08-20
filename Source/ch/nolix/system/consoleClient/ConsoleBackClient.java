//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.system.client.Client;

//class
/**
 * A console is a client that provides a console and an info panel.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 170
 */
public final class ConsoleBackClient extends Client<ConsoleBackClient> {
		
	//constructor
	/**
	 * Creates new console client with the given duplex controller.
	 * 
	 * @param duplexController
	 * @throws NullArgumentException if the given duplex controller is null.
	 */
	public ConsoleBackClient(final DuplexController duplexController) {
		
		//Calls constructor of the base class.
		internal_connect(duplexController);
	}
	
	//method
	/**
	 * Lets this console client clear the console.
	 */
	public void clearConsole() {
		internal_getRefDuplexController().run(Protocol.CLEAR_CONSOLE_COMMAND);
	}
	
	//method
	/**
	 * Lets this console client clear the info panel.
	 */
	public void clearInfoPanel() {
		internal_getRefDuplexController().run(Protocol.CLEAR_INFO_PANEL_COMMAND);
	}
	
	//method
	/**
	 * Lets this console client read the next character of the console.
	 * 
	 * @return the next character of the console.
	 */
	public char readNextCharacterFromConsole() {
		return
		internal_getRefDuplexController()
		.getData(Protocol.NEXT_CHARACTER_OF_CONSOLE_REQUEST)
		.toString()
		.charAt(0);
	}
	
	//method
	/**
	 * Lets this console client read the next enter of the console.
	 */
	public void readNextEnterFromConsole() {
		internal_getRefDuplexController().getData(Protocol.NEXT_ENTER_OF_CONSOLE_REQUEST);
	}
	
	//method
	/**
	 * Lets this console client read the next line of the console.
	 * 
	 * @return the next line of the console.
	 */
	public String readNextLineFromConsole() {
		return internal_getRefDuplexController().getData(Protocol.NEXT_LINE_OF_CONSOLE_REQUEST).toString();
	}
	
	//method
	/**
	 * Lets this console client write the given line to the console.
	 * 
	 * @param line
	 * @throws NullArgumentException if the given line is null.
	 */
	public void writeNextLineToConsole(final String line) {
		
		//Checks if the given line is not null.
		Validator.supposeThat(line).thatIsNamed("line").isNotNull();
	
		internal_getRefDuplexController().run(Protocol.WRITE_NEXT_LINE_TO_CONSOLE_COMMAND + "(" + line + ")");
	}
	
	//method
	/**
	 * Writes the given lines to the console.
	 * 
	 * @param lines
	 * @throws NullArgumentExcetpion if the given line container is null.
     * @throws NullArgumentException if one of the given line is null.
	 */
	public void writeNextLineToConsole(final String... lines) {
		
		//Checks if the given line container is not null
		//and if the given lines are not null.
		Validator.supposeThatTheStrings(lines).areNotNull();
		
		//Iterates the given lines.
		for (final String l : lines) {
			internal_getRefDuplexController().appendCommand(Protocol.WRITE_NEXT_LINE_TO_CONSOLE_COMMAND + "(" + l + ")");
		}
		
		internal_getRefDuplexController().runAppendedCommands();
	}
	
	//method
	/**
	 * Writes the given line to the info panel.
	 * 
	 * @param line
	 * @throws NullArgumentException if the given line is null.
	 */
	public void writeNextLineToInfoPanel(final String line) {
		
		//Checks if the given line is not null.
		Validator.supposeThat(line).thatIsNamed("line").isNotNull();
		
		internal_getRefDuplexController().run(
			Protocol.WRITE_NEXT_LINE_TO_INFO_PANEL_COMMAND
			+ "("
			+ StandardSpecification.createEscapeString(line)
			+ ")"
		);
	}
	
	//method
	/**
	 * Writes the given lines to the info panel.
	 * 
	 * @param lines
	 * @throws NullArgumentExcetpion if the given line container is null.
     * @throws NullArgumentException if one of the given line is null.
	 */
	public void writeNextLineToInfoPanel(final String... lines) { 
		
		//Checks if the given line container is not null
		//and if the given lines are not null.
		Validator.supposeThatTheStrings(lines).areNotNull();
		
		//Iterates the givne lines.
		for (final String l : lines) {
			
			internal_getRefDuplexController().appendCommand(
				Protocol.WRITE_NEXT_LINE_TO_INFO_PANEL_COMMAND
				+ "("
				+ StandardSpecification.createEscapeString(l)
				+ ")"	
			);
		}
		
		internal_getRefDuplexController().runAppendedCommands();
	}

	//method
	/**
	 * Finishes the initialization of the session of this console client.
	 */
	protected void internal_finishSessionInitialization() {}
}
