//package declaration
package ch.nolix.system.consoleClient;

//own import
import ch.nolix.system.client.Session;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 130
 */
public abstract class ConsoleBackClientSession
extends Session<ConsoleBackClient> {

	//method
	/**
	 * Clears the console
	 * of the counterpart of the client of this console back client session.
	 */
	public final void clearConsole() {		
		getRefClient().clearConsole();
	}
	
	//method
	/**
	 * Clears the info panel
	 * of the counterpart of the client of this console back client session.
	 */
	public final void clearInfoPanel() {	
		getRefClient().clearInfoPanel();
	}
	
	//method
	/**
	 * Lets the counterpart of the client of this console back client session
	 * open a file explorer.
	 */
	public final void openFileExplorer() {	
		getRefClient().openFileExplorer();
	}
	
	//method
	/**
	 * @return the next character from the console
	 * of the counterpart of the client of this console back client session.
	 */
	public final char readNextCharacterFromConsole() {
		return getRefClient().readNextCharacterFromConsole();
	}
	
	//method
	/**
	 * Reads the next enter from the console
	 * of the counterpart of the client of this console back client session.
	 */
	public void readNextEnterFromConsole() {
		getRefClient().readNextEnterFromConsole();
	}
	
	//method
	/**
	 * @return the next line from the console
	 * of the counterpart of the client of this console back client session.
	 */
	public final String readNextLineFromConsole() {
		return getRefClient().readNextLineFromConsole();
	}
	
	//method
	/**
	 * @return the next line, that is not empty, from the console
	 * of the counterpart of the client of this console back client session.
	 */
	public final String readNextNonEmptyLineFromConsole() {
		return getRefClient().readNextNonEmptyLineFromConsole();
	}
	
	//method
	/**
	 * Sets the title of the console
	 * of the counterpart of the client of this console back client session.
	 * 
	 * @param title
	 */
	public final void setTitle(final String title) {	
		getRefClient().setTitle(title);
	}
	
	//method
	/**
	 * Writes the given line to the console
	 * of the counterpart of the client of this console back client session.
	 * 
	 * @param line
	 */
	public final void writeNextLineToConsole(final String line) {		
		getRefClient().writeNextLineToConsole(line);
	}
	
	//method
	/**
	 * Writes the given lines to the console
	 * of the counterpart of the client of this console back client session.
	 * 
	 * @param lines
	 */
	public final void writeNextLineToConsole(final String... lines) {		
		getRefClient().writeNextLineToConsole(lines);
	}
	
	//method
	/**
	 * Writes the given line to the info panel
	 * of the counterpart of the client of this console back client session.
	 * 
	 * @param line
	 * @return this console back client session.
	 */
	public final void writeNextLineToInfoPanel(final String line) {	
		getRefClient().writeNextLineToInfoPanel(line);
	}
	
	//method
	/**
	 * Writes the given lines to the info panel
	 * of the counterpart of the client of this console back client session.
	 * 
	 * @param lines
	 */
	public final void writeNextLineToInfoPanel(final String... lines) {		
		getRefClient().writeNextLineToInfoPanel(lines);
	}
}
