//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.states.Visibility;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MultiProperty;
import ch.nolix.element.baseAPI.IConfigurableElement;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.containerWidgets.Accordion;
import ch.nolix.element.containerWidgets.FloatContainer;
import ch.nolix.element.containerWidgets.Grid;
import ch.nolix.element.containerWidgets.SingleContainer;
import ch.nolix.element.containerWidgets.TabContainer;
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.elementEnums.ExtendedContentPosition;
import ch.nolix.element.inputs.IInputTaker;
import ch.nolix.element.inputs.Key;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.widgets.Area;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.Checkbox;
import ch.nolix.element.widgets.Console;
import ch.nolix.element.widgets.Downloader;
import ch.nolix.element.widgets.DropdownMenu;
import ch.nolix.element.widgets.HorizontalLine;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.ImageWidget;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.SelectionMenu;
import ch.nolix.element.widgets.TextBox;
import ch.nolix.element.widgets.VerticalLine;
import ch.nolix.element.widgets.VerticalStack;

//class
/**
 * A {@link LayerGUI} is a {@link GUI} that can contain several {@link ILayer}s, that are stacked.
 * 
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 880
 * @param <LG> The type of a {@link LayerGUI}.
 */
public abstract class LayerGUI<LG extends LayerGUI<LG>> extends GUI<LG> implements Clearable<LG>{
	
	//default value
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//static attribute
	private static final WidgetProvider widgetProvider =
	new WidgetProvider(
		Accordion.class,
		Area.class,
		Button.class,
		Checkbox.class,
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
		VerticalStack.class
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
		l -> addLayerOnTop(l),
		s -> Layer.fromSpecification(s),
		l -> l.getSpecification()
	);
	
	//optional attributes
	private Layer topLayer;
	private final IInputTaker inputTaker;
	
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
	public LayerGUI(final IInputTaker inputTaker) {
		
		super(Visibility.VISIBLE);
		
		Validator.assertThat(inputTaker).thatIsNamed("event taker").isNotNull();
		
		backGround.setParentGUI(this);
		this.inputTaker = inputTaker;
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
		inputTaker = null;
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
	public LayerGUI(IVisualizer visualizer, IInputTaker inputTaker) {
		
		super(visualizer);
		
		Validator.assertThat(inputTaker).thatIsNamed("event taker").isNotNull();
		
		backGround.setParentGUI(this);
		this.inputTaker = inputTaker;
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
		inputTaker = null;
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
	public LayerGUI(final Visibility visibility, final IInputTaker inputTaker) {
		
		super(visibility);
		
		Validator.assertThat(inputTaker).thatIsNamed("input taker").isNotNull();
		
		backGround.setParentGUI(this);
		this.inputTaker = inputTaker;
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
	public LG addLayerOnTop(final ExtendedContentPosition contentPosition, final Widget<?, ?> rootWidget) {		
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
	public LG addLayerOnTop(final Layer layer) {
		
		//Asserts that the given layer is not null.
		Validator.assertThat(layer).thatIsNamed(VariableNameCatalogue.LAYER).isNotNull();
		
		layer.setParentGUI(this);
		layers.add(layer);
		topLayer = layer;	
		
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
	public void addOrChangeAttribute(final BaseNode attribute) {
				
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
	 * Adds or changes the given interaction attributes to the {@link Widget}s of the current {@link GUI}.
	 * 
	 * @param interactionAttributesOfWidgets
	 * @return the current {@link GUI}.
	 * @throws InvalidArgumentException if the given interactionAttributesOfWidgets are not valid.
	 */
	public <S extends BaseNode> LG addOrChangeInteractionAttributesOfWidgets(
		final IContainer<IContainer<S>> interactionAttributesOfWidgets
	) {
		
		final var iterator = interactionAttributesOfWidgets.iterator();
		
		getRefWidgets().forEach(w -> w.addOrChangeAttributes(iterator.next()));
		
		if (iterator.hasNext()) {
			throw new InvalidArgumentException(
				"interaction attributes of Widgets",
				interactionAttributesOfWidgets,
				"contains more than " + getRefWidgets().getSize() + " Widgets"
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
	public boolean containsElement(final String name) {
		return layers.contains(l -> l.containsElement(name));
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
	 * @return the {@link CursorIcon} of the current GUI.
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
	 * @return the interaction attributes of the {@link Widget}s of the current {@link GUI}.
	 */
	public IContainer<IContainer<Node>> getInteractionAttributesOfWidgets() {
		return getRefWidgets().to(w -> w.getInteractionAttributes());
	}
	
	//method
	/**
	 * @return the painter commands of the current {@link LayerGUI}.
	 */
	public final IContainer<ChainedNode> getPaintCommands() {
		
		final var painter = new CanvasGUICommandCreatorPainter();
		paint(painter);
		
		return painter.getCommands();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<IConfigurableElement<?>> getRefConfigurables() {
		
		final var configurables = new LinkedList<IConfigurableElement<?>>();
		
		for (final var l : layers) {
			configurables.addAtEnd(l);
		}
		
		for (final var w : getRefWidgets()) {
			configurables.addAtEnd(w);
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
	public <W extends Widget<?, ?>> W getRefWidgetById(final String id) {
		return (W)getRefWidgets().getRefFirst(w -> w.hasId(id));
	}
	
	//method
	/**
	 * @return the {@link Widget}s of the current {@link LayerGUI}.
	 */
	public final IContainer<Widget<?, ?>> getRefWidgets() {
		return layers.toFromMany(l -> l.getRefWidgets());
	}

	//method
	/**
	 * @return the {@link Widget}s, that are for painting, of the current {@link LayerGUI}.
	 */
	public final LinkedList<Widget<?, ?>> getRefWidgetsForPainting() {
		
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
	public final void noteKeyPress(final Key key) {
		if (hasEventTaker()) {
			inputTaker.noteKeyPress(key);
		}		
		else {
			getRefKeyBoard().noteKeyPress(key);
			getRefTopOrBackgroundLayer().noteKeyPress(key);
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyRelease(final Key key) {		
		if (hasEventTaker()) {
			inputTaker.noteKeyRelease(key);
		}		
		else {
			getRefKeyBoard().noteKeyRelease(key);
			getRefTopOrBackgroundLayer().noteKeyRelease(key);
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyTyping(final Key key) {		
		if (hasEventTaker()) {
			inputTaker.noteKeyTyping(key);
		}		
		else {
			getRefTopOrBackgroundLayer().noteKeyTyping(key);
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonClick() {		
		if (hasEventTaker()) {
			inputTaker.noteLeftMouseButtonClick();
		}		
		else {
			getRefTopOrBackgroundLayer().noteLeftMouseButtonClick();
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonPress() {
		if (hasEventTaker()) {
			inputTaker.noteLeftMouseButtonPress();
		}
		else {
			getRefTopOrBackgroundLayer().noteLeftMouseButtonPress();
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonRelease() {
		if (hasEventTaker()) {
			inputTaker.noteLeftMouseButtonRelease();
		}
		else {
			getRefTopOrBackgroundLayer().noteLeftMouseButtonRelease();
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseMove(final int viewAreaCursorXPosition, final int viewAreaCursorYPosition) {
		if (hasEventTaker()) {
			inputTaker.noteMouseMove(viewAreaCursorXPosition, viewAreaCursorYPosition);
		}
		else {
			setViewAreaCursorXPosition(viewAreaCursorXPosition);
			setViewAreaCursorYPosition(viewAreaCursorYPosition);
			getRefTopOrBackgroundLayer().noteMouseMove(viewAreaCursorXPosition, viewAreaCursorYPosition);
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelClick() {
		if (hasEventTaker()) {
			inputTaker.noteMouseWheelClick();
		}
		else {
			getRefTopOrBackgroundLayer().noteMouseWheelClick();
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelPress() {
		if (hasEventTaker()) {
			inputTaker.noteMouseWheelPress();
		}
		else {
			getRefTopOrBackgroundLayer().noteMouseWheelPress();
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRelease() {
		if (hasEventTaker()) {
			inputTaker.noteMouseWheelRelease();
		}
		else {
			getRefTopOrBackgroundLayer().noteMouseWheelRelease();
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation) {
		if (hasEventTaker()) {
			inputTaker.noteMouseWheelRotationStep(directionOfRotation);
		}
		else {
			getRefTopOrBackgroundLayer().noteMouseWheelRotationStep(directionOfRotation);
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		if (hasEventTaker()) {
			inputTaker.noteResize(viewAreaWidth, viewAreaHeight);
			refresh();
		}
		else {
			setViewAreaWidth(viewAreaWidth);
			setViewAreaHeight(viewAreaHeight);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonClick() {
		if (hasEventTaker()) {
			inputTaker.noteRightMouseButtonClick();
		}
		else {
			getRefTopOrBackgroundLayer().noteRightMouseButtonClick();
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonPress() {
		if (hasEventTaker()) {
			inputTaker.noteRightMouseButtonPress();
		}
		else {
			getRefTopOrBackgroundLayer().noteRightMouseButtonPress();
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonRelease() {
		if (hasEventTaker()) {
			inputTaker.noteRightMouseButtonRelease();
		}
		else {
			getRefTopOrBackgroundLayer().noteRightMouseButtonRelease();
			refresh();
		}
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
	 * {@inheritDoc}
	 */
	@Override
	public void recalculate() {
		if (containsAny()) {
			topLayer.recalculate();
		}
	}
	
	//method
	/**
	 * Removes the top {@link Layer} from the current {@link LayerGUI}.
	 *
	 * @throws EmptyArgumentException if the current {@link GUI} does not contain a layer.
	 */
	public LG removeTopLayer() {
		
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
	public LG reset() {
		
		super.reset();
		
		clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LG resetConfiguration() {
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		
		layers.forEach(l -> l.resetConfiguration());
		
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
	public LG setBackgroundColor(final Color backgroundColor) {
		
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
	protected Layer getRefTopOrBackgroundLayer() {
		
		//Handles the case that the current LayerGUI does not contain a Layer.
		if (isEmpty()) {
			return backGround;
		}
		
		//Handles the case that the current LayerGUI contains Layers.
		return topLayer;
	}
	
	//method
	/**
	 * @return true if the current {@link LayerGUI} has a {@link IInputTaker}.
	 */
	private boolean hasEventTaker() {
		return (inputTaker != null);
	}
}
