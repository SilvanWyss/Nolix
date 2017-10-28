//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.entity2.Property;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.element.data.ScrollHeight;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1090
 * @param <BWS> The type of a border widget structure.
 */
public abstract class BorderWidgetStructure<BWS extends BorderWidgetStructure<BWS>>
extends BackgroundWidgetStructure<BWS> {
	
	//default values
	private static final int DEFAULT_ACTIVE_BORDER_SIZE = 0;
	private static final Color DEFAULT_ACTIVE_BORDER_COLOR = Color.BLACK;
	private static final int DEFAULT_ACTIVE_PADDING = 0;
	
	//attribute headers
	private static final String BORDER_SIZE_HEADER = "BorderSize";
	private static final String LEFT_BORDER_SIZE_HEADER = "LeftBorderSize";
	private static final String RIGHT_BORDER_SIZE_HEADER = "RightBorderSize";
	private static final String TOP_BORDER_SIZE_HEADER = "TopBorderSize";
	private static final String BOTTOM_BORDER_SIZE_HEADER = "BottomBorderSize";
	private static final String BORDER_COLOR_HEADER = "BorderColor";
	private static final String LEFT_BORDER_COLOR_HEADER = "LeftBorderColor";
	private static final String RIGHT_BORDER_COLOR_HEADER = "RightBorderColor";
	private static final String TOP_BORDER_COLOR_HEADER = "TopBorderColor";
	private static final String BOTTOM_BORDER_COLOR_HEADER = "BottomBorderColor";
	private static final String PADDING_HEADER = "Padding";
	private static final String LEFT_PADDING_HEADER = "LeftPadding";
	private static final String RIGHT_PADDING_HEADER = "RightPadding";
	private static final String TOP_PADDING_HEADER = "TopPadding";
	private static final String BOTTOM_PADDING_HEADER = "BottomPadding";
	
	//attribute
	private final Property<ScrollHeight> scrollHeight =
	new Property<ScrollHeight>(
		ScrollHeight.TYPE_NAME,
		new ScrollHeight(),
		a -> new ScrollHeight(a.getRefOne().toInt())
	);
	
	//optional attributes
	private PositiveInteger leftBorderSize;
	private PositiveInteger rightBorderSize;
	private PositiveInteger topBorderSize;
	private PositiveInteger bottomBorderSize;
	private Color leftBorderColor;
	private Color rightBorderColor;
	private Color topBorderColor;
	private Color bottomBorderColor;
	private PositiveInteger leftPadding;	
	private PositiveInteger rightPadding;	
	private PositiveInteger topPadding;	
	private PositiveInteger bottomPadding;
	
	//method
	/**
	 * @return the active bottom border color of this border widget structure.
	 */
	public final Color getActiveBottomBorderColor() {
		
		//Handles the case if this border widget structure has a bottom border color.
		if (hasBottomBorderColor()) {
			return bottomBorderColor;
		}
		
		//Handles the case if this border widget structure
		//has no bottom border color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveBottomBorderColor();
		}
		
		//Handles the case if this border widget structure
		//has no bottom border color and no normal structure.
		return DEFAULT_ACTIVE_BORDER_COLOR;
	}
	
	//method
	/**
	 * @return the active bottom border size of this border widget structure.
	 */
	public final int getActiveBottomBorderSize() {
		
		//Handles the case if this border widget structure has a bottom border size.
		if (hasBottomBorderSize()) {
			return bottomBorderSize.getValue();
		}
		
		//Handles the case if this borderable rectagnle structure
		//has no bottom border size but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveBottomBorderSize();
		}
		
		//Handles the case if this border widget structure
		//has no bottom border size and no normal structure.
		return DEFAULT_ACTIVE_BORDER_SIZE;
	}
	
	//method
	/**
	 * @return the active bottom padding of this border widget structure.
	 */
	public final int getActiveBottomPadding() {
		
		//Handles the case if this border widget structure has a bottom padding.
		if (hasBottomPadding()) {
			return bottomPadding.getValue();
		}
		
		//Handles the case if this border widget structure
		//has no bottom padding but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveBottomPadding();
		}
		
		//Handles the case if this border widget structure
		//has no bottom padding and no normal structure.
		return DEFAULT_ACTIVE_PADDING;
	}
	
	//method
	/**
	 * @return the active left border color of this border widget structure.
	 */
	public final Color getActiveLeftBorderColor() {
		
		//Handles the case if this border widget structure has a left border color.
		if (hasLeftBorderColor()) {
			return leftBorderColor;
		}
		
		//Handles the case if this border widget structure
		//has no left border color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveLeftBorderColor();
		}
		
		//Handles the case if this border widget structure
		//has no left border color and no normal structure.
		return DEFAULT_ACTIVE_BORDER_COLOR;
	}
	
	//method
	/**
	 * @return the active left border size of this border widget structure.
	 */
	public final int getActiveLeftBorderSize() {
		
		//Handles the case if this border widget structure has a left border size.
		if (hasLeftBorderSize()) {
			return leftBorderSize.getValue();
		}
		
		//Handles the case if this border widget structure
		//has no left border size but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveLeftBorderSize();
		}
		
		//Handles the case if this border widget structure
		//has no left border size and no normal structure.
		return DEFAULT_ACTIVE_BORDER_SIZE;
	}
	
	//method
	/**
	 * @return the active left padding of this border widget structure.
	 */
	public final int getActiveLeftPadding() {
		
		//Handles the case if this border widget structure has a left padding.
		if (hasLeftPadding()) {
			return leftPadding.getValue();
		}
		
		//Handles the case if this border widget structure
		//has no left padding but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveLeftPadding();
		}
		
		//Handles the case if this border widget structure
		//has no left padding and no normal structure.
		return DEFAULT_ACTIVE_PADDING;
	}
	
	//method
	/**
	 * @return the active right border color of this border widget structure.
	 */
	public final Color getActiveRightBorderColor() {
		
		//Handles the case if this border widget structure has a right border color.
		if (hasRightBorderColor()) {
			return rightBorderColor;
		}
		
		//Handles the case if this border widget structure
		//has no right border color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveRightBorderColor();
		}
		
		//Hadles the case if this border widget structure
		//has no right border color and no normal structure.
		return DEFAULT_ACTIVE_BORDER_COLOR;
	}
	
	//method
	/**
	 * @return the active right border size of this border widget structure.
	 */
	public final int getActiveRightBorderSize() {
		
		//Handles the case if this border widget structure has a right border size.
		if (hasRightBorderSize()) {
			return rightBorderSize.getValue();
		}
		
		//Handles the case if this border widget structure
		//has no right border size but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveRightBorderSize();
		}
		
		//Handles the case if this border widget structure
		//has no right border size and no normal structure.
		return DEFAULT_ACTIVE_BORDER_SIZE;
	}
	
	//method
	/**
	 * @return the active right padding of this border widget structure.
	 */
	public final int getActiveRightPadding() {
		
		//Handles the case if this border widget structure has a right padding.
		if (hasRightPadding()) {
			return rightPadding.getValue();
		}
		
		//Handles the case if this border widget structure
		//has no right padding but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveRightPadding();
		}
		
		//Handles the case if this border widget structure
		//has no right padding and no normal structure.
		return DEFAULT_ACTIVE_PADDING;
	}
	
	//method
	/**
	 * @return the active scroll height of this border widget structure.
	 */
	public final int getActiveScrollHeight() {
		return scrollHeight.getActiveValue().getValue();
	}
	
	//method
	/**
	 * @return the active top border color of this border widget structure.
	 */
	public final Color getActiveTopBorderColor() {
		
		//Handles the case if this border widget structure has a top border color.
		if (hasTopBorderColor()) {
			return topBorderColor;
		}
		
		//Handles the case if this border widget structure
		//has no top border color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveTopBorderColor();
		}
		
		//Handles the case if this border widget structure
		//has no top border color and no normal structure.
		return DEFAULT_ACTIVE_BORDER_COLOR;
	}
	
	//method
	/**
	 * @return the active top border size of this border widget structure.
	 */
	public final int getActiveTopBorderSize() {
		
		//Handles the case if this border widget structure has no top border size.
		if (hasTopBorderSize()) {
			return topBorderSize.getValue();
		}
		
		//Handles the case if this border widget structure
		//has no top border size but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveTopBorderSize();
		}
		
		//Handles the case if this border widget structure
		//has no top border size and no normal structure.
		return DEFAULT_ACTIVE_BORDER_SIZE;
	}
	
	//method
	/**
	 * @return the active top padding of this border widget structure.
	 */
	public final int getActiveTopPadding() {
		
		//Handles the case if this border widget structure has a top padding.
		if (hasTopPadding()) {
			return topPadding.getValue();
		}
		
		//Handles the case if this border widget structure
		//has no top padding but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveTopPadding();
		}
		
		//Handles the case if this border widget structure
		//has no top padding and no normal structure.
		return DEFAULT_ACTIVE_PADDING;
	}
	
	//method
	/**
	 * @return true if this border widget structure has a bottom border color.
	 */
	public final boolean hasBottomBorderColor() {
		return (bottomBorderColor != null);
	}
	
	//method
	/**
	 * @return true if this border widget structure has a bottom border size.
	 */
	public final boolean hasBottomBorderSize() {
		return (bottomBorderSize != null);
	}
	
	//method
	/**
	 * @return true if this border widget has a bottom padding.
	 */
	public final boolean hasBottomPadding() {
		return (bottomPadding != null);
	}
	
	//method
	/**
	 * @return true if this border widget structure has a left border color.
	 */
	public final boolean hasLeftBorderColor() {
		return (leftBorderColor != null);
	}
	
	//method
	/**
	 * @return true if this border widget structure has a left border size.
	 */
	public final boolean hasLeftBorderSize() {
		return (leftBorderSize != null);
	}
	
	//method
	/**
	 * @return true if this border widget has a left padding.
	 */
	public final boolean hasLeftPadding() {
		return (bottomPadding != null);
	}
	
	//method
	/**
	 * @return true if this border widget structure has a recursive scroll height.
	 */
	public final boolean hasRecursiveScrollHeight() {
		return scrollHeight.hasRecursiveValue();
	}
	
	//method
	/**
	 * @return true if this border widget structure has a right border color.
	 */
	public final boolean hasRightBorderColor() {
		return (rightBorderColor != null);
	}
	
	//method
	/**
	 * @return true if this border widget structure has a right border size.
	 */
	public final boolean hasRightBorderSize() {
		return (rightBorderSize != null);
	}
	
	//method
	/**
	 * @return true if this border widget has a right padding.
	 */
	public final boolean hasRightPadding() {
		return (rightPadding != null);
	}
	
	//method
	/**
	 * @return true if this border widget structure has the same border color at each side.
	 */
	public final boolean hasSameBorderColorAtEachSide()  {
		
		//Handles the case if this border widget structure has no border color.
		if (
			!hasLeftBorderColor()
			&& !hasRightBorderColor()
			&& !hasTopBorderColor()
			&& !hasBottomBorderColor()
		) {
			return true;
		}
		
		//Handles the case if this border widget structure has a border color.
		return (
			hasLeftBorderColor()
			&& leftBorderColor.equals(rightBorderColor)
			&& leftBorderColor.equals(topBorderColor)
			&& leftBorderColor.equals(bottomBorderColor)
		);
	}
	
	//method
	/**
	 * @return true if this border widget structure has the same border size at each side.
	 */
	public final boolean hasSameBorderSizeAtEachSide() {
		
		//Handles the case if this border widget structure has no border size.
		if (
			!hasLeftBorderSize()
			&& !hasRightBorderSize()
			&& !hasTopBorderSize()
			&& !hasBottomBorderSize()
		) {
			return true;
		}
		
		//Handles the case if this border widget structure has a border size.
		return (
			hasLeftBorderSize()
			&& leftBorderSize.equals(rightBorderSize)
			&& leftBorderSize.equals(topBorderSize)
			&& leftBorderSize.equals(bottomBorderSize)
		);
	}
	
	//method
	/**
	 * @return true if this border widget structure has the same padding at each side.
	 */
	public final boolean hasSamePaddingAtEachSide() {
		
		//Handles the case if this border widget structure has no paddings.
		if (
			!hasLeftPadding()
			&& !hasRightPadding()
			&& !hasTopPadding()
			&& !hasBottomPadding()
		) {
			return true;
		}
		
		//Handles the case if this border widget has a padding.
		return (
			hasLeftPadding()
			&& rightPadding.equals(leftPadding)
			&& topPadding.equals(leftPadding)
			&& bottomPadding.equals(bottomPadding)
		);
	}
	
	//method
	/**
	 * @return true if this border widget structure has a topt border color.
	 */
	public final boolean hasTopBorderColor() {
		return (topBorderColor != null);
	}
	
	//method
	/**
	 * @return true if this border widget structure has a top border size.
	 */
	public final boolean hasTopBorderSize() {
		return (topBorderSize != null);
	}
	
	//method
	/**
	 * @return true if this border widget has a top padding.
	 */
	public final boolean hasTopPadding() {
		return (topPadding != null);
	}
	
	//method
	/**
	 * Removes the border colors of this border widget structure.
	 */
	public final void removeBorderColors() {
		removeLeftBorderColor();
		removeRightBorderColor();
		removeTopBorderColor();
		removeBottomBorderColor();
	}
	
	//method
	/**
	 * Removes the border sizes of this border widget structure.
	 */
	public final void removeBorderSizes() {
		removeLeftBorderSize();
		removeRightBorderSize();
		removeTopBorderSize();
		removeBottomBorderSize();
	}
	
	//method
	/**
	 * Removes the bottom border color of this border widget structure.
	 */
	public final void removeBottomBorderColor() {
		bottomBorderColor = null;
	}
	
	//method
	/**
	 * Removes the bottom border size of this rectangle structure.
	 */
	public final void removeBottomBorderSize() {
		bottomBorderSize = null;
	}
	
	//method
	/**
	 * Removes the bottom padding of this border widget structure.
	 */
	public final void removeBottomPadding() {
		bottomPadding = null;
	}
	
	//method
	/**
	 * Removes the left border color of this border widget structure.
	 */
	public final void removeLeftBorderColor() {
		leftBorderColor = null;
	}
	
	//method
	/**
	 * Removes the left border size of this rectangle structure.
	 */
	public final void removeLeftBorderSize() {
		leftBorderSize = null;
	}
	
	//method
	/**
	 * Removes the left padding of this border widget structure.
	 */
	public final void removeLeftPadding() {
		leftPadding = null;
	}
	
	//method
	/**
	 * Removes the paddings of this border widget structure.
	 */
	public final void removePaddings() {
		removeLeftPadding();
		removeRightPadding();
		removeTopPadding();
		removeBottomPadding();
	}
	
	//method
	/**
	 * Removes the right border color of this border widget structure.
	 */
	public final void removeRightBorderColor() {
		rightBorderColor = null;
	}
	
	//method
	/**
	 * Removes the right border size of this border widget structure.
	 */
	public final void removeRightBorderSize() {
		rightBorderSize = null;
	}
	
	//method
	/**
	 * Removes the right padding of this border widget structure.
	 */
	public final void removeRightPadding() {
		rightPadding = null;
	}
	
	//method
	/**
	 * Removes the top border color of this border widget structure.
	 */
	public final void removeTopBorderColor() {
		topBorderColor = null;
	}
	
	//method
	/**
	 * Removes the top border size of this border widget structure.
	 */
	public final void removeTopBorderSize() {
		topBorderSize = null;
	}
	
	//method
	/**
	 * Removes the top padding of this border widget structure.
	 */
	public final void removeTopPadding() {
		topPadding = null;
	}
	
	//method
	/**
	 * Sets the border colors of this border widget structure.
	 * 
	 * @param borderColor
	 * @return this border widget structure.
	 * @throws NullArgumentException if the given border color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setBorderColors(final Color borderColor) {
		
		setLeftBorderColor(borderColor);
		setRightBorderColor(borderColor);
		setTopBorderColor(borderColor);
		setBottomBorderColor(borderColor);
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the border sizes of this border widget structure.
	 * 
	 * @param borderSize
	 * @return this boderable rectangle structure.
	 * @throws NonPositiveArgumentException if the given border size is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setBorderSizes(final int borderSize) {
		
		setLeftBorderSize(borderSize);
		setRightBorderSize(borderSize);
		setTopBorderSize(borderSize);
		setBottomBorderSize(borderSize);
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the bottom border color of this border widget structure.
	 * 
	 * @param bottomBorderColor
	 * @return this border widget structure.
	 * @throws NullArgumentException if the given bottom border color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setBottomBorderColor(final Color bottomBorderColor) {
		
		//Checks if the given bottom border color is not null.
		Validator
		.suppose(bottomBorderColor)
		.thatIsNamed("bottom border color")
		.isNotNull();
		
		//Sets the bottom border color of this border widget structure.
		this.bottomBorderColor = bottomBorderColor;
		
		return (BWS)this;
	}

	//method
	/**
	 * Sets the bottom border size of this border widget structure.
	 * 
	 * @param bottomBorderSize
	 * @return this border widget structure.
	 * @throws NonPositiveArgumentException if the given bottom border size is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setBottomBorderSize(final int bottomBorderSize) {

		this.bottomBorderSize = new PositiveInteger(bottomBorderSize);
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the bottom padding of this border widget structure
	 * 
	 * @param bottomPadding
	 * @return this border widget structure.
	 * @throws NonPositiveArgumentException if the given bottom padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setBottomPadding(final int bottomPadding) {
		
		this.bottomPadding = new PositiveInteger(bottomPadding);
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the left border color of this border widget structure.
	 * 
	 * @param leftBorderColor
	 * @return this border widget structure.
	 * @throws NullArgumentException if the given left border color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setLeftBorderColor(final Color leftBorderColor) {
		
		//Checks if the given left border color is not null.
		Validator
		.suppose(leftBorderColor)
		.thatIsNamed("left border color")
		.isNotNull();
		
		//Sets the left border color of this boderable rectangle structure.
		this.leftBorderColor = leftBorderColor;
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the left border size of this border widget structure.
	 * 
	 * @param leftBordersize
	 * @return this border widget structure.
	 * @throws NonPositiveArgumentException if the given left border size is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setLeftBorderSize(final int leftBordersize) {
		
		this.leftBorderSize = new PositiveInteger(leftBordersize);
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the left padding of this border widget structure.
	 * 
	 * @param leftPadding
	 * @return this border widget structure.
	 * @throws NonPositiveArgumentException if the given left padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setLeftPadding(final int leftPadding) {
		
		this.leftPadding = new PositiveInteger(leftPadding);
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the paddings of this border widget structure.
	 * 
	 * @param padding
	 * @return this border widget structure
	 * @throws NonPositiveArgumentException if the given padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setPaddings(final int padding) {
		
		setLeftPadding(padding);
		setRightPadding(padding);
		setTopPadding(padding);
		setBottomPadding(padding);
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the right border color of this border widget structure.
	 * 
	 * @param rightBorderColor
	 * @return this boderable rectangle structure.
	 * @throws NullArgumentException if the given right border color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setRightBorderColor(final Color rightBorderColor) {
		
		//Checks if the given right border color is not null.
		Validator
		.suppose(rightBorderColor)
		.thatIsNamed("right border color")
		.isNotNull();
		
		//Sets the right border color of this border widget structure.
		this.rightBorderColor = rightBorderColor;
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the right border size of this border widget structure.
	 * 
	 * @param rightBorderSize
	 * @return this border widget structure.
	 * @throws NonPositiveArgumentException if the given right border size is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setRightBorderSize(final int rightBorderSize) {
		
		this.rightBorderSize = new PositiveInteger(rightBorderSize);
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the right padding of this border widget structure.
	 * 
	 * @param rightPadding
	 * @return this border widget structure.
	 * @throws NonPositiveArgumentException if the given right padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setRightPadding(final int rightPadding) {
		
		this.rightPadding = new PositiveInteger(rightPadding);
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the scroll height of this border widget structure.
	 * 
	 * @param scrollHeight
	 * @return this border widget structure
	 * @throws SmallerArgumentException if the given scroll height is smaller than the minimal scroll height.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setScrollHeight(final int scrollHeight) {
		
		this.scrollHeight.setValue(new ScrollHeight(scrollHeight));
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the top border color of this border widget structure.
	 * 
	 * @param topBorderColor
	 * @return this boderable rectangle structure.
	 * @throws NullArgumentException if the given top border color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setTopBorderColor(final Color topBorderColor) {
		
		//Checks if the given top border color is not null.
		Validator
		.suppose(topBorderColor)
		.thatIsNamed("top border color")
		.isNotNull();
		
		//Sets the top border color of this border widget structure.
		this.topBorderColor = topBorderColor;
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the top border size of this border widget structure.
	 * 
	 * @param topBorderSize
	 * @return this border widget structure.
	 * @throws NonPositiveArgumentException if the given top border size is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setTopBorderSize(final int topBorderSize) {
		
		this.topBorderSize = new PositiveInteger(topBorderSize);
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Sets the top padding of this border widget structure.
	 * 
	 * @param leftPadding
	 * @return this border widget structure.
	 * @throws NonPositiveArgumentException if the given top padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BWS setTopPadding(final int topPadding) {
		
		this.topPadding = new PositiveInteger(topPadding);
		
		return (BWS)this;
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this border widget structure.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case BORDER_SIZE_HEADER:
				setBorderSizes(attribute.getOneAttributeToInteger());
				break;
			case LEFT_BORDER_SIZE_HEADER:
				setLeftBorderSize(attribute.getOneAttributeToInteger());
				break;
			case RIGHT_BORDER_SIZE_HEADER:
				setRightBorderSize(attribute.getOneAttributeToInteger());
				break;
			case TOP_BORDER_SIZE_HEADER:
				setTopBorderSize(attribute.getOneAttributeToInteger());
				break;
			case BOTTOM_BORDER_SIZE_HEADER:
				setBottomBorderSize(attribute.getOneAttributeToInteger());
				break;
			case BORDER_COLOR_HEADER:
				setBorderColors(new Color(attribute.getOneAttributeToString()));
				break;
			case LEFT_BORDER_COLOR_HEADER:
				setLeftBorderColor(new Color(attribute.getOneAttributeToString()));
				break;
			case RIGHT_BORDER_COLOR_HEADER:
				setRightBorderColor(new Color(attribute.getOneAttributeToString()));
				break;
			case BOTTOM_BORDER_COLOR_HEADER:
				setBottomBorderColor(new Color(attribute.getOneAttributeToString()));
				break;
			case TOP_BORDER_COLOR_HEADER:
				setTopBorderColor(new Color(attribute.getOneAttributeToString()));
				break;
			case PADDING_HEADER:
				setPaddings(attribute.getOneAttributeToInteger());
				break;
			case LEFT_PADDING_HEADER:
				setLeftPadding(attribute.getOneAttributeToInteger());
				break;
			case RIGHT_PADDING_HEADER:
				setRightPadding(attribute.getOneAttributeToInteger());
				break;
			case TOP_PADDING_HEADER:
				setTopPadding(attribute.getOneAttributeToInteger());
				break;
			case BOTTOM_PADDING_HEADER:
				setBottomPadding(attribute.getOneAttributeToInteger());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this border widget structure
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		if (hasABorderSize() && hasSameBorderSizeAtEachSide()) {
			attributes.addAtEnd(leftBorderSize.getSpecificationAs(BORDER_SIZE_HEADER));
		}
		else {
			
			if (hasLeftBorderSize()) {
				attributes.addAtEnd(leftBorderSize.getSpecificationAs(LEFT_BORDER_SIZE_HEADER));
			}
			
			if (hasRightBorderSize()) {
				attributes.addAtEnd(rightBorderSize.getSpecificationAs(RIGHT_BORDER_SIZE_HEADER));
			}
			
			if (hasTopBorderSize()) {
				attributes.addAtEnd(topBorderSize.getSpecificationAs(TOP_BORDER_SIZE_HEADER));
			}
			
			if (hasBottomBorderSize()) {
				attributes.addAtEnd(bottomBorderSize.getSpecificationAs(BOTTOM_BORDER_SIZE_HEADER));
			}
		}
		
		if (hasABorderColor() && hasSameBorderColorAtEachSide()) {
			attributes.addAtEnd(leftBorderColor.getSpecificationAs(BORDER_COLOR_HEADER));
		}
		else {
			
			if (hasLeftBorderColor()) {
				attributes.addAtEnd(leftBorderColor.getSpecificationAs(LEFT_BORDER_COLOR_HEADER));
			}
			
			if (hasRightBorderColor()) {
				attributes.addAtEnd(rightBorderColor.getSpecificationAs(RIGHT_BORDER_COLOR_HEADER));
			}
			
			if (hasTopBorderColor()) {
				attributes.addAtEnd(topBorderColor.getSpecificationAs(TOP_BORDER_COLOR_HEADER));
			}
			
			if (hasBottomBorderColor()) {
				attributes.addAtEnd(bottomBorderColor.getSpecificationAs(BOTTOM_BORDER_COLOR_HEADER));
			}
		}
		
		if (hasAPadding() && hasSamePaddingAtEachSide()) {
			attributes.addAtEnd(leftPadding.getSpecificationAs(PADDING_HEADER));
		}
		else {
			
			if (hasLeftPadding()) {
				attributes.addAtEnd(leftPadding.getSpecificationAs(LEFT_PADDING_HEADER));
			}
			
			if (hasLeftPadding()) {
				attributes.addAtEnd(rightPadding.getSpecificationAs(RIGHT_PADDING_HEADER));
			}
			
			if (hasLeftPadding()) {
				attributes.addAtEnd(topPadding.getSpecificationAs(TOP_PADDING_HEADER));
			}
			
			if (hasLeftPadding()) {
				attributes.addAtEnd(bottomPadding.getSpecificationAs(BOTTOM_PADDING_HEADER));
			}
		}
		
		return attributes;
	}
	
	//method
	/**
	 * Removes all attributes of this border widget structure.
	 */
	public void clearProperties() {
		
		//Calls method of the base class.
		super.clearProperties();
		
		removeBorderSizes();
		removeBorderColors();
		removePaddings();
	}
	
	//method
	/**
	 * @return true if this boderable rectangle structure has a border color.
	 */
	private boolean hasABorderColor() {
		return (
			hasLeftBorderColor()
			|| hasRightBorderColor()
			|| hasTopBorderColor()
			|| hasBottomBorderColor()
		);
	}
	
	//method
	/**
	 * @return true if this boderable rectangle structure has a border size.
	 */
	private boolean hasABorderSize() {
		return (
			hasLeftBorderSize()
			|| hasRightBorderSize()
			|| hasTopBorderSize()
			|| hasBottomBorderSize()
		);
	}
	
	//method
	/**
	 * @return true if this border widget structure has a padding.
	 */
	private boolean hasAPadding() {
		return (
			hasLeftPadding()
			|| hasRightPadding()
			|| hasTopPadding()
			|| hasBottomPadding()
		);
	}
}
