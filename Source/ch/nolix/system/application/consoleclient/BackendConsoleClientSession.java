//package declaration
package ch.nolix.system.application.consoleclient;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.gui.base.WidgetIdCatalogue;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Console;
import ch.nolix.system.application.baseguiclient.BaseBackendGUIClientSession;
import ch.nolix.template.consoleclientlook.GreyBlueConsoleClientLookCreator;

//class
/**
 * @author Silvan Wyss
 * @date 2017-04-02
 * @param <AC> is
 * the type of the context of
 * the parent {@link Application} of the parent {@link BackendConsoleClientSession} of a {@link BaseBackendGUIClientSession}.
 */
public abstract class BackendConsoleClientSession<AC> extends BaseBackendGUIClientSession<BackendConsoleClient<AC>, AC> {
	
	//attributes
	private final Console infoPanel = new Console().setId(WidgetIdCatalogue.INFO_PANEL).setUneditable();
	private final Console console = new Console().setId(WidgetIdCatalogue.CONSOLE).setFocused();
	
	//method
	/**
	 * Clears the console of the current {@link BackendConsoleClientSession}.
	 * 
	 * @return the current {@link BackendConsoleClientSession}.
	 */
	public final BackendConsoleClientSession<AC> clearConsole() {
		
		console.clear();
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * Clears the info panel of the current {@link BackendConsoleClientSession}.
	 * 
	 * @return the current {@link BackendConsoleClientSession}.
	 */
	public final BackendConsoleClientSession<AC> clearInfoPanel() {
		
		infoPanel.clear();
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * @return the next character from the console of the current {@link BackendConsoleClientSession}.
	 */
	public final char readCharacterFromConsole() {
		return console.readCharacter();
	}
	
	//method
	/**
	 * Reads the next enter from the console of the current {@link BackendConsoleClientSession}.
	 */
	public void readEnterFromConsole() {
		console.readEnter();
	}
	
	//method
	/**
	 * @return the next line from the console of the current {@link BackendConsoleClientSession}.
	 */
	public final String readLineFromConsole() {
		return console.readLine();
	}
	
	//method
	/**
	 * @return the next line, that is not empty, from the console of the current {@link BackendConsoleClientSession}.
	 */
	public final String readNonEmptyLineFromConsole() {
		return console.readNonEmptyLine();
	}
	
	//method
	/**
	 * Setst the look of the GUI of the current {@link BackendConsoleClientSession}.
	 * 
	 * @param look
	 * @return the current {@link BackendConsoleClientSession}.
	 * @throws ArgumentIsNullException if the given look is null.
	 */
	public BackendConsoleClientSession<AC> setLook(final Configuration look) {
		
		getRefGUI().setConfiguration(look);
		
		return this;
	}
	
	//method
	/**
	 * Writes an empty line to the console of the current {@link BackendConsoleClientSession}.
	 * 
	 * @return the current {@link BackendConsoleClientSession}.
	 */
	public final BackendConsoleClientSession<AC> writeEmptyLineToConsole() {
		
		console.writeEmptyLine();
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * Writes the given line to the console of the current {@link BackendConsoleClientSession}.
	 * 
	 * @param line
	 * @return the current {@link BackendConsoleClientSession}.
	 */
	public final BackendConsoleClientSession<AC> writeLineToConsole(final String line) {
		
		console.writeLine(line);
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * Writes the given lines to the console of the current {@link BackendConsoleClientSession}.
	 * 
	 * @param lines
	 * @return the current {@link BackendConsoleClientSession}.
	 */
	public final BackendConsoleClientSession<AC> writeLineToConsole(final String... lines) {
		
		console.writeLine(lines);
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * Writes the given line to the info panel of the current {@link BackendConsoleClientSession}.
	 * 
	 * @param line
	 * @return the current {@link BackendConsoleClientSession}.
	 */
	public final BackendConsoleClientSession<AC> writeLineToInfoPanel(final String line) {
		
		infoPanel.writeLine(line);
		updateCounterpart();
		
		return this;
	}
	
	//method
	/**
	 * Writes the given lines to the info panel of the current {@link BackendConsoleClientSession}.
	 * 
	 * @param lines
	 * @return the current {@link BackendConsoleClientSession}.
	 */
	public final BackendConsoleClientSession<AC> writeLineToInfoPanel(final String... lines) {
		
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
	 * Initializes the current {@link BackendConsoleClientSession}.
	 */
	protected abstract void initializeBackConsoleClientSession();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Class<?> internalGetRefClientClass() {
		return BackendConsoleClient.class;
	}
}
