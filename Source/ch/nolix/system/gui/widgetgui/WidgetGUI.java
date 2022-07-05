//package declaration
package ch.nolix.system.gui.widgetgui;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.core.programatom.stateproperty.Visibility;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.I2ElementTaker;
import ch.nolix.coreapi.programcontrolapi.processproperty.ChangeState;
import ch.nolix.system.element.CatchingProperty;
import ch.nolix.system.element.MultiValue;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.color.ColorGradient;
import ch.nolix.system.gui.containerwidget.Accordion;
import ch.nolix.system.gui.containerwidget.FloatContainer;
import ch.nolix.system.gui.containerwidget.Grid;
import ch.nolix.system.gui.containerwidget.HorizontalStack;
import ch.nolix.system.gui.containerwidget.SingleContainer;
import ch.nolix.system.gui.containerwidget.TabContainer;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.main.CanvasGUICommandCreatorPainter;
import ch.nolix.system.gui.main.GUI;
import ch.nolix.system.gui.main.IVisualizer;
import ch.nolix.system.gui.textbox.TextBox;
import ch.nolix.system.gui.widget.Area;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.CheckBox;
import ch.nolix.system.gui.widget.Console;
import ch.nolix.system.gui.widget.Downloader;
import ch.nolix.system.gui.widget.DropdownMenu;
import ch.nolix.system.gui.widget.HorizontalLine;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.SelectionMenu;
import ch.nolix.system.gui.widget.Uploader;
import ch.nolix.system.gui.widget.VerticalLine;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.inputapi.IResizableInputTaker;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.structureproperty.ExtendedContentPosition;
import ch.nolix.systemapi.guiapi.widgetguiapi.ILayer;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidgetGUI;

//class
/**
 * A {@link WidgetGUI} is a {@link GUI} that can contain several {@link Layer}s, that are stacked.
 * 
 * @author Silvan Wyss
 * @date 2019-08-01
 * @param <WG> is the type of a {@link WidgetGUI}.
 */
public abstract class WidgetGUI<WG extends WidgetGUI<WG>> extends GUI<WG> implements IWidgetGUI<WG> {
	
	//constant
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//constants
	private static final String BACKGROUND_COLOR_HEADER = "BackgroundColor";
	private static final String BACKGROUND_COLOR_GRADIENT_HEADER = "BackgroundColorGradient";
	
	//static attribute
	private static final WidgetProvider widgetProvider =
	new WidgetProvider(
		Accordion.class,
		Area.class,
		Button.class,
		CheckBox.class,
		Console.class,
		Downloader.class,
		DropdownMenu.class,
		FloatContainer.class,
		Grid.class,
		HorizontalLine.class,
		HorizontalStack.class,
		ImageWidget.class,
		Label.class,
		SelectionMenu.class,
		SingleContainer.class,
		TabContainer.class,
		TextBox.class,
		VerticalLine.class,
		VerticalStack.class,
		Uploader.class
	);
	
	//static method
	/**
	 * @param type
	 * @return true if a {@link WidgetGUI} can create a {@link Widget} of the given type.
	 */
	public static boolean canCreateWidget(final String type) {
		return widgetProvider.canCreateWidgetOf(type);
	}
	
	//static method
	/**
	 * @param specification
	 * @return true if a {@link WidgetGUI} can create a {@link Widget} from the given specification.
	 */
	public static boolean canCreateWidgetFrom(final INode<?> specification) {
		return widgetProvider.canCreateWidgetFrom(specification);
	}

	//static method
	/**
	 * @param type
	 * @return a new {@link Widget} of the given type with.
	 * @throws InvalidArgumentException if a {@link WidgetGUI} cannot create a {@link Widget} of the given type.
	 */
	public static Widget<?, ?> createWidget(final String type) {
		return widgetProvider.createWidget(type);
	}

	//static method
	/**
	 * @param specification
	 * @return a new {@link Widget} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Widget<?, ?> createWidgetFrom(final INode<?> specification) {
		return widgetProvider.createWidgetFrom(specification);
	}
	
	//static method
	/**
	 * Registers the given widgetClass.
	 * 
	 * @param widgetClass
	 * @throws ArgumentIsNullException if the given widgetClass is null.
	 * @throws InvalidArgumentException
	 * if a {@link WidgetGUI} contains already a {@link Widget} class wit the same type as the given widgetClass.
	 */
	public static void registerWidgetClass(final Class<Widget<?, ?>> widgetClass) {
		widgetProvider.registerWidgetClass(widgetClass);
	}
	
	//static method
	/**
	 * Registers the given widgetClasses
	 * 
	 * @param widgetClasses
	 * @throws ArgumentIsNullException if one of the given widgetClasses is null.
	 * @throws InvalidArgumentException
	 * if a {@link WidgetGUI} contains already a {@link Widget} class with the same type as one of the given widgetClasses.
	 */
	public static void registerWidgetClass(final Class<?>... widgetClasses) {
		widgetProvider.registerWidgetClass(widgetClasses);
	}
	
	//attribute
	private final Layer background = new Layer();
	
	//attribute
	@SuppressWarnings("unused")
	private final CatchingProperty<Color> backgroundColor =
	new CatchingProperty<>(BACKGROUND_COLOR_HEADER, this::setBackgroundColor, Color::fromSpecification);
	
	//attribute
	@SuppressWarnings("unused")
	private final CatchingProperty<ColorGradient> backgroundColorGradient =
	new CatchingProperty<>(
		BACKGROUND_COLOR_GRADIENT_HEADER,
		this::setBackgroundColorGradient,
		ColorGradient::fromSpecification
	);
	
	//attribute
	private final MultiValue<ILayer<?>> layers =
	new MultiValue<>(
		PascalCaseCatalogue.LAYER,
		this::pushLayer,
		Layer::fromSpecification,
		ILayer::getSpecification
	);
	
	//optional attribute
	private ILayer<?> topLayer;
	
	//constructor
	/**
	 * Creates a new {@link WidgetGUI}.
	 * The {@link WidgetGUI} will be visible.
	 * The {@link WidgetGUI} will forward its received events to the given eventTaker.
	 * 
	 * @param inputTaker
	 * @throws ArgumentIsNullException if the given eventTaker is null.
	 */
	protected WidgetGUI(final IResizableInputTaker inputTaker) {
		
		//Calls constructor of the base class.
		super(Visibility.VISIBLE, inputTaker);
				
		initializeBackground();
	}
	
	//constructor
	/**
	 * Creates a new {@link WidgetGUI}.
	 * The {@link WidgetGUI} will be visible and have the given visualizer..
	 * 
	 * @param visualizer
	 * @throws ArgumentIsNullException if the given visualizer is null.
	 */
	protected WidgetGUI(IVisualizer visualizer) {
		
		//Calls constructor of the base class.
		super(visualizer);
		
		initializeBackground();
	}
	
	//constructor
	/**
	 * Creates a new {@link WidgetGUI}.
	 * The {@link WidgetGUI} will be visible and have the given visualizer.
	 * The {@link WidgetGUI} will forward its received events to the given eventTaker.
	 * 
	 * @param visualizer
	 * @param inputTaker
	 * @throws ArgumentIsNullException if the given visualizer is null.
	 * @throws ArgumentIsNullException if the given eventTaker is null.
	 */
	protected WidgetGUI(IVisualizer visualizer, IResizableInputTaker inputTaker) {
		
		//Calls constructor of the base class.
		super(visualizer, inputTaker);
		
		initializeBackground();
	}
	
	//constructor
	/**
	 * Creates a new {@link WidgetGUI}.
	 * The {@link WidgetGUI} will be visible according to the given visibility.
	 * 
	 * @param visibility
	 */
	protected WidgetGUI(final Visibility visibility) {
		
		//Calls constructor of the base class.
		super(visibility);
		
		initializeBackground();
	}
	
	//constructor
	/**
	 * Creates a new {@link WidgetGUI}.
	 * The {@link WidgetGUI} will be visible according to the given visibility
	 * The {@link WidgetGUI} will forward its received events to the given eventTaker.
	 * 
	 * @param visibility
	 * @param inputTaker
	 * @throws ArgumentIsNullException if the given eventTaker is null.
	 */
	protected WidgetGUI(final Visibility visibility, final IResizableInputTaker inputTaker) {
		
		//Calls constructor of the base class.
		super(visibility, inputTaker);
				
		initializeBackground();
	}
		
	//method
	/**
	 * Removes the root {@link Widget} of the current GUI.
	 */
	@Override
	public final void clear() {
		layers.clear();
		topLayer = null;
	}
	
	//method
	/**
	 * @return the background {@link Color} of the current {@link WidgetGUI}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link WidgetGUI} does not have a background {@link Color}.
	 */
	public final IColor getBackgroundColor() {
		return background.getBackgroundColor();
	}
	
	//method
	/**
	 * @return the background {@link ColorGradient} of the current {@link WidgetGUI}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link WidgetGUI} does not have a background {@link ColorGradient}.
	 */
	public final IColorGradient getBackgroundColorGradient() {
		return background.getBackgroundColorGradient();
	}
	
	//method
	/**
	 * @return the background {@link MutableImage} of the current {@link WidgetGUI}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link WidgetGUI} does not have a background {@link MutableImage}.
	 */
	public final IImage getBackgroundImage() {
		return background.getBackgroundImage();
	}
	
	//method
	/**
	 * @return the {@link CursorIcon} of the current {@link WidgetGUI}.
	 */
	public final CursorIcon getCursorIcon() {		
		
		//Handles the case that the current WidgetGUI does not contain a Layer.
		if (isEmpty()) {
			return CursorIcon.ARROW;
		}
		
		//Handles the case that the current WidgetGUI contains Layers.
		return topLayer.getCursorIcon();
 	}
	
	//method
	/**
	 * @param imageRegistrator
	 * @return the painter commands of the current {@link WidgetGUI}.
	 */
	@Override
	public final LinkedList<ChainedNode> getPaintCommands(final I2ElementTaker<String, IImage> imageRegistrator) {
		
		final var painter =
		CanvasGUICommandCreatorPainter.withImageCacheAndImageRegistrator(getRefImageCache(), imageRegistrator);
		
		paint(painter);
		
		return painter.internalGetRefPaintCommands();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<? extends IConfigurableElement<?>> getSubConfigurables() {
		return layers.getRefValues().getRefSelected(ILayer::allowesConfiguration);
	}
	
	//method
	/**
	 * @return the {@link Layer}s of the current {@link WidgetGUI}.
	 */
	public final IContainer<ILayer<?>> getRefLayers() {
		return layers.getRefValues();
	}
	
	//method
	/**
	 * @return true if the current {@link WidgetGUI} does not contain a {@link Layer}.
	 */
	@Override
	public final boolean isEmpty() {
		return layers.isEmpty();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void paint(final IPainter painter) {
		background.paint(painter.createPainter());
		layers.getRefValues().forEach(l -> l.paint(painter.createPainter()));
	}
	
	//method
	/**
	 * Pushes a new {@link Layer} on the top of the current {@link WidgetGUI}.
	 * The created {@link Layer} will have the given contentPosition and rootWidget.
	 * Sets the given root {@link Widget} focused.
	 * 
	 * @param contentPosition
	 * @param rootWidget
	 * @return the current {@link WidgetGUI}.
	 * @throws ArgumentIsNullException if the given contentPosition is null.
	 * @throws ArgumentIsNullException if the given rootWidget is null.
	 */
	public final WG pushLayer(final ExtendedContentPosition contentPosition, final Widget<?, ?> rootWidget) {		
		return
		pushLayer(
			new Layer()
			.setContentPosition(contentPosition)
			.setRootWidget(rootWidget)
		);
	}
	
	//method
	/**
	 * Pushes the given {@link Layer} on the top of the current {@link WidgetGUI}.
	 * 
	 * Sets the root {@link Widget} of the given {@link Layer} focused if
	 * the given {@link Layer} contains a root {@link Widget}.
	 * 
	 * @param layer
	 * @return the current {@link WidgetGUI}.
	 * @throws ArgumentIsNullException if the given layer is null.
	 */
	public final WG pushLayer(final ILayer<?> layer) {
		
		GlobalValidator.assertThat(layer).thatIsNamed(LowerCaseCatalogue.LAYER).isNotNull();
		
		((Layer)layer).internalSetParentGUI(this);
		layers.add(layer);
		topLayer = layer;
		
		if (layer.containsAny()) {
			layer.getRefRootWidget().setFocused();
		}
		
		updateFromConfiguration();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Pushes a new {@link Layer} on the top of the current {@link WidgetGUI}.
	 * The {@link Layer} will have the given root {@link Widget}.
	 * Sets the given root {@link Widget} focused.
	 * 
	 * @param rootWidget
	 * @return the current {@link IWidgetGUI}.
	 * @throws ArgumentIsNullException if the given rootWidget is null.
	 */
	public final WG pushLayerWithRootWidget(final IWidget<?, ?> rootWidget) {		
		return
		pushLayer(
			new Layer()
			.setRootWidget(rootWidget)
		);
	}
	
	//method
	/**
	 * Removes the given layer from the current {@link WidgetGUI}.
	 * 
	 * @param layer
	 */
	public final void removeLayer(final ILayer<?> layer) {
		if (!isTopLayer(layer)) {
			layers.remove(layer);
		} else {
			removeTopLayer();
		}
	}
	
	//method
	/**
	 * Removes the top {@link Layer} from the current {@link WidgetGUI}.
	 *
	 * @throws EmptyArgumentException if the current {@link GUI} does not contain a layer.
	 */
	public final void removeTopLayer() {
		
		//Asserts that the current WidgetGUI is not empty.
		if (isEmpty()) {
			throw EmptyArgumentException.forArgument(this);
		}
		
		final var previousTopLayer = getRefTopOrBackgroundLayer();
		
		//Handles the case that the current WidgetGUI contains 1 layer.
		if (getRefLayers().containsOne()) {
			clear();
			
		//Handles the case that the current GUI contains several layers.
		} else {
			layers.removeLast();
			topLayer = getRefLayers().getRefLast();
		}
		
		getRefTopOrBackgroundLayer().noteMouseMove(
			previousTopLayer.getCursorXPosition(),
			previousTopLayer.getCursorYPosition()
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void resetElementConfiguration() {
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
	}
	
	//method
	/**
	 * Sets the background {@link Color} of the current {@link WidgetGUI}.
	 * Removes any former background of the current {@link WidgetGUI}.
	 * 
	 * @param backgroundColor
	 * @return the current {@link WidgetGUI}.
	 * @throws ArgumentIsNullException if the given backgroundColor is null.
	 */
	public final WG setBackgroundColor(final IColor backgroundColor) {
		
		background.setBackgroundColor(backgroundColor);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the background {@link ColorGradient} of the current {@link WidgetGUI}.
	 * Removes any former background of the current {@link WidgetGUI}.
	 * 
	 * @param backgroundColorGradient
	 * @return the current {@link WidgetGUI}.
	 * @throws ArgumentIsNullException if the given backgroundColorGradient is null.
	 */
	public final WG setBackgroundColorGradient(final IColorGradient backgroundColorGradient) {
		
		background.setBackgroundColorGradient(backgroundColorGradient);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the background {@link MutableImage} of the current {@link WidgetGUI}.
	 * Removes any former background of the current {@link WidgetGUI}.
	 * 
	 * @param backgroundImage
	 * @return the current {@link WidgetGUI}.
	 * @throws ArgumentIsNullException if the given backgroundImage is null.
	 */
	public final WG setBackgroundImage(final IImage backgroundImage) {
		return setBackgroundImage(backgroundImage, ImageApplication.SCALE_TO_FRAME);
	}
	
	//method
	/**
	 * Sets the background {@link MutableImage} of the current {@link WidgetGUI}.
	 * Removes any former background of the current {@link WidgetGUI}.
	 * 
	 * @param backgroundImage
	 * @param imageApplication
	 * @return the current {@link WidgetGUI}.
	 * @throws ArgumentIsNullException if the given backgroundImage is null.
	 * @throws ArgumentIsNullException if the given imageApplication is null.
	 */
	public final WG setBackgroundImage(final IImage backgroundImage, final ImageApplication imageApplication) {
		
		background.setBackgroundImage(backgroundImage, imageApplication);
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void updateFromConfiguration() {
		
		//Calls method of the base class.
		super.updateFromConfiguration();
		
		refresh();
	}
	
	//method
	/**
	 * @return the top {@link Layer} of the current {@link WidgetGUI}
	 * if the current {@link WidgetGUI} contains {@link Layer}s,
	 * otherwise the background of the current {@link WidgetGUI}.
	 */
	protected final ILayer<?> getRefTopOrBackgroundLayer() {
		
		//Handles the case that the current WidgetGUI does not contain a Layer.
		if (isEmpty()) {
			return background;
		}
		
		//Handles the case that the current WidgetGUI contains Layers.
		return topLayer;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyDownWhenDoesNotHaveInputTaker(final Key key) {
		getRefKeyBoardForMutating().noteKeyDown(key);
		getRefTopOrBackgroundLayer().noteKeyDown(key);
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyReleaseWhenDoesNotHaveInputTaker(final Key key) {		
		getRefKeyBoardForMutating().noteKeyRelease(key);
		getRefTopOrBackgroundLayer().noteKeyRelease(key);
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyTypingWhenDoesNotHaveInputTaker(final Key key) {		
		getRefTopOrBackgroundLayer().noteKeyTyping(key);
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonClickWhenDoesNotHaveInputTaker() {		
		getRefTopOrBackgroundLayer().noteLeftMouseButtonClick();
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonPressWhenDoesNotHaveInputTaker() {
		getRefTopOrBackgroundLayer().noteLeftMouseButtonPress();
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonReleaseWhenDoesNotHaveInputTaker() {
		getRefTopOrBackgroundLayer().noteLeftMouseButtonRelease();
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseMoveWhenDoesNotHaveInputTaker(
		final int viewAreaCursorXPosition,
		final int viewAreaCursorYPosition
	) {
		getRefTopOrBackgroundLayer().noteMouseMove(viewAreaCursorXPosition, viewAreaCursorYPosition);
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelClickWhenDoesNotHaveInputTaker() {
		getRefTopOrBackgroundLayer().noteMouseWheelClick();
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelPressWhenDoesNotHaveInputTaker() {
		getRefTopOrBackgroundLayer().noteMouseWheelPress();
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelReleaseWhenDoesNotHaveInputTaker() {
		getRefTopOrBackgroundLayer().noteMouseWheelRelease();
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelRotationStepWhenDoesNotHaveInputTaker(final RotationDirection rotationDirection) {
		getRefTopOrBackgroundLayer().noteMouseWheelRotationStep(rotationDirection);
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteResizeWhenDoesNotHaveInputTaker(final int viewAreaWidth, final int viewAreaHeight) {
		getRefTopOrBackgroundLayer().noteResize(viewAreaWidth, viewAreaHeight);
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonClickWhenDoesNotHaveInputTaker() {
		getRefTopOrBackgroundLayer().noteRightMouseButtonClick();
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonPressWhenDoesNotHaveInputTaker() {
		getRefTopOrBackgroundLayer().noteRightMouseButtonPress();
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonReleaseWhenDoesNotHaveInputTaker() {
		getRefTopOrBackgroundLayer().noteRightMouseButtonRelease();
		refresh();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void recalculate(ChangeState viewAreaChangeState) {
		if (viewAreaChangeState == ChangeState.CHANGED) {
			getRefLayers().forEach(ILayer::recalculate);
		} else if (topLayer != null) {
			topLayer.recalculate();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetGUI() {
		clear();
	}
	
	//method
	/**
	 * @param layer
	 * @return true if the given layer is the top {@link Layer} of the current {@link WidgetGUI}.
	 */
	private boolean isTopLayer(final ILayer<?> layer) {
		return (topLayer == layer);
	}
	
	//method
	/**
	 * Initializes the background of the current {@link WidgetGUI}.
	 */
	private void initializeBackground() {
		background.internalSetParentGUI(this);
	}
}
