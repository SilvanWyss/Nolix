//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.zetaValidator.ZetaValidator;
import ch.nolix.element.basic.Color;
import ch.nolix.element.basic.PositiveInteger;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 730
 * @param <BRS> - The type of a borderable rectangle structure.
 */
public abstract class BorderWidgetStructure<BRS extends BorderWidgetStructure<BRS>>
extends BackgroundWidgetStructure<BRS> {
	
	//default value
	public static final int DEFAULT_BORDER_SIZE = 1;
	
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
		
	//optional attributes
	private PositiveInteger leftBorderSize;
	private Color leftBorderColor;
	private PositiveInteger rightBorderSize;
	private Color rightBorderColor;
	private PositiveInteger topBorderSize;
	private Color topBorderColor;
	private PositiveInteger bottomBorderSize;
	private Color bottomBorderColor;
	
	public final Color getActiveBottomBorderColor() {
		
		//Handles the case if this borderable rectangle structure has a bottom border color.
		if (hasBottomBorderColor()) {
			return bottomBorderColor;
		}
		
		//Handles the case if this borderable rectangle structure has no bottom border color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveBottomBorderColor();
		}
		
		//Handles the case if this borderable rectangle structure has no bottom border color and no normal structure.
		return new Color(Color.BLACK);
	}
	
	//method
	/**
	 * @return the active bottom border size of this borderable rectangle structure.
	 */
	public final int getActiveBottomBorderSize() {
		
		//Handles the case if this borderable rectangle structure has a bottom border size.
		if (hasBottomBorderSize()) {
			return bottomBorderSize.getValue();
		}
		
		//Handles the case if this borderable rectagnle structure has no bottom border size but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveBottomBorderSize();
		}
		
		//Handles the case if this borderable rectangle structure has no bottom border size and no normal structure.
		return 0;
	}
	
	//method
	/**
	 * @return the active left border color of this borderable rectangle structure.
	 */
	public final Color getActiveLeftBorderColor() {
		
		//Handles the case if this borderable rectangle structure has a left border color.
		if (hasLeftBorderColor()) {
			return leftBorderColor;
		}
		
		//Handles the case if this borderable rectangle structure has no left border color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveLeftBorderColor();
		}
		
		//Handles the case if this borderable rectangle structure has no left border color and no normal structure.
		return new Color(Color.BLACK);
	}
	
	//method
	/**
	 * @return the active left border size of this borderable rectangle structure.
	 */
	public final int getActiveLeftBorderSize() {
		
		//Handles the case if this borderable rectangle structure has a left border size.
		if (hasLeftBorderSize()) {
			return leftBorderSize.getValue();
		}
		
		//Hanldes the case if this borderable rectangle structure has no left border size but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveLeftBorderSize();
		}
		
		//Handles the case if this borderable rectangle structure has no left border size and no normal structure.
		return 0;
	}
	
	//method
	/**
	 * @return the active right border color of this borderable rectangle structure.
	 */
	public final Color getActiveRightBorderColor() {
		
		//Handles the case if this borderable rectangle structure has a right border color.
		if (hasRightBorderColor()) {
			return rightBorderColor;
		}
		
		//Handles the case if this borderable rectangle structure has no right border color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveRightBorderColor();
		}
		
		//Hadles the case if this borderable rectangle structure has no right border color and no normal structure.
		return new Color(Color.BLACK);
	}
	
	//method
	/**
	 * @return the active right border size of this borderable rectangle structure.
	 */
	public final int getActiveRightBorderSize() {
		
		//Handles the case if this borderable rectangle structure has a right border size.
		if (hasRightBorderSize()) {
			return rightBorderSize.getValue();
		}
		
		//Handles the case if this borderable rectangle structure has no right border size but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveRightBorderSize();
		}
		
		//Handles the case if this borderable rectangle structure has no right border size and no normal structure.
		return 0;
	}
	
	//method
	/**
	 * @return the active top border color of this borderable rectangle structure.
	 */
	public final Color getActiveTopBorderColor() {
		
		//Handles the case if this borderable rectangle structure has a top border color.
		if (hasTopBorderColor()) {
			return topBorderColor;
		}
		
		//Handles the case if this borderable rectangle structure has no top border color but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveTopBorderColor();
		}
		
		//Handles the case if this borderable rectangle structure has no top border color and no normal structure.
		return new Color(Color.BLACK);
	}
	
	//method
	/**
	 * @return the active top border size of this borderable rectangle structure.
	 */
	public final int getActiveTopBorderSize() {
		
		//Handles the case if this borderable rectangle structure has no top border size.
		if (hasTopBorderSize()) {
			return topBorderSize.getValue();
		}
		
		//Handles the case if this borderable rectangle structure has no top border size but a normal structure.
		if (hasNormalStructure()) {
			return getRefNormalStructure().getActiveTopBorderSize();
		}
		
		//Handles the case if this borderable rectangle structure has no top border size and no normal structure.
		return 0;
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a bottom border color.
	 */
	public final boolean hasBottomBorderColor() {
		return (bottomBorderColor != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a bottom border size.
	 */
	public final boolean hasBottomBorderSize() {
		return (bottomBorderSize != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a left border color.
	 */
	public final boolean hasLeftBorderColor() {
		return (leftBorderColor != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a left border size.
	 */
	public final boolean hasLeftBorderSize() {
		return (leftBorderSize != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a right border color.
	 */
	public final boolean hasRightBorderColor() {
		return (rightBorderColor != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a right border size.
	 */
	public final boolean hasRightBorderSize() {
		return (rightBorderSize != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle structure has the same border color at each side.
	 */
	public final boolean hasSameBorderColorAtEachSide()  {
		
		if (
			!hasLeftBorderColor()
			&& !hasRightBorderColor()
			&& !hasTopBorderColor()
			&& !hasBottomBorderColor()
		) {
			return true;
		}
		
		return (
			hasLeftBorderColor()
			&& leftBorderColor.equals(rightBorderColor)
			&& leftBorderColor.equals(topBorderColor)
			&& leftBorderColor.equals(bottomBorderColor)
		);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle structure has the same border size at each side.
	 */
	public final boolean hasSameBorderSizeAtEachSide() {
		
		if (
			!hasLeftBorderSize()
			&& !hasRightBorderSize()
			&& !hasTopBorderSize()
			&& !hasBottomBorderSize()
		) {
			return true;
		}
		
		return (
			hasLeftBorderSize()
			&& hasRightBorderSize()
			&& hasTopBorderSize()
			&& hasBottomBorderSize()
			&& leftBorderSize.getValue() == rightBorderSize.getValue()
			&& leftBorderSize.getValue() == topBorderSize.getValue()
			&& leftBorderSize.getValue() == bottomBorderSize.getValue()
		);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a topt border color.
	 */
	public final boolean hasTopBorderColor() {
		return (topBorderColor != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a top border size.
	 */
	public final boolean hasTopBorderSize() {
		return (topBorderSize != null);
	}
	
	//method
	/**
	 * Removes all attributes of this borderable rectangle structure.
	 */
	public void removeAttributes() {
		
		//Calls method of the base class.
		super.removeAttributes();
		
		removeBorderSizes();
		removeBorderColors();
	}
	
	//method
	/**
	 * Removes the border colors of this borderable rectangle structure.
	 */
	public final void removeBorderColors() {
		removeLeftBorderColor();
		removeRightBorderColor();
		removeTopBorderColor();
		removeBottomBorderColor();
	}
	
	//method
	/**
	 * Removes the border sizes of this borderable rectangle structure.
	 */
	public final void removeBorderSizes() {
		removeLeftBorderSize();
		removeRightBorderSize();
		removeTopBorderSize();
		removeBottomBorderSize();
	}
	
	//method
	/**
	 * Removes the bottom border color of this borderable rectangle structure.
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
	 * Removes the left border color of this borderable rectangle structure.
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
	 * Removes the right border color of this borderable rectangle structure.
	 */
	public final void removeRightBorderColor() {
		rightBorderColor = null;
	}
	
	//method
	/**
	 * Removes the right border size of this borderable rectangle structure.
	 */
	public final void removeRightBorderSize() {
		rightBorderSize = null;
	}
	
	//method
	/**
	 * Removes the top border color of this borderable rectangle structure.
	 */
	public final void removeTopBorderColor() {
		topBorderColor = null;
	}
	
	//method
	/**
	 * Removes the top border size of this borderable rectangle structure.
	 */
	public final void removeTopBorderSize() {
		topBorderSize = null;
	}
	
	//method
	/**
	 * Sets the border color of this borderable rectangle structure.
	 * 
	 * @param color
	 * @return this borderable rectangle structure.
	 * @throws NullArgumentException if the given border color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BRS setBorderColor(final Color borderColor) {
		
		setLeftBorderColor(borderColor);
		setRightBorderColor(borderColor);
		setTopBorderColor(borderColor);
		setBottomBorderColor(borderColor);
		
		return (BRS)this;
	}
	
	//method
	/**
	 * Sets the border sizes of this borderable rectangle structure.
	 * 
	 * @param borderSize
	 * @return this boderable rectangle structure.
	 * @throws NonPositiveArgumentException if the given border size is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BRS setBorderSizes(final int borderSize) {
		
		setLeftBorderSize(borderSize);
		setRightBorderSize(borderSize);
		setTopBorderSize(borderSize);
		setBottomBorderSize(borderSize);
		
		return (BRS)this;
	}
	
	//method
	/**
	 * Sets the bottom border color of this borderable rectangle structure.
	 * 
	 * @param bottomBorderColor
	 * @return this borderable rectangle structure.
	 * @throws NullArgumentException if the given bottom broder color.
	 */
	@SuppressWarnings("unchecked")
	public final BRS setBottomBorderColor(final Color bottomBorderColor) {
		
		//Checks if the given bottom border color is not null.
		ZetaValidator.supposeThat(bottomBorderColor).thatIsNamed("bottom border color").isNotNull();
		
		//Sets the bottom border color of this borderable rectangle structure.
		this.bottomBorderColor = bottomBorderColor;
		
		return (BRS)this;
	}

	//method
	/**
	 * Sets the bottom border size of this borderable rectangle structure.
	 * 
	 * @param bottomBorderSize
	 * @return this borderable rectangle structure.
	 * @throws NonPositiveArgumentException if the given bottom border size is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BRS setBottomBorderSize(final int bottomBorderSize) {

		this.bottomBorderSize = new PositiveInteger(bottomBorderSize);
		
		return (BRS)this;
	}
	
	public final BRS setDefaultBorderSizes() {
		return setBorderSizes(DEFAULT_BORDER_SIZE);
	}
	
	//method
	/**
	 * Sets the left border color of this borderable rectangle structure.
	 * 
	 * @param leftBorderColor
	 * @return this borderable rectangle structure.
	 * @throws NullArgumentException if the given left border color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BRS setLeftBorderColor(final Color leftBorderColor) {
		
		//Checks if the given left border color is not null.
		ZetaValidator.supposeThat(leftBorderColor).thatIsNamed("left border color").isNotNull();
		
		//Sets the left border color of this boderable rectangle structure.
		this.leftBorderColor = leftBorderColor;
		
		return (BRS)this;
	}
	
	//method
	/**
	 * Sets the left border size of this borderable rectangle structure.
	 * 
	 * @param leftBordersize
	 * @return this borderable rectangle structure.
	 * @throws NonPositiveArgumentException if the given left border size is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BRS setLeftBorderSize(final int leftBordersize) {
		
		this.leftBorderSize = new PositiveInteger(leftBordersize);
		
		return (BRS)this;
	}
	
	//method
	/**
	 * Sets the right border color of this borderable rectangle structure.
	 * 
	 * @param rightBorderColor
	 * @return this boderable rectangle structure.
	 * @throws NullArgumentException if the given right border color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BRS setRightBorderColor(final Color rightBorderColor) {
		
		//Checks if the given right border color is not null.
		ZetaValidator.supposeThat(rightBorderColor).thatIsNamed("right border color").isNotNull();
		
		//Sets the right border color of this borderable rectangle structure.
		this.rightBorderColor = rightBorderColor;
		
		return (BRS)this;
	}
	
	//method
	/**
	 * Sets the right border size of this borderable rectangle structure.
	 * 
	 * @param rightBorderSize
	 * @return this borderable rectangle structure.
	 * @throws NonPositiveArgumentException if the given right border size is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BRS setRightBorderSize(final int rightBorderSize) {
		
		this.rightBorderSize = new PositiveInteger(rightBorderSize);
		
		return (BRS)this;
	}
	
	//method
	/**
	 * Sets the top border color of this borderable rectangle structure.
	 * 
	 * @param topBorderColor
	 * @return this boderable rectangle structure.
	 * @throws NullArgumentException if the given top border color is null.
	 */
	@SuppressWarnings("unchecked")
	public final BRS setTopBorderColor(final Color topBorderColor) {
		
		//Checks if the given top border color is not null.
		ZetaValidator.supposeThat(topBorderColor).thatIsNamed("top border color").isNotNull();
		
		//Sets the top border color of this borderable rectangle structure.
		this.topBorderColor = topBorderColor;
		
		return (BRS)this;
	}
	
	//method
	/**
	 * Sets the top border size of this borderable rectangle structure.
	 * 
	 * @param topBorderSize
	 * @return this borderable rectangle structure.
	 * @throws NonPositiveArgumentException if the given top border size is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BRS setTopBorderSize(final int topBorderSize) {
		
		this.topBorderSize = new PositiveInteger(topBorderSize);
		
		return (BRS)this;
	}
	
	//method
	/**
	 * @return the attributes of this borderable rectangle structure
	 */
	protected List<Specification> getAttributes() {
		
		//Calls method of the base class.
		final List<Specification> attributes = super.getAttributes();
		
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
		
		return attributes;
	}
	
	//method
	/**
	 * Adds or changes the given attribute to this border widget structure.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	protected void addOrChangeAttribute(final Specification attribute) {
		
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
				setBorderColor(new Color(attribute.getOneAttributeToString()));
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
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
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
}
