//package declaration
package ch.nolix.system.consoleClient;

import ch.nolix.element.GUI.GUI;
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.LayerGUI;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.containerWidgets.ContainerRole;
import ch.nolix.element.widgets.Console;
import ch.nolix.element.widgets.VerticalStack;
import ch.nolix.system.baseGUIClient.BaseBackGUIClient;
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
public final class FrontConsoleClient extends BaseBackGUIClient<FrontConsoleClient> {

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
	 * @throws ArgumentIsNullException if the given target application is null.
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
	 * @throws ArgumentIsNullException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 * @throws ArgumentIsNullException if the given GUI is null.
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
	 * @throws ArgumentIsNullException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 * @throws ArgumentIsNullException if the given GUI is null.
	 */
	public FrontConsoleClient(
		final String ip,
		final int port,
		final String targetApplication,
		final LayerGUI<?> GUI
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
	 * @throws ArgumentIsNullException if the given GUI is null.
	 */
	private FrontConsoleClient(final LayerGUI<?> pGUI) {
		
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
		mGUI.close();
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
	protected Node internal_getData(final ChainedNode request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case Protocol.READ_LINE_FROM_CONSOLE_REQUEST:
				return new Node(console.readLine());
			case Protocol.READ_NON_EMPTY_LINE_FROM_CONSOLE_REQUEST:
				return new Node(console.readNonEmptyLine());
			case Protocol.READ_SECRET_LINE_FROM_CONSOLE_REQUEST:
				return new Node(console.readSecretLine());
			case Protocol.READ_CHARACTER_FROM_CONSOLE_REQUEST:
				return new Node(String.valueOf(console.readCharacter()));
			case Protocol.LINES_OF_CONSOLE_REQUEST:
				
				final Node data = new Node();
				
				//Iterates the lines of the main console of this console front client.
				for (final var l : console.getLines()) {
					data.addAttribute(BaseNode.createReproducingString(l));
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
	protected void internal_run(final ChainedNode command) {
		
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
				setDesign(new StandardConfiguration(command.getOneAttribute().getAttributesAsNodes()));
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
				console.writeLines(command.getAttributesAsStrings());
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
				infoPanel.writeLines(command.getAttributesAsStrings());
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
