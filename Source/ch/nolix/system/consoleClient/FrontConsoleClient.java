//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.element.GUI.Console;
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.validator2.Validator;
import ch.nolix.system.GUIClientoid.GUIClientoid;
import ch.nolix.system.client.Application;
import ch.nolix.templates.frontConsoleClientLooks.GreyBlueFrontConsoleClientLook;

//class
/**
* A console front client is a client that provides a console.
* 
* @author Silvan Wyss
* @month 2017-03
* @lines 260
*/
public final class FrontConsoleClient extends GUIClientoid<FrontConsoleClient> {

	//attributes
		private final GUI<?> GUI;
		
		private final Console infoPanel =
		new Console()
		.setName(WidgetNameCatalogue.INFO_PANEL_NAME)
		.setUneditable();
		
		private final Console console =
		new Console()
		.setName(WidgetNameCatalogue.CONSOLE_NAME)
		.setFocused();
	
	public FrontConsoleClient(final int port) {
		
		this(new Frame());
		
		internal_connectTo(port);
	}
		
	//constructor
	/**
	 * Creates a new front console client that connects to the given application.
	 * 
	 * @param application
	 * @throws NullArgumentException if the given target application is null.
	 */
	public FrontConsoleClient(final Application<BackConsoleClient> application) {
		
		//Calls other constructor.
		this(new Frame());
		
		internal_connectTo(application);
	}

	//constructor
	/**
	 * Creates a new front console client that
	 * connects to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 * @throws NullArgumentException if the given GUI is null.
	 */
	public FrontConsoleClient(
		final String ip,
		final int port,
		final String targetApplication
	) {
		//Calls other constructor.
		this(ip, port, targetApplication, new Frame());
	}
	
	//constructor
	/**
	 * Creates a new front console client that:
	 * -Connects to the given target application on the given port on the machine with the given ip.
	 * -Has the given GUI.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @param GUI
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 * @throws NullArgumentException if the given GUI is null.
	 */
	public FrontConsoleClient(
		final String ip,
		final int port,
		final String targetApplication,
		final GUI<?> GUI
	) {
		//Calls other constructor.
		this(GUI);
		
		//Connects this console front client.
		internal_connectTo(ip, port, targetApplication);
	}
	
	/**
	 * /Creates new console front client with the given GUI.
	 * 
	 * @param GUI
	 * @throws NullArgumentException if the given GUI is null.
	 */
	private FrontConsoleClient(final GUI<?> GUI) {
		
		//Checks if the given GUI is not null.
		Validator.suppose(GUI).thatIsOfType(GUI.class).isNotNull();
		
		//Sets the GUI of this console front client.
		this.GUI = GUI;
		
		//Builds up the GUI of this console front client.					
		GUI
		.setTitle("Console")
		.setRootWidget(
			new VerticalStack(
				infoPanel,
				console
			)
			.setRole(ContainerRole.MainContainer)
		)
		.setConfiguration(new GreyBlueFrontConsoleClientLook());
	}
	
	public void quit() {
		((Frame)GUI).close();
	}
	
	//method
	/**
	 * Resets this front console client client.
	 * 
	 * @return this front console client.
	 */
	public FrontConsoleClient reset() {
		return this;
	}
	
	//method
	/**
	 * Finishes the initialization of the session of this front console client.
	 */
	protected void internal_finishSessionInitialization() {}
	
	//method
	/**
	 * @param request
	 * 
	 * @return the data the given request requests from this console front client.
	 * @throws InvalidArgumentException if the given request is not valid.
	 */
	protected StandardSpecification internal_getData(final Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case Protocol.READ_LINE_FROM_CONSOLE_REQUEST:
				return StandardSpecification.createSpecificationWithHeader(console.readLine());
			case Protocol.READ_NON_EMPTY_LINE_FROM_CONSOLE_REQUEST:
				return StandardSpecification.createSpecificationWithHeader(console.readNonEmptyLine());
			case Protocol.READ_SECRET_LINE_FROM_CONSOLE_REQUEST:
				return StandardSpecification.createSpecificationWithHeader(console.readSecretLine());
			case Protocol.READ_CHARACTER_FROM_CONSOLE_REQUEST:
				return StandardSpecification.createSpecificationWithHeader(String.valueOf(console.readCharacter()));
			case Protocol.LINES_OF_CONSOLE_REQUEST:
				
				final StandardSpecification data = new StandardSpecification();
				
				//Iterates the lines of the main console of this console front client.
				for (final var l : console.getLines()) {
					data.addAttribute(StandardSpecification.createReproducingString(l));
				}
				
				return data;
			default:
				
				//Calls method of the base class.
				return super.internal_getData(request);
		}
	}
	
	//method
	/**
	 * Lets this console front client run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
		
			//Handles general commands.
			case Protocol.QUIT_COMMAND:
				GUI.close();
				break;
			case Protocol.SET_DESIGN_COMMAND:				
				setDesign(new StandardConfiguration(command.getRefOneAttribute().getRefAttributes()));
				break;
			case Protocol.SET_TITLE_COMMAND:
				GUI.setTitle(command.getOneAttributeToString());
				break;			
		
			//Handles console commands.
			case Protocol.CLEAR_CONSOLE_COMMAND:
				console.clear();
				GUI.refresh();
				break;
			case Protocol.READ_ENTER_FROM_CONSOLE_COMMAND:
				console.readEnter();
				break;
			case Protocol.WRITE_EMPTY_LINE_TO_CONSOLE_COMMAND:
				console.writeEmptyLine();
				GUI.refresh();
				break;
			case Protocol.WRITE_LINE_TO_CONSOLE_COMMAND:
				console.writeLine(command.getOneAttributeToString());
				GUI.refresh();
				break;
			case Protocol.WRITE_LINES_TO_CONSOLE_COMMAND:
				console.writeLines(command.getAttributesToStrings());
				break;
			
			//Handles info panel commands.
			case Protocol.CLEAR_INFO_PANEL_COMMAND:
				infoPanel.clear();
				GUI.refresh();
				break;
			case Protocol.WRITE_EMPTY_LINE_TO_INFO_PANEL_COMMAND:
				infoPanel.writeEmptyLine();
				GUI.refresh();
				break;
			case Protocol.WRITE_LINE_TO_INFO_PANEL_COMMAND:
				infoPanel.writeLine(command.getOneAttributeToString());
				GUI.refresh();
				break;
			case Protocol.WRITE_LINES_TO_INFO_PANEL_COMMAND:
				infoPanel.writeLines(command.getAttributesToStrings());
				GUI.refresh();
				break;
				
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}
	
	//method
	/**
	 * Sets the given design to the GUI of this cosole front client.
	 * 
	 * @param design
	 */
	private void setDesign(final StandardConfiguration design) {
		GUI.setConfiguration(design);
	}
}
