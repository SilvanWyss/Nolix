//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.NonNegativeInteger;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1160
 * @param <BWL> The type of a {@link BorderWidgetLook}.
 */
public abstract class BorderWidgetLook<BWL extends BorderWidgetLook<BWL>>
extends BackgroundWidgetLook<BWL> {
	
	//default values
	public static final int DEFAULT_BORDER_THICKNESS = 0;
	public static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	public static final int DEFAULT_PADDING = 0;
	
	//attribute headers
	private static final String BORDER_THICKNESS_HEADER = "BorderThickness";
	private static final String LEFT_BORDER_THICKNESS_HEADER = "LeftBorderThickness";
	private static final String RIGHT_BORDER_THICKNESS_HEADER = "RightBorderThickness";
	private static final String TOP_BORDER_THICKNESS_HEADER = "TopBorderThickness";
	private static final String BOTTOM_BORDER_THICKNESS_HEADER = "BottomBorderThickness";
	
	//attribute headers
	private static final String BORDER_COLOR_HEADER = "BorderColor";
	private static final String LEFT_BORDER_COLOR_HEADER = "LeftBorderColor";
	private static final String RIGHT_BORDER_COLOR_HEADER = "RightBorderColor";
	private static final String TOP_BORDER_COLOR_HEADER = "TopBorderColor";
	private static final String BOTTOM_BORDER_COLOR_HEADER = "BottomBorderColor";
	
	//attribute headers
	private static final String PADDING_HEADER = "Padding";
	private static final String LEFT_PADDING_HEADER = "LeftPadding";
	private static final String RIGHT_PADDING_HEADER = "RightPadding";
	private static final String TOP_PADDING_HEADER = "TopPadding";
	private static final String BOTTOM_PADDING_HEADER = "BottomPadding";
	
	//optional attributes
	private NonNegativeInteger leftBorderThickness;
	private NonNegativeInteger rightBorderThickness;
	private NonNegativeInteger topBorderThickness;
	private NonNegativeInteger bottomBorderThickness;
	
	//optional attributes
	private Color leftBorderColor;
	private Color rightBorderColor;
	private Color topBorderColor;
	private Color bottomBorderColor;
	
	//optional attributes
	private NonNegativeInteger leftPadding;
	private NonNegativeInteger rightPadding;	
	private NonNegativeInteger topPadding;	
	private NonNegativeInteger bottomPadding;
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link BorderWidgetLook}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case BORDER_THICKNESS_HEADER:
				setBorderThicknesses(attribute.getOneAttributeAsInt());
				break;
			case LEFT_BORDER_THICKNESS_HEADER:
				setLeftBorderThickness(attribute.getOneAttributeAsInt());
				break;
			case RIGHT_BORDER_THICKNESS_HEADER:
				setRightBorderThickness(attribute.getOneAttributeAsInt());
				break;
			case TOP_BORDER_THICKNESS_HEADER:
				setTopBorderThickness(attribute.getOneAttributeAsInt());
				break;
			case BOTTOM_BORDER_THICKNESS_HEADER:
				setBottomBorderThickness(attribute.getOneAttributeAsInt());
				break;
			case BORDER_COLOR_HEADER:
				setBorderColors(new Color(attribute.getOneAttributeAsString()));
				break;
			case LEFT_BORDER_COLOR_HEADER:
				setLeftBorderColor(new Color(attribute.getOneAttributeAsString()));
				break;
			case RIGHT_BORDER_COLOR_HEADER:
				setRightBorderColor(new Color(attribute.getOneAttributeAsString()));
				break;
			case BOTTOM_BORDER_COLOR_HEADER:
				setBottomBorderColor(new Color(attribute.getOneAttributeAsString()));
				break;
			case TOP_BORDER_COLOR_HEADER:
				setTopBorderColor(new Color(attribute.getOneAttributeAsString()));
				break;
			case PADDING_HEADER:
				setPaddings(attribute.getOneAttributeAsInt());
				break;
			case LEFT_PADDING_HEADER:
				setLeftPadding(attribute.getOneAttributeAsInt());
				break;
			case RIGHT_PADDING_HEADER:
				setRightPadding(attribute.getOneAttributeAsInt());
				break;
			case TOP_PADDING_HEADER:
				setTopPadding(attribute.getOneAttributeAsInt());
				break;
			case BOTTOM_PADDING_HEADER:
				setBottomPadding(attribute.getOneAttributeAsInt());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
		/**
		 * @return the attributes of the current {@link BorderWidgetLook}
		 */
		public List<StandardSpecification> getAttributes() {
			
			//Calls method of the base class.
			final List<StandardSpecification> attributes = super.getAttributes();
			
			//Handles the border thicknesses of the current border widget look.
			if (hasABorderThickness() && hasSameBorderThicknessAtEachSide()) {
				attributes.addAtEnd(leftBorderThickness.getSpecificationAs(BORDER_THICKNESS_HEADER));
			}
			else {
				
				if (hasLeftBorderThickness()) {
					attributes.addAtEnd(leftBorderThickness.getSpecificationAs(LEFT_BORDER_THICKNESS_HEADER));
				}
				
				if (hasRightBorderThickness()) {
					attributes.addAtEnd(rightBorderThickness.getSpecificationAs(RIGHT_BORDER_THICKNESS_HEADER));
				}
				
				if (hasTopBorderThickness()) {
					attributes.addAtEnd(topBorderThickness.getSpecificationAs(TOP_BORDER_THICKNESS_HEADER));
				}
				
				if (hasBottomBorderThickness()) {
					attributes.addAtEnd(bottomBorderThickness.getSpecificationAs(BOTTOM_BORDER_THICKNESS_HEADER));
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
	 * @return the recursive or default bottom border color of the current {@link BorderWidgetLook}.
	 */
	public final Color getRecursiveOrDefaultBottomBorderColor() {
		
		//Handles the case that the current border widget look has a bottom border color.
		if (hasBottomBorderColor()) {
			return bottomBorderColor;
		}
		
		//Handles the case that the current border widget look
		//has no bottom border color but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultBottomBorderColor();
		}
		
		//Handles the case that the current border widget look
		//has no bottom border color and no base look.
		return DEFAULT_BORDER_COLOR;
	}
	
	//method
	/**
	 * @return the recursive or default bottom border thickness of the current {@link BorderWidgetLook}.
	 */
	public final int getRecursiveOrDefaultBottomBorderThickness() {
		
		//Handles the case that the current border widget look has a bottom border thickness.
		if (hasBottomBorderThickness()) {
			return bottomBorderThickness.getValue();
		}
		
		//Handles the case that the current border widget look
		//has no bottom border thickness but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultBottomBorderThickness();
		}
		
		//Handles the case that the current border widget look
		//has no bottom border thickness and no base look.
		return DEFAULT_BORDER_THICKNESS;
	}
	
	//method
	/**
	 * @return the recursive or default bottom padding of the current {@link BorderWidgetLook}.
	 */
	public final int getRecursiveOrDefaultBottomPadding() {
		
		//Handles the case that the current border widget look has a bottom padding.
		if (hasBottomPadding()) {
			return bottomPadding.getValue();
		}
		
		//Handles the case that the current border widget look
		//has no bottom padding but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultBottomPadding();
		}
		
		//Handles the case that the current border widget look
		//has no bottom padding and no base look.
		return DEFAULT_PADDING;
	}
	
	//method
	/**
	 * @return the recursive or default left border color of the current {@link BorderWidgetLook}.
	 */
	public final Color getRecursiveOrDefaultLeftBorderColor() {
		
		//Handles the case that the current border widget look has a left border color.
		if (hasLeftBorderColor()) {
			return leftBorderColor;
		}
		
		//Handles the case that the current border widget look
		//has no left border color but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultLeftBorderColor();
		}
		
		//Handles the case that the current border widget look
		//has no left border color and no base look.
		return DEFAULT_BORDER_COLOR;
	}
	
	//method
	/**
	 * @return the recursive or default left border thickness of the current {@link BorderWidgetLook}.
	 */
	public final int getRecursiveOrDefaultLeftBorderThickness() {
		
		//Handles the case that the current border widget look has a left border thickness.
		if (hasLeftBorderThickness()) {
			return leftBorderThickness.getValue();
		}
		
		//Handles the case that the current border widget look
		//has no left border thickness but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultLeftBorderThickness();
		}
		
		//Handles the case that the current border widget look
		//has no left border thickness and no base look.
		return DEFAULT_BORDER_THICKNESS;
	}
	
	//method
	/**
	 * @return the recursive or default left padding of the current {@link BorderWidgetLook}.
	 */
	public final int getRecursiveOrDefaultLeftPadding() {
		
		//Handles the case that the current border widget look has a left padding.
		if (hasLeftPadding()) {
			return leftPadding.getValue();
		}
		
		//Handles the case that the current border widget look
		//has no left padding but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultLeftPadding();
		}
		
		//Handles the case that the current border widget look
		//has no left padding and no base look.
		return DEFAULT_PADDING;
	}
	
	//method
	/**
	 * @return the recursive or default right border color of the current {@link BorderWidgetLook}.
	 */
	public final Color getRecursiveOrDefaultRightBorderColor() {
		
		//Handles the case that the current border widget look has a right border color.
		if (hasRightBorderColor()) {
			return rightBorderColor;
		}
		
		//Handles the case that the current border widget look
		//has no right border color but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultRightBorderColor();
		}
		
		//Handles the case if the current {@link BorderWidgetLook}
		//has no right border color and no base look.
		return DEFAULT_BORDER_COLOR;
	}
	
	//method
	/**
	 * @return the recursive or default right border thickness of the current {@link BorderWidgetLook}.
	 */
	public final int getRecursiveOrDefaultRightBorderThickness() {
		
		//Handles the case that the current border widget look has a right border thickness.
		if (hasRightBorderThickness()) {
			return rightBorderThickness.getValue();
		}
		
		//Handles the case that the current border widget look
		//has no right border thickness but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultRightBorderThickness();
		}
		
		//Handles the case that the current border widget look
		//has no right border thickness and no base look.
		return DEFAULT_BORDER_THICKNESS;
	}
	
	//method
	/**
	 * @return the recursive or default right padding of the current {@link BorderWidgetLook}.
	 */
	public final int getRecursiveOrDefaultRightPadding() {
		
		//Handles the case that the current border widget look has a right padding.
		if (hasRightPadding()) {
			return rightPadding.getValue();
		}
		
		//Handles the case that the current border widget look
		//has no right padding but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultRightPadding();
		}
		
		//Handles the case that the current border widget look
		//has no right padding and no base look.
		return DEFAULT_PADDING;
	}
	
	//method
	/**
	 * @return the recursive or default top border color of the current {@link BorderWidgetLook}.
	 */
	public final Color getRecursiveOrDefaultTopBorderColor() {
		
		//Handles the case that the current border widget look has a top border color.
		if (hasTopBorderColor()) {
			return topBorderColor;
		}
		
		//Handles the case that the current border widget look
		//has no top border color but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultTopBorderColor();
		}
		
		//Handles the case that the current border widget look
		//has no top border color and no base look.
		return DEFAULT_BORDER_COLOR;
	}
	
	//method
	/**
	 * @return the recursive or default top border thickness of the current {@link BorderWidgetLook}.
	 */
	public final int getRecursiveOrDefaultTopBorderThickness() {
		
		//Handles the case that the current border widget look has no top border thickness.
		if (hasTopBorderThickness()) {
			return topBorderThickness.getValue();
		}
		
		//Handles the case that the current border widget look
		//has no top border thickness but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultTopBorderThickness();
		}
		
		//Handles the case that the current border widget look
		//has no top border thickness and no base look.
		return DEFAULT_BORDER_THICKNESS;
	}
	
	//method
	/**
	 * @return the recursive or default top padding of the current {@link BorderWidgetLook}.
	 */
	public final int getRecursiveOrDefaultTopPadding() {
		
		//Handles the case that the current border widget look has a top padding.
		if (hasTopPadding()) {
			return topPadding.getValue();
		}
		
		//Handles the case that the current border widget look
		//has no top padding but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultTopPadding();
		}
		
		//Handles the case that the current border widget look
		//has no top padding and no base look.
		return DEFAULT_PADDING;
	}
	
	//method
	/**
	 * Removes the border colors of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBorderColors() {
		
		removeLeftBorderColor();
		removeRightBorderColor();
		removeTopBorderColor();
		removeBottomBorderColor();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the border thicknesss of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBorderThicknesses() {
		
		removeLeftBorderThickness();
		removeRightBorderThickness();
		removeTopBorderThickness();
		removeBottomBorderThickness();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the bottom border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBottomBorderColor() {
		
		bottomBorderColor = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the bottom border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBottomBorderThickness() {
		
		bottomBorderThickness = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the bottom padding of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBottomPadding() {
		
		bottomPadding = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the left border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeLeftBorderColor() {
		
		leftBorderColor = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the left border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeLeftBorderThickness() {
		
		leftBorderThickness = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the left padding of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeLeftPadding() {
		
		leftPadding = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the paddings of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removePaddings() {
		
		removeLeftPadding();
		removeRightPadding();
		removeTopPadding();
		removeBottomPadding();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the right border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeRightBorderColor() {
		
		rightBorderColor = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the right border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeRightBorderThickness() {
		
		rightBorderThickness = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the right padding of the current {@link BorderWidgetLook}.
	 */
	public final BWL removeRightPadding() {
		
		rightPadding = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the top border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeTopBorderColor() {
		
		topBorderColor = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the top border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeTopBorderThickness() {
		
		topBorderThickness = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the top padding of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeTopPadding() {
		
		topPadding = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes all values of the current {@link BorderWidgetLook}.
	 */
	public BWL reset() {
		
		//Calls method of the base class.
		super.reset();
		
		removeBorderThicknesses();
		removeBorderColors();
		removePaddings();
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the border colors of the current {@link BorderWidgetLook}.
	 * 
	 * @param borderColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NullArgumentException if the given border color is null.
	 */
	public final BWL setBorderColors(final Color borderColor) {
		
		setLeftBorderColor(borderColor);
		setRightBorderColor(borderColor);
		setTopBorderColor(borderColor);
		setBottomBorderColor(borderColor);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the border thicknesses of the current {@link BorderWidgetLook}.
	 * 
	 * @param borderSize
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NegativeArgumentException if the given border thickness is negative.
	 */
	public final BWL setBorderThicknesses(final int borderSize) {
		
		setLeftBorderThickness(borderSize);
		setRightBorderThickness(borderSize);
		setTopBorderThickness(borderSize);
		setBottomBorderThickness(borderSize);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the bottom border color of the current {@link BorderWidgetLook}.
	 * 
	 * @param bottomBorderColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NullArgumentException if the given bottom border color is null.
	 */
	public final BWL setBottomBorderColor(final Color bottomBorderColor) {
		
		//Checks if the given bottom border color is not null.
		Validator
		.suppose(bottomBorderColor)
		.thatIsNamed("bottom border color")
		.isNotNull();
		
		//Sets the bottom border color of the current {@link BorderWidgetLook}.
		this.bottomBorderColor = bottomBorderColor;
		
		return getInstance();
	}

	//method
	/**
	 * Sets the bottom border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @param bottomBorderThickness
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NegativeArgumentException if the given bottom border thickness is negative.
	 */
	public final BWL setBottomBorderThickness(final int bottomBorderThickness) {

		this.bottomBorderThickness = new NonNegativeInteger(bottomBorderThickness);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the bottom padding of the current {@link BorderWidgetLook}
	 * 
	 * @param bottomPadding
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NegativeArgumentException if the given bottom padding is negative.
	 */
	public final BWL setBottomPadding(final int bottomPadding) {
		
		this.bottomPadding = new NonNegativeInteger(bottomPadding);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the left border color of the current {@link BorderWidgetLook}.
	 * 
	 * @param leftBorderColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NullArgumentException if the given left border color is null.
	 */
	public final BWL setLeftBorderColor(final Color leftBorderColor) {
		
		//Checks if the given left border color is not null.
		Validator
		.suppose(leftBorderColor)
		.thatIsNamed("left border color")
		.isNotNull();
		
		//Sets the left border color of the current {@link BorderWidgetLook}.
		this.leftBorderColor = leftBorderColor;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the left border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @param leftBordersize
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NegativeArgumentException if the given left border thickness is negative.
	 */
	public final BWL setLeftBorderThickness(final int leftBordersize) {
		
		this.leftBorderThickness = new NonNegativeInteger(leftBordersize);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the left padding of the current {@link BorderWidgetLook}.
	 * 
	 * @param leftPadding
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NegativeArgumentException if the given left padding is negative.
	 */
	public final BWL setLeftPadding(final int leftPadding) {
		
		this.leftPadding = new NonNegativeInteger(leftPadding);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the paddings of the current {@link BorderWidgetLook}.
	 * 
	 * @param padding
	 * @return the current {@link BorderWidgetLook}
	 * @throws NegativeArgumentException if the given padding is negative.
	 */
	public final BWL setPaddings(final int padding) {
		
		setLeftPadding(padding);
		setRightPadding(padding);
		setTopPadding(padding);
		setBottomPadding(padding);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the right border color of the current {@link BorderWidgetLook}.
	 * 
	 * @param rightBorderColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NullArgumentException if the given right border color is null.
	 */
	public final BWL setRightBorderColor(final Color rightBorderColor) {
		
		//Checks if the given right border color is not null.
		Validator
		.suppose(rightBorderColor)
		.thatIsNamed("right border color")
		.isNotNull();
		
		//Sets the right border color of the current {@link BorderWidgetLook}.
		this.rightBorderColor = rightBorderColor;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the right border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @param rightBorderThickness
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NegativeArgumentException if the given right border thickness is negative.
	 */
	public final BWL setRightBorderThickness(final int rightBorderThickness) {
		
		this.rightBorderThickness = new NonNegativeInteger(rightBorderThickness);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the right padding of the current {@link BorderWidgetLook}.
	 * 
	 * @param rightPadding
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NegativeArgumentException if the given right padding is negative.
	 */
	public final BWL setRightPadding(final int rightPadding) {
		
		this.rightPadding = new NonNegativeInteger(rightPadding);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the top border color of the current {@link BorderWidgetLook}.
	 * 
	 * @param topBorderColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NullArgumentException if the given top border color is null.
	 */
	public final BWL setTopBorderColor(final Color topBorderColor) {
		
		//Checks if the given top border color is not null.
		Validator
		.suppose(topBorderColor)
		.thatIsNamed("top border color")
		.isNotNull();
		
		//Sets the top border color of the current {@link BorderWidgetLook}.
		this.topBorderColor = topBorderColor;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the top border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @param topBorderThickness
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NegativeArgumentException if the given top border thickness is negative.
	 */
	public final BWL setTopBorderThickness(final int topBorderThickness) {
		
		this.topBorderThickness = new NonNegativeInteger(topBorderThickness);
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the top padding of the current {@link BorderWidgetLook}.
	 * 
	 * @param leftPadding
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NegativeArgumentException if the given top padding is negative.
	 */
	public final BWL setTopPadding(final int topPadding) {
		
		this.topPadding = new NonNegativeInteger(topPadding);
		
		return getInstance();
	}
	

	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a border color.
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
	 * @return true if the current {@link BorderWidgetLook} has a border thickness.
	 */
	private boolean hasABorderThickness() {
		return (
			hasLeftBorderThickness()
			|| hasRightBorderThickness()
			|| hasTopBorderThickness()
			|| hasBottomBorderThickness()
		);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a padding.
	 */
	private boolean hasAPadding() {
		return (
			hasLeftPadding()
			|| hasRightPadding()
			|| hasTopPadding()
			|| hasBottomPadding()
		);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a bottom border color.
	 */
	private boolean hasBottomBorderColor() {
		return (bottomBorderColor != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a bottom border thickness.
	 */
	private boolean hasBottomBorderThickness() {
		return (bottomBorderThickness != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a bottom padding.
	 */
	private boolean hasBottomPadding() {
		return (bottomPadding != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a left border color.
	 */
	private boolean hasLeftBorderColor() {
		return (leftBorderColor != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a left border thickness.
	 */
	private boolean hasLeftBorderThickness() {
		return (leftBorderThickness != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a left padding.
	 */
	private boolean hasLeftPadding() {
		return (bottomPadding != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a right border color.
	 */
	private boolean hasRightBorderColor() {
		return (rightBorderColor != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a right border thickness.
	 */
	private boolean hasRightBorderThickness() {
		return (rightBorderThickness != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a right padding.
	 */
	private boolean hasRightPadding() {
		return (rightPadding != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has the same border color at each side.
	 */
	private boolean hasSameBorderColorAtEachSide()  {
		
		//Handles the case that the current border widget look has no border color.
		if (
			!hasLeftBorderColor()
			&& !hasRightBorderColor()
			&& !hasTopBorderColor()
			&& !hasBottomBorderColor()
		) {
			return true;
		}
		
		//Handles the case that the current border widget look has a border color.
		return (
			hasLeftBorderColor()
			&& leftBorderColor.equals(rightBorderColor)
			&& leftBorderColor.equals(topBorderColor)
			&& leftBorderColor.equals(bottomBorderColor)
		);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has the same border thickness at each side.
	 */
	private boolean hasSameBorderThicknessAtEachSide() {
		
		//Handles the case that the current border widget look has no border thickness.
		if (
			!hasLeftBorderThickness()
			&& !hasRightBorderThickness()
			&& !hasTopBorderThickness()
			&& !hasBottomBorderThickness()
		) {
			return true;
		}
		
		//Handles the case that the current border widget look has a border thickness.
		return (
			hasLeftBorderThickness()
			&& leftBorderThickness.equals(rightBorderThickness)
			&& leftBorderThickness.equals(topBorderThickness)
			&& leftBorderThickness.equals(bottomBorderThickness)
		);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has the same padding at each side.
	 */
	private boolean hasSamePaddingAtEachSide() {
		
		//Handles the case that the current border widget look has no paddings.
		if (
			!hasLeftPadding()
			&& !hasRightPadding()
			&& !hasTopPadding()
			&& !hasBottomPadding()
		) {
			return true;
		}
		
		//Handles the case that the current {@link BorderWidgetLook} has a padding.
		return (
			hasLeftPadding()
			&& rightPadding.equals(leftPadding)
			&& topPadding.equals(leftPadding)
			&& bottomPadding.equals(bottomPadding)
		);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a topt border color.
	 */
	private boolean hasTopBorderColor() {
		return (topBorderColor != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a top border thickness.
	 */
	private boolean hasTopBorderThickness() {
		return (topBorderThickness != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a top padding.
	 */
	private boolean hasTopPadding() {
		return (topPadding != null);
	}
}
