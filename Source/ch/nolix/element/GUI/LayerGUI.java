//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.EmptyArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.processProperty.ChangeState;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.state.Visibility;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MultiProperty;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.containerWidget.Accordion;
import ch.nolix.element.containerWidget.FloatContainer;
import ch.nolix.element.containerWidget.Grid;
import ch.nolix.element.containerWidget.SingleContainer;
import ch.nolix.element.containerWidget.TabContainer;
import ch.nolix.element.elementAPI.IConfigurableElement;
import ch.nolix.element.elementEnum.RotationDirection;
import ch.nolix.element.elementEnum.ExtendedContentPosition;
import ch.nolix.element.input.IResizableInputTaker;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.widget.Area;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.CheckBox;
import ch.nolix.element.widget.Console;
import ch.nolix.element.widget.Downloader;
import ch.nolix.element.widget.DropdownMenu;
import ch.nolix.element.widget.HorizontalLine;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.SelectionMenu;
import ch.nolix.element.widget.TextBox;
import ch.nolix.element.widget.Uploader;
import ch.nolix.element.widget.VerticalLine;
import ch.nolix.element.widget.VerticalStack;

//class
/**
 * A {@link LayerGUI} is a {@link GUI} that can contain several {@link ILayer}s, that are stacked.
 * 
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 810
 * @param <LG> The type of a {@link LayerGUI}.
 */
public abstract class LayerGUI<LG extends LayerGUI<LG>> extends GUI<LG> implements Clearable<LG>{
	
	//constant
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
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
	 * @return true if a {@link LayerGUI} can create a {@link Widget} of the given type.
	 */
	public static boolean canCreateWidget(final String type) {
		return widgetProvider.canCreateWidgetOf(type);
	}
	
	//static method
	/**
	 * @param specification
	 * @return true if a {@link LayerGUI} can create a {@link Widget} from the given specification.
	 */
	public static boolean canCreateWidgetFrom(final BaseNode specification) {
		return widgetProvider.canCreateWidgetFrom(specification);
	}

	//static method
	/**
	 * @param type
	 * @return a new {@link Widget} of the given type with.
	 * @throws InvalidArgumentException if a {@link LayerGUI} cannot create a {@link Widget} of the given type.
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
	 * if a {@link LayerGUI} contains already a {@link Widget} class wit the same type as the given widgetClass.
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
	 * if a {@link LayerGUI} contains already a {@link Widget} class with the same type as one of the given widgetClasses.
	 */
	public static void registerWidgetClass(final Class<?>... widgetClasses) {
		widgetProvider.registerWidgetClass(widgetClasses);
	}
	
	//attribute
	private final Layer backGround = new Layer();
	
	//attribute
	private final MultiProperty<Layer> layers =
	new MultiProperty<>(
		PascalCaseNameCatalogue.LAYER,
		this::addLayerOnTop,
		Layer::fromSpecification,
		Layer::getSpecification
	);
	
	//optional attribute
	private Layer topLayer;
	
	//constructor
	/**
	 * Creates a new {@link LayerGUI}.
	 * The {@link LayerGUI} will be visible.
	 * The {@link LayerGUI} will forward its received events to the given eventTaker.
	 * 
	 * @param visible
	 * @param inputTaker
	 * @throws ArgumentIsNullException if the given eventTaker is null.
	 */
	public LayerGUI(final IResizableInputTaker inputTaker) {
		
		super(Visibility.VISIBLE, inputTaker);
				
		backGround.setParentGUI(this);
	}
	
	//constructor
	/**
	 * Creates a new {@link LayerGUI}.
	 * The {@link LayerGUI} will be visible and have the given visualizer..
	 * 
	 * @param visible
	 * @param inputTaker
	 * @throws ArgumentIsNullException if the given visualizer is null.
	 */
	public LayerGUI(IVisualizer visualizer) {
		
		super(visualizer);
		
		backGround.setParentGUI(this);
	}
	
	//constructor
	/**
	 * Creates a new {@link LayerGUI}.
	 * The {@link LayerGUI} will be visible and have the given visualizer.
	 * The {@link LayerGUI} will forward its received events to the given eventTaker.
	 * 
	 * @param visible
	 * @param inputTaker
	 * @throws ArgumentIsNullException if the given visualizer is null.
	 * @throws ArgumentIsNullException if the given eventTaker is null.
	 */
	public LayerGUI(IVisualizer visualizer, IResizableInputTaker inputTaker) {
		
		super(visualizer, inputTaker);
		
		backGround.setParentGUI(this);
	}
	
	//constructor
	/**
	 * Creates a new {@link LayerGUI}.
	 * The {@link LayerGUI} will be visible according to the given visibility.
	 * 
	 * @param visibility
	 */
	public LayerGUI(final Visibility visibility) {
		
		super(visibility);
		
		backGround.setParentGUI(this);
	}
	
	//constructor
	/**
	 * Creates a new {@link LayerGUI}.
	 * The {@link LayerGUI} will be visible according to the given visibility
	 * The {@link LayerGUI} will forward its received events to the given eventTaker.
	 * 
	 * @param visibility
	 * @param inputTaker
	 * @throws ArgumentIsNullException if the given eventTaker is null.
	 */
	public LayerGUI(final Visibility visibility, final IResizableInputTaker inputTaker) {
		
		super(visibility, inputTaker);
				
		backGround.setParentGUI(this);
	}

	//method
	/**
	 * Adds a new {@link Layer} on the top of the current {@link LayerGUI}.
	 * The {@link Layer} will have the given contentPosition and rootWidget.
	 * 
	 * @param contentPosition.
	 * @param rootWidget
	 * @return the current {@link LayerGUI}.
	 * @throws ArgumentIsNullException if the given contentPosition is null.
	 * @throws ArgumentIsNullException if the given rootWidget is null.
	 */
	public final LG addLayerOnTop(final ExtendedContentPosition contentPosition, final Widget<?, ?> rootWidget) {		
		return addLayerOnTop(new Layer(contentPosition, rootWidget));
	}

	//method
	/**
	 * Adds the given layer on the top of the current {@link LayerGUI}.
	 * 
	 * @param layer
	 * @return the current {@link LayerGUI}.
	 * @throws ArgumentIsNullException if the given layer is null.
	 */
	public final LG addLayerOnTop(final Layer layer) {
		
		//Asserts that the given layer is not null.
		Validator.assertThat(layer).thatIsNamed(VariableNameCatalogue.LAYER).isNotNull();
		
		layer.setParentGUI(this);
		layers.add(layer);
		topLayer = layer;
		
		updateFromConfiguration();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds a new {@link Layer} on the top of the current {@link LayerGUI}.
	 * The {@link Layer} will have the given rootWidget.
	 * 
	 * @param rootWidget
	 * @return the current {@link ILayerGUI}.
	 * @throws ArgumentIsNullException if the given rootWidget is null.
	 */
	public final LG addLayerOnTop(final Widget<?, ?> rootWidget) {		
		return addLayerOnTop(new Layer(rootWidget));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addOrChangeAttribute(final BaseNode attribute) {
				
		//Handles the case that the given attribute specifies a Widget.
		if (canCreateWidget(attribute.getHeader())) {
			
			//Handles the case that the current GUI does not contain a layer.
			if (isEmpty()) {
				addLayerOnTop(new Layer());
			}
			
			layers.getRefFirst().setRootWidget(createWidgetFrom(attribute));
		}
		
		//Handles the case that the given attribute does not specify a widget.
		else {
			//Enumerates the header of the given attribute.
			switch (attribute.getHeader()) {				
				case PascalCaseNameCatalogue.BACKGROUND_COLOR:
					backGround.setBackgroundColor(Color.fromSpecification(attribute));
					break;		
				case Layer.BACKGROUND_COLOR_GRADIENT_HEADER:
					backGround.setBackgroundColorGradient(ColorGradient.fromSpecification(attribute));
					break;
				default:
					
					//Calls method of the base class.
					super.addOrChangeAttribute(attribute);
			}
		}
	}
	
	//method
	/**
	 * Adds or changes the given attributes to the {@link Widget}s of the current {@link GUI}.
	 * 
	 * @param attributes
	 * @return the current {@link GUI}.
	 * @throws InvalidArgumentException if the given attributes are not valid.
	 */
	public final <S extends BaseNode> LG addOrChangeAttributesOfWidgets(
		final IContainer<IContainer<S>> attributes
	) {
		
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
	 * 
	 * @return the current {@link GUI}.
	 */
	@Override
	public final LG clear() {
		
		layers.clear();
		topLayer = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//Handles the case that the current GUI has a background Color.
		if (backGround.hasBackgroundColor()) {
			attributes.addAtEnd(backGround.getBackgroundColor().getSpecificationAs(PascalCaseNameCatalogue.BACKGROUND_COLOR));
		}
		
		//Handles the case that the current GUI has a background ColorGradient.
		if (backGround.hasBackgroundColorGradient()) {
			attributes.addAtEnd(backGround.getBackgroundColorGradient().getSpecificationAs(Layer.BACKGROUND_COLOR_GRADIENT_HEADER));
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the background {@link Color} of the current {@link LayerGUI}.
	 */
	public final Color getBackgroundColor() {
		return backGround.getBackgroundColor();
	}
	
	//method
	/**
	 * @return the {@link CursorIcon} of the current {@link LayerGUI}.
	 */
	public final CursorIcon getCursorIcon() {		
		
		//Handles the case that the current LayerGUI does not contain a Layer.
		if (isEmpty()) {
			return CursorIcon.Arrow;
		}
		
		//Handles the case that the current LayerGUI contains Layers.
		return topLayer.getCursorIcon();
 	}
	
	//method
	/**
	 * @return the painter commands of the current {@link LayerGUI}.
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
		
		final var configurables = new LinkedList<IConfigurableElement<?>>();
		
		for (final var l : layers) {
			if (l.allowesConfiguration()) {
				configurables.addAtEnd(l);
			}
		}
		
		return configurables;
	}
	
	//method
	/**
	 * @return the {@link Layer}s of the current {@link LayerGUI}.
	 */
	public final IContainer<Layer> getRefLayers() {
		return layers;
	}
	
	//method
	/**
	 * @param id
	 * @return the {@link Widget} with the given id from the current {@link LayerGUI}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link LayerGUI}
	 * does not contain a {@link Widget} with the given id.
	 */
	@SuppressWarnings("unchecked")
	public final <W extends Widget<?, ?>> W getRefWidgetById(final String id) {
		return (W)getRefWidgets().getRefFirst(w -> w.hasId(id));
	}
	
	//method
	/**
	 * @return the {@link Widget}s of the current {@link LayerGUI}.
	 */
	public final IContainer<Widget<?, ?>> getRefWidgets() {
		return layers.toFromMany(Layer::getRefWidgets);
	}

	//method
	/**
	 * @return the {@link Widget}s, that are for painting, of the current {@link LayerGUI}.
	 */
	public final IContainer<Widget<?, ?>> getRefWidgetsForPainting() {
		
		//Handles the case that the current LayerGUI does not contain a Layer.
		if (isEmpty()) {
			return new LinkedList<>();
		}
		
		//Handles the case that the current LayerGUI contains Layers.
		return topLayer.getRefWidgetsForPainting();
	}
	
	//method
	/**
	 * @return true if the current {@link LayerGUI} does not contain a {@link Layer}.
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
		backGround.paint(painter);
		layers.forEach(l -> l.paint(painter));
	}
	
	//method
	/**
	 * Removes the given layer from the current {@link LayerGUI}.
	 * 
	 * @param layer
	 * @return the current {@link LayerGUI}.
	 */
	public final LG removeLayer(final Layer layer) {
		
		if (!isTopLayer(layer)) {
			
			layers.remove(layer);
			
			return asConcrete();
		}
		
		return removeTopLayer();
	}
	
	//method
	/**
	 * Removes the top {@link Layer} from the current {@link LayerGUI}.
	 *
	 * @throws EmptyArgumentException if the current {@link GUI} does not contain a layer.
	 */
	public final LG removeTopLayer() {
		
		//Asserts that the current LayerGUI is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		final var previousTopLayer = getRefTopOrBackgroundLayer();
		
		//Handles the case that the current LayerGUI contains 1 layer.
		if (layers.containsOne()) {
			clear();
		}
		
		//Handles the case that the current GUI contains several layers.
		else {
			layers.removeLast();
			topLayer = layers.getRefLast();
		}
		
		getRefTopOrBackgroundLayer().noteMouseMove(
			previousTopLayer.getCursorXPosition(),
			previousTopLayer.getCursorYPosition()
		);
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final LG reset() {
		
		super.reset();
		
		clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final LG resetConfiguration() {
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		
		layers.getRefSelected(Layer::allowesConfiguration).forEach(Layer::resetConfiguration);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the background {@link Color} of the current {@link LayerGUI}.
	 * Removes any former background of the current {@link LayerGUI}.
	 * 
	 * @param backgroundColor
	 * @return the current {@link LayerGUI}.
	 * @throws ArgumentIsNullException if the given backgroundColor is null.
	 */
	public final LG setBackgroundColor(final Color backgroundColor) {
		
		backGround.setBackgroundColor(backgroundColor);
		
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
	 * @return the top {@link Layer} of the current {@link LayerGUI}
	 * if the current {@link LayerGUI} contains {@link Layer}s,
	 * otherwise the background of the current {@link LayerGUI}.
	 */
	protected final Layer getRefTopOrBackgroundLayer() {
		
		//Handles the case that the current LayerGUI does not contain a Layer.
		if (isEmpty()) {
			return backGround;
		}
		
		//Handles the case that the current LayerGUI contains Layers.
		return topLayer;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyPressWhenDoesNotHaveInputTaker(final Key key) {
		getRefKeyBoardForMutating().noteKeyPress(key);
		getRefTopOrBackgroundLayer().noteKeyPress(key);
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
	protected final void noteMouseMoveWhenDoesNotHaveInputTaker(final int viewAreaCursorXPosition, final int viewAreaCursorYPosition) {
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
		}
		else if (topLayer != null) {
			topLayer.recalculate();
		}
	}
	
	//method
	/**
	 * @param layer
	 * @return true if the given layer is the top {@link Layer} of the current {@link LayerGUI}.
	 */
	private boolean isTopLayer(final Layer layer) {
		return (topLayer == layer);
	}
}
