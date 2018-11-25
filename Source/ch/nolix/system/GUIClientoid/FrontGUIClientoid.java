//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.documentNode.Statement;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.image.Image;
import ch.nolix.element.painter.IPainter;
import ch.nolix.system.GUIClient.FrontBrowserGUIClient;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Client;

//abstract
public abstract class FrontGUIClientoid<FGC extends FrontGUIClientoid<FGC>>
extends Client<FGC> {
	
	//multi-attribute
	private final List<FrontBrowserGUIClientoidPainter> painters = new List<FrontBrowserGUIClientoidPainter>();
	
	//attribute
	private final GUI<?> GUI;
	
	public FrontGUIClientoid(boolean browserGUI) {
		this(new Frame());
	}
	
	public FrontGUIClientoid(final GUI<?> GUI) {
		
		this.GUI = GUI;
		
		GUI.setController(new FrontGUIClientoidGUIController(this));
	}
	
	public abstract FrontGUIClientoidType getFrontEndType();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void internal_finishSessionInitialization() {}
	
	protected DocumentNode internal_getData(final Statement request) {
		
		//Enumerates the header of the given request.
		switch (request.getHeader()) {
			case Protocol.FRONT_END_TYPE_HEADER:
				return DocumentNode.createSpecificationWithHeader(getFrontEndType().toString());
			default:
				
				//Calls method of the base class.
				return super.internal_getData(request);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
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
				runPainterCommands(command.getRefAttributes().to(a -> new Statement(a.toString())));
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
		internal_getDataFromCounterpart(
			Protocol.READ_FILE_HEADER
			+ "("
			+ indexPathOnRootGUI.toString(CharacterCatalogue.DOT)
			+ ")"
		)
		.toString();
	}
	
	//package-visible method
	byte[] readFileToBytesFromCounterpart(final IContainer<Integer> indexPathOnRootGUI) {
		return readFileFromCounterpart(indexPathOnRootGUI).getBytes();
	}
	
	//method
	/**
	 * @return the {IPainter} with the given index from the current {@link FrontBrowserGUIClient}.
	 * @param index
	 */
	private IPainter getRefPainterByIndex(final int index) {
		return painters.getRefFirst(p -> p.hasIndex(index));
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
				GUI.getInteractionAttributesOfWidgetsRecursively()
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
			+ GUI.getAttributes()
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
		GUI.reset(attributes);
		GUI.updateFromConfiguration();
		GUI.noteMouseMove();
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
	private void runPainterCommand(final IPainter painter, final Statement painterCommand) {
		
		//Enumerates the header of the given painter command.
		switch (painterCommand.getHeader()) {
			case Protocol.PAINT_IMAGE_HEADER:
				runPaintImageCommand(painter, painterCommand);				
				break;
			case Protocol.PAINT_FILLED_RECTANGLE_HEADER:
				runPaintFilledRectangleCommand(painter, painterCommand);
				break;
			case Protocol.SET_COLOR_HEADER:
				runSetColorCommand(painter, painterCommand);
				break;
			case Protocol.SET_COLOR_GRADIENT_HEADER:
				runSetColorGradientCommand(painter, painterCommand);
				break;
			default:
				throw new InvalidArgumentException("painter command",	painterCommand, "is not valid");
		}
	}
	
	//method
	private void runPainterCommands(final IContainer<Statement> painterCommands) {
		//TODO
		for (final Statement pc : painterCommands) {
			runPainterCommand(getRefPainterByIndex(pc.getOneAttributeAsInt()), pc.getRefNextStatement());
		}
	}
	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given paint filled rectangle command.
	 * 
	 * @param painter
	 * @param paintFilledRectangleCommand
	 * @throws InvalidArgumentException if the given paint filled rectangle command is not valid.
	 */
	private void runPaintFilledRectangleCommand(
		final IPainter painter,
		final Statement paintFilledRectangleCommand
	) {
		
		//Extracts the attributes of the given paint filled rectangle command.
		final var attributes = paintFilledRectangleCommand.getRefAttributes();
		
		//Enumerates the number of attributes of the given paint filled rectangle command.
		switch (paintFilledRectangleCommand.getAttributeCount()) {
			case 2:
				
				painter.paintFilledRectangle(
					attributes.getRefAt(1).toInt(),
					attributes.getRefAt(2).toInt()
				);				
				
				break;
			case 4:
				
				painter.paintFilledRectangle(
					attributes.getRefAt(1).toInt(),
					attributes.getRefAt(2).toInt(),
					attributes.getRefAt(3).toInt(),
					attributes.getRefAt(4).toInt()
				);
				
				break;
			default:
				throw
				new InvalidArgumentException(
					"paint filled rectangle command",
					paintFilledRectangleCommand,
					"is not valid"
				);
		}
	}
	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given paint image command.
	 * 
	 * @param painter
	 * @param paintImageCommand
	 * @throws InvalidArgumentException if the given paint image command is not valid.
	 */
	private void runPaintImageCommand(
		final IPainter painter,
		final Statement paintImageCommand
	) {
		
		//Extracts the attributes of the given paint image command.
		final var attributes = paintImageCommand.getRefAttributes();
		
		//Enumerates the number of attributes of the given paint image command.
		switch (attributes.getSize()) {
			case 1:
				
				painter.paintImage(Image.createFromSpecification(
					attributes.getRefAt(1))
				);
				
				break;
			case 3:
												
				painter.paintImage(
					Image.createFromSpecification(attributes.getRefAt(1)),
					attributes.getRefAt(2).toInt(),
					attributes.getRefAt(3).toInt()
				);
				
				break;
			default:
				throw
				new InvalidArgumentException(
					"paint image command",
					paintImageCommand,
					"is not valid"
				);
		}
	}
	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given set color command.
	 * 
	 * @param painter
	 * @param setColorCommand
	 * @throws InvalidArgumentException if the given set color command is not valid.
	 */
	private void runSetColorCommand(final IPainter painter, final Statement setColorCommand) {
		painter.setColor(
			Color.createFromSpecification(setColorCommand.getRefOneAttribute())
		);	
	}
	
	//method
	/**
	 * Lets the current {@link FrontBrowserGUIClient} run the given set color gradient command.
	 * 
	 * @param painter
	 * @param setColorGradientCommand
	 * @throws InvalidArgumentException if the given set color gradient command is not valid.
	 */
	private void runSetColorGradientCommand(
		final IPainter painter,
		final Statement setColorGradientCommand
	) {
		painter.setColorGradient(
			ColorGradient.createFromSpecification(setColorGradientCommand.getRefOneAttribute())
		);	
	}
}
