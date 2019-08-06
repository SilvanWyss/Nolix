//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentExceptions.EmptyArgumentException;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.statement.Statement;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI_API.CursorIcon;
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
 * @lines 70
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
	public static boolean canCreateWidgetFrom(final DocumentNodeoid specification) {
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
	public static Widget<?, ?> createWidgetFrom(final DocumentNodeoid specification) {
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
	
	//optional attribute
	/**
	 * The top layer of the current {@link GUI} when the current {@link GUI} contains {@link ILayer}s.
	 */
	private Layer topLayer;
	
	//optional attribute
	private final IEventTaker eventTaker;
	
	//constructor
	public LayerGUI(final boolean visible) {
		
		super(visible);
		
		backGround.setParentGUI(this);
		eventTaker = null;
	}
	
	//constructor
	public LayerGUI(final boolean visible, final IEventTaker eventTaker) {
		
		super(visible);
		
		Validator.suppose(eventTaker).thatIsNamed("event taker").isNotNull();
		
		backGround.setParentGUI(this);
		this.eventTaker = eventTaker;
	}
	
	//constructor
	public LayerGUI(final IEventTaker eventTaker) {
		
		super(true);
		
		Validator.suppose(eventTaker).thatIsNamed("event taker").isNotNull();
		
		backGround.setParentGUI(this);
		this.eventTaker = eventTaker;
	}
	
	//constructor
	public LayerGUI(IVisualizer visualizer) {
		
		super(visualizer);
		
		backGround.setParentGUI(this);
		eventTaker = null;
	}
	
	//constructor
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
	 * {@inheritDoc}
	 * 
	 * @throws NullArgumentException if the given rootWidget is null.
	 */
	public final LG addLayerOnTop(final Widget<?, ?> rootWidget) {		
		return addLayerOnTop(new Layer(rootWidget));
	}
	
	
	
	
	
	
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
				
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
	public <S extends DocumentNodeoid> LG addOrChangeInteractionAttributesOfWidgets(
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
	public final List<DocumentNode> getAttributes() {
		
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
	public final IContainer<Statement> getPainterCommands() {
		final var painter = new CanvasGUICommandCreatorPainter();
		paint(painter);
		return painter.getCommands();
	}
	
	//method
	/**
	 * @return the {@link CursorIcon} of the current GUI.
	 */
	public final CursorIcon getCursorIcon() {		
		
		//Handles the case that the current ViewAreaWidthWidget does not contain a Layer.
		if (isEmpty()) {
			return CursorIcon.Arrow;
		}
		
		//Handles the case that the current ViewAreaWidthWidget contains Layers.
		return topLayer.getCursorIcon();
 	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final IContainer<Layer> getRefLayers() {
		return layers;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 *
	 * @throws EmptyArgumentException if the current {@link GUI} does not contain a layer.
	 */
	public LG removeTopLayer() {
		
		//Checks if the current GUI is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		//Handles the case that the current GUI contains 1 layer.
		if (layers.containsOne()) {
			clear();
		}
		
		//Handles the case that the current GUI contains several layers.
		else {
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
	 * @return the interaction attributes of the {@link Widget} of the current {@link GUI}.
	 */
	public IContainer<IContainer<DocumentNode>> getInteractionAttributesOfWidgets() {
		return getRefWidgets().to(w -> w.getInteractionAttributes());
	}
	
	//method
	@Override
	public IContainer<IConfigurableElement<?>> getRefConfigurables() {
		return getRefWidgets().to(w -> w); //TODO
	}
	
	//method
	@SuppressWarnings("unchecked")
	public <W extends Widget<?, ?>> W getRefWidgetByName(final String name) {
		//TODO: Implement layers.getRefFirstFromMany(l -> l.getRefWidgets().getRefFirstOrNull(w -> w.hasName(name));
		return (W)getRefWidgets().getRefFirst(w -> w.hasName(name));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final List<Widget<?, ?>> getRefWidgetsForPainting() {
		
		//Handles the case that the current GUI does not contain a layer.
		if (isEmpty()) {
			return new List<>();
		}
		
		//Handles the case that the current GUI contains layers.
		return topLayer.getRefWidgetsForPainting();
	}
	
	//method
	/**
	 * @return the widgets of the current GUI.
	 */
	public final IContainer<Widget<?, ?>> getRefWidgets() {
		return layers.to(l -> l.getRefRootWidget());
	}
	
	//method
	/**
	 * @return true if the current {@link GUI} does not contain a GUI layer.
	 */
	@Override
	public final boolean isEmpty() {
		return layers.isEmpty();
	}
	
	//method
	@Override
	public final void noteKeyPress(final Key key) {
		
		if (hasEventTaker()) {
			eventTaker.noteKeyPress(key);
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteKeyPress(key);
		}		
	}
	
	//method
	@Override
	public final void noteKeyRelease(final Key key) {
		
		if (hasEventTaker()) {
			eventTaker.noteKeyRelease(key);
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteKeyRelease(key);
		}
	}
	
	//method
	@Override
	public final void noteKeyTyping(final Key key) {
		
		if (hasEventTaker()) {
			eventTaker.noteKeyTyping(key);
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteKeyTyping(key);
		}
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonClick() {
		
		if (hasEventTaker()) {
			eventTaker.noteLeftMouseButtonClick();
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteLeftMouseButtonClick();
		}
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonPress() {
		
		if (hasEventTaker()) {
			eventTaker.noteLeftMouseButtonPress();
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteLeftMouseButtonPress();
		}
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonRelease() {
		
		if (hasEventTaker()) {
			eventTaker.noteLeftMouseButtonRelease();
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteLeftMouseButtonRelease();
		}
	}
	
	//method
	@Override
	public final void noteMouseMove(int cursorXPositionOnViewArea, int cursorYPositionOnViewArea) {
		
		if (hasEventTaker()) {
			eventTaker.noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteMouseMove(cursorXPositionOnViewArea, cursorYPositionOnViewArea);
		}
		
		refresh();
	}
	
	//method
	@Override
	public final void noteMouseWheelClick() {
		
		if (hasEventTaker()) {
			eventTaker.noteMouseWheelClick();
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteMouseWheelClick();
		}
	}
	
	//method
	@Override
	public final void noteMouseWheelPress() {
		
		if (hasEventTaker()) {
			eventTaker.noteMouseWheelPress();
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteMouseWheelPress();
		}
	}
	
	//method
	@Override
	public final void noteMouseWheelRelease() {
		
		if (hasEventTaker()) {
			eventTaker.noteMouseWheelRelease();
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteMouseWheelRelease();
		}
	}
	
	//method
	@Override
	public final void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation) {
		
		if (hasEventTaker()) {
			eventTaker.noteMouseWheelRotationStep(directionOfRotation);
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteMouseWheelRotationStep(directionOfRotation);
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
		}
	}
	
	//method
	@Override
	public final void noteRightMouseButtonClick() {
		
		if (hasEventTaker()) {
			eventTaker.noteRightMouseButtonClick();
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteRightMouseButtonClick();
		}
	}
	
	//method
	@Override
	public final void noteRightMouseButtonPress() {
		
		if (hasEventTaker()) {
			eventTaker.noteRightMouseButtonPress();
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteRightMouseButtonPress();
		}
	}
	
	//method
	@Override
	public final void noteRightMouseButtonRelease() {
		
		if (hasEventTaker()) {
			eventTaker.noteRightMouseButtonRelease();
		}
		
		else {
			getRefTopLayerOrBackgroundLayer().noteRightMouseButtonRelease();
		}
	}
	
	//method
	@Override
	public final void paint(final IPainter painter) {
		backGround.paint(painter);
		layers.forEach(l -> l.paint(painter));
	}

	public void recalculate() {
		layers.forEach(l -> l.recalculate());
	}

	public LG resetConfiguration() {
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		
		if (containsAny()) {
			layers.forEach(l -> l.resetConfiguration());
			topLayer.resetConfiguration();
		}
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the content position of the current {@link GUI}.
	 * 
	 * @param contentPosition
	 * @return the current {@link GUI}.
	 * @throws NullArgumentException if the given contentPosition is null.
	 */
	public LG setContentPosition(final ExtendedContentPosition contentPosition) {
		
		backGround.setContentPosition(contentPosition);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the background {@link Color} of the current {@link GUI}.
	 * Removes any former background of the current {@link GUI}.
	 * 
	 * @param backgroundColor
	 * @return the current {@link GUI}.
	 * @throws NullArgumentException if the given backgroundColor is null.
	 */
	public LG setBackgroundColor(final Color backgroundColor) {
		
		backGround.setBackgroundColor(backgroundColor);
		
		return asConcreteType();
	}
	
	//method
	private Layer getRefTopLayerOrBackgroundLayer() {
		
		if (isEmpty()) {
			return backGround;
		}
		
		return topLayer;
	}
	
	//method
	private boolean hasEventTaker() {
		return (eventTaker != null);
	}
}
