//package declaration
package ch.nolix.element.gui;

//own imports
import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.processproperty.ChangeState;
import ch.nolix.common.state.Visibility;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.elementapi.IConfigurableElement;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.input.IResizableInputTaker;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;
import ch.nolix.element.textformat.TextFormat;
import ch.nolix.system.baseguiclient.PaintRun;

//class
public abstract class CanvasGUI<CG extends CanvasGUI<CG>> extends GUI<CG> {
	
	//constants
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.ARROW;
	
	//attribute
	private MutableValue<CursorIcon> cursorIcon =
	new MutableValue<>(
		CursorIcon.TYPE_NAME,
		DEFAULT_CURSOR_ICON,
		this::setCursorIcon,
		CursorIcon::fromSpecification,
		CursorIcon::getSpecification
	);
		
	//multi-attribute
	private final LinkedList<IElementTaker<PaintRun>> paintCommands = new LinkedList<>();
	
	//constructor
	public CanvasGUI(final IResizableInputTaker inputTaker, final Visibility visibility) {
		super(visibility, inputTaker);
	}
	
	//constructor
	public CanvasGUI(final IResizableInputTaker inputTaker, final IVisualizer visualizer) {
		super(visualizer, inputTaker);
	}
		
	//method
	@Override
	public CursorIcon getCursorIcon() {
		return cursorIcon.getValue();
	}
	
	//method
	@Override
	public final IContainer<IConfigurableElement<?>> getSubConfigurables() {
		return new LinkedList<>();
	}
	
	//method
	public final void removePaintCommands() {
		paintCommands.clear();
	}
	
	//method
	@Override
	public void resetConfigurationOnSelf() {
		setCursorIcon(DEFAULT_CURSOR_ICON);
	}
	
	//method
	@Override
	public final void paint(final IPainter painter) {
		
		paintBackground(painter);
		
		new PaintRun(painter, paintCommands);
	}
	
	//method
	public final CG setCursorIcon(final CursorIcon cursorIcon) {
		
		this.cursorIcon.setValue(cursorIcon);
		
		return asConcrete();
	}
	
	//method
	public final void setPaintCommands(final IContainer<IElementTaker<PaintRun>> paintCommands) {
		this.paintCommands.refill(paintCommands);
		refresh();
	}
	
	//method
	public final void setPaintCommandsFromChainedNodes(final IContainer<ChainedNode> paintCommands) {
		setPaintCommands(paintCommands.to(this::createPaintCommand));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyPressWhenDoesNotHaveInputTaker(Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyReleaseWhenDoesNotHaveInputTaker(Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyTypingWhenDoesNotHaveInputTaker(Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonClickWhenDoesNotHaveInputTaker() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonPressWhenDoesNotHaveInputTaker() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonReleaseWhenDoesNotHaveInputTaker() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseMoveWhenDoesNotHaveInputTaker(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelClickWhenDoesNotHaveInputTaker() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelPressWhenDoesNotHaveInputTaker() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelReleaseWhenDoesNotHaveInputTaker() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelRotationStepWhenDoesNotHaveInputTaker(
		final RotationDirection rotationDirection
	) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteResizeWhenDoesNotHaveInputTaker(final int viewAreaWidth, final int viewAreaHeight) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonClickWhenDoesNotHaveInputTaker() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonPressWhenDoesNotHaveInputTaker() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonReleaseWhenDoesNotHaveInputTaker() {}
	
	//method
	@Override
	protected final void recalculate(final ChangeState viewAreaChangeState) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetGUI() {
		removePaintCommands();
	}
	
	//method
	private IElementTaker<PaintRun> createCreatePainterCommand(
		final int painterIndex,
		final ChainedNode createPainterCommand
	) {
		
		final var attributes = createPainterCommand.getAttributesAsNodes();
		
		final var xTranslation = attributes.getRefAt(1).toInt();
		final var yTranslation = attributes.getRefAt(2).toInt();
		
		switch (attributes.getElementCount()) {
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
			case CanvasGUICommandProtocol.CREATE_PAINTER:
				return createCreatePainterCommand(painterIndex, paintCommand);
			case CanvasGUICommandProtocol.PAINT_FILLED_RECTANGLE:
				return createPaintFilledRectangleCommand(painterIndex, paintCommand);
			case CanvasGUICommandProtocol.PAINT_IMAGE_BY_ID:
				return createPaintImageByIdCommand(painterIndex, paintCommand);
			case CanvasGUICommandProtocol.PAINT_TEXT:
				return createPaintTextCommand(painterIndex, paintCommand);
			case CanvasGUICommandProtocol.REGISTER_IMAGE:
				return createRegisterImageCommand(painterIndex, paintCommand);
			case CanvasGUICommandProtocol.SET_COLOR:
				return createSetColorCommand(painterIndex, paintCommand);
			case CanvasGUICommandProtocol.SET_COLOR_GRADIENT:
				return createSetColorGradientCommand(painterIndex, paintCommand);
			case CanvasGUICommandProtocol.TRANSLATE:
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
		
		switch (attributes.getElementCount()) {
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
	private IElementTaker<PaintRun> createPaintImageByIdCommand(
		final int painterIndex,
		final ChainedNode paintImageCommand
	) {
		
		final var attributes = paintImageCommand.getAttributesAsNodes();
		final var imageId = attributes.getRefAt(1).toString();
		
		switch (attributes.getElementCount()) {
			case 1:	
				return pr -> pr.getRefPainterByIndex(painterIndex).paintImageById(imageId);
			case 3:
				
				final var width = attributes.getRefAt(2).toInt();
				final var height = attributes.getRefAt(3).toInt();
				
				return pr -> pr.getRefPainterByIndex(painterIndex).paintImageById(imageId, width, height);
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
		final var text = attributes.getRefAt(1).getHeaderOrEmptyString();
		
		switch (attributes.getElementCount()) {
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
	private IElementTaker<PaintRun> createRegisterImageCommand(
		final int painterIndex,
		final ChainedNode registerImageCommand
	) {
		
		final var id = registerImageCommand.getAttributeAt(1).toString();
		final var image = Image.fromSpecification(registerImageCommand.getAttributeAt(2).toNode());
		
		return pr -> pr.getRefPainterByIndex(painterIndex).registerImageAtId(id, image);
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
