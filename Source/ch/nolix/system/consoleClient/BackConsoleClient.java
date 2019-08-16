//package declaration
package ch.nolix.system.consoleClient;

import ch.nolix.core.containers.List;
import ch.nolix.core.endPoint5.EndPoint;
import ch.nolix.core.node.BaseNode;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.system.GUIClientoid.BackGUIClientoid;

//class
/**
 * A console back client is a client
 * whose counterpart provides a console and an info panel.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 360
 */
public final class BackConsoleClient extends BackGUIClientoid<BackConsoleClient> {
		
	//constructor
	/**
	 * Creates a new console client with the given duplex controller.
	 * 
	 * @param endPoint
	 * @throws NullArgumentException if the given duplex controller is null.
	 */
	public BackConsoleClient(final EndPoint endPoint) {
		
		//Calls constructor of the base class.
		internal_setDuplexController(endPoint);
	}
	
	//method
	/**
	 * Clears the console
	 * of the counterpart of this console back client.
	 * 
	 * @return this console back client.
	 */
	public BackConsoleClient clearConsole() {
		
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
	public BackConsoleClient clearInfoPanel() {
		
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
	 * @return the next line as secret line from the console
	 * of the counterpart of this console back client.
	 */
	public String readSecretLineFromConsole() {
		return
		internal_getDataFromCounterpart(Protocol.READ_SECRET_LINE_FROM_CONSOLE_REQUEST)
		.toString();
	}
	
	//method
	/**
	 * Resets this console back client.
	 * 
	 * @return this back console client.
	 */
	public BackConsoleClient reset() {
		
		clearConsole();
		clearInfoPanel();
		
		return this;
	}
	
	//method
	/**
	 * Sets the given design to the counterpart of this console back client.
	 * 
	 * @param design
	 * @return this console back client.
	 */
	public BackConsoleClient setDesign(final StandardConfiguration design) {
		
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
	public BackConsoleClient setTitle(final String title) {
		
		internal_runOnCounterpart(
			Protocol.SET_TITLE_COMMAND
			+ "("
			+ BaseNode.createReproducingString(title)
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
	public BackConsoleClient writeEmptyLineToConsole() {
		
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
	public BackConsoleClient writeEmptyLineToInfoPanel() {
		
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
	public BackConsoleClient writeLinesToConsole(final Iterable<String> lines) {
		
		internal_runOnCounterpart(
			Protocol.WRITE_LINES_TO_CONSOLE_COMMAND
			+ '('
			+ new List<String>(lines).to(s -> BaseNode.createReproducingString(s))
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
	public BackConsoleClient writeLinesToInfoPanel(final Iterable<String> lines) {
		
		internal_runOnCounterpart(
			Protocol.WRITE_LINES_TO_INFO_PANEL_COMMAND
			+ '('
			+ new List<String>(lines).to(s -> BaseNode.createReproducingString(s))
			+ ')'
		);
		
		return this;
	}
	
	//method
	/**
	 * Writes a line, that consists of the given character, to the console
	 * of the counterpart of this console back client.
	 * 
	 * @param character
	 * @return this console back client.
	 */
	public BackConsoleClient writeLineToConsole(final char character) {
		return writeLineToConsole(String.valueOf(character));
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
	public BackConsoleClient writeLineToConsole(final String line) {
		
		//Checks if the given line is not null.
		Validator.suppose(line).thatIsNamed("line").isNotNull();
	
		internal_runOnCounterpart(
			Protocol.WRITE_LINE_TO_CONSOLE_COMMAND
			+ "("
			+ BaseNode.createReproducingString(line)
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
	public BackConsoleClient writeLineToConsole(final String... lines) {
				
		internal_runOnCounterpart(
			Protocol.WRITE_LINES_TO_CONSOLE_COMMAND
			+ '('
			+ new List<String>(lines).to(s -> BaseNode.createReproducingString(s))
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
	public BackConsoleClient writeLineToInfoPanel(final String line) {
		
		internal_runOnCounterpart(
			Protocol.WRITE_LINE_TO_INFO_PANEL_COMMAND
			+ "("
			+ BaseNode.createReproducingString(line)
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
	public BackConsoleClient writeLineToInfoPanel(final String... lines) {
				
		internal_runOnCounterpart(
			Protocol.WRITE_LINES_TO_INFO_PANEL_COMMAND
			+ '('
			+ new List<String>(lines).to(s -> BaseNode.createReproducingString(s))
			+ ')'
		);
		
		return this;
	}
}
