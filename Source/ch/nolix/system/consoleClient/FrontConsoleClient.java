//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.statement.Statement;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.widget.Console;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.system.GUIClientoid.BackGUIClientoid;
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
public final class FrontConsoleClient extends BackGUIClientoid<FrontConsoleClient> {

	//attributes
		private final GUI<?> mGUI;
		
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
	 * @param pGUI
	 * @throws NullArgumentException if the given GUI is null.
	 */
	private FrontConsoleClient(final GUI<?> pGUI) {
		
		//Checks if the given GUI is not null.
		Validator.suppose(pGUI).isOfType(GUI.class);
		
		//Sets the GUI of this console front client.
		mGUI = pGUI;
		
		//Builds up the GUI of this console front client.					
		pGUI
		.setTitle("Console")
		.addLayerOnTop(
			new VerticalStack(
				infoPanel,
				console
			)
			.setRole(ContainerRole.MainContainer)
		)
		.setConfiguration(new GreyBlueFrontConsoleClientLook());
	}
	
	public void quit() {
		((Frame)mGUI).close();
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
	 * @param request
	 * 
	 * @return the data the given request requests from this console front client.
	 * @throws InvalidArgumentException if the given request is not valid.
	 */
	@Override
	protected DocumentNode internal_getData(final Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case Protocol.READ_LINE_FROM_CONSOLE_REQUEST:
				return DocumentNode.createWithHeader(console.readLine());
			case Protocol.READ_NON_EMPTY_LINE_FROM_CONSOLE_REQUEST:
				return DocumentNode.createWithHeader(console.readNonEmptyLine());
			case Protocol.READ_SECRET_LINE_FROM_CONSOLE_REQUEST:
				return DocumentNode.createWithHeader(console.readSecretLine());
			case Protocol.READ_CHARACTER_FROM_CONSOLE_REQUEST:
				return DocumentNode.createWithHeader(String.valueOf(console.readCharacter()));
			case Protocol.LINES_OF_CONSOLE_REQUEST:
				
				final DocumentNode data = new DocumentNode();
				
				//Iterates the lines of the main console of this console front client.
				for (final var l : console.getLines()) {
					data.addAttribute(DocumentNodeoid.createReproducingString(l));
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
	@Override
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
		
			//Handles general commands.
			case Protocol.QUIT_COMMAND:
				
				try {
					mGUI.close();
				}
				catch (final Exception exception) {
					throw new RuntimeException(exception);
				}
				
				break;
			case Protocol.SET_DESIGN_COMMAND:				
				setDesign(new StandardConfiguration(command.getRefOneAttribute().getRefAttributes()));
				break;
			case Protocol.SET_TITLE_COMMAND:
				mGUI.setTitle(command.getOneAttributeAsString());
				break;
		
			//Handles console commands.
			case Protocol.CLEAR_CONSOLE_COMMAND:
				console.clear();
				mGUI.refresh();
				break;
			case Protocol.READ_ENTER_FROM_CONSOLE_COMMAND:
				console.readEnter();
				break;
			case Protocol.WRITE_EMPTY_LINE_TO_CONSOLE_COMMAND:
				console.writeEmptyLine();
				mGUI.refresh();
				break;
			case Protocol.WRITE_LINE_TO_CONSOLE_COMMAND:
				console.writeLine(command.getOneAttributeAsString());
				mGUI.refresh();
				break;
			case Protocol.WRITE_LINES_TO_CONSOLE_COMMAND:
				console.writeLines(command.getAttributesToStrings());
				break;
			
			//Handles info panel commands.
			case Protocol.CLEAR_INFO_PANEL_COMMAND:
				infoPanel.clear();
				mGUI.refresh();
				break;
			case Protocol.WRITE_EMPTY_LINE_TO_INFO_PANEL_COMMAND:
				infoPanel.writeEmptyLine();
				mGUI.refresh();
				break;
			case Protocol.WRITE_LINE_TO_INFO_PANEL_COMMAND:
				infoPanel.writeLine(command.getOneAttributeAsString());
				mGUI.refresh();
				break;
			case Protocol.WRITE_LINES_TO_INFO_PANEL_COMMAND:
				infoPanel.writeLines(command.getAttributesToStrings());
				mGUI.refresh();
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
		mGUI.setConfiguration(design);
	}
}
