//package declaration
package ch.nolix.system.GUIClientoid;

import ch.nolix.core.commonTypeHelpers.StringHelper;
//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.fileSystem.FileSystemAccessor;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.statement.Statement;
import ch.nolix.core.util.PopupWindowProvider;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.widget.Downloader;
import ch.nolix.element.widget.Widget;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.client.Client;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 420
 */
public abstract class BackGUIClientoid<BGUIC extends BackGUIClientoid<BGUIC>> extends Client<BGUIC> {

	//attribute
	private FrontGUIClientoidType frontEndType;
	
	//method
	/**
	 * Lets the counterpart of this base GUI client
	 * create a file with the given relative file path and content.
	 * 
	 * @param relativeFilePath
	 * @param content
	 * @return this base GUI client.
	 */
	public final BGUIC createFile(
		final String relativeFilePath,
		final String content
	) {
		
		internal_runOnCounterpart(
			Protocol.CREATE_FILE_COMMAND
			+ "("
			+ DocumentNodeoid.createReproducingString(relativeFilePath)
			+ ","
			+ DocumentNodeoid.createReproducingString(content)
			+ ")"
		);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @return the type of the front-end of the current {@link BackGUIClientoid}.
	 */
	public FrontGUIClientoidType getFrontEndType() {
		
		fetchFrontEndTypeFromCounterPartIfDoesNotKnowFrontEndType();
		
		return frontEndType;
	}
	
	//method
	/**
	 * Lets the counterpart of this base GUI client open a file explorer.
	 * 
	 * @return this base GUI client.
	 */
	public BGUIC openFileExplorer() {
		
		internal_runOnCounterpart(Protocol.OPEN_FILE_EXPLORER_COMMAND);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClientoid} run the given command locally.
	 * 
	 * @param command
	 * @throws ArgumentMissesAttributeException if the current back GUI client does not have a current session.
	 */
	public void runLocally(final String command) {
		internal_invokeSessionUserRunMethod(DocumentNode.createFromString(command));
		updateGUIOnCounterpart();
	}
	
	//method
	/**
	 * Lets this base GUI client show the given error message.
	 * 
	 * @param errorMessage
	 * @return this base GUI client.
	 * @throws NullArgumentException if the given error message is null.
	 */
	public final BGUIC showErrorMessage(final String errorMessage) {
		
		internal_runOnCounterpart(
			Protocol.SHOW_ERROR_MESSAGE_COMMAND
			+ "("
			+ DocumentNodeoid.createReproducingString(errorMessage)
			+ ")"
		);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Lets this base GUI client create a file with the given relative file path and content.
	 * 
	 * @param relativeFilePath
	 * @param content
	 */
	protected final void internal_createFile(
		final String relativeFilePath,
		final DocumentNodeoid content
	) {
		FileSystemAccessor.createFile(relativeFilePath, content.toString());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DocumentNode internal_getData(final Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case Protocol.READ_FILE_HEADER:
				
				final Downloader downloader =
				getRefGUI().getRefWidgetByIndexPath(
					new ReadContainer<String>(
						request.getOneAttributeAsString().split("/.")).to(s -> StringHelper.toInt(s)
					)
				);
				
				return
				DocumentNode.createWithHeader(DocumentNodeoid.createReproducingString(downloader.readFile()));
			default:
				
				//Calls method of the base class.
				return super.internal_getData(request);
		}
	}
	
	//method
	/**
	 * Finishes the initialization of the session of the current {@link BackGUIClientoid}.
	 * 
	 * @throws ArgumentMissesAttributeException if the current {@link BackGUIClientoid} does not have a current session.
	 */
	@Override
	protected final void internal_finishSessionInitialization() {
		updateGUIOnCounterpart();
	}
	
	//method
	/**
	 * Lets this base GUI client run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	@Override
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case Protocol.COUNTERPART_HEADER:
				runCounterpartCommand(command.getRefNextStatement());
				break;
			case Protocol.GUI_HEADER:
				runGUICommand(command.getRefNextStatement());
				break;
			case Protocol.SHOW_ERROR_MESSAGE_COMMAND:
				internal_showErrorMessage(command.getOneAttributeAsString());
				break;
			case Protocol.CREATE_FILE_COMMAND:
				
				final var parameters
				= command.getRefAttributes();
				
				internal_createFile(
					parameters.getRefAt(1).toString(),
					parameters.getRefAt(2)
				);
				
				break;
			case Protocol.OPEN_FILE_EXPLORER_COMMAND:
				internal_openFileExplorer();
				break;
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}
	
	//method
	/**
	 * Lets this base GUI client open a file explorer.
	 */
	protected final void internal_openFileExplorer() {
		FileSystemAccessor.openFolderOfRunningJarFileInExplorer();
	}
	
	//method
	/**
	 * Lets this base GUI client show the given error message.
	 * 
	 * @param errorMessage
	 */
	protected final void internal_showErrorMessage(final String errorMessage) {
		PopupWindowProvider.showErrorWindow(errorMessage);
	}
	
	//package-visible method
	final void paintOnCounterpart(final IContainer<Statement> painterCommands) {
		if (painterCommands.containsAny()) {	
			internal_runOnCounterpart(
				Protocol.PAINT_HEADER
				+ '('
				+ painterCommands.to(pc -> DocumentNode.createReproducingString(pc.toString()))
				+ ')'
			);
		}
	}
	
	//method
	/**
	 * Adds or changes the given attributes to the {@link GUI} of the current {@link BackGUIClientoid}.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	private <S extends DocumentNodeoid> void addOrChangeGUIAttributes(final IContainer<S> attributes) {
		getRefGUI().addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * Adds or changes the given interaction attributes to the widgets of this {@link BackGUIClientoid}.
	 * 
	 * @param interactionAttributesOfWidgetsOfGUI
	 * @throws InvalidArgumentException if the given interaction attributes are not valid.
	 */
	private <S extends DocumentNodeoid> void addOrChangeGUIWidgetsAttributes(
		final IContainer<IContainer<S>> interactionAttributesOfWidgetsOfGUI	
	) {
		getRefGUI().addOrChangeInteractionAttributesOfWidgetsRecursively(interactionAttributesOfWidgetsOfGUI);
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClientoid} fetch its front end type from its counterpart
	 * if it does not know its front end type.
	 */
	private void fetchFrontEndTypeFromCounterPartIfDoesNotKnowFrontEndType() {
		
		//Handles the case that the current back GUI cliendoid does not know its front end type.
		if (!knowsFrontEndType()) {
			frontEndType
			= FrontGUIClientoidType.valueOf(
				internal_getDataFromCounterpart(Protocol.FRONT_END_TYPE_HEADER).getHeader()
			);
		}
	}
	
	//method
	/**
	 * @return the GUI of the current session of this back GUI client.
	 */
	private GUI<?> getRefGUI() {
		
		final var session = (BackGUIClientSession)internal_getRefCurrentSession();
		
		return session.getRefGUI();
	}

	//method
	/**
	 * @return true if the current {@link BackGUIClientoidoid} knows its front end type.
	 */
	private boolean knowsFrontEndType() {
		return (frontEndType != null);
	}
	
	//method
	/**
	 * Resets the {@link GUI} of the current {@link BackGUIClientoid} with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	private <S extends DocumentNodeoid> void resetGUI(final IContainer<S> attributes) {
		getRefGUI().reset(attributes);
	}
	
	//method
	/**
	 * Resets the dialog of the other side of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetGUIOnCounterpart(final Iterable<DocumentNode> attributes) {
		internal_runOnCounterpart(
			Protocol.GUI_HEADER
			+ "."
			+ Protocol.RESET_HEADER
			+ "("
			+ attributes
			+ ")"
		);
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClientoid} run the given counterpart command.
	 * 
	 * @param counterpartCommand
	 * @throws InvalidArgumentException if the given counterpart command is not valid.
	 */
	private void runCounterpartCommand(final Statement counterpartCommand) {
		
		//Enumerates the header of the given counterpart command.
		switch (counterpartCommand.getHeader()) {
			case Protocol.RESET_HEADER:
				resetGUIOnCounterpart(counterpartCommand.getRefAttributes());
				break;
			default:
				throw new InvalidArgumentException("counterpart command", counterpartCommand, "is not valid");
		}
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClientoid} run the given GUI command.
	 * 
	 * @param GUICommand
	 * @throws InvalidArgumentException if the given GUI command is not valid.
	 */
	private void runGUICommand(final Statement GUICommand) {
		
		//Enumerates the header of the given GUI command.
		switch (GUICommand.getHeader()) {
			case Protocol.ADD_OR_CHANGE_ATTRIBUTES_HEADER:
				addOrChangeGUIAttributes(GUICommand.getRefAttributes());
				break;
			case Protocol.RESET_HEADER:
				resetGUI(GUICommand.getRefAttributes());
				break;
			case Protocol.ADD_OR_CHANGE_WIDGETS_ATTRIBUTES_HEADER:
				addOrChangeGUIWidgetsAttributes(						
					GUICommand.getRefAttributes().to(a -> a.getRefAttributes())
				);
				break;
			case Protocol.WIDGET_BY_INDEX_PATH_HEADER:				
				runWidgetCommand(
					getRefGUI().getRefWidgetByIndexPath(
						new ReadContainer<String>(GUICommand.getOneAttributeAsString().split("/."))
						.to(s -> StringHelper.toInt(s))
					),
					GUICommand.getRefNextStatement()
				);
				break;
			default:
				throw new InvalidArgumentException("GUI command", GUICommand, "is not valid");
		}
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClientoid} run the given widget command.
	 * 
	 * @param widgetCommand
	 * @throws InvalidArgumentException if the given widget command is not valid.
	 */
	private void runWidgetCommand(final Widget<?, ?> widget, final Statement widgetCommand) {
		
		//Enumerates the header of the given widget command.
		switch (widgetCommand.getHeader()) {
			case Protocol.NOTE_LEFT_MOUSE_BUTTON_PRESS_HEADER:
				widget.runLeftMouseButtonPressCommand();
				break;
			case Protocol.NOTE_LEFT_MOUSE_BUTTON_RELEASE_HEADER:
				widget.runLeftMouseButtonReleaseCommand();
				break;
			case Protocol.NOTE_RIGHT_MOUSE_BUTTON_PRESS_HEADER:
				widget.runRightMouseButtonPressCommand();
				break;
			case Protocol.NOTE_RIGHT_MOUSE_BUTTON_RELEASE_HEADER:
				widget.runRightMouseButtonReleaseCommand();
				break;
			default:
				throw new InvalidArgumentException("widget command", widgetCommand, "is not valid");
		}
	}
	
	//method
	/**
	 * Resets the GUI on the counterpart of the current {@link BackGUIClientoid}. 
	 */
	private void updateGUIOnCounterpart() {
		
		//Enumerates the front end type of the current back GUI client.
		switch (getFrontEndType()) {
			case GUISpecificationConsumer:
				resetGUIOnCounterpart(getRefGUI().getAttributes());
				break;
			case PainterCommandsConsumer:
				final var painter = new BackBrowserGUIClientoidPainter(this);
				getRefGUI().refresh();
				getRefGUI().paint(painter);
				painter.flush();
				break;
		}
	}
}
