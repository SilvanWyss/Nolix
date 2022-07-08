//package declaration
package ch.nolix.system.gui.main;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.core.programatom.stateproperty.Visibility;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.programcontrolapi.processproperty.ChangeState;
import ch.nolix.system.application.baseguiapplication.PaintRun;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.color.ColorGradient;
import ch.nolix.system.gui.textformat.TextFormat;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;

//class
public abstract class CanvasGUI<CG extends CanvasGUI<CG>> extends GUI<CG> {
	
	//constants
	public static final Color BACKGROUND_COLOR = Color.WHITE;
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.ARROW;
	
	//constant
	private static final String CURSOR_ICON_HEADER = PascalCaseCatalogue.CURSOR_ICON;
	
	//attribute
	private MutableValue<CursorIcon> cursorIcon =
	new MutableValue<>(
		CURSOR_ICON_HEADER,
		DEFAULT_CURSOR_ICON,
		this::setCursorIcon,
		CursorIcon::fromSpecification,
		Node::fromEnum
	);
		
	//multi-attribute
	private final LinkedList<IElementTaker<PaintRun>> paintCommands = new LinkedList<>();
	
	//constructor
	protected CanvasGUI(final IResizableInputTaker inputTaker, final Visibility visibility) {
		super(visibility, inputTaker);
	}
	
	//constructor
	protected CanvasGUI(final IResizableInputTaker inputTaker, final IVisualizer visualizer) {
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
	public void resetElementConfiguration() {
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
	@Override
	protected final void noteKeyDownWhenDoesNotHaveInputTaker(Key key) {}
	
	//method
	@Override
	protected final void noteKeyReleaseWhenDoesNotHaveInputTaker(Key key) {}
	
	//method
	@Override
	protected final void noteKeyTypingWhenDoesNotHaveInputTaker(Key key) {}
	
	//method
	@Override
	protected final void noteLeftMouseButtonClickWhenDoesNotHaveInputTaker() {}
	
	//method
	@Override
	protected final void noteLeftMouseButtonPressWhenDoesNotHaveInputTaker() {}
	
	//method
	@Override
	protected final void noteLeftMouseButtonReleaseWhenDoesNotHaveInputTaker() {}
	
	//method
	@Override
	protected final void noteMouseMoveWhenDoesNotHaveInputTaker(
		final int cursorXPositionOnViewArea,
		final int cursorYPositionOnViewArea
	) {}
	
	//method
	@Override
	protected final void noteMouseWheelClickWhenDoesNotHaveInputTaker() {}
	
	//method
	@Override
	protected final void noteMouseWheelPressWhenDoesNotHaveInputTaker() {}
	
	//method
	@Override
	protected final void noteMouseWheelReleaseWhenDoesNotHaveInputTaker() {}
	
	//method
	@Override
	protected final void noteMouseWheelRotationStepWhenDoesNotHaveInputTaker(
		final RotationDirection rotationDirection
	) {}
	
	//method
	@Override
	protected final void noteResizeWhenDoesNotHaveInputTaker(final int viewAreaWidth, final int viewAreaHeight) {}
	
	//method
	@Override
	protected final void noteRightMouseButtonClickWhenDoesNotHaveInputTaker() {}
	
	//method
	@Override
	protected final void noteRightMouseButtonPressWhenDoesNotHaveInputTaker() {}
	
	//method
	@Override
	protected final void noteRightMouseButtonReleaseWhenDoesNotHaveInputTaker() {}
	
	//method
	@Override
	protected final void recalculate(final ChangeState viewAreaChangeState) {}
	
	//method
	@Override
	protected final void resetGUI() {
		removePaintCommands();
	}
	
	//method
	private IElementTaker<PaintRun> createCreatePainterCommand(
		final int painterIndex,
		final ChainedNode createPainterCommand
	) {
		
		final var attributes = createPainterCommand.getChildNodesAsNodes();
		
		final var xTranslation = attributes.getRefAt1BasedIndex(1).toInt();
		final var yTranslation = attributes.getRefAt1BasedIndex(2).toInt();
		
		switch (attributes.getElementCount()) {
			case 2:
				return
				(final PaintRun pr) -> {
					
					final var painter =
					pr.getRefPainterByIndex(painterIndex).createPainter(xTranslation, yTranslation);
					
					pr.addPainter(painter);
				};
			case 4:
				
				final var paintAreaWidth = attributes.getRefAt1BasedIndex(3).toInt();
				final var paintAreaHeight = attributes.getRefAt1BasedIndex(4).toInt();
				
				return
				(final PaintRun pr) -> {
					
					final var painter = pr.getRefPainterByIndex(painterIndex).createPainter(
						xTranslation,
						yTranslation,
						paintAreaWidth,
						paintAreaHeight
					);
					
					pr.addPainter(painter);
				};
			default:
				throw
				InvalidArgumentException.forArgumentNameAndArgument("create painter command", createPainterCommand);
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
			case CanvasGUICommandProtocol.SET_COLOR:
				return createSetColorCommand(painterIndex, paintCommand);
			case CanvasGUICommandProtocol.SET_COLOR_GRADIENT:
				return createSetColorGradientCommand(painterIndex, paintCommand);
			case CanvasGUICommandProtocol.SET_OPACITY_PERCENTAGE:
				return createSetOpacityPercentageCommand(painterIndex, paintCommand);
			case CanvasGUICommandProtocol.TRANSLATE:
				return createTranslateCommand(painterIndex, paintCommand);
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument("painter command", paintCommand);
		}
	}
	
	//method
	private IElementTaker<PaintRun> createPaintCommand(final ChainedNode paintCommand) {
		
		final var painterIndex = paintCommand.getSingleChildNodeAsInt();
		
		return createPaintCommand(painterIndex, paintCommand.getNextNode());
	}

	//method
	private IElementTaker<PaintRun> createPaintFilledRectangleCommand(
		final int painterIndex,
		final ChainedNode paintFilledRectangleCommand
	) {
		
		final var attributes = paintFilledRectangleCommand.getChildNodesAsNodes();
		
		switch (attributes.getElementCount()) {
			case 2:
				
				final var width = attributes.getRefAt1BasedIndex(1).toInt();
				final var height = attributes.getRefAt1BasedIndex(2).toInt();
				
				return pr -> pr.getRefPainterByIndex(painterIndex).paintFilledRectangle(width, height);
			case 4:
				
				final var xPosition = attributes.getRefAt1BasedIndex(1).toInt();
				final var yPosition = attributes.getRefAt1BasedIndex(2).toInt();
				final var width2 = attributes.getRefAt1BasedIndex(3).toInt();
				final var height2 = attributes.getRefAt1BasedIndex(4).toInt();
				
				return
				painting ->
				painting.getRefPainterByIndex(painterIndex).paintFilledRectangle(xPosition, yPosition, width2, height2);
			
			default:
				throw
				InvalidArgumentException.forArgumentNameAndArgument(
					"paint filled rectangle command",
					paintFilledRectangleCommand
				);
		}
	}
	
	//method
	private IElementTaker<PaintRun> createPaintImageByIdCommand(
		final int painterIndex,
		final ChainedNode paintImageCommand
	) {
		
		final var attributes = paintImageCommand.getChildNodesAsNodes();
		final var imageId = attributes.getRefAt1BasedIndex(1).toString();
		
		switch (attributes.getElementCount()) {
			case 1:	
				return pr -> pr.getRefPainterByIndex(painterIndex).paintImageById(imageId);
			case 3:
				
				final var width = attributes.getRefAt1BasedIndex(2).toInt();
				final var height = attributes.getRefAt1BasedIndex(3).toInt();
				
				return pr -> pr.getRefPainterByIndex(painterIndex).paintImageById(imageId, width, height);
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument("paint image command", paintImageCommand);
		}
	}
	
	//method
	private IElementTaker<PaintRun> createPaintTextCommand(
		final int painterIndex,
		final ChainedNode paintTextCommand
	) {
		
		final var attributes = paintTextCommand.getChildNodesAsNodes();
		final var text = attributes.getRefAt1BasedIndex(1).getHeaderOrEmptyString();
		
		switch (attributes.getElementCount()) {
			case 1:
				return pr -> pr.getRefPainterByIndex(painterIndex).paintText(text);
			case 2:
					
				final var textFormat = TextFormat.fromSpecification(attributes.getRefAt1BasedIndex(2));
				
				return pr -> pr.getRefPainterByIndex(painterIndex).paintText(text, textFormat);
			case 3:
				
				final var textFormat2 = TextFormat.fromSpecification(attributes.getRefAt1BasedIndex(2));
				final var maxLength = attributes.getRefAt1BasedIndex(3).toInt();
				
				return pr -> pr.getRefPainterByIndex(painterIndex).paintText(text, textFormat2, maxLength);
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument("paint text command",	paintTextCommand);
		}
	}
	
	//method
	private IElementTaker<PaintRun> createSetColorCommand(final int painterIndex, final ChainedNode setColorCommand) {
		
		final var color = Color.fromSpecification(setColorCommand.getSingleChildNodeAsNode());
		
		return pr -> pr.getRefPainterByIndex(painterIndex).setColor(color);
	}
	
	//method
	private IElementTaker<PaintRun> createSetColorGradientCommand(
		final int painterIndex,
		final ChainedNode setColorGradientCommand
	) {
		
		final var colorGradient = ColorGradient.fromSpecification(setColorGradientCommand.getSingleChildNodeAsNode());
		
		return pr -> pr.getRefPainterByIndex(painterIndex).setColorGradient(colorGradient);
	}
	
	//method
	private IElementTaker<PaintRun> createSetOpacityPercentageCommand(
		final int painterIndex,
		final ChainedNode textualSetOpacityPercentageCommand
	) {
		
		final var opacityPercentage = textualSetOpacityPercentageCommand.getSingleChildNodeAsDouble();
		
		return pr -> pr.getRefPainterByIndex(painterIndex).setOpacity(opacityPercentage);
	}
	
	//method
	private IElementTaker<PaintRun> createTranslateCommand(final int painterIndex, final ChainedNode translateCommand) {
		
		final var attributes = translateCommand.getChildNodesAsNodes();
		final var xTranslation = attributes.getRefAt1BasedIndex(1).toInt();
		final var yTranslation = attributes.getRefAt1BasedIndex(2).toInt();
		
		return pr -> pr.getRefPainterByIndex(painterIndex).translate(xTranslation, yTranslation);
	}
	
	//method
	private void paintBackground(final IPainter painter) {
		painter.setColor(BACKGROUND_COLOR);
		painter.paintFilledRectangle(getViewAreaWidth(), getViewAreaHeight());
	}
}
