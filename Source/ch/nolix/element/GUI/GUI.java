//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.event.KeyEvent;


//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.controller.ILevel1Controller;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.ErrorPredicate;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.exception.UnexistingPropertyException;
import ch.nolix.common.interfaces.Clearable;
import ch.nolix.common.interfaces.IRequestableContainer;
import ch.nolix.common.specification.Configurable;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.zetaValidator.ZetaValidator;
import ch.nolix.element.basic.ConfigurationElement;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.data.Title;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 610
 * @param <G> - The type of a GUI.
 */
public abstract class GUI<G extends GUI<G>>
extends ConfigurationElement<G>
implements Clearable, IRequestableContainer {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "GUI";
	
	//default values
	public static final String DEFAULT_TITLE = "GUI";
	public static final ContentPosition DEFAULT_CONTENT_POSITION = ContentPosition.LeftTop;
	
	//attribute header
	private static final String ROOT_WIDGET_HEADER = "RootRectangle";

	//command
	private static final String REMOVE_ROOT_WIDGET_COMMAND = "RemoveRootRectangle";
		
	//attributes
	private Title title = new Title(DEFAULT_TITLE);
	private BackgroundColor backgroundColor = new BackgroundColor();
	private ContentPosition contentPosition = DEFAULT_CONTENT_POSITION;
				
	//optional attributes
	private Widget<?, ?> rootWidget;
	private ILevel1Controller controller;
	
	//multiple attribute
	private final List<Class<?>> widgetClasses = new List<Class<?>>();
	
	//constructor
	/**
	 * Creates new GUI with default values.
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
		
		//Handles the case if the given attribute specifies a widget.
		if (canCreateWidget(attribute.getHeader())) {
			setRootWidget(createAndAddWidget(attribute));
			return;
		}
		
		//Handles the case if the given attribute specifices no widget.
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case Title.SIMPLE_CLASS_NAME:
				setTitle(attribute.getOneAttributeToString());
				break;
			case BackgroundColor.SIMPLE_CLASS_NAME:
				setBackgroundColor(new BackgroundColor(attribute.getOneAttributeToString()));
				break;
			case ContentPosition.SIMPLE_CLASS_NAME:
				setContentPosition(ContentPosition.valueOf(attribute.getOneAttributeToString()));
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
	 * @throws InvalidArgumentException if this GUI already can already create a widget of the same type as the given widget class.
	 */
	@SuppressWarnings("unchecked")
	public final G addWidgetClass(final Class<?> widgetClass) {
		
		//Checks if the given widget class is not null.
		ZetaValidator.supposeThat(widgetClass).thatIsNamed("widget class").isNotNull();

		//Checks if this GUI can already create a widget of the same type as the given widget class.
		if (canCreateWidget(widgetClass.getSimpleName())) {
			throw new InvalidArgumentException(
				new Argument(widgetClass),
				new ErrorPredicate("is invalid because the GUI can already create a widget of the same type")
			);
		}
		
		//Adds the given widget class to this GUI.
		widgetClasses.addAtEnd(widgetClass);
		
		return (G)this;
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
	@SuppressWarnings("unchecked")
	public final G addWidgetClass(final Class<?>... widgetClasses) {
		
		//Iterates the given widget classes.
		for (final Class<?> wc : widgetClasses) {
			addWidgetClass(wc);
		}
		
		return (G)this;
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
	 */
	public final void clear() {
		removeRootWidget();
	}
	
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
	 * @return the attributes of GUI.
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class.
		final List<Specification> attributes = super.getAttributes();
		
		attributes.addAtEnd(title.getSpecification(), backgroundColor.getSpecification());
		
		if (contentPosition != DEFAULT_CONTENT_POSITION) {
			attributes.addAtEnd(contentPosition.getSpecification());
		}
		
		if (hasRootWidget()) {
			attributes.addAtEnd(getRefRootWidget().getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the background color of this GUI.
	 */
	public final BackgroundColor getBackgroundColor() {
		return backgroundColor;
	}
	
	//method
	/**
	 * @return the content position of this GUI.
	 */
	public final ContentPosition getContentPosition() {
		return contentPosition;
	}
	
	//method
	/**
	 * @return the configurable elements of this GUI.
	 */
	public final List<Configurable> getRefConfigurables() {
		
		final List<Configurable> configurableElements = new List<Configurable>();
		
		if (hasRootWidget()) {
			final Widget<?, ?> rootWidget = getRefRootWidget();
			configurableElements.addAtEnd(rootWidget);
		}
		
		return configurableElements;
	}
	
	//abstract method
	/**
	 * @return the frame context of this GUI.
	 */
	//public abstract FrameContext getRefFrameContext();
	
	//method
	/**
	 * @return the root widget of this GUI.
	 * @throws UnexistingPropertyException if this GUI has no root widget.
	 */
	@SuppressWarnings("unchecked")
	public final <W extends Widget<?, ?>> W getRefRootWidget() {
		
		//Checks if this GUI has a root widget.
		if (!hasRootWidget()) {
			throw new UnexistingPropertyException(this, ROOT_WIDGET_HEADER);
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
	 * @return the title of this GUI.
	 */
	public final String getTitle() {
		return title.getValue();
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
	 * @return true if this GUI has no root widget.
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
		if (hasRootWidget()) {
			getRefRootWidget().noteKeyTyping(keyEvent);
		}
	}
	
	//method
	/**
	 * Lets this GUI note a left mouse button press.
	 */
	public void noteLeftMouseButtonPress() {	
		if (hasRootWidget()) {
			getRefRootWidget().noteLeftMouseButtonPress();
		}
	}
	
	//method
	/**
	 * Lets this GUI note a left mouse button release.
	 */
	public void noteLeftMouseButtonRelease() {
		if (hasRootWidget()) {
			getRefRootWidget().noteLeftMouseButtonRelease();
		}
	}
	
	//method
	/**
	 * Lets this GUI note a mouse move.
	 */
	public void noteMouseMove() {
		
		if (hasRootWidget()) {
			getRefRootWidget().setRelativeMousePosition(getMouseXPosition(), getMouseYPosition());
			getRefRootWidget().noteMouseMove();
		}
		
		if (!hasRootWidget() || !getRefRootWidget().isPointed()) {
			applyCursorIcon(CursorIcon.Arrow);
		}
	}
	
	//method
	/**
	 * Lets this GUI note a right mouse button press.
	 */
	public void noteRightMouseButtonPress() {
		
		if (hasRootWidget()) {
			getRefRootWidget().noteRightMouseButtonPress();
		}
	}
	
	//method
	/**
	 * Lets this GUI note a right mouse button release.
	 */
	public void noteRightMouseButtonRelease() {
		
		if (hasRootWidget()) {
			getRefRootWidget().noteRightMouseButtonRelease();
		}
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
		resetConfiguration();
	}
	
	//method
	/**
	 * Resets the configuration of this GUI.
	 */
	public void resetConfiguration() {
		
		setBackgroundColor(new BackgroundColor());
		setContentPosition(DEFAULT_CONTENT_POSITION);
		
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
				getRefRootWidget().run(command.getNextStatement());
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
	@SuppressWarnings("unchecked")
	public final G setBackgroundColor(final BackgroundColor backgroundColor) {
		
		//Checks if the given background color is not null.
		ZetaValidator
		.supposeThat(backgroundColor)
		.thatIsInstanceOf(BackgroundColor.class)
		.isNotNull();
		
		//Sets the background color of this GUI.
		this.backgroundColor = backgroundColor;
		
		return (G)this;
	}

	//method
	/**
	 * Sets the content position of this GUI.
	 * 
	 * @param contentPosition
	 * @return this GUI.
	 * @throws NullArgumentException if the given content position is null.
	 */
	@SuppressWarnings("unchecked")
	public final G setContentPosition(final ContentPosition contentPosition) {
		
		//Checks if the given content position is not null.
		ZetaValidator
		.supposeThat(contentPosition)
		.thatIsInstanceOf(ContentPosition.class)
		.isNotNull();
		
		//Sets the content position of this GUI.
		this.contentPosition = contentPosition;
		
		return (G)this;
	}
	
	//method
	/**
	 * Sets the controller of this GUI.
	 * 
	 * @param controller
	 * @return this GUI.
	 * @throws NullArgumentException if the given controller is null.
	 */
	@SuppressWarnings("unchecked")
	public final G setController(ILevel1Controller controller) {
		
		//Checks if the given controller is not null.
		ZetaValidator.supposeThat(controller).thatIsNamed("controller").isNotNull();
		
		//Sets the controller of this GUI.
		this.controller = controller;
		
		return (G)this;
	}
	
	//method
	/**
	 * Sets the root widget of this GUI.
	 * 
	 * @param rootWidget
	 * @throws NullArgumentException if the given root widget is null.
	 * @throws InvalidArgumentException if the given root widget belongs already to an other GUI. 
	 */
	@SuppressWarnings("unchecked")
	public final G setRootWidget(final Widget<?, ?> rootWidget) {
		
		//Checks if the given root widget is not null.
		ZetaValidator.supposeThat(rootWidget).thatIsNamed("root widget").isNotNull();
		
		//Sets the root widget of this GUI.
		rootWidget.setDialog(this);
		this.rootWidget = rootWidget;
		
		return (G)this;
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
	@SuppressWarnings("unchecked")
	public final G setTitle(final String title) {
		
		this.title.setValue(title);
		
		return (G)this;
	}
	
	//abstract method
	/**
	 * Lets this GUI show the given cursor icon.
	 * 
	 * @param cursorIcon
	 */
	public abstract void applyCursorIcon(final CursorIcon cursorIcon);
	
	//method
	/**
	 * Creates a widget the given specification specifies and adds it to this GUI.
	 * 
	 * @param specification
	 * @return the widget the given specification specifies.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	protected final Widget<?, ?> createAndAddWidget(final Specification specification) {
		
		final Widget<?, ?> widget = createWidget(specification.getHeader());
		widget.setDialog(this);
		widget.addOrChangeAttributes(specification.getRefAttributes());
		
		return widget;		
	}
	
	//method
	/**
	 * @return the x-position of the mouse within this GUI.
	 */
	protected abstract int getMouseXPosition();
	
	//method
	/**
	 * @return the y-position of the mouse within this GUI.
	 */
	protected abstract int getMouseYPosition();
	
	//method
	/**
	 * @return the controller of this GUI.
	 */
	protected final ILevel1Controller getRefController() {
		return controller;
	}
	
	//method
	/**
	 * @param type
	 * @return a new widget of the given type with default values.
	 * @throws InvalidArgumentException if this GUI cannot create a widget of the given type.
	 */
	private Widget<?, ?> createWidget(final String type) {
		try {
			return (Widget<?, ?>)(widgetClasses.getRefFirst(rc -> rc.getSimpleName().equals(type)).newInstance());
		}
		catch (final Exception exception) {
			throw new RuntimeException(exception);
		}
	}
}
