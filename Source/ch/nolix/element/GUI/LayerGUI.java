//package declaration
package ch.nolix.element.GUI;

import ch.nolix.core.chainedNode.ChainedNode;
//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;
import ch.nolix.core.invalidArgumentExceptions.EmptyArgumentException;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.node.Node;
import ch.nolix.core.node.BaseNode;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI_API.CursorIcon;
import ch.nolix.element.GUI_API.ILayerGUI;
import ch.nolix.element.GUI_API.Widget;
import ch.nolix.element.base.MultiProperty;
import ch.nolix.element.baseAPI.IConfigurableElement;
import ch.nolix.element.baseGUI_API.IEventTaker;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.containerWidgets.Accordion;
import ch.nolix.element.containerWidgets.FloatContainer;
import ch.nolix.element.containerWidgets.Grid;
import ch.nolix.element.containerWidgets.SingleContainer;
import ch.nolix.element.containerWidgets.TabContainer;
import ch.nolix.element.elementEnums.ContentPosition;
import ch.nolix.element.elementEnums.DirectionOfRotation;
import ch.nolix.element.elementEnums.ExtendedContentPosition;
import ch.nolix.element.input.Key;
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

//abstract class
/**
 * A {@link LayerGUI} is a {@link GUI} that can contain several {@link ILayer}s, that are stacked.
 * 
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 860
 * @param <LG> The type of a {@link LayerGUI}.
 */
public abstract class LayerGUI<LG extends LayerGUI<LG>> extends GUI<LG> implements Clearable<LG> {
	
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
	 * @throws NullArgumentException if the given widgetClass is null.
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
	 * @throws NullArgumentException if one of the given widgetClasses is null.
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
		s -> Layer.createFromSpecification(s),
		l -> l.getSpecification()
	);
	
	//optional attributes
	private Layer topLayer;
	private final IEventTaker eventTaker;
	
	//constructor
	/**
	 * Creates a new {@link LayerGUI}.
	 * The {@link LayerGUI} will be visible if the given visible flag is true.
	 * 
	 * @param visible
	 */
	public LayerGUI(final boolean visible) {
		
		super(visible);
		
		backGround.setParentGUI(this);
		eventTaker = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link LayerGUI}.
	 * The {@link LayerGUI} will be visible if the given visible flag is true.
	 * The {@link LayerGUI} will forward its received events to the given eventTaker.
	 * 
	 * @param visible
	 * @param eventTaker
	 * @throws NullArgumentException if the given eventTaker is null.
	 */
	public LayerGUI(final boolean visible, final IEventTaker eventTaker) {
		
		super(visible);
		
		Validator.suppose(eventTaker).thatIsNamed("event taker").isNotNull();
		
		backGround.setParentGUI(this);
		this.eventTaker = eventTaker;
	}
	
	//constructor
	/**
	 * Creates a new {@link LayerGUI}.
	 * The {@link LayerGUI} will be visible.
	 * The {@link LayerGUI} will forward its received events to the given eventTaker.
	 * 
	 * @param visible
	 * @param eventTaker
	 * @throws NullArgumentException if the given eventTaker is null.
	 */
	public LayerGUI(final IEventTaker eventTaker) {
		
		super(true);
		
		Validator.suppose(eventTaker).thatIsNamed("event taker").isNotNull();
		
		backGround.setParentGUI(this);
		this.eventTaker = eventTaker;
	}
	
	//constructor
	/**
	 * Creates a new {@link LayerGUI}.
	 * The {@link LayerGUI} will be visible and have the given visualizer..
	 * 
	 * @param visible
	 * @param eventTaker
	 * @throws NullArgumentException if the given visualizer is null.
	 */
	public LayerGUI(IVisualizer visualizer) {
		
		super(visualizer);
		
		backGround.setParentGUI(this);
		eventTaker = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link LayerGUI}.
	 * The {@link LayerGUI} will be visible and have the given visualizer.
	 * The {@link LayerGUI} will forward its received events to the given eventTaker.
	 * 
	 * @param visible
	 * @param eventTaker
	 * @throws NullArgumentException if the given visualizer is null.
	 * @throws NullArgumentException if the given eventTaker is null.
	 */
	public LayerGUI(IVisualizer visualizer, IEventTaker eventTaker) {
		
		super(visualizer);
		
		Validator.suppose(eventTaker).thatIsNamed("event taker").isNotNull();
		
		backGround.setParentGUI(this);
		this.eventTaker = eventTaker;
	}

	//method
	/**
	 * Adds a new {@link Layer} on the top of the current {@link LayerGUI}.
	 * The {@link Layer} will have the given contentPosition and rootWidget.
	 * 
	 * @param contentPosition.
	 * @param rootWidget
	 * @return the current {@link LayerGUI}.
	 * @throws NullArgumentException if the given contentPosition is null.
	 * @throws NullArgumentException if the given rootWidget is null.
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
	 * @throws NullArgumentException if the given layer is null.
	 */
	public LG addLayerOnTop(final Layer layer) {
		
		//Checks if the given layer is not null.
		Validator.suppose(layer).thatIsNamed(VariableNameCatalogue.LAYER).isNotNull();
		
		layer.setParentGUI(this);
		layers.addValue(layer);
		topLayer = layer;	
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Adds a new {@link Layer} on the top of the current {@link ILayerGUI}.
	 * The {@link Layer} will have the given rootWidget.
	 * 
	 * @param rootWidget
	 * @return the current {@link ILayerGUI}.
	 * @throws NullArgumentException if the given rootWidget is null.
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
					backGround.setBackgroundColor(Color.createFromSpecification(attribute));
					break;		
				case Layer.BACKGROUND_COLOR_GRADIENT_HEADER:
					backGround.setBackgroundColorGradient(ColorGradient.createFromSpecification(attribute));
					break;
				case ContentPosition.TYPE_NAME:
					backGround.setContentPosition(ExtendedContentPosition.createFromSpecification(attribute));
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
		
		return asConcreteType();
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
		
		return asConcreteType();
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
	public final List<Node> getAttributes() {
		
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
		
		attributes.addAtEnd(backGround.getContentPosition().getSpecificationAs(ContentPosition.TYPE_NAME));
		
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
		return getRefWidgets().to(w -> w); //TODO
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
	 * @param name
	 * @return the {@link Widget} with the given name from the current {@link LayerGUI}.
	 * @throws ArgumentMissesAttributeException if the current {@link LayerGUI}
	 * does not contain a {@link Widget} with the given name.
	 */
	@SuppressWarnings("unchecked")
	public <W extends Widget<?, ?>> W getRefWidgetByName(final String name) {
		//TODO: Implement toFromManyAtTime().
		return (W)getRefWidgets().getRefFirst(w -> w.hasName(name));
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
	public final List<Widget<?, ?>> getRefWidgetsForPainting() {
		
		//Handles the case that the current LayerGUI does not contain a Layer.
		if (isEmpty()) {
			return new List<>();
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
			eventTaker.noteKeyPress(key);
		}		
		else {
			getRefTopLayerOrBackgroundLayer().noteKeyPress(key);
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
			eventTaker.noteKeyRelease(key);
		}		
		else {
			getRefTopLayerOrBackgroundLayer().noteKeyRelease(key);
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
			eventTaker.noteKeyTyping(key);
		}		
		else {
			getRefTopLayerOrBackgroundLayer().noteKeyTyping(key);
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
			eventTaker.noteLeftMouseButtonClick();
		}		
		else {
			getRefTopLayerOrBackgroundLayer().noteLeftMouseButtonClick();
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
			eventTaker.noteLeftMouseButtonPress();
		}
		else {
			getRefTopLayerOrBackgroundLayer().noteLeftMouseButtonPress();
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
			eventTaker.noteLeftMouseButtonRelease();
		}
		else {
			getRefTopLayerOrBackgroundLayer().noteLeftMouseButtonRelease();
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseMove(int cursorXPositionOnViewArea, int cursorYPositionOnViewArea) {
		
		if (hasEventTaker()) {
			eventTaker.noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		}
		else {
			getRefTopLayerOrBackgroundLayer().noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
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
			eventTaker.noteMouseWheelClick();
		}
		else {
			getRefTopLayerOrBackgroundLayer().noteMouseWheelClick();
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
			eventTaker.noteMouseWheelPress();
		}
		else {
			getRefTopLayerOrBackgroundLayer().noteMouseWheelPress();
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
			eventTaker.noteMouseWheelRelease();
		}
		else {
			getRefTopLayerOrBackgroundLayer().noteMouseWheelRelease();
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
			eventTaker.noteMouseWheelRotationStep(directionOfRotation);
		}
		else {
			getRefTopLayerOrBackgroundLayer().noteMouseWheelRotationStep(directionOfRotation);
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		if (hasEventTaker()) {
			eventTaker.noteResize(viewAreaWidth, viewAreaHeight);
			refresh();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonClick() {
		if (hasEventTaker()) {
			eventTaker.noteRightMouseButtonClick();
		}
		else {
			getRefTopLayerOrBackgroundLayer().noteRightMouseButtonClick();
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
			eventTaker.noteRightMouseButtonPress();
		}
		else {
			getRefTopLayerOrBackgroundLayer().noteRightMouseButtonPress();
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
			eventTaker.noteRightMouseButtonRelease();
		}
		else {
			getRefTopLayerOrBackgroundLayer().noteRightMouseButtonRelease();
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
		
		//Checks if the current LayerGUI is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		//Handles the case that the current LayerGUI contains 1 layer.
		if (layers.containsOne()) {
			clear();
		}
		
		//Handles the case that the current GUI contains several layers.
		else {
			//TODO: Extend MultiProperty.
			topLayer = layers.getRefAt(layers.getSize() - 1);
			layers.removeValue(layers.getRefAt(layers.getSize()));
		}
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LG reset() {
		
		super.reset();
		
		clear();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LG resetConfiguration() {
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		
		layers.forEach(l -> l.resetConfiguration());
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the content position of the current {@link LayerGUI}.
	 * 
	 * @param contentPosition
	 * @return the current {@link LayerGUI}.
	 * @throws NullArgumentException if the given contentPosition is null.
	 */
	public LG setContentPosition(final ExtendedContentPosition contentPosition) {
		
		backGround.setContentPosition(contentPosition);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the background {@link Color} of the current {@link LayerGUI}.
	 * Removes any former background of the current {@link LayerGUI}.
	 * 
	 * @param backgroundColor
	 * @return the current {@link LayerGUI}.
	 * @throws NullArgumentException if the given backgroundColor is null.
	 */
	public LG setBackgroundColor(final Color backgroundColor) {
		
		backGround.setBackgroundColor(backgroundColor);
		
		return asConcreteType();
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
	 * otherwise the backround of the current {@link LayerGUI}.
	 */
	private Layer getRefTopLayerOrBackgroundLayer() {
		
		//Handles the case that the current LayerGUI does not contain a Layer.
		if (isEmpty()) {
			return backGround;
		}
		
		//Handles the case that the current LayerGUI contains Layers.
		return topLayer;
	}
	
	//method
	/**
	 * @return true if the current {@link LayerGUI} has a {@link IEventTaker}.
	 */
	private boolean hasEventTaker() {
		return (eventTaker != null);
	}
}
