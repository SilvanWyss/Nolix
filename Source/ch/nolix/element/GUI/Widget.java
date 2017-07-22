//package declaration
package ch.nolix.element.GUI;

//Java imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.specificationInterfaces.Configurable;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.basic.ConfigurableElement;

//abstract class
/**
 * A widget is an element on a GUI.
 * A widget has a width and height.
 * A widget is a configurable element.
 * The methods concerning the position of this widget are not public.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1100
 * @param <W> The type of a widget.
 * @param <WS> The type of the widget structures of a widget.
 */
//public abstract class Widget<WS extends WidgetStructure<WS>, W extends Widget<WS, W>>
//extends ConfigurableElement<W> {
public abstract class Widget<W extends Widget<W, WS>, WS extends WidgetStructure<WS>>
extends ConfigurableElement<W> {
	
	//simple class name
	public static final String SIMPLE_CLASS_NAME = "Widget";
	
	//attribute headers
	private static final String STATE ="State";
	private static final String LEFT_MOUSE_BUTTON_PRESS_COMMAND_HEADER = "LeftMouseButtonPressCommand";
	private static final String LEFT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER = "LeftMouseButtonReleaseCommand";
	private static final String RIGHT_MOUSE_BUTTON_PRESS_COMMAND_HEADER = "RightMouseButtonPressCommand";
	private static final String RIGHT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER = "RightMouseButtonReleaseCommand";
	
	//attribute header prefixes
	private static final String NORMAL = "Normal";
	private static final String FOCUS = "Focus";
	private static final String HOVER = "Hover";
		
	//attribute
	/**
	 * The GUI this widget belongs to.
	 */
	private GUI<?> GUI;
	
	//attributes
	private int xPositionOnContainer = 0;
	private int yPositionOnContainer = 0;
	private WidgetState state = WidgetState.Normal;
	private CursorIcon cursorIcon = CursorIcon.Arrow;
	private final WS normalStructure;
	private final WS hoverStructure;
	private final WS focusStructure;
	private int mouseXPosition;
	private int mouseYPosition;
	
	//optional attributes
	private Statement leftMouseButtonPressCommand;
	private Statement leftMouseButtonReleaseCommand;
	private Statement rightMouseButtonPressCommand;
	private Statement rightMouseButtonReleaseCommand;
	
	//constructor
	/**
	 * Creates new widget.
	 */
	public Widget() {	
		
		normalStructure = createWidgetStructure();
		hoverStructure = createWidgetStructure();
		focusStructure = createWidgetStructure();
		
		getRefHoverStructure().setNormalStructure(getRefNormalStructure());
		getRefFocusStructure().setNormalStructure(getRefNormalStructure());
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this widget.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case STATE:
				setState(WidgetState.valueOf(attribute.getOneAttributeToString()));
				break;
			case CursorIcon.SIMPLE_CLASS_NAME:
				setCursorIcon(CursorIcon.valueOf(attribute.getOneAttributeToString()));
				break;
			case LEFT_MOUSE_BUTTON_PRESS_COMMAND_HEADER:
				setLeftMouseButtonPressCommand(attribute.getOneAttributeToString());
				break;
			case LEFT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER:
				setLeftMouseButtonReleaseCommand(attribute.getOneAttributeToString());
				break;
			case RIGHT_MOUSE_BUTTON_PRESS_COMMAND_HEADER:
				setRightMouseButtonPressCommand(attribute.getOneAttributeToString());
				break;
			case RIGHT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER:
				setRightMouseButtonReleaseCommand(attribute.getOneAttributeToString());
				break;
			default:
				if (attribute.getHeader().startsWith(NORMAL)) {
					StandardSpecification temp = attribute.getCopy();
					temp.setHeader(attribute.getHeader().substring(NORMAL.length()));
					getRefNormalStructure().addOrChangeAttribute(temp);
				}
				else if (attribute.getHeader().startsWith(HOVER)) {
					StandardSpecification temp = attribute.getCopy();
					temp.setHeader(attribute.getHeader().substring(HOVER.length()));
					getRefHoverStructure().addOrChangeAttribute(temp);
				}
				else if (attribute.getHeader().startsWith(FOCUS)) {
					StandardSpecification temp = attribute.getCopy();
					temp.setHeader(attribute.getHeader().substring(FOCUS.length()));
					getRefFocusStructure().addOrChangeAttribute(temp);
				}
				else {
				
					//Calls method of the base class.
					super.addOrChangeAttribute(attribute);
				}
		}				
	}
	
	//method
	/**
	 * @return true if this widget belongs to a GUI.
	 */
	public final boolean belongsToGUI() {
		return (GUI != null);
	}
	
	//method
	/**
	 * @param GUI
	 * @return true if this widget belongs to the given GUI.
	 * @throws NullArgumentException if the given GUI is null.
	 */
	public final boolean belongsToGUI(final GUI<?> GUI) {
		
		//Checks if the given GUI is not null.
		Validator.supposeThat(GUI).isNotNull();
		
		return (this.GUI != GUI);
	}

	//method
	/**
	 * @return the attributes of this widget
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		if (!isNormal()) {
			attributes.addAtEnd(new StandardSpecification(STATE, state.toString()));
		}
		
		if (cursorIcon != CursorIcon.Arrow) {
			attributes.addAtEnd(cursorIcon.getSpecification());
		}
		
		//Handles the option that this widget has a left mouse button press command.
		if (hasLeftMouseButtonPressCommand()) {
			attributes.addAtEnd(
				new StandardSpecification(
					LEFT_MOUSE_BUTTON_PRESS_COMMAND_HEADER,
					leftMouseButtonPressCommand.toString()
				)
			);
		}
		
		//Handles the option that this widget has a left mouse button release command.
		if (hasLeftMouseButtonReleaseCommand()) {
			attributes.addAtEnd(
				new StandardSpecification(
					LEFT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER,
					leftMouseButtonReleaseCommand.toString()
				)
			);
		}
		
		//Handles the option that this widget has a right mouse button press command.
		if (hasRightMouseButtonPressCommand()) {
			attributes.addAtEnd(
				new StandardSpecification(
					RIGHT_MOUSE_BUTTON_PRESS_COMMAND_HEADER,
					rightMouseButtonPressCommand.toString()
				)
			);	
		}
		
		//Handles the option that this widget has a right mouse button release command.
		if (hasRightMouseButtonReleaseCommand()) {
			attributes.addAtEnd(
				new StandardSpecification(
					RIGHT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER,
					rightMouseButtonReleaseCommand.toString()
				)
			);
		}
	
		//Extracts the normal state attributes of this widget.
		final List<StandardSpecification> normalStateAttributes = getRefNormalStructure().getAttributes();
		normalStateAttributes.forEach(a -> a.addPrefixToHeader(NORMAL));
		attributes.addAtEnd(normalStateAttributes);
		
		//Extracts the hover state attributes of this widget.
		final List<StandardSpecification> hoverStateAttributes = getRefHoverStructure().getAttributes();
		hoverStateAttributes.forEach(a -> a.addPrefixToHeader(HOVER));
		attributes.addAtEnd(hoverStateAttributes);
		
		//Extracts focus state attributes of this widget.
		final List<StandardSpecification> focusStateAttributes = getRefFocusStructure().getAttributes();
		focusStateAttributes.forEach(a -> a.addPrefixToHeader(FOCUS));
		attributes.addAtEnd(focusStateAttributes);
		
		return attributes;
	}
	
	//method
	/**
	 * @return the height of this widget.
	 */
	public final int getHeight() {
		
		//Handles the case if this widget is collapsed.
		if (isCollapsed()) {
			return 0;
		}
		
		//Handles the case if this widget is not collapsed.
		return getHeightWhenNotCollapsed();
	}
	
	//abstract method
	/**
	 * @return the height of this widget when it is s not collapsed.
	 */
	public abstract int getHeightWhenNotCollapsed();
	
	//method
	/**
	 * @return the left mouse button press command of this widget.
	 * @throws UnexistingAttibuteException if this widget has no left mouse button press command.
	 */
	public final Statement getLeftMouseButtonPressCommand() {
		
		//Checks if this widget has a left mouse button press command.
		if (!hasLeftMouseButtonPressCommand()) {
			throw new UnexistingAttributeException(this, "left mouse button press command");
		}
		
		return leftMouseButtonPressCommand.getCopy();
	}
	
	//method
	/**
	 * @return the left mouse button release command of this widget.
	 * @throws UnexistingAttributeException if this widget has no left mouse button release command.
	 */
	public final Statement getLeftMouseButtonReleaseCommand() {
		
		//Checks if this widget has a left mouse button release command.
		if (!hasLeftMouseButtonReleaseCommand()) {
			throw new UnexistingAttributeException(this, "right mouse button press command");
		}
		
		return rightMouseButtonPressCommand.getCopy();
	}
	
	//method
	/**
	 * @return the x-position of the mouse of this widget.
	 */
	public final int getMouseXPosition() {
		return mouseXPosition;
	}
	
	//method
	/**
	 * @return the y-position of the mouse of this widget.
	 */
	public final int getMouseYPosition() {
		return mouseYPosition;
	}
	
	//method
	/**
	 * @return the configurable elements of this widget.
	 */
	public final AccessorContainer<Configurable> getRefConfigurables() {
		
		//TODO: Implement this better.
		return new AccessorContainer<Configurable>(getRefElements().to(e -> e));
	}
	
	//method
	/**
	 * @return the cursor icon of this widget.
	 */
	public final CursorIcon getCursorIcon() {
		return cursorIcon;
	}
	
	//abstract method
	/**
	 * 
	 * @return the elements of this widget.
	 */
	public abstract AccessorContainer<Widget<?, ?>> getRefElements();
	
	//method
	/**
	 * @return the focus structure of this widget.
	 */
	public final WS getRefFocusStructure() {
		return focusStructure;
	}
	
	//method
	/**
	 * @return the GUI this widget belongs to.
	 * @throws InvalidStateException if this widget belongs to no GUI.
	 */
	public final GUI<?> getRefGUI() {
		
		//Checks if this widget belongs to a GUI.
		if (!belongsToGUI()) {
			throw new InvalidStateException(this, "belongs to no GUI");
		}
		
		return GUI;
	}
	
	//method
	/**
	 * @return the hover structure of this widget.
	 */
	public final WS getRefHoverStructure() {
		return hoverStructure;
	}
	
	//method
	/**
	 * @return the normal structure of this widget.
	 */
	public final WS getRefNormalStructure() {
		return normalStructure;
	}
	
	//method
	/**
	 * @return the right mouse button press command of this widget.
	 * @throws UnexistingAttributeException if this widget has no right mouse button press command.
	 */
	public final Statement getRightMouseButtonPressCommand() {
		
		//Checks if this widget has a right mouse button press command.
		if (!hasRightMouseButtonPressCommand()) {
			throw new UnexistingAttributeException(this, "right mouse button press command");
		}
		
		return rightMouseButtonPressCommand;
	}
	
	//method
	/**
	 * @return the right mouse button release command of this widget.
	 * @throws UnexistingAttributeException if this widget has no right mouse button release command.
	 */
	public final Statement getRightMouseButtonReleaseCommand() {
		
		//Checks if this widget has a right mouse button release command.
		if (!hasRightMouseButtonReleaseCommand()) {
			throw new UnexistingAttributeException(this, "right mouse button release command");
		}
		
		return rightMouseButtonReleaseCommand;
	}
	
	//method
	/**
	 * @return the state of this widget.
	 */
	public final WidgetState getState() {
		return state;
	}
	
	//method
	/**
	 * @return the width of this widget.
	 */
	public final int getWidth() {
		
		//Handles the case if this widget is collapsed.
		if (isCollapsed()) {
			return 0;
		}
		
		//Handles the case if this widget is not collapsed.
		return getWidthWhenNotCollapsed();
	}
	
	//abstract method
	/**
	 * @return the width of this widget when it is not collapsed.
	 */
	public abstract int getWidthWhenNotCollapsed();
	
	//method
	/**
	 * @return true if this widget has a left mouse button press command.
	 */
	public final boolean hasLeftMouseButtonPressCommand() {
		return (leftMouseButtonPressCommand != null);
	}
	
	//method
	/**
	 * @return true if this widget has a left mouse button release command.
	 */
	public final boolean hasLeftMouseButtonReleaseCommand() {
		return (leftMouseButtonReleaseCommand != null);
	}
	
	//method
	/**
	 * @return true if this widget has a right mouse button press command.
	 */
	public final boolean hasRightMouseButtonPressCommand() {
		return (rightMouseButtonPressCommand != null);
	}
	
	//method
	/**
	 * @return true if this widget has a right mouse button release command.
	 */
	public final boolean hasRightMouseButtonReleaseCommand() {
		return (rightMouseButtonReleaseCommand != null);
	}
	
	//method
	/**
	 * @return true if this widget is collapsed.
	 */
	public final boolean isCollapsed() {
		return (getState() == WidgetState.Collapsed);
	}
	
	//method
	/**
	 * @return true if this widget is disabled.
	 */
	public final boolean isDisabled() {
		return (getState() == WidgetState.Disabled);
	}
	
	//method
	/**
	 * A widget is enables when it is normal, hovered or focused.
	 * 
	 * @return true if this widget is enabled.
	 */
	public final boolean isEnabled() {
		return (isNormal() || isHovered() || isFocused());
	}
	
	//method
	/**
	 * @return true if this widget is focused.
	 */
	public final boolean isFocused() {
		return (getState() == WidgetState.Focused);
	}
	
	//method
	/**
	 * @return true if this widget is hovered
	 */
	public final boolean isHovered() {
		return (getState() == WidgetState.Hovered);
	}
	
	//method
	/**
	 * @return true if this widget is normal.
	 */
	public final boolean isNormal() {
		return (getState() == WidgetState.Normal);
	}
	
	//method
	/**
	 * If a widget is under mouse it is not surely hovered, for example when it is disabled.
	 * 
	 * @return true if the mouse is on this widget.
	 */
	public final boolean isUnderMouse() {
		return (
			mouseXPosition >= 0	//First, checks the conditions that can be calculated easily.
			&& mouseYPosition >= 0
			&& mouseXPosition < getWidth()
			&& mouseYPosition < getHeight()
		);		
	}
	
	//method
	/**
	 * Lets this widget note a key press.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyPress(KeyEvent keyEvent) {}
	
	//method
	/**
	 * Lets this widget note a key typing.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyTyping(KeyEvent keyEvent) {}
	
	//method
	/**
	 * Lets this widget note a left mouse button press.
	 */
	public void noteLeftMouseButtonPress() {
		
		//Handles the option that this widget has a left mouse button press command.
		if (hasLeftMouseButtonPressCommand()) {
			getRefGUI().getRefController().run(getLeftMouseButtonPressCommand());
		}
	}
	
	//method
	/**
	 * Lets this widget note a left mouse button release.
	 */
	public void noteLeftMouseButtonRelease() {
		
		//Handles the option that this widget has a left mouse button release command.
		if (hasLeftMouseButtonReleaseCommand()) {
			System.out.println(13420);
			getRefGUI().getRefController().run(getLeftMouseButtonReleaseCommand());
		}
	}
	
	//method
	/**
	 * Lets this widget note a mouse move.
	 */
	public void noteMouseMove() {
		//TODO
		//Updates the state of this widget.
		if (isUnderMouse()) {
			if (isNormal()) {
				setHovered();
			}
		}
		else {
			if (isHovered()) {
				setNormal();
			}
		}
		
		if (isUnderMouse()) {
			getRefGUI().proposeCursorIcon(cursorIcon);
		}
	}
	
	//method
	/**
	 * Lets this widget note a right mouse button press.
	 */
	public void noteRightMouseButtonPress() {}
	
	//method
	/**
	 * Lets this widget note a right mouse button release.
	 */
	public void noteRightMouseButtonRelease() {}
	
	//method
	/**
	 * Removes the left mouse button press command of this widget.
	 */
	public final void removeLeftMouseButtonPressCommand() {
		leftMouseButtonPressCommand = null;
	}
	
	//method
	/**
	 * Removes the left mouse button release command of this widget.
	 */
	public final void removeLeftMouseButtonReleaseCommand() {
		leftMouseButtonReleaseCommand = null;
	}
	
	//method
	/**
	 * Removes the right mouse button press command of this widget.
	 */
	public final void removeRightMouseButtonPressCommand() {
		rightMouseButtonPressCommand = null;
	}
	
	//method
	/**
	 * Removes the right mouse button release command of this widget.
	 */
	public final void removeRightMouseButtonReleaseCommand() {
		rightMouseButtonReleaseCommand = null;
	}
	
	//method
	/**
	 * Resets this widget.
	 */
	public void reset() {
		
		setNormal();
		
		removeLeftMouseButtonPressCommand();
		removeLeftMouseButtonReleaseCommand();
		removeRightMouseButtonPressCommand();
		removeRightMouseButtonReleaseCommand();
		
		resetConfiguration();
	}
	
	//method
	/**
	 * Resets the configuration of this widget.
	 */
	public void resetConfiguration() {		
		setCursorIcon(CursorIcon.Arrow);
	}
	
	//method
	/**
	 * Sets this widget collapsed.
	 * 
	 * @return this widget.
	 */
	@SuppressWarnings("unchecked")
	public final W setCollapsed() {
		
		state = WidgetState.Collapsed;
		
		return (W)this;
	}
	
	//method
	/**
	 * Sets the cursor icon of this widget.
	 * 
	 * @param cursorIcon
	 * @throws NullArgumentException if the given cursor icon is null.
	 */
	@SuppressWarnings("unchecked")
	public final W setCursorIcon(final CursorIcon cursorIcon) {
		
		//Checks if the given cursor icon is not null.
		Validator.supposeThat(cursorIcon).thatIsInstanceOf(CursorIcon.class).isNotNull();
		
		//Sets the cursor icon of this widget.
		this.cursorIcon = cursorIcon;
		
		return (W)this;
	}
	
	//method
	/**
	 * Sets this widget disabled.
	 * 
	 * @return this widget.
	 */
	@SuppressWarnings("unchecked")
	public final W setDisabled() {
		
		state = WidgetState.Disabled;
		
		return (W)this;
	}
	
	//method
	/**
	 * Sets this widget focused.
	 * 
	 * @return this widget.
	 */
	@SuppressWarnings("unchecked")
	public final W setFocused() {
		
		state = WidgetState.Focused;
		
		return (W)this;
	}
		
	//method
	/**
	 * Sets this widget hovered.
	 * 
	 * @return this widget.
	 */
	@SuppressWarnings("unchecked")
	public final W setHovered() {
		
		state = WidgetState.Hovered;
		
		return (W)this;
	}
	
	//method
	/**
	 * Sets the left mouse button press command of this widget.
	 * 
	 * @param leftMouseButtonPressCommand
	 * @return this widget.
	 * @throws NullArgumentException if the given left mouse button press command is null.
	 */
	@SuppressWarnings("unchecked")
	public final W setLeftMouseButtonPressCommand(final Statement leftMouseButtonPressCommand) {
		
		//Checks if the given left mouse button press command is not null.
		Validator
		.supposeThat(leftMouseButtonPressCommand)
		.thatIsNamed("left mouse button press command")
		.isNotNull();
		
		this.leftMouseButtonPressCommand = leftMouseButtonPressCommand.getCopy();
		
		return (W)this;
	}
	
	//method
	/**
	 * Sets the left mouse button press command of this widget.
	 * 
	 * @param leftMouseButtonPressCommand
	 * @return this widget.
	 * @throws InvalidArgumentException if the given left mouse button press command is not valid.
	 */
	public final W setLeftMouseButtonPressCommand(final String leftMouseButtonPressCommand) {
		return setLeftMouseButtonPressCommand(new Statement(leftMouseButtonPressCommand));
	}

	//method
	/**
	 * Sets the given left mouse button press command, that has the given arguments, to this widget.
	 * 
	 * @param leftMouseButtonPressCommand
	 * @return this widget.
	 * @throws InvalidArgumentException if the given left mouse button press command is not valid.
	 * @throws InvalidArgumentExcepiton if one of the given arguments is not valid.
	 */
	public final W setLeftMouseButtonPressCommand(
		final String leftMouseButtonPressCommand,
		final String... arguments
	) {
		return setLeftMouseButtonPressCommand(new Statement(leftMouseButtonPressCommand, arguments));
	}
	
	//method
	/**
	 * Sets the left mouse button release command of this widget.
	 * 
	 * @param leftMouseButtonReleaseCommand
	 * @return this widget.
	 * @throws NullArgumentException if the given left mouse button release command is null.
	 */
	@SuppressWarnings("unchecked")
	public final W setLeftMouseButtonReleaseCommand(final Statement leftMouseButtonReleaseCommand) {
		
		//Checks if the given left mouse button release command is not null.
		Validator
		.supposeThat(leftMouseButtonReleaseCommand)
		.thatIsNamed("left mouse button release command")
		.isNotNull();
		this.leftMouseButtonReleaseCommand = leftMouseButtonReleaseCommand.getCopy();
		
		return (W)this;
	}
	
	//method
	/**
	 * Sets the left mouse button release command of this widget.
	 * 
	 * @param leftMouseButtonReleaseCommand
	 * @return this widget.
	 * @throws InvalidArgumentException if the given left mouse button release command is not valid.
	 */
	public final W setLeftMouseButtonReleaseCommand(final String leftMouseButtonReleaseCommand) {
		return setLeftMouseButtonReleaseCommand(new Statement(leftMouseButtonReleaseCommand));
	}
	
	//method
	/**
	 * Sets the given left mouse release command, that has the given attributes, to this widget.
	 * 
	 * @param leftMouseButtonReleaseCommand
	 * @param arguments
	 * @return this widget.
	 * @throws InvalidArgumentException if the given left mouse button release command is not valid.
	 * @throws InvalidArgumentException if one of the given arguments are not valid.
	 */
	public final W setLeftMouseButtonReleaseCommand(
		final String leftMouseButtonReleaseCommand,
		final String... arguments
	) {
		return setLeftMouseButtonReleaseCommand(new Statement(leftMouseButtonReleaseCommand, arguments));
	}
	
	//method
	/**
	 * Sets the position of the mouse on this widget
	 * using the mouse position of the mouse on the parent container of this widget.
	 * 
	 * @param mouseXPositionOnParentContainer
	 * @param mouseYPositionOnParentContainer
	 */
	public void setMousePositionFromParentContainer(
			final int mouseXPositionOnParentContainer,
			final int mouseYPositionOnParentContainer
	) {
		this.mouseXPosition = mouseXPositionOnParentContainer - getXPositionOnContainer();
		this.mouseYPosition = mouseYPositionOnParentContainer - getYPositionOnContainer();
	}
	
	//method
	/**
	 * Sets this widget normal.
	 * 
	 * @return this widget.
	 */
	@SuppressWarnings("unchecked")
	public final W setNormal() {
		
		state = WidgetState.Normal;
		
		return (W)this;
	}
	
	//method
	/**
	 * Sets the right mouse button press command of this widget.
	 * 
	 * @param rightMouseButtonPressCommand
	 * @return this widget.
	 * @throws NullArgumentException if the given right mouse button press command is null.
	 */
	@SuppressWarnings("unchecked")
	public final W setRightMouseButtonPressCommand(final Statement rightMouseButtonPressCommand) {
		
		//Checks if the given right mouse button press command is not null.
		Validator
		.supposeThat(rightMouseButtonPressCommand)
		.thatIsNamed("right mouse button press command")
		.isNotNull();
		
		this.rightMouseButtonPressCommand = rightMouseButtonPressCommand.getCopy();
		
		return (W)this;
	}
	
	//method
	/**
	 * Sets the right mouse button press command of this widget.
	 * 
	 * @param rightMouseButtonPressCommand
	 * @return this widget.
	 * @throws InvalidArgumentException if the givne right mouse button press command is not valid.
	 */
	public final W setRightMouseButtonPressCommand(final String rightMouseButtonPressCommand) {
		return setRightMouseButtonPressCommand(new Statement(rightMouseButtonPressCommand));
	}
	
	//method
	/**
	 * Sets the given right mouse button press command, that has the given arguments, to this widget.
	 * 
	 * @param rightMouseButtonPressCommand
	 * @param arguments
	 * @return this widget.
	 * @throws InvalidArgumentException if the given right mouse button press command is not valid.
	 * @throws InvalidArgumentException if one of the given arguments is not valid.
	 */
	public final W setRightMouseButtonPressCommand(
		final String rightMouseButtonPressCommand,
		final String... arguments
	) {
		return setRightMouseButtonPressCommand(new Statement(rightMouseButtonPressCommand, arguments));
	}
	
	//method
	/**
	 * Sets the right mouse button release command of this widget.
	 * 
	 * @param rightMouseButtonReleaseCommand
	 * @return this widget.
	 * @throws NullArgumentException if the given right mouse button release command is null.
	 */
	@SuppressWarnings("unchecked")
	public final W setRightMouseButtonReleaseCommand(final Statement rightMouseButtonReleaseCommand) {
		
		//Checks if the given right mouse button release command is not null.
		Validator
		.supposeThat(rightMouseButtonReleaseCommand)
		.thatIsNamed("right mouse button release command")
		.isNotNull();
		
		this.rightMouseButtonReleaseCommand = rightMouseButtonReleaseCommand.getCopy();
		
		return (W)this;
	}
	
	//method
	/**
	 * Sets the right mouse button release command of this widget.
	 * 
	 * @param rightMouseButtonReleaseCommand
	 * @return this widget.
	 * @throws InvalidArgumentException if the given right mouse button release command is not valid.
	 */
	public final W setRightMouseButtonReleaseCommand(final String rightMouseButtonReleaseCommand) {
		return setRightMouseButtonReleaseCommand(new Statement(rightMouseButtonReleaseCommand));
	}
	
	//method
	/**
	 * Sets the givne right mouse button release command, that has the given arguments, to this widget.
	 * 
	 * @param rightMouseButtonReleaseCommand
	 * @param arguments
	 * @return this widget.
	 * @throws InvalidArgumentException if the given right mouse button release command is not valid.
	 * @throws InvalidArgumentException if one of the given argumnets is not valid.
	 */
	public final W setRightMouseButtonReleaseCommand(
		final String rightMouseButtonReleaseCommand,
		final String... arguments
	) {
		return setRightMouseButtonReleaseCommand(new Statement(rightMouseButtonReleaseCommand, arguments));
	}
	
	//method
	/**
	 * Sets the state of this widget.
	 * 
	 * @param state
	 * @throws NullArgumentException if the given state is null.
	 */
	@SuppressWarnings("unchecked")
	public final W setState(final WidgetState state) {
		
		//Checks if the given state is not null.
		Validator.supposeThat(state).thatIsNamed("state").isNotNull();
		
		//Sets the state of this widget.
		this.state = state;
		
		return (W)this;
	}
	
	//abstract method
	/**
	 * Creates a new widget structure for this widget.
	 * 
	 * @return a new widget structure.
	 */
	protected abstract WS createWidgetStructure();
	
	//method
	/**
	 * @return the current structure of this widget.
	 * @throws UnexistingAttributeException if this widget has no current structure.
	 */
	protected final WS getRefCurrentStructure() {
		
		//Enumerates the state of this widget.
		switch (getState()) {
			case Normal:
				return getRefNormalStructure();
			case Hovered:
				return getRefHoverStructure();
			case Focused:
				return getRefFocusStructure();
			case Disabled:
				return getRefNormalStructure();
			default:
				throw new UnexistingAttributeException(this, "current structure");
		}
	}
	
	//method
	/**
	 * @return the x-position of this widget on its parent container.
	 */
	protected final int getXPositionOnContainer() {
		return xPositionOnContainer;
	}
	
	//method
	/**
	 * @return the relative y-position of this widget on its parent container.
	 */
	protected final int getYPositionOnContainer() {
		return yPositionOnContainer;
	}
	
	//abstract method
	/**
	 * Paints this widget using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param graphics
	 */
	protected abstract void paint(final WS widgetStructure, final Graphics graphics);
	
	//method
	/**
	 * Paints this widget using the position on its parent container using the given graphics.
	 * This method does not translate the given graphics in the end.
	 * 
	 * @param graphics
	 */
	protected final void paintUsingPositionOnContainer(final Graphics graphics) {
		graphics.translate(getXPositionOnContainer(), getYPositionOnContainer());
		paint(graphics);
		graphics.translate(-getXPositionOnContainer(), -getYPositionOnContainer());
	}
	
	//method
	/**
	 * Sets the GUI this widget will belong to.
	 * 
	 * @param GUI
	 * @throws NullArgumentException if the given GUI is null.
	 * @throws InvalidArgumentException if this widget belongs already to an other GUI.
	 */
	protected void setGUI(final GUI<?> GUI) {
		
		//Calls method of the base class.
		setParentContainer(GUI);
		
		//Sets the GUI of this widget.
		this.GUI = GUI;
	}
	
	//method
	/**
	 * Sets the position of this widget on its parent container
	 * 
	 * @param xPositionOnParentContainer
	 * @param yPositionOnParentContainer
	 */
	protected void setPositionOnContainer(int xPositionOnParentContainer, int yPositionOnParentContainer) {
		this.xPositionOnContainer = xPositionOnParentContainer;
		this.yPositionOnContainer = yPositionOnParentContainer;
	}
	
	//method
	/**
	 * Paints this widget using the given graphics.
	 * 
	 * @param graphics
	 */
	private void paint(final Graphics graphics) {
		
		paint(getRefCurrentStructure(), graphics);
		
		//Handles the option that this widget is under the mouse.
		if (isUnderMouse()) {
			getRefGUI().proposeCursorIcon(getCursorIcon());
		}
		
		//Handles the option that this widget is disabled.
		if (isDisabled()) {
			graphics.setColor(new Color(127, 127, 127, 127));
			graphics.drawRect(0, 0, getWidth(), getHeight());
		}
	}
}
