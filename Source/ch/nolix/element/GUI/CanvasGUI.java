//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.statement.Statement;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI_API.CursorIcon;
import ch.nolix.element.baseAPI.IConfigurableElement;
import ch.nolix.element.baseGUI_API.IEventTaker;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.image.Image;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.textFormat.TextFormat;
import ch.nolix.system.GUIClientoid.PaintRun;

//class
public abstract class CanvasGUI<CG extends CanvasGUI<CG>> extends GUI<CG> {
	
	//attribute
	private CursorIcon cursorIcon;
	
	//attribute
	private final IEventTaker eventTaker;
	
	//multi-attribute
	private final List<IElementTaker<PaintRun>> painterCommands = new List<>();
	
	//constructor
	public CanvasGUI(final IEventTaker eventTaker, final boolean visible) {
		
		super(visible);
		
		Validator.suppose(eventTaker).thatIsNamed("event taker").isNotNull();
		
		this.eventTaker = eventTaker;
	}
	
	//constructor
	public CanvasGUI(final IEventTaker eventTaker, final IVisualizer visualizer) {
		
		super(visualizer);
		
		Validator.suppose(eventTaker).thatIsNamed("event taker").isNotNull();
		
		this.eventTaker = eventTaker;
	}
	
	//method
	@Override
	public final boolean containsElement(final String name) {
		return false;
	}
	
	//method
	@Override
	public CursorIcon getCursorIcon() {
		return cursorIcon;
	}
	
	//method
	@Override
	public final IContainer<IConfigurableElement<?>> getRefConfigurables() {
		return new List<>();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyPress(final Key key) {
		eventTaker.noteKeyPress(key);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyRelease(final Key key) {
		eventTaker.noteKeyRelease(key);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyTyping(final Key key) {
		eventTaker.noteKeyTyping(key);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonClick() {
		eventTaker.noteLeftMouseButtonClick();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonPress() {
		eventTaker.noteLeftMouseButtonPress();
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonRelease() {
		eventTaker.noteLeftMouseButtonRelease();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseMove(final int cursorXPosition, final int cursorYPosition) {
		eventTaker.noteMouseMove(cursorXPosition, cursorYPosition);		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelClick() {
		eventTaker.noteMouseWheelClick();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelPress() {
		eventTaker.noteMouseWheelPress();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRelease() {
		eventTaker.noteMouseWheelRelease();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRotationStep(DirectionOfRotation directionOfRotation) {
		eventTaker.noteMouseWheelRotationStep(directionOfRotation);		
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		eventTaker.noteResize(viewAreaWidth, viewAreaHeight);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonClick() {
		eventTaker.noteRightMouseButtonClick();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonPress() {
		eventTaker.noteRightMouseButtonPress();		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonRelease() {
		eventTaker.noteRightMouseButtonRelease();		
	}
	
	//method
	@Override
	public CG resetConfiguration() {
		return asConcreteType();
	}
	
	//method
	@Override
	public final void paint(final IPainter painter) {
		new PaintRun(painter, painterCommands);
	}
	
	//method
	@Override
	public final void recalculate() {}
	
	//method
	public final void setPainterCommands(final Iterable<IElementTaker<PaintRun>> painterCommands) {
		this.painterCommands.refill(painterCommands);
	}
	
	//method
	public final void setPainterCommands2(final Iterable<Statement> painterCommands) {
		this.painterCommands.clear();
		painterCommands.forEach(pc -> this.painterCommands.addAtEnd(createPainterCommand(pc)));
	}
	
	//method
	private IElementTaker<PaintRun> createCreatePainerCommand(final Statement painterCommand) {
		return painting -> painting.addPainter(painting.getRefPainterByIndex(1).createPainter());
	}
	
	//method
	private IElementTaker<PaintRun> createPainterCommand(final Statement painterCommand) {
		
		final var painterIndex = painterCommand.getOneAttributeAsInt();
		
		return createPainterCommand(painterIndex, painterCommand.getRefNextStatement());
	}
	
	//method
	private IElementTaker<PaintRun> createPainterCommand(
		final int painterIndex,
		final Statement painterCommand
	) {
		
		//Enumerates the header of the given painter command.
		switch (painterCommand.getHeader()) {
			case CanvasGUIProtocol.CREATE_PAINTER_HEADER:
				return createCreatePainerCommand(painterCommand);
			case CanvasGUIProtocol.PAINT_FILLED_RECTANGLE_HEADER:
				return createPaintFilledRectangleCommand(painterIndex, painterCommand);
			case CanvasGUIProtocol.PAINT_IMAGE_HEADER:
				return createPaintImageCommand(painterIndex, painterCommand);
			case CanvasGUIProtocol.PAINT_TEXT_HEADER:
				return createPaintTextCommand(painterIndex, painterCommand);
			case CanvasGUIProtocol.SET_COLOR_HEADER:
				return createSetColorCommand(painterIndex, painterCommand);
			case CanvasGUIProtocol.SET_COLOR_GRADIENT_HEADER:
				return createSetColorGradientCommand(painterIndex, painterCommand);
			default:
				throw new InvalidArgumentException("painter command",	painterCommand, "is not valid");
		}
	}
	
	//method
	private IElementTaker<PaintRun> createPaintFilledRectangleCommand(
		final int painterIndex,
		final Statement paintFilledRectangleCommand
	) {
		
		//Extracts the attributes of the given paint filled rectangle command.
		final var attributes = paintFilledRectangleCommand.getRefAttributes();
		
		//Enumerates the number of attributes of the given paint filled rectangle command.
		switch (paintFilledRectangleCommand.getAttributeCount()) {
			case 2:
				
				final var width = attributes.getRefAt(1).toInt();
				final var height = attributes.getRefAt(2).toInt();
				
				return painting ->	painting.getRefPainterByIndex(painterIndex).paintFilledRectangle(width,	height);
			case 4:
				
				final var xPosition = attributes.getRefAt(1).toInt();
				final var yPosition = attributes.getRefAt(2).toInt();
				final var width2 = attributes.getRefAt(3).toInt();
				final var height2 = attributes.getRefAt(4).toInt();
				
				return
				painting ->
				painting.getRefPainterByIndex(painterIndex).paintFilledRectangle(xPosition, yPosition, width2, height2);
			
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
	private IElementTaker<PaintRun> createPaintImageCommand(
		final int painterIndex,
		final Statement paintImageCommand
	) {
		
		//Extracts the attributes of the given paint image command.
		final var attributes = paintImageCommand.getRefAttributes();
		
		
		//Enumerates the number of attributes of the given paint image command.
		switch (attributes.getSize()) {
			case 1:	{
				final var image = Image.createFromSpecification(attributes.getRefAt(1));
				
				return painting ->	painting.getRefPainterByIndex(painterIndex).paintImage(image);
			}
			case 3:
				
				final var image = Image.createFromSpecification(attributes.getRefAt(1));
				final var width = attributes.getRefAt(2).toInt();
				final var height = attributes.getRefAt(3).toInt();
				
				return
				painting ->
				painting.getRefPainterByIndex(painterIndex).paintImage(
					image,
					width,
					height
				);
			default:
				throw
				new InvalidArgumentException("paint image command",	paintImageCommand, "is not valid");
		}
	}
	
	//method
	private IElementTaker<PaintRun> createPaintTextCommand(
		final int painterIndex,
		final Statement paintTextCommand
	) {
		
		//Extracts the attributes of the given paint text command.
		final var attributes = paintTextCommand.getRefAttributes();
		
		//Enumerates the number of attributes of the given paint text command.
		switch (attributes.getSize()) {
			case 1: {
				
				final var text = attributes.getRefOne().toString();
				
				return painting -> painting.getRefPainterByIndex(painterIndex).paintText(text);
			}
			case 2: {
				
				final var text = attributes.getRefAt(1).toString();
				final var textFormat = TextFormat.createFromSpecification(attributes.getRefAt(2));
				
				return painting -> painting.getRefPainterByIndex(painterIndex).paintText(text, textFormat);
			}
			case 3:
				
				final var text = attributes.getRefAt(1).toString();
				final var textFormat = TextFormat.createFromSpecification(attributes.getRefAt(2));
				final var maxLength = attributes.getRefAt(3).toInt();
				
				return painting -> painting.getRefPainterByIndex(painterIndex).paintText(text, textFormat, maxLength); 
			default:
				throw
				new InvalidArgumentException("paint text command", paintTextCommand,"is not valid");
		}
	}
	
	//method
	private IElementTaker<PaintRun> createSetColorCommand(
		final int painterIndex,
		final Statement setColorCommand
	) {
		
		final var color = Color.createFromSpecification(setColorCommand.getRefOneAttribute());
		
		return painting -> painting.getRefPainterByIndex(painterIndex).setColor(color);
	}
	
	//method
	private IElementTaker<PaintRun> createSetColorGradientCommand(
		final int painterIndex,
		final Statement setColorGradientCommand
	) {
		
		final var colorGradient = ColorGradient.createFromSpecification(setColorGradientCommand.getRefOneAttribute());
		
		return painting -> painting.getRefPainterByIndex(painterIndex).setColorGradient(colorGradient);
	}
}
