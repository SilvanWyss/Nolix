//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.specificationInterfaces.Configurable;
import ch.nolix.element.bases.ConfigurableElement;
import ch.nolix.element.color.Color;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidStateException.ClosedStateException;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * A {@link Widget} is an element on a {@link GUI}.
 * A {@link Widget} determines its width and height.
 * A {@link Widget} is a {@link ConfigurableElement}.
 * The methods concerning the position of a {@link Widget} are not public.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1460
 * @param <W> The type of a {@link Widget}.
 * @param <WL> The type of the {@link WidgetLook} of a {@link Widget}.
 */
public abstract class Widget<W extends Widget<W, WL>, WL extends WidgetLook<WL>>
extends ConfigurableElement<W> {
	
	//constants
	private static final String STATE_HEADER ="State";
	private static final String NO_GREY_OUT_WHEN_DISABLED_HEADER = "NoGreyOutWhenDisabled";
	
	//constants
	private static final String BASE_PREFIX = "Base";
	private static final String FOCUS_PREFIX = "Focus";
	private static final String HOVER_PREFIX = "Hover";
	private static final String HOVER_FOCUS_PREFIX = "HoverFocus";
	
	//constants
	private static final String LEFT_MOUSE_BUTTON_PRESS_COMMAND_HEADER = "LeftMouseButtonPressCommand";
	private static final String LEFT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER = "LeftMouseButtonReleaseCommand";
	private static final String RIGHT_MOUSE_BUTTON_PRESS_COMMAND_HEADER = "RightMouseButtonPressCommand";
	private static final String RIGHT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER = "RightMouseButtonReleaseCommand";
	
	//attributes
	private WidgetState state = WidgetState.Normal;
	private CursorIcon cursorIcon = CursorIcon.Arrow;
	private boolean greyOutWhenDisabled = true;	
	
	//attributes
	private final WL baseLook = createWidgetLook();
	private final WL hoverLook = createWidgetLook();
	private final WL focusLook = createWidgetLook();
	private final WL hoverFocusLook = createWidgetLook();
	
	//attributes
	private int xPositionOnParent = 0;
	private int yPositionOnParent = 0;
	private int cursorXPosition = 0;
	private int cursorYPosition = 0;
	
	//attributes
	private boolean calculatedWidthIsUpToDate = false;
	private boolean calculatedHeightIsUpToDate = false;
	private int calculatedWidth;
	private int calculatedHeight;
	
	//optional attribute
	/**
	 * The {@link GUI} the current {@link Widget} belongs to.
	 */
	private GUI<?> parentGUI;
	
	//optional attributes
	private Statement leftMouseButtonPressCommand;
	private Statement leftMouseButtonReleaseCommand;
	private Statement rightMouseButtonPressCommand;
	private Statement rightMouseButtonReleaseCommand;
	
	//constructor
	/**
	 * Creates a new {@link Widget}.
	 */
	public Widget() {			
		getRefHoverLook().setBaseStructure(getRefBaseLook());
		getRefFocusLook().setBaseStructure(getRefBaseLook());
		getRefHoverFocusLook().setBaseStructure(getRefFocusLook());
	}
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link Widget}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case STATE_HEADER:
				setState(WidgetState.createFromSpecification(attribute));
				break;
			case CursorIcon.TYPE_NAME:
				setCursorIcon(CursorIcon.valueOf(attribute.getOneAttributeAsString()));
				break;
			case NO_GREY_OUT_WHEN_DISABLED_HEADER:
				removeGreyOutWhenDisabled();
				break;
			case LEFT_MOUSE_BUTTON_PRESS_COMMAND_HEADER:
				
				setLeftMouseButtonPressCommand(
					Specification.createOriginStringFromReproducingString(
						attribute.getOneAttributeAsString()
					)
				);
				
				break;
			case LEFT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER:
				
				setLeftMouseButtonReleaseCommand(
				Specification.createOriginStringFromReproducingString(
						attribute.getOneAttributeAsString()
					)
				);
				
				break;
			case RIGHT_MOUSE_BUTTON_PRESS_COMMAND_HEADER:
				
				setRightMouseButtonPressCommand(
				Specification.createOriginStringFromReproducingString(
						attribute.getOneAttributeAsString()
					)
				);
				
				break;
			case RIGHT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER:
				
				setRightMouseButtonReleaseCommand(				
				Specification.createOriginStringFromReproducingString(
						attribute.getOneAttributeAsString()
					)
				);
				
				break;
			default:
				if (attribute.getHeader().startsWith(BASE_PREFIX)) {
					final var temp = attribute.createCopy();
					temp.setHeader(attribute.getHeader().substring(BASE_PREFIX.length()));
					getRefBaseLook().addOrChangeAttribute(temp);
				}
				else if (attribute.getHeader().startsWith(HOVER_PREFIX)) {
					final var temp = attribute.createCopy();
					temp.setHeader(attribute.getHeader().substring(HOVER_PREFIX.length()));
					getRefHoverLook().addOrChangeAttribute(temp);
				}
				else if (attribute.getHeader().startsWith(FOCUS_PREFIX)) {
					final var temp = attribute.createCopy();
					temp.setHeader(attribute.getHeader().substring(FOCUS_PREFIX.length()));
					getRefFocusLook().addOrChangeAttribute(temp);
				}
				else if (attribute.getHeader().startsWith(HOVER_FOCUS_PREFIX)) {
					final var temp = attribute.createCopy();
					temp.setHeader(attribute.getHeader().substring(HOVER_FOCUS_PREFIX.length()));
					getRefFocusLook().addOrChangeAttribute(temp);
				}
				else {
				
					//Calls method of the base class.
					super.addOrChangeAttribute(attribute);
				}
		}				
	}
	
	//method
	/**
	 * Resets the configuration of the current {@link Widget}
	 * and applies a usable configuration to the current{@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public W applyUsableConfiguration() {
		
		resetConfiguration();		
		applyUsableConfigurationWhenConfigurationIsReset();
		
		return getInstance();
	}

	//method
	/**
	 * @return true if the current {@link Widget} belongs to a GUI.
	 */
	public final boolean belongsToGUI() {
		return (parentGUI != null);
	}
	
	//method
	/**
	 * @param GUI
	 * @return true if the current {@link Widget} belongs to the given GUI.
	 * @throws NullArgumentException if the given GUI is null.
	 */
	public final boolean belongsToGUI(final GUI<?> GUI) {
		
		//Checks if the given GUI is not null.
		Validator.suppose(GUI).isNotNull();
		
		return (this.parentGUI != GUI);
	}
	
	//abstract method
	/**
	 * @return the active cursor icon of the current {@link Widget}.
	 */
	public abstract CursorIcon getActiveCursorIcon();
	
	//method
	/**
	 * @return the attributes of the current {@link Widget}.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		if (!isNormal()) {
			attributes.addAtEnd(getState().getSpecificationAs(STATE_HEADER));
		}
		
		if (cursorIcon != CursorIcon.Arrow) {
			attributes.addAtEnd(cursorIcon.getSpecification());
		}
		
		//Handles the case that the current widget has a left mouse button press command.
		if (hasLeftMouseButtonPressCommand()) {
			attributes.addAtEnd(
				new StandardSpecification(
					LEFT_MOUSE_BUTTON_PRESS_COMMAND_HEADER,
					leftMouseButtonPressCommand.toString()
				)
			);
		}
		
		//Handles the case that the current widget has a left mouse button release command.
		if (hasLeftMouseButtonReleaseCommand()) {
			attributes.addAtEnd(
				new StandardSpecification(
					LEFT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER,
					leftMouseButtonReleaseCommand.toString()
				)
			);
		}
		
		//Handles the case that the current widget has a right mouse button press command.
		if (hasRightMouseButtonPressCommand()) {
			attributes.addAtEnd(
				new StandardSpecification(
					RIGHT_MOUSE_BUTTON_PRESS_COMMAND_HEADER,
					rightMouseButtonPressCommand.toString()
				)
			);	
		}
		
		//Handles the case that the current widget has a right mouse button release command.
		if (hasRightMouseButtonReleaseCommand()) {
			attributes.addAtEnd(
				new StandardSpecification(
					RIGHT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER,
					rightMouseButtonReleaseCommand.toString()
				)
			);
		}
		
		//Handles the case that the current widget does not grey out when it is disabled.
		if (!greysOutWhenDisabled()) {
			attributes.addAtEnd(new StandardSpecification(NO_GREY_OUT_WHEN_DISABLED_HEADER));
		}
	
		//Extracts the normal state attributes of the current widget.
		final List<StandardSpecification> normalStateAttributes = getRefBaseLook().getAttributes();
		normalStateAttributes.forEach(a -> a.addPrefixToHeader(BASE_PREFIX));
		attributes.addAtEnd(normalStateAttributes);
		
		//Extracts the hover state attributes of the current widget.
		final List<StandardSpecification> hoverStateAttributes = getRefHoverLook().getAttributes();
		hoverStateAttributes.forEach(a -> a.addPrefixToHeader(HOVER_PREFIX));
		attributes.addAtEnd(hoverStateAttributes);
		
		//Extracts focus state attributes of the current widget.
		final List<StandardSpecification> focusStateAttributes = getRefFocusLook().getAttributes();
		focusStateAttributes.forEach(a -> a.addPrefixToHeader(FOCUS_PREFIX));
		attributes.addAtEnd(focusStateAttributes);
		
		return attributes;
	}
	
	//method
	/**
	 * @return the cursor icon of the current {@link Widget}.
	 */
	public final CursorIcon getCursorIcon() {
		return cursorIcon;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link Widget}.
	 */
	public final int getCursorXPosition() {
		return cursorXPosition;
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link Widget}.
	 */
	public final int getCursorYPosition() {
		return cursorYPosition;
	}
	
	//method
	/**
	 * @return the height of the current {@link Widget}.
	 */
	public final int getHeight() {
		
		//Handles the case that the calculated height of the current widget is not up to date.
		if (!calculatedHeightIsUpToDate) {
			calculatedHeight = getNewCalculatedHeight();
			calculatedHeightIsUpToDate = true;
		}
		
		return calculatedHeight;
	}

	//method
	/**
	 * The interaction attributes of a {@link Widget} are those a user can change.
	 * 
	 * @return the interaction attributes of the current {@link Widget}.
	 */
	public List<StandardSpecification> getInteractionAttributes() {
		return
		new List<StandardSpecification> (
			getState().getSpecificationAs(STATE_HEADER)
		);
	}
	
	//method
	/**
	 * @return the left mouse button press command of the current {@link Widget}.
	 * @throws UnexistingAttibuteException if the current {@link Widget} has no left mouse button press command.
	 */
	public final Statement getLeftMouseButtonPressCommand() {
		
		//Checks if the current {@link Widget} has a left mouse button press command.
		if (!hasLeftMouseButtonPressCommand()) {
			throw new UnexistingAttributeException(this, "left mouse button press command");
		}
		
		return leftMouseButtonPressCommand.getCopy();
	}
	
	//method
	/**
	 * @return the left mouse button release command of the current {@link Widget}.
	 * @throws UnexistingAttributeException if the current {@link Widget} has no left mouse button release command.
	 */
	public final Statement getLeftMouseButtonReleaseCommand() {
		
		//Checks if the current {@link Widget} has a left mouse button release command.
		if (!hasLeftMouseButtonReleaseCommand()) {
			throw new UnexistingAttributeException(this, "right mouse button press command");
		}
		
		return rightMouseButtonPressCommand.getCopy();
	}
	
	//method
	/**
	 * @return the GUI the current {@link Widget} belongs to.
	 * @throws InvalidStateException if the current {@link Widget} belongs to no GUI.
	 */
	public final GUI<?> getParentGUI() {
		
		//Checks if the current widget belongs to a GUI.
		supposeBelongsToGUI();
		
		return parentGUI;
	}
	
	//method
	/**
	 * @return the base look of the current {@link Widget}.
	 */
	public final WL getRefBaseLook() {
		return baseLook;
	}
	
	//method
	/**
	 * @return the configurable elements of the current {@link Widget}.
	 */
	public final ReadContainer<Configurable<?>> getRefConfigurables() {
		return new ReadContainer<Configurable<?>>(getRefWidgets());
	}
	
	//method
	/**
	 * @return the focus look of the current {@link Widget}.
	 */
	public final WL getRefFocusLook() {
		return focusLook;
	}

	//method
	/**
	 * @return the hover focus structure of the current {@link Widget}.
	 */
	public final WL getRefHoverFocusLook() {
		return hoverFocusLook;
	}
	
	//method
	/**
	 * @return the hover look of the current {@link Widget}.
	 */
	public final WL getRefHoverLook() {
		return hoverLook;
	}
	
	//abstract method
	/**
	 * 
	 * @return the widgets of the current {@link Widget}.
	 */
	public abstract ReadContainer<Widget<?, ?>> getRefWidgets();
	
	//method
	/**
	 * @return the right mouse button press command of the current {@link Widget}.
	 * @throws UnexistingAttributeException if the current {@link Widget} has no right mouse button press command.
	 */
	public final Statement getRightMouseButtonPressCommand() {
		
		//Checks if the current {@link Widget} has a right mouse button press command.
		if (!hasRightMouseButtonPressCommand()) {
			throw new UnexistingAttributeException(this, "right mouse button press command");
		}
		
		return rightMouseButtonPressCommand;
	}
	
	//method
	/**
	 * @return the right mouse button release command of the current {@link Widget}.
	 * @throws UnexistingAttributeException if the current {@link Widget} has no right mouse button release command.
	 */
	public final Statement getRightMouseButtonReleaseCommand() {
		
		//Checks if the current {@link Widget} has a right mouse button release command.
		if (!hasRightMouseButtonReleaseCommand()) {
			throw new UnexistingAttributeException(this, "right mouse button release command");
		}
		
		return rightMouseButtonReleaseCommand;
	}
	
	//method
	/**
	 * @return the state of the current {@link Widget}.
	 */
	public final WidgetState getState() {
		return state;
	}
	
	//method
	/**
	 * @return the width of the current {@link Widget}.
	 */
	public final int getWidth() {
		
		//Handles the case that the calculated width of the current widget is not up to date.
		if (!calculatedWidthIsUpToDate) {
			calculatedWidth = getNewCalculatedWidth();
			calculatedWidthIsUpToDate = true;
		}
		
		return calculatedWidth;
	}
	

	
	//method
	/**
	 * @return true if the current {@link Widget} greys out when it is disabled.
	 */
	public final boolean greysOutWhenDisabled() {
		return greyOutWhenDisabled;
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a left mouse button press command.
	 */
	public final boolean hasLeftMouseButtonPressCommand() {
		return (leftMouseButtonPressCommand != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a left mouse button release command.
	 */
	public final boolean hasLeftMouseButtonReleaseCommand() {
		return (leftMouseButtonReleaseCommand != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a right mouse button press command.
	 */
	public final boolean hasRightMouseButtonPressCommand() {
		return (rightMouseButtonPressCommand != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a right mouse button release command.
	 */
	public final boolean hasRightMouseButtonReleaseCommand() {
		return (rightMouseButtonReleaseCommand != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is collapsed.
	 */
	public final boolean isCollapsed() {
		return (getState() == WidgetState.Collapsed);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is disabled.
	 */
	public final boolean isDisabled() {
		return (getState() == WidgetState.Disabled);
	}
	
	//method
	/**
	 * A {@link Widget} is enables when it is normal, hovered, focused or hover focused.
	 * 
	 * @return true if the current {@link Widget} is enabled.
	 */
	public final boolean isEnabled() {
		return (isNormal() || isHovered() || isFocused() || isHoverFocused());
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is focused.
	 */
	public final boolean isFocused() {
		return (getState() == WidgetState.Focused);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is hovered.
	 */
	public final boolean isHovered() {
		return (getState() == WidgetState.Hovered);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is hover-focused.
	 */
	public final boolean isHoverFocused() {
		return (getState() == WidgetState.HoverFocused);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is normal.
	 */
	public final boolean isNormal() {
		return (getState() == WidgetState.Normal);
	}
	
	//method
	/**
	 * If a widget is under mouse it is not surely hovered, for example when it is disabled.
	 * 
	 * @return true if the mouse is on the current {@link Widget}.
	 */
	public final boolean isUnderCursor() {
		return (
			cursorXPosition >= 0	//First, checks the conditions that can be calculated easily.
			&& cursorYPosition >= 0
			&& cursorXPosition < getWidth()
			&& cursorYPosition < getHeight()
		);		
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any key press.
	 * 
	 * @param keyEvent
	 */
	public final void noteAnyKeyPress(final KeyEvent keyEvent) {
		
		//Handles the case that the current widget is focused or hover focused.
		if (isFocused() || isHoverFocused()) {
			noteKeyPress(keyEvent);
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any key typing.
	 * 
	 * @param keyEvent
	 */
	public final void noteAnyKeyTyping(final KeyEvent keyEvent) {
		
		//Handles the case that the current widget is focused or hover focused.
		if (isFocused() || isHoverFocused()) {
			noteKeyTyping(keyEvent);
		}
	}

	//method
	/**
	 * Lets the current {@link Widget} note any mouse button press.
	 */
	public final void noteAnyLeftMouseButtonPress() {
		if (isEnabled()) {
			if (!isUnderCursor()) {
				
				if (isFocused()) {				
					setNormal();
				}
				
				else if (isHoverFocused()) {
					setNormal();
				}
			}
			else {
				
				if (isNormal()) {
					setHoverFocused();
				}
				else if (isHovered()) {
					setHoverFocused();
				}
				
				noteLeftMouseButtonPress();
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any mouse button release.
	 */
	public final void noteAnyLeftMouseButtonRelease() {
		if (isEnabled()) {
			if (isUnderCursor() || isFocused()) {
				noteLeftMouseButtonRelease();
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any mouse move.
	 */
	public final void noteAnyMouseMove() {
		if (isEnabled()) {
			if (!isUnderCursor()) {
				
				if (isHovered()) {
					setNormal();
				}
				
				else if (isHoverFocused()) {
					setFocused();
				}
				
				else if (isFocused()) {
					noteMouseMove();
				}
			}
			else {
				
				if (isNormal()) {
					setHovered();
				}
				else if (isHoverFocused()) {
					setFocused();
				}
				
				noteMouseMove();
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note the given mouse wheel rotation steps.
	 * The given number of mouse wheel rotation steps is positive if the mouse wheel was rotated forward.
	 * The given number mouse wheel rotation steps is negative if the mouse wheel was rotated backward.
	 * 
	 * @param rotationSteps
	 */
	public void noteAnyMouseWheelRotationSteps(final int mouseWheelRotationSteps) {
		if (isEnabled() && isUnderCursor()) {
			noteMouseWheelRotationSteps(mouseWheelRotationSteps);
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any right mouse button press.
	 */
	public final void noteAnyRightMouseButtonPress() {
		if (isEnabled() && isUnderCursor()) {
			noteRightMouseButtonPress();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any right mouse button release.
	 */
	public final void noteAnyRightMouseButtonRelease() {
		if (isEnabled() && isUnderCursor()) {
			noteRightMouseButtonRelease();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a key press.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyPress(final KeyEvent keyEvent) {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a key typing.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyTyping(final KeyEvent keyEvent) {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button press.
	 */
	public void noteLeftMouseButtonPress() {
		
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button release.
	 */
	public void noteLeftMouseButtonRelease() {
		
		//Handles the case that the current widget has a left mouse button release command.
		if (hasLeftMouseButtonReleaseCommand()) {
			getParentGUI().getRefController().run(getLeftMouseButtonReleaseCommand());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse move.
	 */
	public void noteMouseMove() {}
	
	//method
	/**
	 * Lets this GUI note the given mouse wheel rotation steps.
	 * The given number of mouse wheel rotation steps is positive if the mouse wheel was rotated forward.
	 * The given number mouse wheel rotation steps is negative if the mouse wheel was rotated backward.
	 * 
	 * @param rotationSteps
	 */
	public void noteMouseWheelRotationSteps(final int mouseWheelRotationSteps) {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button press.
	 */
	public void noteRightMouseButtonPress() {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button release.
	 */
	public void noteRightMouseButtonRelease() {}
	
	//method
	/**
	 * Avoids that the current widget greys out when it is disabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeGreyOutWhenDisabled() {
		
		greyOutWhenDisabled = false;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the left mouse button press command of the current {@link Widget}.
	 */
	public final void removeLeftMouseButtonPressCommand() {
		leftMouseButtonPressCommand = null;
	}
	
	//method
	/**
	 * Removes the left mouse button release command of the current {@link Widget}.
	 */
	public final void removeLeftMouseButtonReleaseCommand() {
		leftMouseButtonReleaseCommand = null;
	}
	
	//method
	/**
	 * Removes the right mouse button press command of the current {@link Widget}.
	 */
	public final void removeRightMouseButtonPressCommand() {
		rightMouseButtonPressCommand = null;
	}
	
	//method
	/**
	 * Removes the right mouse button release command of the current {@link Widget}.
	 */
	public final void removeRightMouseButtonReleaseCommand() {
		rightMouseButtonReleaseCommand = null;
	}
	
	//method
	/**
	 * Resets the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public W reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setNormal();
		setGreyOutWhenDisabled();
		
		removeLeftMouseButtonPressCommand();
		removeLeftMouseButtonReleaseCommand();
		removeRightMouseButtonPressCommand();
		removeRightMouseButtonReleaseCommand();
		
		resetConfiguration();
		
		return getInstance();
	}
	
	//method
	/**
	 * Resets the configuration of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public W resetConfiguration() {
		
		getRefBaseLook().reset();
		getRefHoverLook().reset();
		getRefFocusLook().reset();
		
		setCursorIcon(CursorIcon.Arrow);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} collapsed.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setCollapsed() {
		
		state = WidgetState.Collapsed;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the cursor icon of the current {@link Widget}.
	 * 
	 * @param cursorIcon
	 * @throws NullArgumentException if the given cursor icon is null.
	 */
	public final W setCursorIcon(final CursorIcon cursorIcon) {
		
		//Checks if the given cursor icon is not null.
		Validator.suppose(cursorIcon).thatIsOfType(CursorIcon.class).isNotNull();
		
		//Sets the cursor icon of the current {@link Widget}.
		this.cursorIcon = cursorIcon;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the cursor position of the parent of the current {@link Widget}.
	 * 
	 * @param parentCursorXPosition
	 * @param parentCursorYPosition
	 */
	public void setParentCursorPosition(
		int parentCursorXPosition,
		int parentCursorYPosition
	) {				
		this.cursorXPosition = parentCursorXPosition - getXPositionOnParent();
		this.cursorYPosition = parentCursorYPosition - getYPositionOnParent();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} disabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setDisabled() {
		
		state = WidgetState.Disabled;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} focused.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setFocused() {
		
		state = WidgetState.Focused;
		
		return getInstance();
	}
	
	//method
	/**
	 * Lets the current {@link Widget} grey out when it is disabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setGreyOutWhenDisabled() {
		
		greyOutWhenDisabled = true;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} hovered.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setHovered() {
		
		state = WidgetState.Hovered;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} hover-focused.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W  setHoverFocused() {
		
		state = WidgetState.HoverFocused;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the left mouse button press command of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonPressCommand
	 * @return the current {@link Widget}.
	 * @throws NullArgumentException if the given left mouse button press command is null.
	 */
	public final W setLeftMouseButtonPressCommand(final Statement leftMouseButtonPressCommand) {
		
		//Checks if the given left mouse button press command is not null.
		Validator
		.suppose(leftMouseButtonPressCommand)
		.thatIsNamed("left mouse button press command")
		.isNotNull();
		
		this.leftMouseButtonPressCommand = leftMouseButtonPressCommand.getCopy();
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the left mouse button press command of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonPressCommand
	 * @return the current {@link Widget}.
	 * @throws InvalidArgumentException if the given left mouse button press command is not valid.
	 */
	public final W setLeftMouseButtonPressCommand(final String leftMouseButtonPressCommand) {
		return setLeftMouseButtonPressCommand(new Statement(leftMouseButtonPressCommand));
	}

	//method
	/**
	 * Sets the given left mouse button press command, that has the given arguments, to the current {@link Widget}.
	 * 
	 * @param leftMouseButtonPressCommand
	 * @return the current {@link Widget}.
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
	 * Sets the left mouse button release command of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonReleaseCommand
	 * @return the current {@link Widget}.
	 * @throws NullArgumentException if the given left mouse button release command is null.
	 */
	public final W setLeftMouseButtonReleaseCommand(final Statement leftMouseButtonReleaseCommand) {
		
		//Checks if the given left mouse button release command is not null.
		Validator
		.suppose(leftMouseButtonReleaseCommand)
		.thatIsNamed("left mouse button release command")
		.isNotNull();
		this.leftMouseButtonReleaseCommand = leftMouseButtonReleaseCommand.getCopy();
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the left mouse button release command of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonReleaseCommand
	 * @return the current {@link Widget}.
	 * @throws InvalidArgumentException if the given left mouse button release command is not valid.
	 */
	public final W setLeftMouseButtonReleaseCommand(final String leftMouseButtonReleaseCommand) {
		return setLeftMouseButtonReleaseCommand(new Statement(leftMouseButtonReleaseCommand));
	}
	
	//method
	/**
	 * Sets the given left mouse release command, that has the given attributes, to the current {@link Widget}.
	 * 
	 * @param leftMouseButtonReleaseCommand
	 * @param arguments
	 * @return the current {@link Widget}.
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
	 * Sets the current {@link Widget} normal.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setNormal() {
		
		state = WidgetState.Normal;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the right mouse button press command of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonPressCommand
	 * @return the current {@link Widget}.
	 * @throws NullArgumentException if the given right mouse button press command is null.
	 */
	public final W setRightMouseButtonPressCommand(final Statement rightMouseButtonPressCommand) {
		
		//Checks if the given right mouse button press command is not null.
		Validator
		.suppose(rightMouseButtonPressCommand)
		.thatIsNamed("right mouse button press command")
		.isNotNull();
		
		this.rightMouseButtonPressCommand = rightMouseButtonPressCommand.getCopy();
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the right mouse button press command of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonPressCommand
	 * @return the current {@link Widget}.
	 * @throws InvalidArgumentException if the givne right mouse button press command is not valid.
	 */
	public final W setRightMouseButtonPressCommand(final String rightMouseButtonPressCommand) {
		return setRightMouseButtonPressCommand(new Statement(rightMouseButtonPressCommand));
	}
	
	//method
	/**
	 * Sets the given right mouse button press command, that has the given arguments, to the current {@link Widget}.
	 * 
	 * @param rightMouseButtonPressCommand
	 * @param arguments
	 * @return the current {@link Widget}.
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
	 * Sets the right mouse button release command of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonReleaseCommand
	 * @return the current {@link Widget}.
	 * @throws NullArgumentException if the given right mouse button release command is null.
	 */
	public final W setRightMouseButtonReleaseCommand(final Statement rightMouseButtonReleaseCommand) {
		
		//Checks if the given right mouse button release command is not null.
		Validator
		.suppose(rightMouseButtonReleaseCommand)
		.thatIsNamed("right mouse button release command")
		.isNotNull();
		
		this.rightMouseButtonReleaseCommand = rightMouseButtonReleaseCommand.getCopy();
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the right mouse button release command of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonReleaseCommand
	 * @return the current {@link Widget}.
	 * @throws InvalidArgumentException if the given right mouse button release command is not valid.
	 */
	public final W setRightMouseButtonReleaseCommand(final String rightMouseButtonReleaseCommand) {
		return setRightMouseButtonReleaseCommand(new Statement(rightMouseButtonReleaseCommand));
	}
	
	//method
	/**
	 * Sets the givne right mouse button release command, that has the given arguments, to the current {@link Widget}.
	 * 
	 * @param rightMouseButtonReleaseCommand
	 * @param arguments
	 * @return the current {@link Widget}.
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
	 * Sets the state of the current {@link Widget}.
	 * 
	 * @param state
	 * @throws NullArgumentException if the given state is null.
	 */
	public final W setState(final WidgetState state) {
		
		//Checks if the given state is not null.
		Validator.suppose(state).thatIsNamed("state").isNotNull();

		//Sets the state of the current {@link Widget}.
		this.state = state;
		
		return getInstance();
	}
	
	//abstract method
	/**
	 * @return a new widget look for the current {@link Widget}.
	 */
	protected abstract WL createWidgetLook();
	
	//abstract method
	/**
	 * @return the height of the current {@link Widget} when it is s not collapsed.
	 */
	protected abstract int getHeightWhenNotCollapsed();
	
	//method
	/**
	 * @return the current look of the current {@link Widget}.
	 * @throws UnexistingAttributeException if the current {@link Widget} has no current look.
	 */
	protected final WL getRefCurrentLook() {
		
		//Enumerates the state of the current {@link Widget}.
		switch (getState()) {
			case Normal:
				return getRefBaseLook();
			case Hovered:
				return getRefHoverLook();
			case Focused:
				return getRefFocusLook();
			case HoverFocused:
				return getRefHoverFocusLook();
			case Disabled:
				return getRefBaseLook();
			default:
				throw new UnexistingAttributeException(
					this,
					"current look");
		}
	}
	
	//method
	/**
	 * @return the widgets of the current {@link Widget} recursively.
	 */
	protected final List<Widget<?, ?>> getRefWidgetsRecursively() {
		
		final var widgets = new List<Widget<?, ?>>(getRefWidgets());
		getRefWidgets().forEach(w -> widgets.addAtEnd(w.getRefWidgetsRecursively()));
		
		return widgets;
	}
	
	//abstract method
	/**
	 * @return the width of the current {@link Widget} when it is not collapsed.
	 */
	protected abstract int getWidthWhenNotCollapsed();
	
	//method
	/**
	 * @return the x-position of the current {@link Widget} on its parent container.
	 */
	protected final int getXPositionOnParent() {
		return xPositionOnParent;
	}
	
	//method
	/**
	 * @return the relative y-position of the current {@link Widget} on its parent container.
	 */
	protected final int getYPositionOnParent() {
		return yPositionOnParent;
	}
	
	//abstract method
	/**
	 * Paints the current {@link Widget} using the given widget structure and painter.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
	protected abstract void paint(final WL widgetStructure, final IPainter painter);
	
	//method
	/**
	 * Paints the current {@link Widget} using the position on its parent container using the given painter.
	 * This method promises that the given painter
	 * has the same position at the end as at the beginning.
	 * 
	 * @param painter
	 */
	protected final void paintUsingPositionOnParent(final IPainter painter) {
		paint(
			painter.createTranslatedPainter(
				getXPositionOnParent(),
				getYPositionOnParent()
			)
		);
	}
	
	//abstract method
	/**
	 * Applies a usable configuration to the current {@link Widget}
	 * when the configuration of the current {@link Widget} has been reset.
	 */
	protected abstract void applyUsableConfigurationWhenConfigurationIsReset();
	
	//method
	/**
	 * Sets the GUI the current {@link Widget} will belong to.
	 * 
	 * @param GUI
	 * @throws NullArgumentException if the given GUI is null.
	 * @throws InvalidArgumentException if the current {@link Widget} belongs already to an other GUI.
	 */
	protected void setGUI(final GUI<?> GUI) {
		
		//Calls method of the base class.
		setRequestableContainer(GUI);
		
		getRefWidgets().forEach(w -> w.setGUI(GUI));
		
		//Sets the GUI of the current {@link Widget}.
		this.parentGUI = GUI;
	}
	
	//method
	/**
	 * Sets the position of the current {@link Widget} on its parent.
	 * 
	 * @param xPositionOnParent
	 * @param yPositionOnParent
	 */
	protected void setPositionOnParent(
		final int xPositionOnParent,
		final int yPositionOnParent
	) {
		this.xPositionOnParent = xPositionOnParent;
		this.yPositionOnParent = yPositionOnParent;
		
		calculatedWidthIsUpToDate = false;
		calculatedHeightIsUpToDate = false;
	}
	
	//method
	/**
	 * @throws ClosedStateException if the current {@link Widget} belongs to a GUI that is closed.
	 */
	protected void supposeGUIIsAlive() {
		if (belongsToGUI() && getParentGUI().isClosed()) {
			throw new ClosedStateException(getParentGUI());
		}
	}
	
	//method
	/**
	 * Paints the current {@link Widget} using the given painter.
	 * 
	 * @param painter
	 */
	protected void paint(final IPainter painter) {
		
		paint(getRefCurrentLook(), painter);
		
		//Handles the case that the current widget is disabled and would grey out.
		if (isDisabled() && greysOutWhenDisabled()) {
			painter.setColor(Color.GREY);
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
	}
	
	//method
	/**
	 * @return the newly calculated height of the current {@link Widget}.
	 */
	private int getNewCalculatedHeight() {
		
		//Handles the case that the current widget is collapsed.
		if (isCollapsed()) {
			return 0;
		}
		
		//Handles the case that the current widget is not collapsed.
		return getHeightWhenNotCollapsed();
	}
	
	//method
	/**
	 * @return the newly calculated width of the current {@link Widget}.
	 */
	private int getNewCalculatedWidth() {
		
		//Handles the case that the current widget is collapsed.
		if (isCollapsed()) {
			return 0;
		}
		
		//Handles the case that the current widget is not collapsed.
		return getWidthWhenNotCollapsed();
	}
	
	//method
	/**
	 * @throws InvalidStateException if the current {@link Widget} belongs to no GUI.
	 */
	private void supposeBelongsToGUI() {
		
		//Checks if the current widget belongs to a GUI.
		if (!belongsToGUI()) {
			throw new InvalidStateException(
				this,
				"belongs to no GUI"
			);
		}
	}
}
