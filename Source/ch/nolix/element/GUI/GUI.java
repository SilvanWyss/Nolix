//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.entity.MutableOptionalProperty;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.interfaces.Closable;
import ch.nolix.core.interfaces.IRequestableContainer;
import ch.nolix.core.interfaces.Refreshable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.specificationInterfaces.Configurable;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.configurationElement.ConfigurationElement;
import ch.nolix.element.core.NonEmptyText;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ArgumentName;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A GUI is clearable.
 * A GUI is closable.
 * A GUI is configurable.
 * A GUI is refreshable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 840
 * @param <G> The type of a GUI.
 */
public abstract class GUI<G extends GUI<G>>
extends ConfigurationElement<G>
implements Clearable<G>, Closable, IRequestableContainer, Refreshable {
	
	//default values
	public static final String DEFAULT_TITLE = "GUI";
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	public static final ContentPosition DEFAULT_CONTENT_POSITION = ContentPosition.Top;
	
	//constants
	private static final String BACKGROUND_COLOR_GRADIENT_HEADER = "BackgroundColorGradient";
	private static final String ROOT_WIDGET_HEADER = "RootRectangle";
	private static final String REMOVE_ROOT_WIDGET_COMMAND = "RemoveRootWidget";
	
	//static attribute
	private static final WidgetCreator widgetCreator = new WidgetCreator(
		Area.class,
		Button.class,
		Checkbox.class,
		Console.class,
		Grid.class,
		HorizontalLine.class,
		HorizontalStack.class,
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
	 * @return true if a widget from the given specification can be created.
	 */
	public static boolean canCreateWidget(final Specification specification) {
		return canCreateWidget(specification.getHeader());
	}
	
	//static method
	/**
	 * @param type
	 * @return true if a widget of the given type can be created.
	 */
	public static boolean canCreateWidget(final String type) {
		return widgetCreator.canCreateWidget(type);
	}
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Widget} from the given specificatio.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Widget<?, ?> createWidget(final Specification specification) {
		return widgetCreator.createWidget(specification);
	}
	
	//static method
	/**
	 * @param type
	 * @return a new {@link Widget} of the given type with default values.
	 * @throws InvalidArgumentException
	 * if a {@link Widget} of the given type cannot be created.
	 */
	public static Widget<?, ?> createWidget(final String type) {
		return widgetCreator.createWidget(type);
	}
	
	//static method
	/**
	 * Registers the given widget class
	 * 
	 * @param widgetClass
	 * @throws NullArgumentException if the given widget class is null.
	 * @throws InvalidArgumentException
	 * if there can already be created a widget of the same type as the given widget class.
	 */
	public static void registerWidgetClass(final Class<?> widgetClass) {
		widgetCreator.addWidgetClass(widgetClass);
	}
	
	//static method
	/**
	 * Registers the given widget classes
	 * 
	 * @param widgetClasses
	 * @throws NullArgumentException if one of the given widget classes is null.
	 * @throws InvalidArgumentException
	 * if there can already be created a widget of the same type as one of the given widget classes.
	 */
	public static void registerWidgetClass(final Class<?>... widgetClasses) {
		widgetCreator.addWidgetClass(widgetClasses);
	}
	
	//attribute
	private final MutableProperty<NonEmptyText> title =
	new MutableProperty<NonEmptyText>(
		PascalCaseNameCatalogue.TITLE,
		t -> setTitle(t.getValue()),
		s -> NonEmptyText.createFromSpecification(s)
	);
	
	//attribute
	private final MutableOptionalProperty<Color> backgroundColor =
	new MutableOptionalProperty<Color>(
		PascalCaseNameCatalogue.BACKGROUND_COLOR,
		bc -> setBackgroundColor(bc),
		s -> Color.createFromSpecification(s)
	);
	
	//attribute
	private final MutableOptionalProperty<ColorGradient> backgroundColorGradient =
	new MutableOptionalProperty<ColorGradient>(
		BACKGROUND_COLOR_GRADIENT_HEADER, 
		bcg -> setBackgroundColorGradient(bcg),
		s -> ColorGradient.createFromSpecification(s)
	);
	
	//attributes
	private ContentPosition contentPosition = DEFAULT_CONTENT_POSITION;
	private boolean closed = false;
				
	//optional attributes
	private Widget<?, ?> rootWidget;
	private IGUIController controller;
	
	//method
	/**
	 * Adds or changes the given attribute to this GUI.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Handles the case that the given attribute specifies a widget.
		if (canCreateWidget(attribute.getHeader())) {
			setRootWidget(createWidget(attribute));
			return;
		}
		
		//Handles the case that the given attribute specificies no widget.
		
			//Enumerates the header of the given attribute.
			switch (attribute.getHeader()) {
				case ContentPosition.TYPE_NAME:
					setContentPosition(
						ContentPosition.valueOf(attribute.getOneAttributeAsString())
					);
					break;
				default:
					
					//Calls method of the base class.
					super.addOrChangeAttribute(attribute);
			}
	}
	
	//method
	/**
	 * Adds or changes the given interaction attributes to the widgets of the current {@link GUI}.
	 * 
	 * @param interactionAttributesOfWidgets
	 * @return the current {@link GUI}.
	 * @throws InvalidArgumentException if the given interaction attributes of widgets is not valid.
	 */
	public <S extends Specification> G addOrChangeInteractionAttributesOfWidgetsRecursively(
		final IContainer<IContainer<S>> interactionAttributesOfWidgets
	) {
		final var iterator = interactionAttributesOfWidgets.iterator();
		
		getRefWidgetsRecursively().forEach(w -> w.addOrChangeAttributes(iterator.next()));
		
		if (iterator.hasNext()) {
			throw new InvalidArgumentException(
				new ArgumentName("interaction attributes per widget"),
				new Argument(interactionAttributesOfWidgets),
				new ErrorPredicate("contains more elements than this GUI contains widgets")
			);
		}
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the root widget of this GUI.
	 * 
	 * @return this GUI.
	 */
	public final G clear() {
		
		removeRootWidget();
		
		return getInstance();
	}
	
	//abstract method
	/**
	 * Closes this GUI.
	 */
	public void close() {
		closed = true;
	};
	
	//method
	/**
	 * @return true if this GUI contains an element with the given name.
	 * @param name
	 */
	public final boolean containsElement(String name) {
		return getRefConfigurablesRecursively().contains(c -> c.hasName(name));
	}
	
	//method
	/**
	 * @return the active cursor icon of this GUI.
	 */
	public CursorIcon getActiveCursorIcon() {
		
		if (hasRootWidget() && getRefRootWidget().isUnderCursor()) {
			return getRefRootWidget().getActiveCursorIcon();
		}
		
		return CursorIcon.Arrow;	
 	}
	
	//method
	/**
	 * @return the attributes of GUI.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the case that the content position of this GUI is not its default content position.
		if (contentPosition != DEFAULT_CONTENT_POSITION) {
			attributes.addAtEnd(contentPosition.getSpecification());
		}
		
		//Handles the case that this GUI has a root widget.
		if (hasRootWidget()) {
			attributes.addAtEnd(getRefRootWidget().getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the background color of this GUI.
	 * @throws UnexistingAttributeException if this GUI has no background color.
	 */
	public final Color getBackgroundColor() {
		return backgroundColor.getValue();
	}
	
	//method
	/**
	 * @return the background color gradient of this GUI.
	 * @throws UnexistingAttributeException if this GUI has no background color gradient.
	 */
	public final ColorGradient getBackgroundColorGradient() {
		return backgroundColorGradient.getValue();
	}
	
	//method
	/**
	 * @return the content position of this GUI.
	 */
	public final ContentPosition getContentPosition() {
		return contentPosition;
	}
	
	//abstract method
	/**
	 * @return the x-position of the cursor on this GUI.
	 */
	public abstract int getCursorXPosition();
	
	//abstract method
	/**
	 * @return the y-position of the cursor on this GUI.
	 */
	public abstract int getCursorYPosition();
	
	//method
	/**
	 * The interaction attributes of a {@link GUI} are those a user can change.
	 * 
	 * @return the interaction attributes of the current {@link GUI}.
	 */
	public List<StandardSpecification> getInteractionAttributes() {
		return new List<StandardSpecification> ();
	}
	
	//method
	/**
	 * @return the interaction attributes of all {@link Widget} of the current {@link GUI}.
	 */
	public IContainer<IContainer<StandardSpecification>> getInteractionAttributesOfWidgetsRecursively() {
		return getRefWidgetsRecursively().to(w -> w.getInteractionAttributes());
	}
	
	//method
	/**
	 * @return the configurable elements of this GUI.
	 */
	public final ReadContainer<Configurable<?>> getRefConfigurables() {
		return new ReadContainer<>(getRefWidgets().to(w -> w));		
	}
	
	//method
	/**
	 * @return the root widget of this GUI.
	 * @throws UnexistingAttributeException if this GUI has no root widget.
	 */
	@SuppressWarnings("unchecked")
	public final <W extends Widget<?, ?>> W getRefRootWidget() {
		
		//Checks if this GUI has a root widget.
		if (!hasRootWidget()) {
			throw new UnexistingAttributeException(this, ROOT_WIDGET_HEADER);
		}
		
		return (W)rootWidget;
	}
	
	//method
	/**
	 * @param index
	 * @return the widget with the given index recursively from this GUI.
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 * @throws UnexistingAttributeException if this GUI contains no widget with the given index.
	 */
	@SuppressWarnings("unchecked")
	public final <W extends Widget<?, ?>> W getRefWidgetByIndexRecursively(final int index) {
		return (W)getRefWidgetsRecursively().getRefAt(index);
	}
	
	//method
	/**
	 * @param name
	 * @return the widget that has the given name recursively from this GUI.
	 * @throws InvalidArgumentException if this GUI contains no widget with the given name.
	 */
	@SuppressWarnings("unchecked")
	public final <W extends Widget<?, ?>> W getRefWidgetByNameRecursively(final String name) {
		return (W)getRefWidgetsRecursively().getRefFirst(c -> c.hasName(name));
	}
	
	//method
	/**
	 * @return the widgets of this GUI.
	 */
	public final ReadContainer<Widget<?, ?>> getRefWidgets() {
		
		final List<Widget<?, ?>> widgets = new List<Widget<?, ?>>();
		
		//Handles the case that this GUI has a root widget.
		if (hasRootWidget()) {
			final Widget<?, ?> rootWidget = getRefRootWidget();
			widgets.addAtEnd(rootWidget);//.addAtEnd(getRefRootWidget().getRefWidgets());
		}
		
		return new ReadContainer<Widget<?, ?>>(widgets);
	}
	
	//method
	/**
	 * @return the widgets of this GUI recursively.
	 */
	public final List<Widget<?, ?>> getRefWidgetsRecursively() {
		
		final var widgets = new List<Widget<?, ?>>();
		
		//Handles the case that this GUI has a root widget.
		if (hasRootWidget()) {
			widgets.addAtEnd((Widget<?, ?>)getRefRootWidget());
			widgets.addAtEnd(getRefRootWidget().getRefWidgetsRecursively());
		}
		
		return widgets;
	}
	
	//method
	/**
	 * @return the title of this GUI.
	 */
	public final String getTitle() {
		return title.getValue().getValue();
	}
	
	//method
	/**
	 * @return true if this GUI has a background color.
	 */
	public final boolean hasBackgroundColor() {
		return backgroundColor.containsAny();
	}
	
	//method
	/**
	 * @return true if this GUI has a background color gradient.
	 */
	public final boolean hasBackgroundColorGradient() {
		return backgroundColorGradient.containsAny();
	}
	
	//method
	/**
	 * @return true if this GUI has the given role.
	 */
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * @return true if this GUI has a root widget.
	 */
	public final boolean hasRootWidget() {
		return (rootWidget != null);
	}
	
	//method
	/**
	 * @return true if this GUI isclosed.
	 */
	public final boolean isClosed() {
		return closed;
	}
	
	//method
	/**
	 * @return true if this GUI contains no widget.
	 */
	public final boolean isEmpty() {
		return !hasRootWidget();
	}
	
	//method
	/**
	 * Lets this GUI note a key press.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyPress(final KeyEvent keyEvent) {
		
		getRefWidgetsRecursively()
		.forEach(w -> w.noteAnyKeyPress(keyEvent));
		
		refresh();
	}
	
	//method
	/**
	 * Lets this GUI note a key typing.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyTyping(final KeyEvent keyEvent) {
		
		getRefWidgetsRecursively()
		.forEach(w -> w.noteAnyKeyTyping(keyEvent));
		
		refresh();
	}

	//method
	/**
	 * Lets this GUI note a left mouse button press.
	 */
	public void noteLeftMouseButtonPress() {
		
		getRefWidgetsRecursively()
		.forEach(w -> w.noteAnyLeftMouseButtonPress());
		
		refresh();
	}
	
	//method
	/**
	 * Lets this GUI note a left mouse button release.
	 */
	public void noteLeftMouseButtonRelease() {
		
		getRefWidgetsRecursively().forEach(w -> w.noteAnyLeftMouseButtonRelease());
		
		refresh();
	}
	
	//method
	/**
	 * Lets this GUI note a mouse move.
	 */
	public void noteMouseMove() {
				
		if (hasRootWidget()) {
			
			getRefRootWidget().setParentCursorPosition(
				getCursorXPosition(), getCursorYPosition()
			);
			
			getRefRootWidget().noteAnyMouseMoveRecursively();
		}
		
		refresh();
	}
	
	//method
	/**
	 * Lets this GUI note the given mouse wheel rotation steps.
	 * The given number of mouse wheel rotation steps is positive if the mouse wheel was rotated forward.
	 * The given number mouse wheel rotation steps is negative if the mouse wheel was rotated backward.
	 * 
	 * @param rotationSteps
	 */
	public final void noteMouseWheelRotationSteps(final int mouseWheelRotationSteps) {
		
		getRefWidgetsRecursively()
		.forEach(w -> w.noteAnyMouseWheelRotationSteps(mouseWheelRotationSteps));
		
		refresh();
	}
	
	//method
	/**
	 * Lets this GUI note a right mouse button press.
	 */
	public void noteRightMouseButtonPress() {
		
		getRefWidgetsRecursively()
		.forEach(w -> w.noteAnyRightMouseButtonPress());
		
		refresh();
	}
	
	//method
	/**
	 * Lets this GUI note a right mouse button release.
	 */
	public void noteRightMouseButtonRelease() {
		
		getRefWidgetsRecursively()
		.forEach(w -> w.noteAnyRightMouseButtonRelease());
		
		refresh();
	}
	
	//method
	/**
	 * Removes the background color and the background color gradient of this GUI.
	 * 
	 * @return this GUI.
	 */
	public final G removeBackground() {
		
		backgroundColor.clear();
		backgroundColorGradient.clear();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the root widget of this GUI.
	 */
	public final void removeRootWidget() {
		rootWidget = null;
	}
	
	//method
	/**
	 * Resets this GUI.
	 * 
	 * @return this GUI.
	 */
	public G reset() {
		
		setTitle(DEFAULT_TITLE);
		removeRootWidget();
		
		//Calls method of the base class.
		return super.reset();
	}
	
	//method
	/**
	 * Resets the configuration of this GUI.
	 * 
	 * @return this GUI.
	 */
	public G resetConfiguration() {
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		setContentPosition(DEFAULT_CONTENT_POSITION);
		
		//Handles the case that this GUI has a root widget.
		if (hasRootWidget()) {
			getRefRootWidget().resetConfiguration();
		}
		
		return getInstance();
	}
	
	//method
	/**
	 * Lets this GUI run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	public void run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case ROOT_WIDGET_HEADER:
				getRefRootWidget().run(command.getRefNextStatement());
				break;
			case REMOVE_ROOT_WIDGET_COMMAND:
				removeRootWidget();
				break;
			default:
				
				//Calls method of the base class.
				super.run(command);
		}
	}
	
	//method
	/**
	 * Sets the background color of this GUI.
	 * Removes the background color gradient of this GUI.
	 * 
	 * @param backgroundColor
	 * @return this GUI.
	 * @throws NullArgumentException if the given background color is null.
	 */
	public final G setBackgroundColor(final Color backgroundColor) {
		
		removeBackground();
		
		this.backgroundColor.setValue(backgroundColor);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the background color gradient of this GUI.
	 * Removes the background color of this GUI.
	 * 
	 * @param backgroundColorGradient
	 * @return this GUI.
	 * @throws NullArgumentException if the given background color gradient is null.
	 */
	public final G setBackgroundColorGradient(final ColorGradient backgroundColorGradient) {
		
		removeBackground();
		
		this.backgroundColorGradient.setValue(backgroundColorGradient);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the configuration of this GUI.
	 * Will refresh this GUI.
	 * 
	 * @param configuration
	 * @return this GUI.
	 */
	public final G setConfiguration(final StandardConfiguration configuration) {
		
		//Calls method of the base class.
		super.setConfiguration(configuration);
		
		refresh();
		
		return getInstance();
	}

	//method
	/**
	 * Sets the content position of this GUI.
	 * 
	 * @param contentPosition
	 * @return this GUI.
	 * @throws NullArgumentException if the given content position is null.
	 */
	public final G setContentPosition(final ContentPosition contentPosition) {
		
		//Checks if the given content position is not null.
		Validator
		.suppose(contentPosition)
		.thatIsOfType(ContentPosition.class)
		.isNotNull();
		
		//Sets the content position of this GUI.
		this.contentPosition = contentPosition;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the controller of this GUI.
	 * 
	 * @param controller
	 * @return this GUI.
	 * @throws NullArgumentException if the given controller is null.
	 */
	public final G setController(final IGUIController controller) {
		
		Validator
		.suppose(controller)
		.thatIsNamed(VariableNameCatalogue.CONTROLLER)
		.isNotNull();
		
		this.controller = controller;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the root widget of this GUI.
	 * 
	 * @param rootWidget
	 * @throws NullArgumentException if the given root widget is null.
	 * @throws InvalidArgumentException if the given root widget belongs already to an other GUI. 
	 */
	public final G setRootWidget(final Widget<?, ?> rootWidget) {
		
		//Checks if the given root widget is not null.
		Validator.suppose(rootWidget).thatIsNamed("root widget").isNotNull();
		
		//Sets the root widget of this GUI.
		rootWidget.setParentGUI(this);
		this.rootWidget = rootWidget;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the title of this GUI.
	 * 
	 * @param title
	 * @return this GUI.
	 * @throws NullArgumentException if the given title is null.
	 * @throws EmptyArgumentException if the given title is empty.
	 */
	public final G setTitle(final String title) {
		
		this.title.setValue(new NonEmptyText(title));
		
		return getInstance();
	}
	
	//package-visible method
	/**
	 * @return the controller of this GUI.
	 * @throws UnexistingAttributeException if this GUI has no controller.
	 */
	final IGUIController getRefController() {
		
		supposeHasController();
		
		return controller;
	}
	
	//package-visible method
	/**
	 * @return true if this GUI has a controller.
	 */
	final boolean hasController() {
		return (controller != null);
	}

	//method
	/**
	 * @throws UnexistingAttributeException if this GUI has no controller.
	 */
	private void supposeHasController() {
		if (!hasController()) {
			throw
			new UnexistingAttributeException(this, VariableNameCatalogue.CONTROLLER);
		}
	}
}
