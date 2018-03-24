//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.controllerInterfaces.IController;
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
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.configurationElement.ConfigurationElement;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.data.Title;
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
 * @lines 740
 * @param <G> The type of a GUI.
 */
public abstract class GUI<G extends GUI<G>>
extends ConfigurationElement<G>
implements Clearable<G>, Closable, IRequestableContainer, Refreshable {
	
	//default values
	public static final String DEFAULT_TITLE = "GUI";
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	public static final ContentPosition DEFAULT_CONTENT_POSITION = ContentPosition.Top;
	
	//attribute header
	private static final String ROOT_WIDGET_HEADER = "RootRectangle";

	//command
	private static final String REMOVE_ROOT_WIDGET_COMMAND = "RemoveRootWidget";
	
	//attribute
	private final MutableProperty<Title> title =
	new MutableProperty<Title>(
		Title.TYPE_NAME,
		t -> setTitle(t.getValue()),
		s -> Title.createFromSpecification(s)
	);
	
	//attribute
	private final MutableProperty<Color> backgroundColor =
	new MutableProperty<Color>(
		BackgroundColor.TYPE_NAME,
		bc -> setBackgroundColor(bc),
		s -> Color.createFromSpecification(s)
	);
	
	//attributes
	private ContentPosition contentPosition = DEFAULT_CONTENT_POSITION;
	private boolean closed = false;
				
	//optional attributes
	private Widget<?, ?> rootWidget;
	private IController controller;
	
	//multiple attribute
	private final List<Class<?>> widgetClasses = new List<Class<?>>();
	
	//constructor
	/**
	 * Creates a new GUI.
	 */
	public GUI() {
		
		//Adds widget classes.
		addWidgetClass(
			Area.class,
			Button.class,
			CheckBox.class,
			Console.class,
			HorizontalLine.class,
			HorizontalStack.class,
			Label.class,
			SingleContainer.class,
			TabContainer.class,
			TextBox.class,
			VerticalLine.class,
			VerticalStack.class
		);
	}
	
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
	 * Adds the given widget class to this GUI.
	 * 
	 * @param widgetClass
	 * @return this GUI.
	 * @throws NullArgumentException if the given widget class is null.
	 * @throws InvalidArgumentException
	 * if this GUI can already create a widget of the same type as the given widget class.
	 */
	public final G addWidgetClass(final Class<?> widgetClass) {
		
		//Checks if the given widget class is not null.
		Validator.suppose(widgetClass).thatIsNamed("widget class").isNotNull();

		//Checks if this GUI can already create a widget of the same type as the given widget class.
		if (canCreateWidget(widgetClass.getSimpleName())) {
			throw new InvalidArgumentException(
				new Argument(widgetClass),
				new ErrorPredicate(
					"is invalid because the GUI can already create a widget of the same type"
				)
			);
		}
		
		//Adds the given widget class to this GUI.
		widgetClasses.addAtEnd(widgetClass);
		
		return getInstance();
	}
	
	//method
	/**
	 * Adds the given widget classes to this GUI.
	 * 
	 * @param widgetClasses
	 * @return this dialog.
	 * @throws NullArgumentException if one of the given widget classes is null.
	 * @throws InvalidArgumentException if this GUI already can already create a widget of the same type as one of the given widget classes.
	 */
	public final G addWidgetClass(final Class<?>... widgetClasses) {
		
		//Iterates the given widget classes.
		for (final Class<?> wc : widgetClasses) {
			addWidgetClass(wc);
		}
		
		return getInstance();
	}
	
	//method
	/**
	 * @param type
	 * @return true if this GUI can create a widget of the given type.
	 */
	public final boolean canCreateWidget(final String type) {
		return widgetClasses.contains(wc -> wc.getSimpleName().equals(type));
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
		
		final Widget<?, ?> widget
		= getRefWidgetsRecursively()
		.getRefSelected(w -> w.isEnabled() && w.isUnderCursor())
		.getRefLastOrNull();
		
		if (widget == null) {
			return CursorIcon.Arrow;
		}
		
		return widget.getCursorIcon();
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
	 */
	public final Color getBackgroundColor() {
		return backgroundColor.getValue();
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
	 * @return the configurable elements of this GUI.
	 */
	public final ReadContainer<Configurable> getRefConfigurables() {
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
	 * @param name
	 * @return the widget that has the given name recursively from this GUI.
	 * @throws InvalidArgumentException if this GUI contains no widget with the given name.
	 */
	@SuppressWarnings("unchecked")
	public final <W extends Widget<?, ?>> W getRefWidgetByNameRecursively(final String name) {
		return (W)getRefRootWidget().getRefConfigurablesRecursively().getRefFirst(c -> c.hasName(name));
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
		final List<Widget<?, ?>> widgets = new List<>(getRefWidgets());
		getRefWidgets().forEach(w -> widgets.addAtEnd(w.getRefWidgets()));
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
	 * Lets this dialog note a key typing.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyTyping(final KeyEvent keyEvent) {
		
		getRefWidgetsRecursively()
		.getRefSelected(w -> w.isFocused())
		.forEach(w -> w.noteKeyTyping(keyEvent));
		
		refresh();
	}

	//method
	/**
	 * Lets this GUI note a left mouse button press.
	 */
	public void noteLeftMouseButtonPress() {
		
		getRefWidgetsRecursively()
		.getRefSelected(w -> w.isEnabled())
		.forEach(
			w -> {
				if (!w.isUnderCursor()) {
					if (w.isFocused()) {				
						w.setNormal();
					}
				}
				else {
					
					if (!w.isFocused()) {
						w.setFocused();
						
					}
					
					w.noteLeftMouseButtonPress();
				}
			}
		);
		
		refresh();
	}
	
	//method
	/**
	 * Lets this GUI note a left mouse button release.
	 */
	public void noteLeftMouseButtonRelease() {
		
		getRefWidgetsRecursively()
		.getRefSelected(w -> w.isEnabled() && w.isUnderCursor())
		.forEach(w -> w.noteLeftMouseButtonRelease());
		
		refresh();
	}
	
	//method
	/**
	 * Lets this GUI note a mouse move.
	 */
	public void noteMouseMove() {
				
		if (hasRootWidget()) {
			getRefRootWidget().setCursorPositionFromParentContainer(
				getCursorXPosition(),
				getCursorYPosition()
			);
		}
		
		getRefWidgetsRecursively().getRefSelected(w -> w.isEnabled()).forEach(
			w -> {
				
				if (!w.isUnderCursor()) {				
					if (w.isHovered()) {
						w.setNormal();
					}
				}
				else {
					
					if (w.isNormal()) {
						w.setHovered();
					}
					
					w.noteMouseMove();
				}
			}				
		);
		
		refresh();
	}
	
	//method
	/**
	 * Lets this GUI note a right mouse button press.
	 */
	public void noteRightMouseButtonPress() {
		
		getRefWidgetsRecursively()
		.getRefSelected(w -> w.isEnabled() && w.isUnderCursor())
		.forEach(w -> w.noteRightMouseButtonPress());
		
		refresh();
	}
	
	//method
	/**
	 * Lets this GUI note a right mouse button release.
	 */
	public void noteRightMouseButtonRelease() {
		
		getRefWidgetsRecursively()
		.getRefSelected(w -> w.isEnabled() && w.isUnderCursor())
		.forEach(w -> w.noteRightMouseButtonRelease());
		
		refresh();
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
	 */
	public void reset() {

		//Calls method of the base class.
		super.reset();
		
		setTitle(DEFAULT_TITLE);
		removeRootWidget();
	}
	
	//method
	/**
	 * Resets the configuration of this GUI.
	 */
	public void resetConfiguration() {
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		setContentPosition(DEFAULT_CONTENT_POSITION);
		
		//Handles the case that this GUI has a root widget.
		if (hasRootWidget()) {
			getRefRootWidget().resetConfiguration();
		}
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
	 * 
	 * @param backgroundColor
	 * @return this GUI.
	 * @throws NullArgumentException if the given background color is null.
	 */
	public final G setBackgroundColor(final Color backgroundColor) {
		
		this.backgroundColor.setValue(backgroundColor);
		
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
	public final G setController(IController controller) {
		
		//Checks if the given controller is not null.
		Validator.suppose(controller).thatIsNamed("controller").isNotNull();
		
		//Sets the controller of this GUI.
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
		rootWidget.setGUI(this);
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
		
		this.title.setValue(new Title(title));
		
		return getInstance();
	}
	
	//method
	/**
	 * @return the controller of this GUI.
	 */
	protected final IController getRefController() {
		return controller;
	}
	
	//method
	/**
	 * Creates a new widget the given specification specifies.
	 * The widget and all of its widgets will belong to this GUI.
	 * 
	 * @param specification
	 * @return the created widget.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	protected final Widget<?, ?> createWidget(final Specification specification) {
		
		final Widget<?, ?> widget = createWidget(specification.getHeader());
		widget.setGUI(this);
		widget.addOrChangeAttributes(specification.getRefAttributes());
		
		return widget;		
	}
	
	//method
	/**
	 * @param type
	 * @return a new widget of the given type with default values.
	 * @throws InvalidArgumentException if this GUI cannot create a widget of the given type.
	 */
	protected final Widget<?, ?> createWidget(final String type) {
		try {
			return
			(Widget<?, ?>)
			(widgetClasses.getRefFirst(wc -> wc.getSimpleName().equals(type)).getDeclaredConstructor().newInstance());	
		}
		catch (
			final
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			| NoSuchMethodException
			| SecurityException
			exception
		) {
			throw new InvalidArgumentException(
				new ArgumentName("type"),
				new Argument(type),
				new ErrorPredicate(
					"is invalid because the " + getType() + " cannot create a widget of " + type
				)
			);
		}
	}
}
