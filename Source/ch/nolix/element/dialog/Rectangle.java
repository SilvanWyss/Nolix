/*
 * file:	Rectangle.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	700
 */

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
import ch.nolix.element.basic.ConfigurableElement;
import ch.nolix.element.data.LeftClickCommand;
import ch.nolix.element.data.RightClickCommand;

//class
/**
 * A rectangle is a dialog element and has a specific width and height.
 */
public abstract class Rectangle<R extends Rectangle<R, ?>, RS extends RectangleStructure<?>>
	extends ConfigurableElement<R>
	implements Configurable {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "Rectangle";
	
	//attribute header
	private final static String STATE ="State";
	private final static String NORMAL = "Normal";
	private final static String FOCUS = "Focus";
	private final static String HOVER = "Hover";
		
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
	private LeftClickCommand leftClickCommand;
	private RightClickCommand rightClickCommand;
	
	//constructor
	/**
	 * Creates new rectangle with the given structures.
	 * 
	 * @param normalStructure
	 * @param hoverStructure
	 * @param focusStructure
	 */
	public Rectangle(
		RS normalStructure,
		RS hoverStructure,
		RS focusStructure
	) {
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
			case LeftClickCommand.SIMPLE_CLASS_NAME:
				setLeftClickCommandFromString(attribute.getOneAttributeToString());
				break;
			case RightClickCommand.SIMPLE_CLASS_NAME:
				setRightClickCommandFromString(attribute.getOneAttributeToString());
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
		
		if (hasLeftClickCommand()) {
			attributes.addAtEnd(leftClickCommand.getSpecification());
		}
		
		if (hasRightClickCommand()) {
			attributes.addAtEnd(rightClickCommand.getSpecification());
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
	 * @return true if this rectangle has a left click command
	 */
	public final boolean hasLeftClickCommand() {
		return (leftClickCommand != null);
	}
	
	//method
	/**
	 * @return true if this rectangle has a right click command
	 */
	public final boolean hasRightClickCommand() {
		return (rightClickCommand != null);
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
			if (hasLeftClickCommand()) {
				leftClickCommand.run();
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
	 * Removes the click command of this rectangle.
	 */
	public final void removeLeftClickCommand() {
		leftClickCommand = null;
	}
	
	//method
	/**
	 * Removes the right click command of this rectangle.
	 */
	public final void removeRightClickCommand() {
		rightClickCommand = null;
	}
	
	//method
	/**
	 * Resets this rectangle.
	 */
	public void reset() {
		setNormal();
		removeLeftClickCommand();
		removeRightClickCommand();
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
	public final void setCursorIcon(CursorIcon cursorIcon) {
		this.cursorIcon = cursorIcon;
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
	/**
	 * Sets the left click command of this rectangle.
	 * 
	 * @param leftClickCommand
	 * @param arguments
	 * @throws Exception if the given left click command is not valid
	 */
	@SuppressWarnings("unchecked")
	public final R setLeftClickCommand(String leftClickCommand, String... arguments) {
		
		setLeftClickCommandFromString(new Specification(leftClickCommand, arguments).toString());
		
		return (R)this;
	}
	
	//method
	/**
	 * Sets the left click command of this rectangle from string.
	 * 
	 * @param leftClickCommand
	 * @throws Exception if the given left click command is not valid
	 */
	public final void setLeftClickCommandFromString(String leftClickCommand) {
		this.leftClickCommand = new LeftClickCommand(new RectangleController(this), leftClickCommand);
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
	 * Sets the right click command of this rectangle.
	 * 
	 * @param rightClickCommand
	 * @param arguments
	 * @throws Exception if the given right click command is not valid
	 */
	public final void setRightClickCommand(String rightClickCommand, String... arguments) {
		setRightClickCommandFromString(new Specification(rightClickCommand, arguments).toString());
	}
	
	//method
	/**
	 * Sets the right click command of this rectangle from string.
	 * 
	 * @param rightClickCommand
	 * @throws Exception if the given left click command is not valid
	 */
	public final void setRightClickCommandFromString(String rightClickCommand) {
		this.rightClickCommand = new RightClickCommand(new RectangleController(this), rightClickCommand);
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
