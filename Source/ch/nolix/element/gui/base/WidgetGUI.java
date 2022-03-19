//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.processproperty.ChangeState;
import ch.nolix.core.state.Visibility;
import ch.nolix.element.base.CatchingProperty;
import ch.nolix.element.base.MultiValue;
import ch.nolix.element.elementapi.IConfigurableElement;
import ch.nolix.element.elementenum.ExtendedContentPosition;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.color.ColorGradient;
import ch.nolix.element.gui.containerwidget.Accordion;
import ch.nolix.element.gui.containerwidget.FloatContainer;
import ch.nolix.element.gui.containerwidget.Grid;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.containerwidget.SingleContainer;
import ch.nolix.element.gui.containerwidget.TabContainer;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.element.gui.image.ImageApplication;
import ch.nolix.element.gui.input.IResizableInputTaker;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;
import ch.nolix.element.gui.textbox.TextBox;
import ch.nolix.element.gui.widget.Area;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.CheckBox;
import ch.nolix.element.gui.widget.Console;
import ch.nolix.element.gui.widget.Downloader;
import ch.nolix.element.gui.widget.DropdownMenu;
import ch.nolix.element.gui.widget.HorizontalLine;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.SelectionMenu;
import ch.nolix.element.gui.widget.Uploader;
import ch.nolix.element.gui.widget.VerticalLine;
import ch.nolix.element.gui.widget.Widget;

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
	public static boolean canCreateWidgetFrom(final BaseNode specification) {
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
	public static Widget<?, ?> createWidgetFrom(final BaseNode specification) {
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
	private final MultiValue<Layer> layers =
	new MultiValue<>(
		PascalCaseCatalogue.LAYER,
		this::addLayerOnTop,
		Layer::fromSpecification,
		Layer::getSpecification
	);
	
	//optional attribute
	private Layer topLayer;
	
	//constructor
	/**
	 * Creates a new {@link WidgetGUI}.
	 * The {@link WidgetGUI} will be visible.
	 * The {@link WidgetGUI} will forward its received events to the given eventTaker.
	 * 
	 * @param inputTaker
	 * @throws ArgumentIsNullException if the given eventTaker is null.
	 */
	public WidgetGUI(final IResizableInputTaker inputTaker) {
		
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
	public WidgetGUI(IVisualizer visualizer) {
		
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
	public WidgetGUI(IVisualizer visualizer, IResizableInputTaker inputTaker) {
		
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
	public WidgetGUI(final Visibility visibility) {
		
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
	public WidgetGUI(final Visibility visibility, final IResizableInputTaker inputTaker) {
		
		//Calls constructor of the base class.
		super(visibility, inputTaker);
				
		initializeBackground();
	}
	
	//method
	/**
	 * Adds a new {@link Layer} on the top of the current {@link WidgetGUI}.
	 * The {@link Layer} will have the given contentPosition and rootWidget.
	 * 
	 * @param contentPosition
	 * @param rootWidget
	 * @return the current {@link WidgetGUI}.
	 * @throws ArgumentIsNullException if the given contentPosition is null.
	 * @throws ArgumentIsNullException if the given rootWidget is null.
	 */
	public final WG addLayerOnTop(final ExtendedContentPosition contentPosition, final Widget<?, ?> rootWidget) {		
		return addLayerOnTop(new Layer().setContentPosition(contentPosition).setRootWidget(rootWidget));
	}
	
	//method
	/**
	 * Adds the given layer on the top of the current {@link WidgetGUI}.
	 * Sets the root {@link Widget} of the given layer focused if the given layer contains a root {@link Widget}.
	 * 
	 * @param layer
	 * @return the current {@link WidgetGUI}.
	 * @throws ArgumentIsNullException if the given layer is null.
	 */
	public final WG addLayerOnTop(final Layer layer) {
		
		//Asserts that the given layer is not null.
		Validator.assertThat(layer).thatIsNamed(LowerCaseCatalogue.LAYER).isNotNull();
		
		layer.setParentGUI(this);
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
	 * Adds a new {@link Layer} on the top of the current {@link WidgetGUI}.
	 * The {@link Layer} will have the given rootWidget.
	 * 
	 * @param rootWidget
	 * @return the current {@link IWidgetGUI}.
	 * @throws ArgumentIsNullException if the given rootWidget is null.
	 */
	public final WG addLayerOnTop(final Widget<?, ?> rootWidget) {		
		return addLayerOnTop(new Layer().setRootWidget(rootWidget));
	}
	
	//method
	/**
	 * Adds or changes the given attributes to the {@link Widget}s of the current {@link GUI}.
	 * 
	 * @param attributes
	 * @param <BN> is the type of the given attributes.
	 * @return the current {@link GUI}.
	 * @throws InvalidArgumentException if the given attributes are not valid.
	 */
	public final <BN extends BaseNode> WG addOrChangeAttributesOfWidgets(final IContainer<IContainer<BN>> attributes) {
		
		final var iterator = attributes.iterator();
		
		getRefWidgets().forEach(w -> w.addOrChangeAttributes(iterator.next()));
		
		if (iterator.hasNext()) {
			throw new InvalidArgumentException(
				"attributes",
				attributes,
				"contains more than " + getRefWidgets().getElementCount() + " Widgets"
			);
		}
		
		return asConcrete();
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
	 * the current {@WidgetGUI} does not have a background {@link Color}.
	 */
	public final Color getBackgroundColor() {
		return background.getBackgroundColor();
	}
	
	//method
	/**
	 * @return the background {@link ColorGradient} of the current {@link WidgetGUI}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@WidgetGUI} does not have a background {@link ColorGradient}.
	 */
	public final ColorGradient getBackgroundColorGradient() {
		return background.getBackgroundColorGradient();
	}
	
	//method
	/**
	 * @return the background {@link MutableImage} of the current {@link WidgetGUI}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@WidgetGUI} does not have a background {@link MutableImage}.
	 */
	public final MutableImage getBackgroundImage() {
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
	 * @return the painter commands of the current {@link WidgetGUI}.
	 */
	public final IContainer<ChainedNode> getPaintCommands() {
		
		final var painter = new CanvasGUICommandCreatorPainter(getRefImageCache());
		paint(painter);
		
		return painter.getCommands();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<IConfigurableElement<?>> getSubConfigurables() {
		return layers.getRefSelected(Layer::allowesConfiguration).asContainerWithElementsOfEvaluatedType();
	}
	
	//method
	/**
	 * @return the {@link Layer}s of the current {@link WidgetGUI}.
	 */
	public final IContainer<Layer> getRefLayers() {
		return layers;
	}
	
	//method
	/**
	 * @param id
	 * @param <W> is the type of the returned {@link Widget}.
	 * @return the {@link Widget} with the given id from the current {@link WidgetGUI}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link WidgetGUI}
	 * does not contain a {@link Widget} with the given id.
	 */
	@SuppressWarnings("unchecked")
	public final <W extends Widget<?, ?>> W getRefWidgetById(final String id) {
		return (W)getRefWidgets().getRefFirst(w -> w.hasId(id));
	}
	
	//method
	/**
	 * @return the {@link Widget}s of the current {@link WidgetGUI}.
	 */
	public final IContainer<Widget<?, ?>> getRefWidgets() {
		return layers.toFromMany(Layer::getRefWidgets);
	}

	//method
	/**
	 * @return the {@link Widget}s, that are for painting, of the current {@link WidgetGUI}.
	 */
	public final IContainer<Widget<?, ?>> getRefWidgetsForPainting() {
		
		//Handles the case that the current WidgetGUI does not contain a Layer.
		if (isEmpty()) {
			return new LinkedList<>();
		}
		
		//Handles the case that the current WidgetGUI contains Layers.
		return topLayer.getRefWidgetsForPainting();
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
		background.paint(painter);
		layers.forEach(l -> l.paint(painter));
	}
	
	//method
	/**
	 * Removes the given layer from the current {@link WidgetGUI}.
	 * 
	 * @param layer
	 */
	public final void removeLayer(final Layer layer) {
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
			throw new EmptyArgumentException(this);
		}
		
		final var previousTopLayer = getRefTopOrBackgroundLayer();
		
		//Handles the case that the current WidgetGUI contains 1 layer.
		if (layers.containsOne()) {
			clear();
			
		//Handles the case that the current GUI contains several layers.
		} else {
			layers.removeLast();
			topLayer = layers.getRefLast();
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
	public final WG setBackgroundColor(final Color backgroundColor) {
		
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
	public final WG setBackgroundColorGradient(final ColorGradient backgroundColorGradient) {
		
		background.setBackgroundColorGradient(backgroundColorGradient);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the background {@link MutableImage} of the current {@link WidgetGUI}.
	 * Removes any former background of the current {@link WidgetGUI}.
	 * 
	 * @param backgroundImage
	 * @param imageApplication
	 * @return the current {@link WidgetGUI}.
	 * @throws ArgumentIsNullException if the given backgroundColor is null.
	 * @throws ArgumentIsNullException if the given imageApplication is null.
	 */
	public final WG setBackgroundImage(final MutableImage backgroundImage, final ImageApplication imageApplication) {
		
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
	protected final Layer getRefTopOrBackgroundLayer() {
		
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
			layers.forEach(Layer::recalculate);
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
	private boolean isTopLayer(final Layer layer) {
		return (topLayer == layer);
	}
	
	//method
	/**
	 * Initializes the background of the current {@link WidgetGUI}.
	 */
	private void initializeBackground() {
		background.setParentGUI(this);
	}
}
