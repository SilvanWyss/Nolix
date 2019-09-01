//package declaration
package ch.nolix.element.GUI;

import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.validator.Validator;
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
	
	//constant
	public static final Color BACKGROUND_COLOR = Color.WHITE;

	//attributes
	private CursorIcon cursorIcon = CursorIcon.Arrow;
	private final IEventTaker eventTaker;
	
	//multi-attribute
	private final List<IElementTaker<PaintRun>> paintCommands = new List<>();
	
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
	@Override
	public final void noteKeyPress(final Key key) {
		eventTaker.noteKeyPress(key);
	}
	
	//method
	@Override
	public final void noteKeyRelease(final Key key) {
		eventTaker.noteKeyRelease(key);
	}
	
	//method
	@Override
	public final void noteKeyTyping(final Key key) {
		eventTaker.noteKeyTyping(key);
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonClick() {
		eventTaker.noteLeftMouseButtonClick();
	}
	
	//method
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
	@Override
	public final void noteMouseMove(final int cursorXPosition, final int cursorYPosition) {
		eventTaker.noteMouseMove(cursorXPosition, cursorYPosition);		
	}
	
	//method
	@Override
	public final void noteMouseWheelClick() {
		eventTaker.noteMouseWheelClick();		
	}
	
	//method
	@Override
	public final void noteMouseWheelPress() {
		eventTaker.noteMouseWheelPress();
	}
	
	//method
	@Override
	public final void noteMouseWheelRelease() {
		eventTaker.noteMouseWheelRelease();		
	}
	
	//method
	@Override
	public final void noteMouseWheelRotationStep(DirectionOfRotation directionOfRotation) {
		eventTaker.noteMouseWheelRotationStep(directionOfRotation);		
	}
		
	//method
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		eventTaker.noteResize(viewAreaWidth, viewAreaHeight);
	}
	
	//method
	@Override
	public final void noteRightMouseButtonClick() {
		eventTaker.noteRightMouseButtonClick();		
	}
	
	//method
	@Override
	public final void noteRightMouseButtonPress() {
		eventTaker.noteRightMouseButtonPress();		
	}
	
	//method
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
		
		paintBackground(painter);
		
		new PaintRun(painter, paintCommands);
	}
	
	//method
	@Override
	public final void recalculate() {}
	
	//method
	public final void setPaintCommands(final IContainer<IElementTaker<PaintRun>> paintCommands) {
		this.paintCommands.refill(paintCommands);
		refresh();
	}
	
	//method
	public final void setPaintCommandsFromStatements(final IContainer<ChainedNode> paintCommands) {
		setPaintCommands(paintCommands.to(pc -> createPaintCommand(pc)));
	}
	
    //method
	private IElementTaker<PaintRun> createCreatePainterCommand(
		final int painterIndex,
		final ChainedNode createPainterCommand
	) {
		
		final var attributes = createPainterCommand.getRefAttributes();
		
		final var xTranslation = attributes.getRefAt(1).toInt();
		final var yTranslation = attributes.getRefAt(2).toInt();
		
		switch (attributes.getSize()) {
			case 2:
				return pr -> {
					
					final var painter =
					pr.getRefPainterByIndex(painterIndex).createPainter(xTranslation, yTranslation);
					
					pr.addPainter(painter);
				};
			case 4:
				
				final var paintAreaWidth = attributes.getRefAt(3).toInt();
				final var paintAreaHeight = attributes.getRefAt(4).toInt();
				
				return
				pr -> {
					
					final var painter = pr.getRefPainterByIndex(painterIndex).createPainter(
						xTranslation,
						yTranslation,
						paintAreaWidth,
						paintAreaHeight
					);
					
					pr.addPainter(painter);
				};
			default:
				throw new InvalidArgumentException("create painter command", createPainterCommand, "is not valid");
		}
	}
	
	//method
	private IElementTaker<PaintRun> createPaintCommand(final int painterIndex,	final ChainedNode paintCommand) {
		switch (paintCommand.getHeader()) {
			case CanvasGUIProtocol.CREATE_PAINTER_HEADER:
				return createCreatePainterCommand(painterIndex, paintCommand);
			case CanvasGUIProtocol.PAINT_FILLED_RECTANGLE_HEADER:
				return createPaintFilledRectangleCommand(painterIndex, paintCommand);
			case CanvasGUIProtocol.PAINT_IMAGE_HEADER:
				return createPaintImageCommand(painterIndex, paintCommand);
			case CanvasGUIProtocol.PAINT_TEXT_HEADER:
				return createPaintTextCommand(painterIndex, paintCommand);
			case CanvasGUIProtocol.SET_COLOR_HEADER:
				return createSetColorCommand(painterIndex, paintCommand);
			case CanvasGUIProtocol.SET_COLOR_GRADIENT_HEADER:
				return createSetColorGradientCommand(painterIndex, paintCommand);
			case CanvasGUIProtocol.TRANSLATE_HEADER:
				return createTranslateCommand(painterIndex, paintCommand);
			default:
				throw new InvalidArgumentException("painter command",	paintCommand, "is not valid");
		}
	}
	
	//method
	private IElementTaker<PaintRun> createPaintCommand(final ChainedNode paintCommand) {
	    		
	    final var painterIndex = paintCommand.getOneAttributeAsInt();
	    
	    return createPaintCommand(painterIndex, paintCommand.getRefNextNode());
	}

	//method
	private IElementTaker<PaintRun> createPaintFilledRectangleCommand(
		final int painterIndex,
		final ChainedNode paintFilledRectangleCommand
	) {
		
		final var attributes = paintFilledRectangleCommand.getRefAttributes();
		
		switch (attributes.getSize()) {
			case 2:
				
				final var width = attributes.getRefAt(1).toInt();
				final var height = attributes.getRefAt(2).toInt();
				
				return pr -> pr.getRefPainterByIndex(painterIndex).paintFilledRectangle(width, height);
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
		final ChainedNode paintImageCommand
	) {
		
		final var attributes = paintImageCommand.getRefAttributes();
		final var image = Image.fromSpecification(attributes.getRefAt(1));
		
		switch (attributes.getSize()) {
			case 1:	
				return pr -> pr.getRefPainterByIndex(painterIndex).paintImage(image);
			case 3:
				
				final var width = attributes.getRefAt(2).toInt();
				final var height = attributes.getRefAt(3).toInt();
				
				return pr -> pr.getRefPainterByIndex(painterIndex).paintImage(image,	width, height);
			default:
				throw new InvalidArgumentException("paint image command", paintImageCommand, "is not valid");
		}
	}
	
	//method
	private IElementTaker<PaintRun> createPaintTextCommand(
		final int painterIndex,
		final ChainedNode paintTextCommand
	) {
		
		final var attributes = paintTextCommand.getRefAttributes();
		final var text = attributes.getRefAt(1).toString();
		
		switch (attributes.getSize()) {
			case 1:
				return pr -> pr.getRefPainterByIndex(painterIndex).paintText(text);
			case 2:
					
				final var textFormat = TextFormat.fromSpecification(attributes.getRefAt(2));
				
				return pr -> pr.getRefPainterByIndex(painterIndex).paintText(text, textFormat);
			case 3:
				
				final var textFormat2 = TextFormat.fromSpecification(attributes.getRefAt(2));
				final var maxLength = attributes.getRefAt(3).toInt();
				
				return pr -> pr.getRefPainterByIndex(painterIndex).paintText(text, textFormat2, maxLength);
			default:
				throw new InvalidArgumentException("paint text command", paintTextCommand,"is not valid");
		}
	}
	
	//method
	private IElementTaker<PaintRun> createSetColorCommand(final int painterIndex, final ChainedNode setColorCommand) {
		
		final var color = Color.fromSpecification(setColorCommand.getRefOneAttribute());
		
		return pr -> pr.getRefPainterByIndex(painterIndex).setColor(color);
	}
	
	//method
	private IElementTaker<PaintRun> createSetColorGradientCommand(
		final int painterIndex,
		final ChainedNode setColorGradientCommand
	) {
		
		final var colorGradient = ColorGradient.fromSpecification(setColorGradientCommand.getRefOneAttribute());
		
		return pr -> pr.getRefPainterByIndex(painterIndex).setColorGradient(colorGradient);
	}
	
	private IElementTaker<PaintRun> createTranslateCommand(final int painterIndex, final ChainedNode translateCommand) {
		
		final var attributes = translateCommand.getRefAttributes();
		final var xTranslation = attributes.getRefAt(1).toInt();
		final var yTranslation = attributes.getRefAt(2).toInt();
		
		return pr -> pr.getRefPainterByIndex(painterIndex).translate(xTranslation, yTranslation);
	}
	
	//method
	private void paintBackground(final IPainter painter) {
		painter.setColor(BACKGROUND_COLOR);
		painter.paintFilledRectangle(getViewAreaWidth(), getViewAreaHeight());
	}
}
