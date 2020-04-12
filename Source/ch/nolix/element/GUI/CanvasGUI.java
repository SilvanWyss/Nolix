//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.states.Visibility;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseAPI.IConfigurableElement;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.image.Image;
import ch.nolix.element.input.IInputTaker;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.textFormat.TextFormat;
import ch.nolix.system.baseGUIClient.PaintRun;

//class
public abstract class CanvasGUI<CG extends CanvasGUI<CG>> extends GUI<CG> {
	
	//constant
	public static final Color BACKGROUND_COLOR = Color.WHITE;

	//attributes
	private CursorIcon cursorIcon = CursorIcon.Arrow;
	private final IInputTaker inputTaker;
	
	//multi-attribute
	private final LinkedList<IElementTaker<PaintRun>> paintCommands = new LinkedList<>();
	
	//constructor
	public CanvasGUI(final IInputTaker inputTaker, final Visibility visibility) {
		
		super(visibility);
		
		Validator.assertThat(inputTaker).thatIsNamed("event taker").isNotNull();
		
		this.inputTaker = inputTaker;
	}
	
	//constructor
	public CanvasGUI(final IInputTaker inputTaker, final IVisualizer visualizer) {
		
		super(visualizer);
		
		Validator.assertThat(inputTaker).thatIsNamed("event taker").isNotNull();
		
		this.inputTaker = inputTaker;
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
		return new LinkedList<>();
	}
	
	//method
	@Override
	public final void noteKeyPress(final Key key) {
		inputTaker.noteKeyPress(key);
	}
	
	//method
	@Override
	public final void noteKeyRelease(final Key key) {
		inputTaker.noteKeyRelease(key);
	}
	
	//method
	@Override
	public final void noteKeyTyping(final Key key) {
		inputTaker.noteKeyTyping(key);
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonClick() {
		inputTaker.noteLeftMouseButtonClick();
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonPress() {
		inputTaker.noteLeftMouseButtonPress();
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonRelease() {
		inputTaker.noteLeftMouseButtonRelease();		
	}
	
	//method
	@Override
	public void noteMouseMove(final int cursorXPosition, final int cursorYPosition) {
		inputTaker.noteMouseMove(cursorXPosition, cursorYPosition);		
	}
	
	//method
	@Override
	public final void noteMouseWheelClick() {
		inputTaker.noteMouseWheelClick();		
	}
	
	//method
	@Override
	public final void noteMouseWheelPress() {
		inputTaker.noteMouseWheelPress();
	}
	
	//method
	@Override
	public final void noteMouseWheelRelease() {
		inputTaker.noteMouseWheelRelease();		
	}
	
	//method
	@Override
	public final void noteMouseWheelRotationStep(DirectionOfRotation directionOfRotation) {
		inputTaker.noteMouseWheelRotationStep(directionOfRotation);		
	}
		
	//method
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		inputTaker.noteResize(viewAreaWidth, viewAreaHeight);
	}
	
	//method
	@Override
	public final void noteRightMouseButtonClick() {
		inputTaker.noteRightMouseButtonClick();		
	}
	
	//method
	@Override
	public final void noteRightMouseButtonPress() {
		inputTaker.noteRightMouseButtonPress();		
	}
	
	//method
	@Override
	public final void noteRightMouseButtonRelease() {
		inputTaker.noteRightMouseButtonRelease();		
	}
	
	//method
	@Override
	public CG resetConfiguration() {
		return asConcrete();
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
	public final void setPaintCommandsFromChainedNodes(final IContainer<ChainedNode> paintCommands) {
		setPaintCommands(paintCommands.to(pc -> createPaintCommand(pc)));
	}
	
	//method
	private IElementTaker<PaintRun> createCreatePainterCommand(
		final int painterIndex,
		final ChainedNode createPainterCommand
	) {
		
		final var attributes = createPainterCommand.getAttributesAsNodes();
		
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
		
		return createPaintCommand(painterIndex, paintCommand.getNextNode());
	}

	//method
	private IElementTaker<PaintRun> createPaintFilledRectangleCommand(
		final int painterIndex,
		final ChainedNode paintFilledRectangleCommand
	) {
		
		final var attributes = paintFilledRectangleCommand.getAttributesAsNodes();
		
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
		
		final var attributes = paintImageCommand.getAttributesAsNodes();
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
		
		final var attributes = paintTextCommand.getAttributesAsNodes();
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
		
		final var color = Color.fromSpecification(setColorCommand.getOneAttributeAsNode());
		
		return pr -> pr.getRefPainterByIndex(painterIndex).setColor(color);
	}
	
	//method
	private IElementTaker<PaintRun> createSetColorGradientCommand(
		final int painterIndex,
		final ChainedNode setColorGradientCommand
	) {
		
		final var colorGradient = ColorGradient.fromSpecification(setColorGradientCommand.getOneAttributeAsNode());
		
		return pr -> pr.getRefPainterByIndex(painterIndex).setColorGradient(colorGradient);
	}
	
	//method
	private IElementTaker<PaintRun> createTranslateCommand(final int painterIndex, final ChainedNode translateCommand) {
		
		final var attributes = translateCommand.getAttributesAsNodes();
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
