//package declaration
package ch.nolix.element.dialog;

//Java imports
import java.awt.Graphics;
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.specification.Configurable;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.zetaValidator.ZetaValidator;
import ch.nolix.element.basic.ConfigurableElement;

//class
/**
 * A rectangle is a control on a dialog and has a specific width and height.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 960
 */
public abstract class Rectangle<RS extends RectangleStructure<RS>, R extends Rectangle<RS, R>>
extends ConfigurableElement<R> {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "Rectangle";
	
	//attribute headers
	private static final String LEFT_MOUSE_BUTTON_PRESS_COMMAND_HEADER = "LeftMouseButtonPressCommand";
	private static final String LEFT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER = "LeftMouseButtonReleaseCommand";
	private static final String RIGHT_MOUSE_BUTTON_PRESS_COMMAND_HEADER = "RightMouseButtonPressCommand";
	private static final String RIGHT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER = "RightMouseButtonReleaseCommand";
	
	//TODO: Remove.
	private static final String STATE ="State";
	private static final String NORMAL = "Normal";
	private static final String FOCUS = "Focus";
	private static final String HOVER = "Hover";
		
	//attributes
	private Dialog<?> dialog;
	private int relativeXPosition = 0;
	private int relativeYPosition = 0;
	private RectangleState state = RectangleState.Normal;
	private CursorIcon cursorIcon = CursorIcon.Arrow;
	private final RS normalStructure;
	private final RS hoverStructure;
	private final RS focusStructure;
	private int mouseXPosition;
	private int mouseYPosition;
	
	//optional attributes
	private Statement leftMouseButtonPressCommand;
	private Statement leftMouseButtonReleaseCommand;
	private Statement rightMouseButtonPressCommand;
	private Statement rightMouseButtonReleaseCommand;
	
	//constructor
	/**
	 * Creates new rectangle with the given structures.
	 * 
	 * @param normalStructure
	 * @param hoverStructure
	 * @param focusStructure
	 */
	public Rectangle(
		final RS normalStructure,
		final RS hoverStructure,
		final RS focusStructure
	) {
		ZetaValidator.supposeThat(normalStructure).thatIsInstanceOf(RectangleStructure.class).isNotNull();
		ZetaValidator.supposeThat(hoverStructure).thatIsInstanceOf(RectangleStructure.class).isNotNull();
		ZetaValidator.supposeThat(focusStructure).thatIsInstanceOf(RectangleStructure.class).isNotNull();
		
		this.normalStructure = normalStructure;
		this.hoverStructure = hoverStructure;
		this.focusStructure = focusStructure;
		
		getRefHoverStructure().setNormalStructure(getRefNormalStructure());
		getRefFocusStructure().setNormalStructure(getRefNormalStructure());
	}
	
	//method
	/**
	 * Sets the given attribute to this rectangle.
	 * 
	 * @param attributes
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case STATE:
				setState(RectangleState.valueOf(attribute.getOneAttributeToString()));
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
				if (attribute.getHeader().startsWith(NORMAL.toString())) {
					Specification temp = attribute.getClone();
					temp.setHeader(attribute.getHeader().substring(NORMAL.toString().length()));
					getRefNormalStructure().setAttribute(temp);
				}
				else if (attribute.getHeader().startsWith(HOVER.toString())) {
					Specification temp = attribute.getClone();
					temp.setHeader(attribute.getHeader().substring(HOVER.toString().length()));
					getRefHoverStructure().setAttribute(temp);
				}
				else if (attribute.getHeader().startsWith(FOCUS.toString())) {
					Specification temp = attribute.getClone();
					temp.setHeader(attribute.getHeader().substring(FOCUS.toString().length()));
					getRefFocusStructure().setAttribute(temp);
				}
				else {
				
					//Calls method of the base class.
					super.addOrChangeAttribute(attribute);
				}
		}				
	}
	
	//method
	/**
	 * @return true if this rectangle belongs to a dialog
	 */
	public final boolean belongsToDialog() {
		return (dialog != null);
	}

	//method
	/**
	 * @return the attributes of this rectangle
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class.
		List<Specification> attributes = super.getAttributes();
		
		if (!isNormal()) {
			attributes.addAtEnd(new Specification(STATE, state.toString()));
		}
		
		if (cursorIcon != CursorIcon.Arrow) {
			attributes.addAtEnd(cursorIcon.getSpecification());
		}
		
		if (hasLeftMouseButtonPressCommand()) {
			attributes.addAtEnd(
				new Specification(
					LEFT_MOUSE_BUTTON_PRESS_COMMAND_HEADER,
					leftMouseButtonPressCommand.toString()
				)
			);
		}
		
		if (hasLeftMouseButtonReleaseCommand()) {
			attributes.addAtEnd(
				new Specification(
					LEFT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER,
					leftMouseButtonReleaseCommand.toString()
				)
			);
		}
		
		if (hasRightMouseButtonPressCommand()) {
			attributes.addAtEnd(
				new Specification(
					RIGHT_MOUSE_BUTTON_PRESS_COMMAND_HEADER,
					rightMouseButtonPressCommand.toString()
				)
			);	
		}
		
		if (hasRightMouseButtonReleaseCommand()) {
			attributes.addAtEnd(
				new Specification(
					RIGHT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER,
					rightMouseButtonReleaseCommand.toString()
				)
			);
		}
	
		//Adds normal attributes.		
		List<Specification> structureAttributes = getRefNormalStructure().getAttributes();
		structureAttributes.forEach(a -> a.setHeader(NORMAL + a.getHeader()));
		attributes.addAtEnd(structureAttributes);
		
		//Adds hover attributes.
		structureAttributes = getRefHoverStructure().getAttributes();
		structureAttributes.forEach(a -> a.setHeader(HOVER + a.getHeader()));
		attributes.addAtEnd(structureAttributes);
		
		//Adds focus attributes.
		structureAttributes = getRefFocusStructure().getAttributes();
		structureAttributes.forEach(a -> a.setHeader(FOCUS + a.getHeader()));
		attributes.addAtEnd(structureAttributes);
		
		return attributes;
	}
	
	//method
	/**
	 * @return the configurable objects of this rectangle
	 */
	public List<Configurable> getRefConfigurables() {
		return new List<Configurable>();
	}
	
	//method
	/**
	 * @return the focus structure of this rectangle
	 */
	public final RS getRefFocusStructure() {
		return focusStructure;
	}
	
	//method
	/**
	 * @return the hover structure of this rectangle
	 */
	public final RS getRefHoverStructure() {
		return hoverStructure;
	}
	
	//method
	/**
	 * @return the normal structure of this rectangle
	 */
	public final RS getRefNormalStructure() {
		return normalStructure;
	}
	
	//method
	/**
	 * @return the state of this rectangle
	 */
	public final RectangleState getState() {
		return state;
	}
	
	//method
	/**
	 * @return true if this rectangle has a left mouse button press command.
	 */
	public final boolean hasLeftMouseButtonPressCommand() {
		return (leftMouseButtonPressCommand != null);
	}
	
	//method
	/**
	 * @return true if this rectangle has a left mouse button release command.
	 */
	public final boolean hasLeftMouseButtonReleaseCommand() {
		return (leftMouseButtonReleaseCommand != null);
	}
	
	//method
	/**
	 * @return true if this rectangle has a right mouse button press command.
	 */
	public final boolean hasRightMouseButtonPressCommand() {
		return (rightMouseButtonPressCommand != null);
	}
	
	//method
	/**
	 * @return true if this rectangle has a right mouse button release command.
	 */
	public final boolean hasRightMouseButtonReleaseCommand() {
		return (rightMouseButtonReleaseCommand != null);
	}
	
	//method
	/**
	 * @return true if this rectangle is collapsed
	 */
	public final boolean isCollapsed() {
		return (state == RectangleState.Collapsed);
	}
	
	//method
	/**
	 * @return true if this rectangle is disabled
	 */
	public final boolean isDisabled() {
		return (state == RectangleState.Disabled);
	}
	
	//method
	/**
	 * @return true if this rectangle is focused
	 */
	public final boolean isFocused() {
		return (state == RectangleState.Focused);
	}
	
	//method
	/**
	 * @return true if this rectangle is hovered
	 */
	public final boolean isHovered() {
		return (state == RectangleState.Hovered);
	}
	
	//method
	/**
	 * @return true if this rectangle is hover focues
	 */
	public final boolean isHoverFocused() {
		return (state == RectangleState.HoverFocused);
	}
	
	//method
	/**
	 * @return true if this rectangle is invisble
	 */
	public final boolean isInvisible() {
		return (state == RectangleState.Invisible);
	}
	
	//method
	/**
	 * @return true if this rectangle is normal
	 */
	public final boolean isNormal() {
		return (state == RectangleState.Normal);
	}
	
	//method
	/**
	 * Lets this rectangle note a key press.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyPress(KeyEvent keyEvent) {}
	
	//method
	/**
	 * Lets this rectangle note a key typing.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyTyping(KeyEvent keyEvent) {}
	
	//method
	/**
	 * Lets this rectangle note a left mouse button press.
	 */
	public void noteLeftMouseButtonPress() {
		
		//Updates the state of this rectangle.
		if (isPointed()) {	
			setHoverFocused();
		}
		else {
			if (isFocused() || isHoverFocused()) {
				setNormal();
			}
		}
		
		if (isPointed()) {
			if (hasLeftMouseButtonPressCommand()) {
				getRefDialog().getRefController().run(leftMouseButtonPressCommand);
			}
		}
	}
	
	//method
	/**
	 * Lets this rectangle note a left mouse button release.
	 */
	public void noteLeftMouseButtonRelease() {}
	
	//method
	/**
	 * Lets this rectangle note a mouse move.
	 */
	public void noteMouseMove() {
		
		//Updates the state of this rectangle.
		if (isPointed()) {
			if (isNormal()) {
				setHovered();
			}
			else if (isFocused()) {
				setHoverFocused();
			}
		}
		else {
			if (isHovered()) {
				setNormal();
			}
			if (isHoverFocused()) {
				setFocused();
			}
		}
		
		if (isPointed()) {
			getRefDialog().getRefFrameContext().setCurrentCursorIcon(cursorIcon);
		}
	}
	
	//method
	/**
	 * Lets this rectangle note a right mouse button press.
	 */
	public void noteRightMouseButtonPress() {}
	
	//method
	/**
	 * Lets this rectangle note a right mouse button release.
	 */
	public void noteRightMouseButtonRelease() {}
	
	//method
	/**
	 * Removes the left mouse button press command of this rectangle.
	 */
	public final void removeLeftMouseButtonPressCommand() {
		leftMouseButtonPressCommand = null;
	}
	
	//method
	/**
	 * Removes the left mouse button release command of this rectangle.
	 */
	public final void removeLeftMouseButtonReleaseCommand() {
		leftMouseButtonReleaseCommand = null;
	}
	
	//method
	/**
	 * Removes the right mouse button press command of this rectangle.
	 */
	public final void removeRightMouseButtonPressCommand() {
		rightMouseButtonPressCommand = null;
	}
	
	//method
	/**
	 * Removes the right mouse button release command of this rectangle.
	 */
	public final void removeRightMouseButtonReleaseCommand() {
		rightMouseButtonReleaseCommand = null;
	}
	
	//method
	/**
	 * Resets this rectangle.
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
	 * Resets the configuration of this rectangle.
	 */
	public void resetConfiguration() {		
		setCursorIcon(CursorIcon.Arrow);
	}
	
	//method
	/**
	 * Sets this rectangle collapsed.
	 */
	public final void setCollapsed() {
		state = RectangleState.Collapsed;
	}
	
	//method
	/**
	 * Sets the cursor icon of this rectangle.
	 * 
	 * @param cursorIcon
	 */
	@SuppressWarnings("unchecked")
	public final R setCursorIcon(CursorIcon cursorIcon) {
		
		this.cursorIcon = cursorIcon;
		
		return (R)this;
	}
	
	//method
	/**
	 * Sets this rectangle disabled.
	 */
	public final void setDisabled() {
		state = RectangleState.Disabled;
	}
	
	//method
	/**
	 * Sets this rectangle focused.
	 */
	public final void setFocused() {
		state = RectangleState.Focused;
	}
	
	//method
	/**
	 * Sets this rectangle hover focused.
	 */
	public final void setHoverFocused() {
		state = RectangleState.HoverFocused;
	}
	
	//method
	/**
	 * Sets this rectangle hovered.
	 */
	public final void setHovered() {
		state = RectangleState.Hovered;
	}
	
	//metohd
	/**
	 * Sets this rectangle invisible.
	 */
	public final void setInvisible() {
		state = RectangleState.Invisible;
	}
	
	//method
	@SuppressWarnings("unchecked")
	/**
	 * Sets the left mouse button press command of this rectangle.
	 * 
	 * @param leftMouseButtonPressCommand
	 * @return this rectangle.
	 * @throws NullArgumentException if the given left mouse button press command is null.
	 */
	public final R setLeftMouseButtonPressCommand(final Statement leftMouseButtonPressCommand) {
		
		//Checks if the given left mouse button press command is not null.
		ZetaValidator.supposeThat(leftMouseButtonPressCommand)
		.thatIsNamed("left mouse button press command")
		.isNotNull();
		
		this.leftMouseButtonPressCommand = leftMouseButtonPressCommand;
		
		return (R)this;
	}
	
	//method
	/**
	 * Sets the left mouse button press command of this rectangle.
	 * 
	 * @param leftMouseButtonPressCommand
	 * @return this rectangle.
	 * @throws InvalidArgumentException if the given left mouse button press command is not valid.
	 */
	public final R setLeftMouseButtonPressCommand(final String leftMouseButtonPressCommand) {
		return setLeftMouseButtonPressCommand(new Statement(leftMouseButtonPressCommand));
	}

	//method
	/**
	 * Sets the given left mouse button press command, that has the given arguments, to this rectangle.
	 * 
	 * @param leftMouseButtonPressCommand
	 * @return this rectangle.
	 * @throws InvalidArgumentException if the given left mouse button press command or the given arguments are not valid.
	 */
	public final R setLeftMouseButtonPressCommand(
		final String leftMouseButtonPressCommand,
		final String... arguments
	) {
		return setLeftMouseButtonPressCommand(
			new Specification(leftMouseButtonPressCommand, arguments).toString()
		);
	}
	
	//method
	/**
	 * Sets the left mouse button release command of this rectangle.
	 * 
	 * @param leftMouseButtonReleaseCommand
	 * @return this rectangle.
	 * @throws NullArgumentException if the given left mouse button release command is null.
	 */
	@SuppressWarnings("unchecked")
	public final R setLeftMouseButtonReleaseCommand(final Statement leftMouseButtonReleaseCommand) {
		
		//Checks if the given left mouse button release command is not null.
		ZetaValidator
		.supposeThat(leftMouseButtonReleaseCommand)
		.thatIsNamed("left mouse button release command")
		.isNotNull();
		
		this.leftMouseButtonReleaseCommand = leftMouseButtonReleaseCommand;
		
		return (R)this;
	}
	
	//method
	/**
	 * Sets the left mouse button release command of this rectangle.
	 * 
	 * @param leftMouseButtonReleaseCommand
	 * @return this rectangle.
	 * @throws InvalidArgumentException if the given left mouse button release command is not valid.
	 */
	public final R setLeftMouseButtonReleaseCommand(final String leftMouseButtonReleaseCommand) {
		return setLeftMouseButtonReleaseCommand(new Statement(leftMouseButtonReleaseCommand));
	}
	
	//method
	/**
	 * Sets the given left mouse release command, that has the given attributes, to this rectangle.
	 * 
	 * @param leftMouseButtonReleaseCommand
	 * @param arguments
	 * @return this rectangle.
	 * @throws InvalidArgumentException if the given left mouse button release command or the given arguments are not valid.
	 */
	public final R setLeftMouseButtonReleaseCommand(
		final String leftMouseButtonReleaseCommand,
		final String... arguments
	) {
		return setLeftMouseButtonReleaseCommand(
			new Specification(leftMouseButtonReleaseCommand, arguments).toString()
		);
	}
	
	//method
	/**
	 * Sets the right mouse button press command of this rectangle.
	 * 
	 * @param rightMouseButtonPressCommand
	 * @return this rectangle.
	 * @throws NullArgumentException if the given right mouse button press command is null.
	 */
	@SuppressWarnings("unchecked")
	public final R setRightMouseButtonPressCommand(final Statement rightMouseButtonPressCommand) {
		
		//Checks if the given right mouse button press command is not null.
		ZetaValidator
		.supposeThat(rightMouseButtonPressCommand)
		.thatIsNamed("right mouse button press command")
		.isNotNull();
		
		this.rightMouseButtonPressCommand = rightMouseButtonPressCommand;
		
		return (R)this;
	}
	
	//method
	/**
	 * Sets the right mouse button press command of this rectangle.
	 * 
	 * @param rightMouseButtonPressCommand
	 * @return this rectangle.
	 * @throws InvalidArgumentException if the givne right mouse button press command is not valid.
	 */
	public final R setRightMouseButtonPressCommand(final String rightMouseButtonPressCommand) {
		return setRightMouseButtonPressCommand(new Statement(rightMouseButtonPressCommand));
	}
	
	//method
	/**
	 * Sets the given right mouse button press command, that has the given arguments, to this rectangle.
	 * 
	 * @param rightMouseButtonPressCommand
	 * @param arguments
	 * @return this rectangle.
	 * @throws InvalidArgumentException if the given right mouse button press command or the given arguments are not valid.
	 */
	public final R setRightMouseButtonPressCommand(
		final String rightMouseButtonPressCommand,
		final String... arguments
	) {
		return setRightMouseButtonPressCommand(
			new Specification(rightMouseButtonPressCommand, arguments).toString()	
		);
	}
	
	//method
	/**
	 * Sets the right mouse button release command of this rectangle.
	 * 
	 * @param rightMouseButtonReleaseCommand
	 * @return this rectangle.
	 * @throws NullArgumentException if the given right mouse button release command is null.
	 */
	@SuppressWarnings("unchecked")
	public final R setRightMouseButtonReleaseCommand(final Statement rightMouseButtonReleaseCommand) {
		
		//Checks if the given right mouse button release command is not null.
		ZetaValidator
		.supposeThat(rightMouseButtonReleaseCommand)
		.thatIsNamed("right mouse button release command")
		.isNotNull();
		
		this.rightMouseButtonReleaseCommand = rightMouseButtonReleaseCommand;
		
		return (R)this;
	}
	
	//method
	/**
	 * Sets the right mouse button release command of this rectangle.
	 * 
	 * @param rightMouseButtonReleaseCommand
	 * @return this rectangle.
	 * @throws InvalidArgumentException if the given right mouse button release command is not valid.
	 */
	public final R setRightMouseButtonReleaseCommand(final String rightMouseButtonReleaseCommand) {
		return setRightMouseButtonReleaseCommand(new Statement(rightMouseButtonReleaseCommand));
	}
	
	//method
	/**
	 * Sets the givne right mouse button release command, that has the given arguments, to this rectangle.
	 * 
	 * @param rightMouseButtonReleaseCommand
	 * @param arguments
	 * @return this rectangle.
	 * @throws InvalidArgumentException if the given right mouse button release command or the given argumnets are not valid.
	 */
	public final R setRightMouseButtonReleaseCommand(
		final String rightMouseButtonReleaseCommand,
		final String... arguments
	) {
		return setRightMouseButtonReleaseCommand(
			new Specification(rightMouseButtonReleaseCommand, arguments).toString()	
		);
	}
	
	//method
	/**
	 * Sets this rectangle normal.
	 * 
	 * @throws Exception if an error occurs
	 */
	public final void setNormal() {
		state = RectangleState.Normal;
	}
	
	//method
	/**
	 * Sets the state of this rectangle.
	 * 
	 * @param state
	 */
	@SuppressWarnings("unchecked")
	public final R setState(RectangleState state) {
		
		this.state = state;
		
		return (R)this;
	}
	
	//method
	/**
	 * @param dialog
	 * @return true if this rectangle belongs to the given dialog
	 */
	protected final boolean belongsToDialog(Dialog<?> dialog) {
		return (this.dialog == dialog);
	}
	
	//method
	/**
	 * @return the current height of this rectangle
	 */
	protected final int getHeight() {
		
		if (isCollapsed()) {
			return 0;
		}
		
		return getHeightWhenNotCollapsed();
	}
	
	//abstract method
	/**
	 * @return the current height of this rectangle when it is s not collapsed
	 */
	protected abstract int getHeightWhenNotCollapsed();
	
	//method
	/**
	 * @return the current width of this rectangle
	 */
	protected final int getWidth() {
		
		if (isCollapsed()) {
			return 0;
		}
		
		return getWidthWhenNotCollapsed();
	}
	
	//abstract method
	/**
	 * @return the current width of this rectangle for the case when it is not collapsed
	 */
	protected abstract int getWidthWhenNotCollapsed();
	
	//method
	/**
	 * @return the x-position of the mouse of this rectangle
	 */
	protected final int getMouseXPosition() {
		return mouseXPosition;
	}
	
	//method
	/**
	 * @return the y-position of the mouse of this rectangle
	 */
	protected final int getMouseYPosition() {
		return mouseYPosition;
	}
	
	//method
	/**
	 * @return the current structure of this rectangle
	 * @throws Exception if this rectangle has no current structure
	 */
	protected final RS getRefCurrentStructure() {
		switch (state) {
			case Normal:
				return getRefNormalStructure();
			case Hovered:
				return getRefHoverStructure();
			case Focused:
				return getRefFocusStructure();
			case HoverFocused:
				return getRefHoverStructure();
			default:
				throw new UnexistingAttributeException(this, "current structure");
		}
	}
	
	//method
	/**
	 * @return the dialog this rectangle belongs to
	 */
	protected final Dialog<?> getRefDialog() {
		return dialog;
	}
	
	//method
	/**
	 * @return the relative x position of this rectangle
	 */
	protected final int getRelativeXPosition() {
		return relativeXPosition;
	}
	
	//method
	/**
	 * @return the relative y position of this rectangle
	 */
	protected final int getRelativeYPosition() {
		return relativeYPosition;
	}
	
	//method
	/**
	 * @return true if this rectangle is pointed by the mouse
	 */
	protected final boolean isPointed() {
		return (
			mouseXPosition >= 0 &&	//First, checks the conditions that can be calculated easily.
			mouseYPosition >= 0 &&
			mouseXPosition < getWidth() &&
			mouseYPosition < getHeight()
		);		
	}
	
	
	
	//method
	/**
	 * Paints this rectangle using the given graphics and relative position.
	 * 
	 * @param graphics
	 * 
	 * This method does not translate the given graphics in the end.
	 */
	protected final void paintUsingRelativePosition(Graphics graphics) {
		
		if (getState() != RectangleState.Invisible) {
			graphics.translate(getRelativeXPosition(), getRelativeYPosition());
			paint(getRefCurrentStructure(), graphics);
			graphics.translate(-getRelativeXPosition(), -getRelativeYPosition());
			
			this.getRefDialog().getRefFrameContext().setCurrentCursorIcon(cursorIcon);
		}
	}
	
	//abstract method
	/**
	 * Paints this rectangle using the given rectangle structure and graphics.
	 * 
	 * @param rectangleStructure
	 * @param graphics
	 * 
	 * This method does not translate the given graphics in the end.
	 */
	protected abstract void paint(RS rectangleStructure, Graphics graphics);
	
	//method
	/**
	 * Sets the dialog this rectangle will belong to.
	 * 
	 * @param dialog
	 * @throws Exception if:
	 *  -the given dialog is null
	 *  -this rectangle already belongs to an other dialog
	 */
	protected void setDialog(Dialog<?> dialog) {
		
		//Calls method of the base class.
		setSearchContainer(dialog);
		
		this.dialog = dialog;
	}
	
	//method
	/**
	 * Sets the relative mouse position of this rectangle.
	 * 
	 * @param relativeMouseXPosition
	 * @param relativeMouseYPosition
	 */
	protected void setRelativeMousePosition(final int relativeMouseXPosition, final int relativeMouseYPosition) {
		mouseXPosition = relativeMouseXPosition - getRelativeXPosition();
		mouseYPosition = relativeMouseYPosition - getRelativeYPosition();
	}
	
	//method
	/**
	 * Sets the relative position of this rectangle.
	 * 
	 * @param relativeXPosition
	 * @param relativeYPosition
	 */
	protected void setRelativePosition(int relativeXPosition, int relativeYPosition) {
		this.relativeXPosition = relativeXPosition;
		this.relativeYPosition = relativeYPosition;
	}
}
