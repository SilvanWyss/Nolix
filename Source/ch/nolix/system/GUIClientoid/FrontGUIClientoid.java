//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.statement.Statement;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.GUI.LayerFrame;
import ch.nolix.system.GUIClient.FrontBrowserGUIClient;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Client;

//abstract
public abstract class FrontGUIClientoid<FGC extends FrontGUIClientoid<FGC>> extends Client<FGC> {
	
	//attributes
	private final GUI<?> mGUI;
	private final FrontBrowserGUIClientoidWidget frontBrowserGUIClientoidWidget;
	
	//constructor
	public FrontGUIClientoid() {
		
		//Calls other constructor.
		this(new LayerFrame());
	}
	
	//constructor
	public FrontGUIClientoid(final GUI<?> rGUI) {
		mGUI = rGUI;
		this.frontBrowserGUIClientoidWidget = new FrontBrowserGUIClientoidWidget();
		rGUI.setController(new FrontGUIClientoidGUIController(this));
		rGUI.addLayerOnTop(frontBrowserGUIClientoidWidget);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {
		
		//Calls method of the base class.
		super.close();
		
		mGUI.close();
	}
	
	public abstract FrontGUIClientoidType getFrontEndType();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void internal_finishSessionInitialization() {}
	
	@Override
	protected DocumentNode internal_getData(final Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case Protocol.FRONT_END_TYPE_HEADER:
				return DocumentNode.createWithHeader(getFrontEndType().toString());
			default:
				
				//Calls method of the base class.
				return super.internal_getData(request);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
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
			case Protocol.PAINT_HEADER:
				setPainterCommands(
					command
					.getRefAttributes()
					.to(a -> Statement.fromString(DocumentNode.createOriginStringFromReproducingString(a.getHeader())))
				);
				break;
			default:
			
				//Calls method of the base class.
				super.internal_run(command);
		}
	}

	//package-visible method
	FileProvider getFileProvider(final IContainer<Integer> indexPathOnRootGUI) {
		return new FileProvider(this, indexPathOnRootGUI);
	}
	
	//package-visible method
	void noteLeftMouseButtonPressCommandOnCounterpart(final IContainer<Integer> indexPathOnRootGUI) {
		noteCommandOnCounterpart(indexPathOnRootGUI, Protocol.NOTE_LEFT_MOUSE_BUTTON_PRESS_HEADER);
	}
	
	//package-visible method
	void noteLeftMouseButtonReleaseCommandOnCounterpart(final IContainer<Integer> indexPathOnRootGUI) {
		noteCommandOnCounterpart(indexPathOnRootGUI, Protocol.NOTE_LEFT_MOUSE_BUTTON_RELEASE_HEADER);
	}

	//package-visible method
	void noteRightMouseButtonPressCommandOnCounterpart(final IContainer<Integer> indexPathOnRootGUI) {
		noteCommandOnCounterpart(indexPathOnRootGUI, Protocol.NOTE_RIGHT_MOUSE_BUTTON_PRESS_HEADER);
	}
	
	//package-visible method
	void noteRightMouseButtonReleaseCommandOnCounterpart(final IContainer<Integer> indexPathOnRootGUI) {
		noteCommandOnCounterpart(indexPathOnRootGUI, Protocol.NOTE_RIGHT_MOUSE_BUTTON_RELEASE_HEADER);
	}
	
	//package-visible method
	String readFileFromCounterpart(final IContainer<Integer> indexPathOnRootGUI) {
		return
		DocumentNodeoid.createOriginStringFromReproducingString(
			internal_getDataFromCounterpart(
				Protocol.READ_FILE_HEADER
				+ "("
				+ indexPathOnRootGUI.toString(CharacterCatalogue.DOT)
				+ ")"
			)
			.toString()
		);
	}
	
	//package-visible method
	byte[] readFileToBytesFromCounterpart(final IContainer<Integer> indexPathOnRootGUI) {
		return readFileFromCounterpart(indexPathOnRootGUI).getBytes();
	}
	
	//method
	/**
	 * Lets the counterpart of the current {@link FrontGUIClient}
	 * note the run of the command identified by the given command header on the given widget.
	 * 
	 * @param widget
	 * @param commandHeader
	 */
	private void noteCommandOnCounterpart(final IContainer<Integer> indexPathOnRootGUI, final String commandHeader) {
		internal_runOnCounterpart(			
				
			Protocol.GUI_HEADER
			+ '.'
			+ Protocol.ADD_OR_CHANGE_WIDGETS_ATTRIBUTES_HEADER
			+ '('
			+ 
				mGUI.getInteractionAttributesOfWidgetsRecursively()
				.to(ias -> '(' + ias.toString() + ')')
			+ ')',
			
			Protocol.GUI_HEADER
			+ '.'
			+ Protocol.WIDGET_BY_INDEX_PATH_HEADER
			+ '('
			+ indexPathOnRootGUI.toString(CharacterCatalogue.DOT)
			+ ')'
			+ '.'
			+ commandHeader
		);
	}
	
	//method
	/**
	 * Resets the GUI of the counterpart of the current {@link FrontGUIClient}
	 * with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetCounterpartGUI(final Iterable<? extends DocumentNodeoid> attributes) {
		internal_runOnCounterpart(
			Protocol.GUI_HEADER
			+ '.'
			+ Protocol.RESET_HEADER
			+ '('
			+ attributes
			+ ')'
		);
	}
	
	//method
	/**
	 * Resets the GUI of the current {@link FrontGUIClient}
	 * with the given attributes.
	 * 
	 * @param attributes
	 * @throws InvalidArgumentException if one of the given attributes is not valid.
	 */
	private void resetGUI(final Iterable<? extends DocumentNodeoid> attributes) {
		mGUI.reset(attributes);
		mGUI.updateFromConfiguration();
	}
	
	//method
	/**
	 * Lets the current {@link FrontGUIClient} run the given counterpart command.
	 * 
	 * @param counterpartCommand
	 * @throws InvalidArgumentException if the given counterpart command is not valid.
	 */
	private void runCounterpartCommand(final Statement counterpartCommand) {
		
		//Enumerates the header of the given counterpart command.
		switch (counterpartCommand.getHeader()) {
			case Protocol.RESET_HEADER:
				resetCounterpartGUI(counterpartCommand.getRefAttributes());
				break;
			default:
				throw
				new InvalidArgumentException(
					"counterpart command",
					counterpartCommand,
					"is not valid"
				);
		}
	}

	//method
	/**
	 * Lets the current {@link FrontGUIClient} run the given GUI command.
	 * 
	 * @param GUICommand
	 * @throws InvalidArgumentException if the given GUI command is not valid.
	 */
	private void runGUICommand(final Statement GUICommand) {
		
		//Enumerates the header of the given GUI command.
		switch (GUICommand.getHeader()) {
			case Protocol.RESET_HEADER:
				resetGUI(GUICommand.getRefAttributes());
				break;
			default:
				throw new InvalidArgumentException("GUI command", GUICommand, "is not valid");
		}
	}
	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given painter command.
	 * 
	 * @param painterCommand
	 * @throws InvalidArgumentException if the given painter command is not valid.
	 */

	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given paint filled rectangle command.
	 * 
	 * @param painter
	 * @param paintFilledRectangleCommand
	 * @throws InvalidArgumentException if the given paint filled rectangle command is not valid.
	 */

	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given paint image command.
	 * 
	 * @param painter
	 * @param paintImageCommand
	 * @throws InvalidArgumentException if the given paint image command is not valid.
	 */

	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given set color command.
	 * 
	 * @param painter
	 * @param setColorCommand
	 * @throws InvalidArgumentException if the given set color command is not valid.
	 */

	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given set color gradient command.
	 * 
	 * @param painter
	 * @param setColorGradientCommand
	 * @throws InvalidArgumentException if the given set color gradient command is not valid.
	 */

	
	//method
	private void setPainterCommands(final IContainer<Statement> painterCommands) {
		frontBrowserGUIClientoidWidget.setPainterCommands(painterCommands);
	}
}
