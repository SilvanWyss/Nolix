//package declaration
package ch.nolix.system.client.consoleclient;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.gui.base.WidgetIdCatalogue;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Console;
import ch.nolix.system.client.baseguiclient.BaseBackGUIClientSession;
import ch.nolix.template.consoleclientlook.GreyBlueConsoleClientLookCreator;

//class
/**
 * @author Silvan Wyss
 * @date 2017-04-02
 * @lines 200
 */
public abstract class BackConsoleClientSession extends BaseBackGUIClientSession<BackConsoleClient> {
	
	//attributes
	private final Console infoPanel = new Console().setId(WidgetIdCatalogue.INFO_PANEL).setUneditable();
	private final Console console = new Console().setId(WidgetIdCatalogue.CONSOLE).setFocused();
	
	//method
	/**
	 * Clears the console of the current {@link BackConsoleClientSession}.
	 * 
	 * @return the current {@link BackConsoleClientSession}.
	 */
	public final BackConsoleClientSession clearConsole() {
		
		console.clear();
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * Clears the info panel of the current {@link BackConsoleClientSession}.
	 * 
	 * @return the current {@link BackConsoleClientSession}.
	 */
	public final BackConsoleClientSession clearInfoPanel() {
		
		infoPanel.clear();
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * @return the next character from the console of the current {@link BackConsoleClientSession}.
	 */
	public final char readCharacterFromConsole() {
		return console.readCharacter();
	}
	
	//method
	/**
	 * Reads the next enter from the console of the current {@link BackConsoleClientSession}.
	 */
	public void readEnterFromConsole() {
		console.readEnter();
	}
	
	//method
	/**
	 * @return the next line from the console of the current {@link BackConsoleClientSession}.
	 */
	public final String readLineFromConsole() {
		return console.readLine();
	}
	
	//method
	/**
	 * @return the next line, that is not empty, from the console of the current {@link BackConsoleClientSession}.
	 */
	public final String readNonEmptyLineFromConsole() {
		return console.readNonEmptyLine();
	}
	
	//method
	/**
	 * Setst the look of the GUI of the current {@link BackConsoleClientSession}.
	 * 
	 * @param look
	 * @return the current {@link BackConsoleClientSession}.
	 * @throws ArgumentIsNullException if the given look is null.
	 */
	public BackConsoleClientSession setLook(final Configuration look) {
		
		getRefGUI().setConfiguration(look);
		
		return this;
	}
	
	//method
	/**
	 * Writes an empty line to the console of the current {@link BackConsoleClientSession}.
	 * 
	 * @return the current {@link BackConsoleClientSession}.
	 */
	public final BackConsoleClientSession writeEmptyLineToConsole() {
		
		console.writeEmptyLine();
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * Writes the given line to the console of the current {@link BackConsoleClientSession}.
	 * 
	 * @param line
	 * @return the current {@link BackConsoleClientSession}.
	 */
	public final BackConsoleClientSession writeLineToConsole(final String line) {
		
		console.writeLine(line);
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * Writes the given lines to the console of the current {@link BackConsoleClientSession}.
	 * 
	 * @param lines
	 * @return the current {@link BackConsoleClientSession}.
	 */
	public final BackConsoleClientSession writeLineToConsole(final String... lines) {
		
		console.writeLine(lines);
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * Writes the given line to the info panel of the current {@link BackConsoleClientSession}.
	 * 
	 * @param line
	 * @return the current {@link BackConsoleClientSession}.
	 */
	public final BackConsoleClientSession writeLineToInfoPanel(final String line) {
		
		infoPanel.writeLine(line);
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * Writes the given lines to the info panel of the current {@link BackConsoleClientSession}.
	 * 
	 * @param lines
	 * @return the current {@link BackConsoleClientSession}.
	 */
	public final BackConsoleClientSession writeLineToInfoPanel(final String... lines) {
		
		infoPanel.writeLine(lines);
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void initializeBaseBackGUIClientSession() {
		
		getRefGUI()
		.addLayerOnTop(
			new VerticalStack()
			.add(infoPanel, console)
			.setRole(ContainerRole.MAINT_CONTAINER)
		)
		.setConfiguration(new GreyBlueConsoleClientLookCreator().createConsoleClientLook());
		
		initializeBackConsoleClientSession();
	}
	
	//method declaration
	/**
	 * Initializes the current {@link BackConsoleClientSession}.
	 */
	protected abstract void initializeBackConsoleClientSession();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Class<BackConsoleClient> internalGetRefClientClass() {
		return BackConsoleClient.class;
	}
}
