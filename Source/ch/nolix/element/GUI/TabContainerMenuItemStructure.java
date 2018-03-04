/*
 * file:	TabContainerMenuItemStructure.java
 * author:	Silvan Wyss
 * month:	2016-04
 * lines:	300
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.invalidStateException.UnremovableAttributeException;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.data.TextColor;
import ch.nolix.element.intData.TextSize;

//class
/**
 * A tab container menu item structure stores the state-depended attributes of the menu items of a tab container.
 */
public final class TabContainerMenuItemStructure {
	
	private static final String LEFT_PADDING = "LeftPadding";
	private static final String RIGHT_PADDING = "RightPadding";
	private static final String TOP_PADDING = "TopPadding";
	private static final String BOTTOM_PADDING = "BottomPadding";
	
	//optional attributes
	private TabContainerMenuItemStructure normalStructure;
	private BackgroundColor backgroundColor;
	
	//conditionally optional attributes
	private TextSize textSize = new TextSize();
	private TextColor textColor = new TextColor();
	private PositiveInteger leftPadding;
	private PositiveInteger rightPadding;
	private PositiveInteger bottomPadding;
	private PositiveInteger topPadding;
	
	public int getRecLeftPadding() {
		
		if (hasLeftPadding()) {
			return leftPadding.getValue();
		}
		
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRecLeftPadding();
		}
		
		return 0;
	}
	
	private boolean hasLeftPadding() {
		return (leftPadding != null);
	}

	public int getRecRightPadding() {
		
		if (hasRightPadding()) {
			return rightPadding.getValue();
		}
		
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRecRightPadding();
		}
		
		return 0;
	}
	
	private boolean hasRightPadding() {
		return (rightPadding != null);
	}

	public int getRecBottomPadding() {
		
		if (hasBottomPadding()) {
			return bottomPadding.getValue();
		}
		
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRecBottomPadding();
		}
		
		return 0;
	}
	
	private boolean hasBottomPadding() {
		return (bottomPadding != null);
	}

	public int getRecTopPadding() {
		
		if (hasTopPadding()) {
			return topPadding.getValue();
		}
		
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRecTopPadding();
		}
		
		return 0;
	}
	
	private boolean hasTopPadding() {
		return (topPadding != null);
	}

	//method
	/**
	 * @return the recursive background color of this tab container menu item structure
	 * @throws UnexistingAttributeException if this tab container menu item structure has no background color
	 */
	public final BackgroundColor getRefRecBackgroundColor() {
		
		//Handles the case that this tab container menu item structure has a background color itself.
		if (hasBackgroundColor()) {
			return backgroundColor;
		}
		
		//Handles the case that this tab container menu item structure has a background from its normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRefRecBackgroundColor();
		}
		
		//Handles the case that this tab container menu item structure has a background color neither from itself nor from its normal structure.
		throw new UnexistingAttributeException(this, "background color");
	}
	
	//method
	/**
	 * @return the recursive text color of this tab container menu item structure
	 * @throws UnexistingAttributeException if this tab container menu item structure has no text color
	 */
	public final Color getRefRecTextColor() {
		
		//Handles the case that this tab container menu item structure has a text color itself.
		if (hasTextColor()) {
			return textColor;
		}
		
		//Handles the case that this tab container menu item structure has a text color from its normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRefRecTextColor();
		}
		
		//Handles the case that this tab container menu item structure has a text color neither itself nor from its normal structure.
		throw new UnexistingAttributeException(this, "text color");
	}
	
	//method
	/**
	 * @return the recursive text size of this tab container menu item structure.
	 * @throws UnexistingAttributeException if this tab container menu item structure has no text size
	 */
	public final int getRefRecTextSize() {
		
		//Handles the case that this tab container menu item structure has a text size itself.
		if (hasTextSize()) {
			return textSize.getValue();
		}
		
		//Handles the case that this tab container menu item structure has a text size from its normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getRefRecTextSize();
		}
		
		//Handles the case that this tab container menu item structure has a text size neither itself nor from its normal structure.
		throw new UnexistingAttributeException(this, "text size");
	}
	
	//method
	/**
	 * @return true if this tab container menu item structure has a background color
	 */
	public final boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	//method
	/**
	 * @return true if this tab container menu item structure has a text color
	 */
	public final boolean hasTextColor() {
		return (textColor != null);
	}
	
	//method
	/**
	 * @return true if this tab container menu item structure has a text size
	 */
	public final boolean hasTextSize() {
		return (textSize != null);
	}
	
	//method
	/**
	 * Removes the background color of this tab container menu item structure.
	 * 
	 * @return this tab container menu item structure
	 */
	public final TabContainerMenuItemStructure removeBackgroundColor() {
		
		backgroundColor = null;
		
		return this;
	}
	
	//method
	/**
	 * Removes the text color of this tab container menu item structure.
	 * 
	 * @return this tab container menu item structure
	 * @throws Exception if this tab container menu item structure cannot remove its text color
	 */
	public final TabContainerMenuItemStructure removeTextColor() {
		
		if (!hasNormalStructure()) {
			throw new UnremovableAttributeException(this, "text color");
		}
		
		textColor = null;
		
		return this;
	}
	
	//method
	/**
	 * Removes the text size of this tab container menu item structure.
	 * 
	 * @return this tab container menu item structure
	 * @throws Exception if this tab container menu item structure cannot remove its text size
	 */
	public final TabContainerMenuItemStructure removeTextSize() {
		
		if (!hasNormalStructure()) {
			throw new UnremovableAttributeException(this, "text size");
		}
		
		textSize = null;
		
		return this;
	}
	
	//method
	/**
	 * Sets the background color of this tab container menu item structure.
	 * 
	 * @param backgroundColor
	 * @return this tab container menu item structure
	 * @throws Exception if the given background color is null
	 */
	public final TabContainerMenuItemStructure setBackgroundColor(final BackgroundColor backgroundColor) {
		
		//Checks the given background color.
		Validator.throwExceptionIfValueIsNull("background color", backgroundColor);
		
		this.backgroundColor = backgroundColor;
		
		return this;
	}
	
	//method
	/**
	 * Sets the text color of this tab container menu item structure.
	 * 
	 * @param textColor
	 * @return this tab container menu item structure
	 * @throws Exception if the given text color is null
	 */
	public final TabContainerMenuItemStructure setTextColor(final TextColor textColor) {
		
		//Checks the given text color.
		Validator.throwExceptionIfValueIsNull("text color", textColor);
		
		this.textColor = textColor;
		
		return this;
	}
	
	//method
	/**
	 * Sets the text size of this tab container menu item structure.
	 * 
	 * @param textSize
	 * @return this tab container menu item structure
	 * @throws Exception if the given text size is not positive
	 */
	public final TabContainerMenuItemStructure setTextSize(final int textSize) {
		
		this.textSize = new TextSize(textSize);
		
		return this;
	}
	
	//method
	/**
	 * @return the attributes of this tab container menu item structure
	 */
	protected final List<StandardSpecification> getAttributes() {
		
		final List<StandardSpecification> attributes = new List<StandardSpecification>();
		
		if (hasLeftPadding()) {
			attributes.addAtEnd(leftPadding.getSpecificationAs(LEFT_PADDING));
		}
		if (hasRightPadding()) {
			attributes.addAtEnd(leftPadding.getSpecificationAs(RIGHT_PADDING));
		}
		if (hasTopPadding()) {
			attributes.addAtEnd(leftPadding.getSpecificationAs(TOP_PADDING));
		}
		if (hasBottomPadding()) {
			attributes.addAtEnd(leftPadding.getSpecificationAs(BOTTOM_PADDING));
		}
		
		if (hasBackgroundColor()) {
			attributes.addAtEnd(backgroundColor.getSpecification());
		}
		
		if (hasTextSize()) {
			attributes.addAtEnd(textSize.getSpecification());
		}
		
		if (hasTextColor()) {
			attributes.addAtEnd(textColor.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the normal structure of this tab container menu item structure
	 * @throws UnexistingAttributeException if this tab container menu item structure has no normal structure
	 */
	protected final TabContainerMenuItemStructure getRefNormalStructure() {
		
		if (!hasNormalStructure()) {
			throw new UnexistingAttributeException(this, "normal structure");
		}
		
		return normalStructure;
	}
	
	//method
	/**
	 * @return true if this tab container menu item structure has a normal structure
	 */
	protected final boolean hasNormalStructure() {
		return (normalStructure != null);
	}
	
	//method
	/**
	 * Sets the given attribute to this tab container menu item structure.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	protected final void setAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case LEFT_PADDING:
				setLeftPadding(attribute.getOneAttributeAsInt());
				break;
			case RIGHT_PADDING:
				setRightPadding(attribute.getOneAttributeAsInt());
				break;
			case TOP_PADDING:
				setTopPadding(attribute.getOneAttributeAsInt());
				break;
			case BOTTOM_PADDING:
				setBottomPadding(attribute.getOneAttributeAsInt());
				break;
			case BackgroundColor.TYPE_NAME:
				setBackgroundColor(new BackgroundColor(attribute.getOneAttributeAsString()));
				break;
			case TextSize.TYPE_NAME:
				setTextSize(attribute.getOneAttributeAsInt());
				break;
			case TextColor.TYPE_NAME:
				setTextColor(new TextColor(attribute.getOneAttributeAsString()));
				break;
			default:
				throw new InvalidArgumentException(
					new ArgumentName("attribute"),
					new Argument(attribute)
				);
		}
	}
	
	private void setBottomPadding(int bottomPadding) {
		this.bottomPadding = new PositiveInteger(bottomPadding);
		
	}

	private void setTopPadding(int topPadding) {
		this.topPadding = new PositiveInteger(topPadding);
	}

	private void setLeftPadding(int leftPadding) {
		this.leftPadding = new PositiveInteger(leftPadding);
	}

	private void setRightPadding(int rightPadding) {
		this.rightPadding = new PositiveInteger(rightPadding);
	}

	//method
	/**
	 * Sets the normal structure of this tab container menu item structure
	 * 
	 * @param normalStructure
	 * @throws Exception if the given normal structure is null
	 */
	protected void setNormalStructure(TabContainerMenuItemStructure normalStructure) {
		
		//Checks the given normal structure.
		Validator.throwExceptionIfValueIsNull("normal structure", normalStructure);
		
		this.normalStructure = normalStructure;
	}
	
	void applyTo(TextLineWidgetStructure textLineRectangleStructure) {
		if (hasBackgroundColor()) {
			//textLineRectangleStructure.setBackgroundColor(get);
		}
	}
}
