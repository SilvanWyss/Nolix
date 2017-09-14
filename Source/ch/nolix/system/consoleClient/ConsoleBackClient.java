//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.system.baseGUIClient.BaseGUIClient;

//class
/**
 * A console back client is a client
 * whose counterpart provides a console and an info panel.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 340
 */
public final class ConsoleBackClient extends BaseGUIClient<ConsoleBackClient> {
		
	//constructor
	/**
	 * Creates new console client with the given duplex controller.
	 * 
	 * @param duplexController
	 * @throws NullArgumentException if the given duplex controller is null.
	 */
	public ConsoleBackClient(final DuplexController duplexController) {
		
		//Calls constructor of the base class.
		internal_connectWith(duplexController);
	}
	
	//method
	/**
	 * Clears the console
	 * of the counterpart of this console back client.
	 * 
	 * @return this console back client.
	 */
	public ConsoleBackClient clearConsole() {
		
		internal_runOnCounterpart(Protocol.CLEAR_CONSOLE_COMMAND);
		
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
		
		internal_runOnCounterpart(Protocol.CLEAR_INFO_PANEL_COMMAND);
		
		return this;
	}
	
	//method
	/**
	 * @return the lines of the console
	 * of the counterpart of this console back client.
	 */
	public List<String> getLinesOfConsole() {
		return
		internal_getDataFromCounterpart(Protocol.LINES_OF_CONSOLE_REQUEST)
		.getRefAttributes()
		.to(a -> a.toString());
	}
	
	//method
	/**
	 * Quits the counterpart of this console back client.
	 */
	public void quit() {
		internal_runOnCounterpart(Protocol.QUIT_COMMAND);
	}
	
	//method
	/**
	 * @return the next character from the console
	 * of the counterpart of this console back client.
	 */
	public char readCharacterFromConsole() {
		return
		internal_getDataFromCounterpart(Protocol.READ_CHARACTER_FROM_CONSOLE_REQUEST)
		.toString()
		.charAt(0);
	}
	
	//method
	/**
	 * Reads the next enter from the console
	 * of the counterpart of this console back client.
	 */
	public void readEnterFromConsole() {
		internal_runOnCounterpart(Protocol.READ_ENTER_FROM_CONSOLE_COMMAND);
	}
	
	//method
	/**
	 * @return the next line from the console
	 * of the counterpart of this console back client.
	 * 
	 */
	public String readLineFromConsole() {
		return
		internal_getDataFromCounterpart(Protocol.READ_LINE_FROM_CONSOLE_REQUEST)
		.toString();
	}
	
	//method
	/**
	 * @return the next line, that is not empty, from the console
	 * of the counterpart of this console back client.
	 */
	public String readNonEmptyLineFromConsole() {
		return
		internal_getDataFromCounterpart(Protocol.READ_NON_EMPTY_LINE_FROM_CONSOLE_REQUEST)
		.toString();
	}
	
	//method
	/**
	 * Resets this console back client.
	 */
	public void reset() {	
		clearConsole();
		clearInfoPanel();
	}
	
	//method
	/**
	 * Sets the given design to the counterpart of this console back client.
	 * 
	 * @param design
	 * @return this console back client.
	 */
	public ConsoleBackClient setDesign(final StandardConfiguration design) {
		
		internal_runOnCounterpart(
			Protocol.SET_DESIGN_COMMAND
			+ "("
			+ design.toString()
			+ ")"
		);
		
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
		
		internal_runOnCounterpart(
			Protocol.SET_TITLE_COMMAND
			+ "("
			+ StandardSpecification.createEscapeString(title)
			+")"
		);
		
		return this;
	}
	
	//method
	/**
	 * Writes an empty line to the console
	 * of the counterpart of this console back client.
	 * 
	 * @return this console back client.
	 */
	public ConsoleBackClient writeEmptyLineToConsole() {
		
		internal_runOnCounterpart(Protocol.WRITE_EMPTY_LINE_TO_CONSOLE_COMMAND);
		
		return this;
	}
	
	//method
	/**
	 * Writes an empty line to the info panel
	 * of the counterpart of this console back client.
	 * 
	 * @return this console back client.
	 */
	public ConsoleBackClient writeEmptyTextLineToInfoPanel() {
		
		internal_runOnCounterpart(Protocol.WRITE_EMPTY_LINE_TO_INFO_PANEL_COMMAND);
		
		return this;
	}
	
	//method
	/**
	 * Writes the given lines to the console
	 * of the counterpart of this console back client.
	 * 
	 * @param lines
	 * @return this console back client.
	 * @throws NullArgumentException if the given line container is null.
	 * @throws NullArgumentException if one of the given line is null.
	 */
	public ConsoleBackClient writeLinesToConsole(final Iterable<String> lines) {
		
		internal_runOnCounterpart(
			Protocol.WRITE_LINES_TO_CONSOLE_COMMAND
			+ '('
			+ new List<String>(lines).to(s -> StandardSpecification.createEscapeString(s))
			+ ')'
		);
		
		return this;
	}
	
	//method
	/**
	 * Writes the given lines to the info panel
	 * of the counterpart of this console back client.
	 * 
	 * @param lines
	 * @return this console back client.
	 * @throws NullArgumentException if the given line container is null.
	 * @throws NullArgumentException if one of the given line is null.
	 */
	public ConsoleBackClient writeLinesToInfoPanel(final Iterable<String> lines) {
		
		internal_runOnCounterpart(
			Protocol.WRITE_LINES_TO_INFO_PANEL_COMMAND
			+ '('
			+ new List<String>(lines).to(s -> StandardSpecification.createEscapeString(s))
			+ ')'
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
	public ConsoleBackClient writeLineToConsole(final String line) {
		
		//Checks if the given line is not null.
		Validator.supposeThat(line).thatIsNamed("line").isNotNull();
	
		internal_runOnCounterpart(
			Protocol.WRITE_LINE_TO_CONSOLE_COMMAND
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
	public ConsoleBackClient writeLineToConsole(final String... lines) {
				
		internal_runOnCounterpart(
			Protocol.WRITE_LINES_TO_CONSOLE_COMMAND
			+ '('
			+ new List<String>(lines).to(s -> StandardSpecification.createEscapeString(s))
			+ ')'
		);
		
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
	public ConsoleBackClient writeLineToInfoPanel(final String line) {
		
		internal_runOnCounterpart(
			Protocol.WRITE_LINE_TO_INFO_PANEL_COMMAND
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
	public ConsoleBackClient writeLineToInfoPanel(final String... lines) { 
				
		internal_runOnCounterpart(
			Protocol.WRITE_LINES_TO_INFO_PANEL_COMMAND
			+ '('
			+ new List<String>(lines).to(s -> StandardSpecification.createEscapeString(s))
			+ ')'
		);
		
		return this;
	}

	//method
	/**
	 * Finishes the initialization of the session of this console back client.
	 */
	protected void internal_finishSessionInitialization() {}
}
