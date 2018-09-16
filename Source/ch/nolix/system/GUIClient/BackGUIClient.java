//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.duplexController.DuplexController;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.element.GUI.Downloader;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.Widget;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ArgumentName;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.system.client.Client;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 320
 */
public final class BackGUIClient extends Client<BackGUIClient> {
	
	//constants
	static final String GUI_HEADER = "GUI";
	static final String WIDGET_BY_INDEX_HEADER = "WidgetByIndex";
	static final String PAINTER_BY_INDEX_HEADER = "PainterByIndex";
	static final String COUNTERPART_HEADER = "Counterpart";
		
	//constants
	static final String ADD_OR_CHANGE_ATTRIBUTES_HEADER = "AddOrChangeAttributes";
	static final String ADD_OR_CHANGE_WIDGETS_ATTRIBUTES_HEADER = "AddOrChangeWidgetAttributes";
	static final String RESET_HEADER = "Reset";
	static final String NOTE_LEFT_MOUSE_BUTTON_PRESS_HEADER = "NoteLeftMouseButtonPress";
	static final String NOTE_LEFT_MOUSE_BUTTON_RELEASE_HEADER = "NoteLeftMouseButtonRelease";
	static final String NOTE_RIGHT_MOUSE_BUTTON_PRESS_HEADER = "NoteRightMouseButtonPress";
	static final String NOTE_RIGHT_MOUSE_BUTTON_RELEASE_HEADER = "NoteRightMouseButtonRelease";
	
	//constants
	static final String CREATE_PAINTER_HEADER = "CreatePainter";
	static final String PAINT_FILLED_RECTANGLE_HEADER = "PaintFilledRectangle";
	static final String PAINT_FILLED_POLYGON_HEADER = "PaintFilledPolygon";
	static final String PAINT_IMAGE_HEADER = "PaintImage";
	static final String PAINT_TEXT_HEADER = "PaintText";
	static final String SET_COLOR_HEADER = "SetColor";
	static final String SET_COLOR_GRADIENT_HEADER = "SetColorGradient";
	static final String TRANSLATE_HEADER = "Translate";
	
	//constant
	static final String READ_FILE_HEADER = "ReadFile";
	
	//constructor
	/**
	 * Creates a new {@link BackGUIClient} with the given duplex controller.
	 * This constructor is public that it can be found by reflection.
	 * 
	 * @param duplexController
	 * @throws NullArgumentException if the given duplex controller is not an instance.
	 */
	public BackGUIClient(final DuplexController duplexController) {
			
		//Calls constructor of the base class.
		internal_setDuplexController(duplexController);
	}
	
	//method
	/**
	 * @return the type of the front-end of the current {@link BackGUIClient}.
	 */
	public FrontEndType getFrontEndType() {
		//TODO
		return FrontEndType.JAVA_APPLICATION;
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClient} run the given command locally.
	 * 
	 * @param command
	 * @throws UnexistingAttributeException if the current back GUI client has no current session.
	 */
	public void runLocally(final String command) {
		internal_invokeSessionUserRunMethod(new StandardSpecification(command));
		updateGUIOnCounterpart();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected StandardSpecification internal_getData(final Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case READ_FILE_HEADER:
				
				final Downloader downloader =
				getRefGUI().getRefWidgetByIndexRecursively(request.getOneAttributeAsInt());
				
				return
				StandardSpecification.createSpecificationWithHeader(downloader.readFile());
			default:
				
				//Calls method of the base class.
				return super.internal_getData(request);
		}
	}
	
	//method
	/**
	 * Finishes the initialization of the session of the current {@link BackGUIClient}.
	 * 
	 * @throws UnexistingAttributeException if the current {@link BackGUIClient} has no current session.
	 */
	protected void internal_finishSessionInitialization() {
		updateGUIOnCounterpart();
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClient} run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case GUI_HEADER:
				runGUICommand(command.getRefNextStatement());
				break;
			case COUNTERPART_HEADER:
				runCounterpartCommand(command.getRefNextStatement());
				break;
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}
	

	
	//method
	/**
	 * Adds or changes the given attributes to the {@link GUI} of the current {@link BackGUIClient}.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	private <S extends Specification> void addOrChangeGUIAttributes(final IContainer<S> attributes) {
		getRefGUI().addOrChangeAttributes(attributes);
	}
	
	//method
	/**
	 * Adds or changes the given interaction attributes to the widgets of this {@link BackGUIClient}.
	 * 
	 * @param interactionAttributesOfWidgetsOfGUI
	 * @throws InvalidArgumentException if the given interaction attributes are not valid.
	 */
	private <S extends Specification> void addOrChangeGUIWidgetsAttributes(
		final IContainer<IContainer<S>> interactionAttributesOfWidgetsOfGUI	
	) {
		getRefGUI().addOrChangeInteractionAttributesOfWidgetsRecursively(interactionAttributesOfWidgetsOfGUI);
	}
	
	//method
	/**
	 * @return the GUI of the current session of this back GUI client.
	 */
	private GUI<?> getRefGUI() {
		
		final var session = (BackGUISession)internal_getRefCurrentSession();
		
		return session.getRefGUI();
	}
	
	//method
	/**
	 * Resets the {@link GUI} of the current {@link BackGUIClient} with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	private <S extends Specification> void resetGUI(final IContainer<S> attributes) {
		getRefGUI().reset(attributes);
	}
	
	//method
	/**
	 * Resets the dialog of the other side of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetGUIOnCounterpart(final Iterable<StandardSpecification> attributes) {
		internal_runOnCounterpart(
			GUI_HEADER
			+ "."
			+ RESET_HEADER
			+ "("
			+ attributes
			+ ")"
		);	
	}

	//method
	/**
	 * Lets the current {@link BackGUIClient} run the given counterpart command.
	 * 
	 * @param counterpartCommand
	 * @throws InvalidArgumentException if the given counterpart command is not valid.
	 */
	private void runCounterpartCommand(final Statement counterpartCommand) {
		
		//Enumerates the header of the given counterpart command.
		switch (counterpartCommand.getHeader()) {
			case RESET_HEADER:
				resetGUIOnCounterpart(counterpartCommand.getRefAttributes());
				break;
			default:
				throw
				new InvalidArgumentException(
					new ArgumentName("counterpart command"),
					new Argument(counterpartCommand)
				);
		}
	}
	
	//method
	/**
	 * Lets the current {@link BackGUIClient} run the given GUI command.
	 * 
	 * @param GUICommand
	 * @throws InvalidArgumentException if the given GUI command is not valid.
	 */
	private void runGUICommand(final Statement GUICommand) {
		
		//Enumerates the header of the given GUI command.
		switch (GUICommand.getHeader()) {
			case ADD_OR_CHANGE_ATTRIBUTES_HEADER:
				addOrChangeGUIAttributes(GUICommand.getRefAttributes());
				break;
			case RESET_HEADER:
				resetGUI(GUICommand.getRefAttributes());
				break;
			case ADD_OR_CHANGE_WIDGETS_ATTRIBUTES_HEADER:
				addOrChangeGUIWidgetsAttributes(						
					GUICommand.getRefAttributes().to(a -> a.getRefAttributes())
				);
				break;
			case WIDGET_BY_INDEX_HEADER:
				runWidgetCommand(
					getRefGUI().getRefWidgetByIndexRecursively(GUICommand.getOneAttributeAsInt()),
					GUICommand.getRefNextStatement()
				);
				break;
			default:
				throw
				new InvalidArgumentException(
					new ArgumentName("GUI command"),
					new Argument(GUICommand)
				);
		}
	}
	




	//method
	//method
	/**
	 * Lets the current {@link BackGUIClient} run the given widget command.
	 * 
	 * @param widgetCommand
	 * @throws InvalidArgumentException if the given widget command is not valid.
	 */
	private void runWidgetCommand(final Widget<?, ?> widget, final Statement widgetCommand) {
		
		//Enumerates the header of the given widget command.
		switch (widgetCommand.getHeader()) {
			case NOTE_LEFT_MOUSE_BUTTON_PRESS_HEADER:
				widget.runLeftMouseButtonPressCommand();
				break;
			case NOTE_LEFT_MOUSE_BUTTON_RELEASE_HEADER:
				widget.runLeftMouseButtonReleaseCommand();
				break;
			case NOTE_RIGHT_MOUSE_BUTTON_PRESS_HEADER:
				widget.runRightMouseButtonPressCommand();
				break;
			case NOTE_RIGHT_MOUSE_BUTTON_RELEASE_HEADER:
				widget.runRightMouseButtonReleaseCommand();
				break;
			default:
				new InvalidArgumentException(
					new ArgumentName("widget command"),
					new Argument(widgetCommand)
				);
		}
	}
	
	//method
	/**
	 * Resets the GUI on the counterpart of the current {@link BackGUIClient}. 
	 */
	private void updateGUIOnCounterpart() {
		
		//Enumerates the front end type of the current back GUI client.
		switch (getFrontEndType()) {
			case JAVA_APPLICATION:
				resetGUIOnCounterpart(getRefGUI().getAttributes());
				break;
			case BROWSER_APPLICATION:
				getRefGUI().refresh();
				break;
		}
	}
}
