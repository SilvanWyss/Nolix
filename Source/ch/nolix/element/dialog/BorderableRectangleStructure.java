/*
 * file:	BorderableRectangleStructure.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	700
 */

//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.exception.ArgumentName;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.specification.Specification;
import ch.nolix.element.basic.Color;
import ch.nolix.element.data.BackgroundColor;

//class
public abstract class BorderableRectangleStructure<BRS extends BorderableRectangleStructure<?>>
extends RectangleStructure<BRS> {
	
	//attribute headers
	private final static String BORDER_SIZE = "BorderSize";
	private final static String BORDER_COLOR = "BorderColor";
	private static final String LEFT_BORDER_SIZE = "LeftBorderSize";
	private static final String LEFT_BORDER_COLOR = "LeftBorderColor";
	private static final String RIGHT_BORDER_SIZE = "RightBorderSize";
	private static final String RIGHT_BORDER_COLOR = "RightBorderColor";
	private static final String TOP_BORDER_SIZE = "TopBorderSize";
	private static final String TOP_BORDER_COLOR = "TopBorderColor";
	private static final String BOTTOM_BORDER_SIZE = "BottomBorderSize";
	private static final String BOTTOM_BORDER_COLOR = "BottomBorderColor";	
		
	//optional attributes
	private BackgroundColor backgroundColor;
	private BorderableRectangleBorder leftBorder;
	private BorderableRectangleBorder rightBorder;
	private BorderableRectangleBorder topBorder;
	private BorderableRectangleBorder bottomBorder;
	
	//method
	/**
	 * @return the recursive bottom border size of this borderable rectangle structure
	 */
	public final int getRecBottomBorderSize() {
		
		//Handles the case if this borderable rectangle structure has a bottom border.
		if (hasBottomBorder()) {
			return bottomBorder.getSize();
		}
		
		//Handles the case if this borderable rectangle structure has no bottom border but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRecBottomBorderSize();
		}
		
		//Handles the case if this borderable rectangle structure has no bottom border and no normal structure.
		return 0;
	}
	
	//method
	/**
	 * @return the recursive left border size of this borderable rectangle structure
	 */
	public final int getRecLeftBorderSize() {
		
		//Handles the case if this borderable rectangle structure has a left border.
		if (hasLeftBorder()) {
			return leftBorder.getSize();
		}
		
		//Handles the case if this borderablre rectangle structure has no left border but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRecLeftBorderSize();
		}
		
		//Handles the case if this borderable rectangle structure has no right border and no normal structure.
		return 0;
	}
	
	//method
	/**
	 * @return the recursive right border size of this borderable rectangle structure
	 */
	public final int getRecRightBorderSize() {
		
		//Handles the case if this borderable rectangle structure has a right border.
		if (hasRightBorder()) {
			return rightBorder.getSize();
		}
		
		//Handles the case if this borderable rectangle structure has no right border but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRecRightBorderSize();
		}
		
		//Handles the case if this borderable rectangle structure has no right border and no normal structure.
		return 0;
	}
	
	//method
	/**
	 * @return the recursive top border ssize of this borderable rectangle structure
	 */
	public final int getRecTopBorderSize() {
		
		//Handles the case if this borderable rectangle structure has a top border.
		if (hasTopBorder()) {
			return topBorder.getSize();
		}
		
		//Handles the case if this borderable rectangle structure has no top border but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRecTopBorderSize();
		}
		
		//Handles the case if this borderable rectangle structure has no top border and no normal structure.
		return 0;
	}
	
	//method
	/**
	 * @return the recursive background color of this borderable rectange structure
	 * @throws Exception if this borderable rectangle has no recursive background color
	 */
	public final BackgroundColor getRefRecBackgroundColor() {
		
		//Handles the case if this borderable rectangle structure has a background color.
		if (hasBackgroundColor()) {
			return backgroundColor.getCopy();
		}
		
		//Handles the case if this borderable rectangle has no background color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRefRecBackgroundColor();
		}
		
		//Handles the case if this borderable rectangle structure has no background color and no normal structure.
		throw new UnexistingAttributeException(this, "background color");
	}
	
	//method
	/**
	 * @return the recursive bottom border color of this borderable rectangle structure
	 * @throws Exception if this borderable rectangle structure has no recursive bottom border
	 */
	public final Color getRefRecBottomBorderColor() {
		
		//Handles the case if this borderable rectangle structure has a bottom border.
		if (hasBottomBorder()) {
			return bottomBorder.getRefColor();
		}
		
		//Handles the case if this borderable rectangle structure has no bottom border but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRefRecBottomBorderColor();
		}
		
		//Handles the case if this borderable rectangle structure has no bottom border and no normal structure.
		throw new UnexistingAttributeException("borderable rectangle", "bottom border");
	}
		
	//method
	/**
	 * @return the recursive left border color of this borderable rectangle structure
	 * @throws Exception if this borderable rectangle structure has no recursive left border
	 */
	public final Color getRefRecLeftBorderColor() {
		
		//Handles the case if this borderable rectangle structure has a left border.
		if (hasLeftBorder()) {
			return leftBorder.getRefColor();
		}
		
		//Handles the case if this borderable rectangle structure has no left border but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRefRecLeftBorderColor();
		}
		
		//Handles the case if this borderable rectangle structure has no left border and no normal structure.
		throw new UnexistingAttributeException(this, "left border");
	}
	
	//method
	/**
	 * @return the recursive right border color of this borderable rectangle structure
	 * @throws Exception if this borderable rectangle structure has no recursive right border color
	 */
	public final Color getRefRecRightBorderColor() {
		
		//Handles the case if this borderable rectangle structure has a right border.
		if (hasRightBorder()) {
			return rightBorder.getRefColor();
		}
		
		//Handles the case if this borderable rectangle structure has no right border but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRefRecRightBorderColor();
		}
		
		//Handles the case if this borderable rectangle structure has no right border and no normal structure.
		throw new UnexistingAttributeException(this, "right border");
	}
	
	//method
	/**
	 * @return the recursive top border color of this borderable rectangle structure
	 * @throws Exception if this borderable rectangle structure has no recursive top border color
	 */
	public final Color getRefRecTopBorderColor() {
		
		//Handles the case if this borderable rectangle stucture has a top border.
		if (hasTopBorder()) {
			return topBorder.getRefColor();
		}
		
		//Handles the case if this borderable rectangle structure has no top border but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRefRecTopBorderColor();
		}
		
		//Handles the case if this borderable rectangle structure has no top border and no normal structure.
		throw new UnexistingAttributeException(this, "top border");
	}
	
	//method
	/**
	 * @return true if this borderable rectangle structure has a background color
	 */
	public final boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle structre has a bottom border
	 */
	public final boolean hasBottomBorder() {
		return (bottomBorder != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle structure has a recursive background color
	 */
	public final boolean hasRecBackgroundColor() {
		
		//Handles the case if this borderable rectangle structure has a background color.
		if (hasBackgroundColor()) {
			return true;
		}
		
		//Handles the case if this borderable rectangle structure has no background color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().hasBackgroundColor();
		}
		
		//Handles the case if this borderable rectangle structure has no background color border and no normal structure.
		return false;
	}	
	
	//method
	/**
	 * @return true if this borderable rectangle structure has a recursive bottom border
	 */
	public final boolean hasRecBottomBorder() {
		
		//Handles the case if this borderable rectangle structure has a bottom border.
		if (hasBottomBorder()) {
			return true;
		}
		
		//Handles the case if this borderablre rectangle structure has no bottom border but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().hasBottomBorder();
		}
		
		//Handles the case if this borderable rectangle structure has no bottom border and no normal structure.//Handles the case if this borderable rectangle structure has neither a bottom border nor a normal structure.
		return false;
	}
	
	//method
	/**
	 * @return true if this borderable rectangle structure has a recursive left border
	 */
	public final boolean hasRecLeftBorder() {
		
		//Handles the case if this borderable rectangle structure has a left border.
		if (hasLeftBorder()) {
			return true;
		}
		
		//Handles the case if this borderable rectangle structure has no left border but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().hasLeftBorder();
		}
		
		//Handles the case if this borderable rectangle structure has no left border and no normal structure.
		return false;
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a recursive right border
	 */
	public final boolean hasRecRightBorder() {
		
		//Handles the case if this borderable rectangle structure has a right border.
		if (hasRightBorder()) {
			return true;
		}
		
		//Handles the case if this borderable rectangle structure has no right border, but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().hasRightBorder();
		}
		
		//Handles the case if this borderable rectangle has no right border and no normal structure.
		return false;
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a recursive top border
	 */
	public final boolean hasRecTopBorder() {
		
		//Handles the case if this rectangle structure has a top border.
		if (hasTopBorder()) {
			return true;
		}
		
		//Handles the case if this rectangle structure has no top border but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().hasTopBorder();
		}
		
		//Handles the case if this rectangle structure has no top border and no normal structure.
		return false;
	}
	
	//method
	/**
	 * @return true if this borderable rectangle structure has a left border
	 */
	public final boolean hasLeftBorder() {
		return (leftBorder != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle structure has a right border
	 */
	public final boolean hasRightBorder() {
		return (rightBorder != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle structure has a top border
	 */
	public final boolean hasTopBorder() {
		return (topBorder != null);
	}
	
	//method
	/**
	 * Removes the background color of this borderable rectangle structure.
	 */
	public final void removeBackgroundColor() {
		backgroundColor = null;
	}
	
	//method
	/**
	 * Removes the border of this borderable rectangle structure.
	 */
	public final void removeBorder() {
		removeLeftBorder();
		removeRightBorder();
		removeTopBorder();
		removeBottomBorder();
	}
	
	//method
	/**
	 * Removes the bottom border of this borderable rectangle structure.
	 */
	public final void removeBottomBorder() {
		bottomBorder = null;
	}
	
	//method
	/**
	 * Removes the left border of this borderable rectangle structure.
	 */
	public final void removeLeftBorder() {
		leftBorder = null;
	}
	
	//method
	/**
	 * Removes the right border of this borderable rectangle structure.
	 */
	public final void removeRightBorder() {
		rightBorder = null;
	}
	
	//method
	/**
	 * Removes the top border of this borderable rectangle structure.
	 */
	public final void removeTopBorder() {
		topBorder = null;
	}
	
	//method
	/**
	 * Sets the background color of this borderable rectangle structure.
	 * 
	 * @param backgroundColor
	 * @throws Exception if the given background color is no color name or no true color value
	 */
	public final void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = new BackgroundColor(backgroundColor);
	}
	
	//method
	/**
	 * Sets the background color of this borderable rectangle structure.
	 * 
	 * @param backgroundColor
	 * @throws InvalidArgumentException if the given background color is no true color value.
	 */
	public final void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = new BackgroundColor(backgroundColor);
	}
	
	//method
	/**
	 * Sets the color of the border of this borderable rectangle structure.
	 * 
	 * @param borderColor
	 * @throws Exception if the given border color is no color name or no true color value
	 */
	public final void setBorderColor(String borderColor) {
		setLeftBorderColor(borderColor);
		setRightBorderColor(borderColor);
		setTopBorderColor(borderColor);
		setBottomBorderColor(borderColor);
	}
	
	//method
	/**
	 * Sets the size of the border of this borderbale rectangle structure.
	 * 
	 * @param borderSize
	 * @throws Exception if the given border size is not positive
	 */
	public final void setBorderSize(int borderSize) {
		setLeftBorderSize(borderSize);
		setRightBorderSize(borderSize);
		setTopBorderSize(borderSize);
		setBottomBorderSize(borderSize);
	}
	
	//method
	/**
	 * Sets the color of the bottom border of this borderable rectangle structure.
	 * Creates new bottom border if this rectangle structure has no bottom border.
	 * 
	 * @param bottomBorderColor
	 * @throws Exception if the given bottom border color is no color name or no true color value
	 */
	public final void setBottomBorderColor(String bottomBorderColor) {
		createBottomBorderIfHasNoBottomBorder();
		bottomBorder.setColor(bottomBorderColor);
	}
	
	//method
	/**
	 * Sets the size of the bottom border of this borderable rectangle structure.
	 * Creates new bottom border if this borderable rectangle structure has no bottom border.
	 * 
	 * @param bottomBorderSize
	 * @throws Exception if the given bottom border size is not positive
	 */
	public final void setBottomBorderSize(int bottomBorderSize) {
		createBottomBorderIfHasNoBottomBorder();
		bottomBorder.setSize(bottomBorderSize);
	}
	
	//method
	/**
	 * Sets a default border to this borderable rectangle structure.
	 */
	public final void setDefaultBorder() {
		setBorderSize(BorderableRectangleBorder.DEFAULT_SIZE);
		setBorderColor(BorderableRectangleBorder.DEFAULT_COLOR);
	}
	
	//method
	/**
	 * Sets the color of the left border of this borderable rectangle structure.
	 * Creates new left border if this borderable rectangle structure has no left border.
	 * 
	 * @param leftBorderColor
	 * @throws Exception if the given left border color is no color name or no true color value
	 */
	public final void setLeftBorderColor(String leftBorderColor) {
		createLeftBorderIfHasNoLeftBorder();
		leftBorder.setColor(leftBorderColor);
	}
	
	//method
	/**
	 * Sets the size of the left border of this borderable rectangle structure.
	 * Creates new left border if this rectangle structure has no left border.
	 * 
	 * @param leftBorderSize
	 * @throws Exception if the given left border size is not positive
	 */
	public final void setLeftBorderSize(int leftBorderSize) {
		createLeftBorderIfHasNoLeftBorder();
		leftBorder.setSize(leftBorderSize);
	}
	
	//method
	/**
	 * Sets the color of the right border of this borderable rectangle structure.
	 * Creates new right border if this right borderable rectangle structure has no right border.
	 * 
	 * @param rightBorderColor
	 * @throws Exception if the given right border color is no color name or no true color value
	 */
	public final void setRightBorderColor(String rightBorderColor) {
		createRightBorderIfHasNoRightBorder();
		rightBorder.setColor(rightBorderColor);
	}
	
	//method
	/**
	 * Sets the size of the right border of this borderable rectangle structure.
	 * Creates new right border if this borderable rectangle structure has no right border.
	 * 
	 * @param rightBorderSize
	 * @throws Exception if the given right border size is not positive
	 */
	public final void setRightBorderSize(int rightBorderSize) {
		createRightBorderIfHasNoRightBorder();
		rightBorder.setSize(rightBorderSize);
	}
	
	//method
	/**
	 * Sets the color of the top border of this borderable rectangle structure.
	 * Creates new top border if this borderable rectangle structure has no top border.
	 * 
	 * @param topBorderColor
	 * @throws Exception if the given top border color is no color name or no true color value
	 */
	public final void setTopBorderColor(String topBorderColor) {
		createTopBorderIfHasNoTopBorder();
		topBorder.setColor(topBorderColor);
	}
	
	//method
	/**
	 * Sets the size of the top border of this borderable rectangle structure.
	 * Creates new top border if this borderable rectangle structure has no top border.
	 * 
	 * @param topBorderSize
	 * @throws Exception if the given top border is not positive
	 */
	public final void setTopBorderSize(int topBorderSize) {
		createTopBorderIfHasNoTopBorder();
		topBorder.setSize(topBorderSize);
	}
	
	//method
	/**
	 * Applies the default configuration to this borderable rectangle structure.
	 */
	protected void applyDefaultConfiguration() {
		removeBackgroundColor();
		removeBorder();
	}
	
	//method
	/**
	 * @return the attributes of this borderable rectangle structure
	 */
	protected List<Specification> getAttributes() {
		
		//Calls method of the base class.
		List<Specification> attributes = super.getAttributes();
		
		if (hasBackgroundColor()) {
			attributes.addAtEnd(backgroundColor.getSpecification());
		}
		
		if (hasLeftBorder()) {
			attributes.addAtEnd(
				leftBorder.getRefSize().getSpecificationAs(LEFT_BORDER_SIZE),
				leftBorder.getRefColor().getSpecificationAs(LEFT_BORDER_COLOR)
			);
		}
		
		if (hasRightBorder()) {
			attributes.addAtEnd(
				rightBorder.getRefSize().getSpecificationAs(RIGHT_BORDER_SIZE),
				rightBorder.getRefColor().getSpecificationAs(RIGHT_BORDER_COLOR)
			);
		}
		
		if (hasTopBorder()) {
			attributes.addAtEnd(
				topBorder.getRefSize().getSpecificationAs(TOP_BORDER_SIZE),
				topBorder.getRefColor().getSpecificationAs(TOP_BORDER_COLOR)
			);
		}
		
		if (hasBottomBorder()) {
			attributes.addAtEnd(
				bottomBorder.getRefSize().getSpecificationAs(BOTTOM_BORDER_SIZE),
				bottomBorder.getRefColor().getSpecificationAs(BOTTOM_BORDER_COLOR)
			);
		}

		return attributes;
	}
	
	//method
	/**
	 * Sets the given attribute to this borderable rectangle structure.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	protected void setAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case BackgroundColor.SIMPLE_CLASS_NAME:
				setBackgroundColor(attribute.getOneAttributeToString());
				break;
			case BORDER_SIZE:
				setBorderSize(attribute.getOneAttributeToInteger());
				break;
			case BORDER_COLOR:
				setBorderColor(attribute.getOneAttributeToString());
				break;
			case LEFT_BORDER_SIZE:
				setLeftBorderSize(attribute.getOneAttributeToInteger());
				break;
			case LEFT_BORDER_COLOR:
				setLeftBorderColor(attribute.getOneAttributeToString());
				break;
			case RIGHT_BORDER_SIZE:
				setRightBorderSize(attribute.getOneAttributeToInteger());
				break;
			case RIGHT_BORDER_COLOR:
				setRightBorderColor(attribute.getOneAttributeToString());
				break;
			case TOP_BORDER_SIZE:
				setTopBorderSize(attribute.getOneAttributeToInteger());
				break;
			case TOP_BORDER_COLOR:
				setTopBorderColor(attribute.getOneAttributeToString());
				break;
			case BOTTOM_BORDER_SIZE:
				setBottomBorderSize(attribute.getOneAttributeToInteger());
				break;
			case BOTTOM_BORDER_COLOR:
				setBottomBorderColor(attribute.getOneAttributeToString());
				break;
			default:
				throw new InvalidArgumentException(
					new ArgumentName("attribute"),
					new Argument(attribute)
				);
		}
	}
	
	//method
	/**
	 * Creates new bottom border if this borderable rectangle structure has no bottom border.
	 */
	private final void createBottomBorderIfHasNoBottomBorder() {
		if (!hasBottomBorder()) {
			bottomBorder = new BorderableRectangleBorder();
		}
	}
	
	//method
	/**
	 * Creates new left border if this borderable rectangle structure has no left border.
	 */
	private final void createLeftBorderIfHasNoLeftBorder() {
		if (!hasLeftBorder()) {
			leftBorder = new BorderableRectangleBorder();
		}
	}
	
	//method
	/**
	 * Creates new right border if this borderable rectangle structure has no right border.
	 */
	private final void createRightBorderIfHasNoRightBorder() {
		if (!hasRightBorder()) {
			rightBorder = new BorderableRectangleBorder();
		}
	}
	
	//method
	/**
	 * Creates new top border if this borderable rectangle structure has no top border.
	 */
	private final void createTopBorderIfHasNoTopBorder() {
		if (!hasTopBorder()) {
			topBorder = new BorderableRectangleBorder();
		}
	}
}
