//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.system.client.Client;

//class
/**
 * A console back client is a client
 * whose counterpart provides a console and an info panel.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 240
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
	 * Clears the console
	 * of the counterpart of this console back client.
	 * 
	 * @return this console back client.
	 */
	public ConsoleBackClient clearConsole() {
		
		internal_getRefDuplexController()
		.run(Protocol.CLEAR_CONSOLE_COMMAND);
		
		return this;
	}
	
	//method
	/**
	 * Clears the info panel
	 * of the counterpart of this console back client.
	 * 
	 * @return this console back client.
	 */
	public ConsoleBackClient clearInfoPanel() {
		
		internal_getRefDuplexController()
		.run(Protocol.CLEAR_INFO_PANEL_COMMAND);
		
		return this;
	}
	
	//method
	/**
	 * @return the lines from the console
	 * of the counterpart of this console back client.
	 */
	public List<String> getLinesFromConsole() {
		return
		internal_getRefDuplexController()
		.getData(Protocol.LINES_FROM_CONSOLE_REQUEST)
		.getRefAttributes()
		.to(a -> a.toString());
	}
	
	//method
	/**
	 * Lets the counterpart of this console back client open a file explorer.
	 * 
	 * @return this console back client.
	 */
	public ConsoleBackClient openFileExplorer() {
		
		internal_getRefDuplexController()
		.run(Protocol.OPEN_FILE_EXPLORER_COMMAND);
		
		return this;
	}
	
	//method
	/**
	 * @return the next character from the console
	 * of the counterpart of this console back client.
	 */
	public char readNextCharacterFromConsole() {
		return
		internal_getRefDuplexController()
		.getData(Protocol.NEXT_CHARACTER_FROM_CONSOLE_REQUEST)
		.toString()
		.charAt(0);
	}
	
	//method
	/**
	 * Reads the next enter from the console
	 * of the counterpart of this console back client.
	 */
	public void readNextEnterFromConsole() {
		internal_getRefDuplexController()
		.run(Protocol.READ_NEXT_ENTER_FROM_CONSOLE_COMMAND);
	}
	
	//method
	/**
	 * @return the next line from the console
	 * of the counterpart of this console back client.
	 * 
	 */
	public String readNextLineFromConsole() {
		return
		internal_getRefDuplexController()
		.getData(Protocol.NEXT_LINE_FROM_CONSOLE_REQUEST)
		.toString();
	}
	
	//method
	/**
	 * Let this console back client read the next line, that is not empty, of the console of its counterpart.
	 * 
	 * @return the next line, that is not empty, from the console
	 * of the counterpart of this console back client.
	 */
	public String readNextNonEmptyLineFromConsole() {
		return
		internal_getRefDuplexController()
		.getData(Protocol.NEXT_NON_EMPTY_LINE_FROM_CONSOLE_REQUEST)
		.toString();
	}
	
	//method
	/**
	 * Resets this console back client.
	 * 
	 * @return this console back client.
	 */
	public ConsoleBackClient reset() {
		
		setTitle("Console");
		clearConsole();
		clearInfoPanel();
		
		return this;
	}
	
	//method
	/**
	 * Sets the title of the console
	 * of the counterpart of this console back client.
	 * 
	 * @param title
	 * @return this console back client.
	 * @throws NullArgumentException if the given title is null.
	 */
	public ConsoleBackClient setTitle(final String title) {
		
		internal_getRefDuplexController().run(
			Protocol.SET_TITLE_COMMAND
			+ "("
			+ StandardSpecification.createEscapeString(title)
			+")"
		);
		
		return this;
	}
	
	//method
	/**
	 * Writes the given line to the console
	 * of the counterpart of this console back client.
	 * 
	 * @param line
	 * @return this console back client.
	 * @throws NullArgumentException if the given line is null.
	 */
	public ConsoleBackClient writeNextLineToConsole(final String line) {
		
		//Checks if the given line is not null.
		Validator.supposeThat(line).thatIsNamed("line").isNotNull();
	
		internal_getRefDuplexController().run(
			Protocol.WRITE_NEXT_LINE_TO_CONSOLE_COMMAND
			+ "("
			+ StandardSpecification.createEscapeString(line)
			+ ")"
		);
		
		return this;
	}
	
	//method
	/**
	 * Writes the given lines to the console
	 * of the counterpart of this console back client.
	 * 
	 * @param lines
	 * @return this console back client.
     * @throws NullArgumentException if one of the given line is null.
	 */
	public ConsoleBackClient writeNextLineToConsole(final String... lines) {
				
		//Iterates the given lines.
		for (final String l : lines) {
			internal_getRefDuplexController().appendCommand(
				Protocol.WRITE_NEXT_LINE_TO_CONSOLE_COMMAND
				+ "("
				+ StandardSpecification.createEscapeString(l)
				+ ")"
			);
		}
		
		internal_getRefDuplexController().runAppendedCommands();
		
		return this;
	}
	
	//method
	/**
	 * Writes the given line to the info panel
	 * of the counterpart of this console back client.
	 * 
	 * @param line
	 * @return this console back client.
	 * @throws NullArgumentException if the given line is null.
	 */
	public ConsoleBackClient writeNextLineToInfoPanel(final String line) {
		
		internal_getRefDuplexController().run(
			Protocol.WRITE_NEXT_LINE_TO_INFO_PANEL_COMMAND
			+ "("
			+ StandardSpecification.createEscapeString(line)
			+ ")"
		);
		
		return this;
	}
	
	//method
	/**
	 * Writes the given lines to the info panel
	 * of the counterpart of this console back client.
	 * 
	 * @param lines
     * @throws NullArgumentException if one of the given line is null.
	 */
	public ConsoleBackClient writeNextLineToInfoPanel(final String... lines) { 
				
		//Iterates the given lines.
		for (final String l : lines) {
			internal_getRefDuplexController().appendCommand(
				Protocol.WRITE_NEXT_LINE_TO_INFO_PANEL_COMMAND
				+ "("
				+ StandardSpecification.createEscapeString(l)
				+ ")"	
			);
		}
		
		internal_getRefDuplexController().runAppendedCommands();
		
		return this;
	}

	//method
	/**
	 * Finishes the initialization of the session of this console back client.
	 */
	protected void internal_finishSessionInitialization() {}
}
