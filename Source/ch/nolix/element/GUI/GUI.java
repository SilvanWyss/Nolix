//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI_API.CursorIcon;
import ch.nolix.element.GUI_API.IGUI;
import ch.nolix.element.GUI_API.IGUIController;
import ch.nolix.element.GUI_API.IGUILayer;
import ch.nolix.element.GUI_API.Widget;
import ch.nolix.element.base.MultiProperty;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.baseAPI.IConfigurableElement;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.configuration.ConfigurationElement;
import ch.nolix.element.containerWidget.Accordion;
import ch.nolix.element.containerWidget.FloatContainer;
import ch.nolix.element.containerWidget.Grid;
import ch.nolix.element.containerWidget.SingleContainer;
import ch.nolix.element.containerWidget.TabContainer;
import ch.nolix.element.elementEnums.ContentPosition;
import ch.nolix.element.elementEnums.ExtendedContentPosition;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.widgets.Area;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.Checkbox;
import ch.nolix.element.widgets.Console;
import ch.nolix.element.widgets.Downloader;
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
 * A {@link GUI} contains several {@link GUILayers}, that are stacked.
 * A {@link GUI} is clearable.
 * A {@link GUI} is closable.
 * A {@link GUI} is configurable.
 * A {@link GUI} is refreshable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 800
 * @param <G> The type of a {@link GUI}.
 */
public abstract class GUI<G extends GUI<G>> extends ConfigurationElement<G> implements IGUI<G> {
	
	//default values
	public static final String DEFAULT_TITLE = "GUI";
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//static attribute
	private static final WidgetCreator widgetCreator =
	new WidgetCreator(
		Accordion.class,
		Area.class,
		Button.class,
		Checkbox.class,
		Console.class,
		Downloader.class,
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
	 * @param specification
	 * @return true if a {@link GUI} can create a {@link Widget} from the given specification.
	 */
	public static boolean canCreateWidgetFrom(final DocumentNodeoid specification) {
		return widgetCreator.canCreateWidgetFrom(specification);
	}
	
	//static method
	/**
	 * @param type
	 * @return true if a {@link GUI} can create a {@link Widget} of the given type.
	 */
	public static boolean canCreateWidgetOf(final String type) {
		return widgetCreator.canCreateWidgetOf(type);
	}
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Widget} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Widget<?, ?> createWidget(final DocumentNodeoid specification) {
		return widgetCreator.createWidget(specification);
	}
	
	//static method
	/**
	 * @param type
	 * @return a new {@link Widget} of the given type with.
	 * @throws InvalidArgumentException if a {@link GUI} cannot create a {@link Widget} of the given type.
	 */
	public static Widget<?, ?> createWidget(final String type) {
		return widgetCreator.createWidget(type);
	}
	
	//static method
	/**
	 * Registers the given widgetClass.
	 * 
	 * @param widgetClass
	 * @throws NullArgumentException if the given widgetClass is null.
	 * @throws InvalidArgumentException
	 * if a {@link GUI} contains already a {@link Widget} class wit the same type as the given widgetClass.
	 */
	public static void registerWidgetClass(final Class<Widget<?, ?>> widgetClass) {
		widgetCreator.registerWidgetClass(widgetClass);
	}
	
	//static method
	/**
	 * Registers the given widgetClasses
	 * 
	 * @param widgetClasses
	 * @throws NullArgumentException if one of the given widgetClasses is null.
	 * @throws InvalidArgumentException
	 * if a {@link GUI} contains already a {@link Widget} class with the same type as one of the given widgetClasses.
	 */
	public static void registerWidgetClass(final Class<?>... widgetClasses) {
		widgetCreator.addWidgetClass(widgetClasses);
	}
	
	//attribute
	private boolean closed = false;
	
	//attribute
	private final MutableProperty<String> title =
	new MutableProperty<>(
		PascalCaseNameCatalogue.TITLE,
		t -> setTitle(t),
		s -> s.getOneAttributeAsString(),
		t -> new DocumentNode(PascalCaseNameCatalogue.TITLE, t)
	);
	
	//attribute
	private final MultiProperty<IGUILayer<?>> layers =
	new MultiProperty<>(
		PascalCaseNameCatalogue.LAYER,
		l -> addLayerOnTop(l),
		s -> GUILayer.createFromSpecification(s),
		l -> l.getSpecification()
	);
	
	//attribute
	private final GUILayer backGround = new GUILayer();
	
	//optional attribute
	private IGUIController controller;
	
	//optional attribute
	/**
	 * The top layer of the current {@link GUI} when the current {@link GUI} contains layers.
	 */
	private IGUILayer<?> topLayer;
	
	public GUI() {
		backGround.setParentGUI(this);
	}
	
	//method
	/**
	 * Adds a new layer on the top of the current {@link GUI}.
	 * The layer will have the given contentPosition and rootWidget.
	 * 
	 * @param contentPosition.
	 * @param rootWidget
	 * @return the current {@link GUI}.
	 * @throws NullArgumentException if the given contentPosition is null.
	 * @throws NullArgumentException if the given rootWidget is null.
	 */
	public G addLayerOnTop(ExtendedContentPosition contentPosition, Widget<?, ?> rootWidget) {		
		return addLayerOnTop(new GUILayer(contentPosition, rootWidget));
	}

	//method
	/**
	 * Adds the given layer on the top of the current {@link GUI}.
	 * 
	 * @param layer
	 * @return the current {@link GUI}.
	 * @throws NullArgumentException if the given layer is null.
	 */
	@Override
	public G addLayerOnTop(final IGUILayer<?> layer) {
		
		//Checks if the given layer is not null.
		Validator.suppose(layer).thatIsNamed(VariableNameCatalogue.LAYER).isNotNull();
		
		layer.setParentGUI(this);
		layers.addValue(layer);
		topLayer = layer;
		
		noteMouseMove();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 * 
	 * @throws NullArgumentException if the given rootWidget is null.
	 */
	public final G addLayerOnTop(final Widget<?, ?> rootWidget) {		
		return addLayerOnTop(new GUILayer(rootWidget));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Handles the case that the given attribute specifies a Widget.
		if (canCreateWidgetOf(attribute.getHeader())) {
			
			//Handles the case that the current GUI does not contain a layer.
			if (isEmpty()) {
				addLayerOnTop(new GUILayer());
			}
			
			layers.getRefFirst().setRootWidget(createWidget(attribute));
		}
		
		//Handles the case that the given attribute does not specify a widget.
			//Enumerates the header of the given attribute.
			switch (attribute.getHeader()) {				
				case PascalCaseNameCatalogue.BACKGROUND_COLOR:
					backGround.setBackgroundColor(Color.createFromSpecification(attribute));
					break;		
				case GUILayer.BACKGROUND_COLOR_GRADIENT_HEADER:
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
	
	//method
	/**
	 * Adds or changes the given interaction attributes to the {@link Widget}s of the current {@link GUI}.
	 * 
	 * @param interactionAttributesOfWidgets
	 * @return the current {@link GUI}.
	 * @throws InvalidArgumentException if the given interactionAttributesOfWidgets are not valid.
	 */
	public <S extends DocumentNodeoid> G addOrChangeInteractionAttributesOfWidgetsRecursively(
		final IContainer<IContainer<S>> interactionAttributesOfWidgets
	) {
		
		final var iterator = interactionAttributesOfWidgets.iterator();
		
		getRefWidgetsRecursively().forEach(w -> w.addOrChangeAttributes(iterator.next()));
		
		if (iterator.hasNext()) {
			throw new InvalidArgumentException(
				"interaction attributes of Widgets",
				interactionAttributesOfWidgets,
				"contains more than " + getRefWidgetsRecursively().getSize() + " Widgets"
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
	public final G clear() {
		
		layers.clear();
		topLayer = null;
		
		return asConcreteType();
	}
	
	@Override
	public final void close() {
		closed = true;
		noteClosing();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsElement(final String name) {
		return getRefWidgetsRecursively().contains(c -> c.hasName(name));
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
			attributes.addAtEnd(backGround.getBackgroundColorGradient().getSpecificationAs(GUILayer.BACKGROUND_COLOR_GRADIENT_HEADER));
		}
		
		attributes.addAtEnd(backGround.getContentPosition().getSpecificationAs(ContentPosition.TYPE_NAME));
		
		return attributes;
	}
	
	//method
	/**
	 * @return the {@link CursorIcon} of the current GUI.
	 */
	public final CursorIcon getCursorIcon() {
		
		//Handles the case that the current GUI does not contain a layer.
		if (isEmpty()) {
			return CursorIcon.Arrow;
		}
		
		//Handles the case that the current GUI contains layers.
		return topLayer.getCursorIcon();
 	}
	
	//abstract method
	/**
	 * @return the x-position of the cursor on the current {@link GUI}.
	 */
	public abstract int getCursorXPosition();
	
	//abstract method
	/**
	 * @return the y-position of the cursor on the current {@link GUI}.
	 */
	public abstract int getCursorYPosition();
	
	//abstract method
	/**
	 * @return the height of the current GUI.
	 */
	public abstract int getHeight();
	
	//method
	/**
	 * @return the interaction attributes of all {@link Widget} of the current {@link GUI}.
	 */
	public IContainer<IContainer<DocumentNode>> getInteractionAttributesOfWidgetsRecursively() {
		return getRefWidgetsRecursively().to(w -> w.getInteractionAttributes());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<IConfigurableElement<?>> getRefConfigurables() {
		return new List<>(getRefWidgets());
	}
	
	//method
	/**
	 * @return the controller of the current {@link GUI}.
	 * @throws ArgumentMissesAttributeException if the current {@link GUI} does not have a controller.
	 */
	@Override
	public final IGUIController getRefController() {
		
		//Checks if the current GUI has a controller.
		//For a better performance, this implementation does not use all comfortable methods.
		if (controller == null) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.CONTROLLER);
		}
		
		return controller;
	}
	
	//method
	/**
	 * @return the triggerable {@link Widget}s of the current widget recursively.
	 */
	public final List<Widget<?, ?>> getRefTriggerableWidgetsRecursively() {
		
		if (isEmpty()) {
			return new List<>();
		}
		
		return topLayer.getRefTriggerableWidgetsRecursively();
	}
		
	//method
	/**
	 * @param name
	 * @return the widget that has the given name recursively from the current {@link GUI}.
	 * @throws InvalidArgumentException if the current {@link GUI} does not contain a widget with the given name.
	 */
	@SuppressWarnings("unchecked")
	public final <W extends Widget<?, ?>> W getRefWidgetByNameRecursively(final String name) {
		return (W)getRefWidgetsRecursively().getRefFirst(c -> c.hasName(name));
	}
	
	//method
	/**
	 * @return the widgets of the current GUI.
	 */
	public final List<Widget<?, ?>> getRefWidgets() {
		return layers.to(l -> l.getRefRootWidget());
	}
	
	//method
	/**
	 * @return the widgets of the current GUI recursively.
	 */
	@Override
	public final List<Widget<?, ?>> getRefWidgetsRecursively() {
		return layers.toFromMany(l -> l.getRefRootWidget().getChildWidgetsRecursively());
	}
	
	//method
	/**
	 * @return the title of the current GUI.
	 */
	public final String getTitle() {
		return title.getValue();
	}
	
	//abstract method
	/**
	 * @return the width of the current GUI.
	 */
	public abstract int getWidth();
	
	//method
	/**
	 * @return true if the current {@link GUI} has a controller.
	 */
	@Override
	public final boolean hasController() {
		return (controller != null);
	}
	
	//method
	/**
	 * @return true if the current {@link GUI} has the given role.
	 */
	@Override
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isClosed() {
		return closed;
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
	/**
	 * Lets the current {@link GUI} note a key press.
	 * 
	 * @param keyEvent
	 */
	public final void noteKeyPress(final KeyEvent keyEvent) {
		
		getRefWidgetsRecursively().forEach(w -> w.noteAnyKeyPress(keyEvent));
		
		refresh();
	}
	
	//method
	/**
	 * Lets the current {@link GUI} note a key typing.
	 * 
	 * @param keyEvent
	 */
	public final void noteKeyTyping(final KeyEvent keyEvent) {
		
		getRefWidgetsRecursively().forEach(w -> w.noteAnyKeyTyping(keyEvent));
		
		refresh();
	}

	//method
	/**
	 * Lets the current {@link GUI} note a left mouse button press.
	 */
	public final void noteLeftMouseButtonPress() {
		
		getRefTriggerableWidgetsRecursively().forEach(w -> w.noteAnyLeftMouseButtonPress());
		
		refresh();
	}
	
	//method
	/**
	 * Lets the current {@link GUI} note a left mouse button release.
	 */
	public final void noteLeftMouseButtonRelease() {
		
		getRefTriggerableWidgetsRecursively().forEach(w -> w.noteAnyLeftMouseButtonRelease());
		
		refresh();
	}
	
	//method
	/**
	 * Lets the current {@link GUI} note a mouse move.
	 */
	public final void noteMouseMove() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		if (topLayer != null && topLayer.containsAny()) {
			
			final var topLayerRootWidget = topLayer.getRefRootWidget();
			
			topLayerRootWidget.setParentCursorPosition(getCursorXPosition(), getCursorYPosition());
			topLayerRootWidget.noteAnyMouseMoveRecursively();
		}		
		
		refresh();
	}
	
	//method
	/**
	 * Lets the current {@link GUI} note the given mouse wheel rotation steps.
	 * The given mouseWheelRotationSteps is positive if the mouse wheel has been rotated forward.
	 * The given mouseWheelRotationSteps is negative if the mouse wheel has been rotated backward.
	 * 
	 * @param rotationSteps
	 */
	public final void noteMouseWheelRotationSteps(final int mouseWheelRotationSteps) {
		
		getRefTriggerableWidgetsRecursively().forEach(w -> w.noteAnyMouseWheelRotationSteps(mouseWheelRotationSteps));
		
		refresh();
	}
	
	//method
	/**
	 * Lets the current {@link GUI} note a right mouse button press.
	 */
	public void noteRightMouseButtonPress() {
		
		getRefTriggerableWidgetsRecursively().forEach(w -> w.noteAnyRightMouseButtonPress());
		
		refresh();
	}
	
	//method
	/**
	 * Lets the current {@link GUI} note a right mouse button release.
	 */
	public void noteRightMouseButtonRelease() {
		
		getRefTriggerableWidgetsRecursively().forEach(w -> w.noteAnyRightMouseButtonRelease());
		
		refresh();
	}
	
	//method
	/**
	 * Paints the current GUI using the given painter.
	 * 
	 * @param painter
	 */
	public final void paint(final IPainter painter) {
		
		backGround.paint(painter);
		
		layers.forEach(l -> l.paint(painter));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void refresh() {
		layers.forEach(l -> l.recalculate());
		paint();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 *
	 * @throws EmptyArgumentException if the current {@link GUI} does not contain a layer.
	 */
	@Override
	public G removeTopLayer() {
		
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
		
		noteMouseMove();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public G reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setTitle(DEFAULT_TITLE);
		clear();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public G resetConfiguration() {
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		
		if (containsAny()) {
			layers.forEach(l -> l.resetConfiguration());
			topLayer.resetConfiguration();
		}
		
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
	public G setBackgroundColor(final Color backgroundColor) {
		
		backGround.setBackgroundColor(backgroundColor);
		
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
	public G setContentPosition(final ExtendedContentPosition contentPosition) {
		
		backGround.setContentPosition(contentPosition);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the controller of the current {@link GUI}.
	 * 
	 * @param controller
	 * @return the current {@link GUI}.
	 * @throws NullArgumentException if the given controller is null.
	 */
	public final G setController(final IGUIController controller) {
		
		Validator
		.suppose(controller)
		.thatIsNamed(VariableNameCatalogue.CONTROLLER)
		.isNotNull();
		
		this.controller = controller;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the title of the current GUI.
	 * 
	 * @param title
	 * @return the current {@link GUI}.
	 * @throws NullArgumentException if the given title is null.
	 * @throws InvalidArgumentException if the given title is blank.
	 */
	public final G setTitle(final String title) {
		
		//Checks if the given title is not null or blank.
		Validator.suppose(title).thatIsNamed(VariableNameCatalogue.TITLE).isNotBlank();
		
		this.title.setValue(title);
		
		return asConcreteType();
	}
	
	//abstract method
	/**
	 * Paints the current {@link GUI}.
	 */
	public abstract void paint();
	
	//abstract method
	/**
	 * Lets the current {@link GUI} note a closing.
	 */
	protected abstract void noteClosing();

	//method
	/**
	 * Lets the current {@link GUI} note a resizing.
	 */
	protected final void noteResizing() {
		refresh();
	}
}
