//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.List;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.node.Node;
import ch.nolix.core.node.BaseNode;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI.WidgetLook;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.core.NonNegativeInteger;
import ch.nolix.element.image.Image;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1660
 * @param <BWL> The type of a {@link BorderWidgetLook}.
 */
public abstract class BorderWidgetLook<BWL extends BorderWidgetLook<BWL>> extends WidgetLook<BWL> {
	
	//default values
	public static final int DEFAULT_BORDER_THICKNESS = 0;
	public static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//default value
	public static final ColorGradient DEFAULT_BACKGROUND_COLOR_GRADIENT =
	ColorGradient.VERTICAL_BLACK_WHITE_COLOR_GRADIENT;
	
	//default values
	public static final Image DEFAULT_BACKGROUND_IMAGE = new Image(200, 100, Color.BLACK);
	public static final int DEFAULT_PADDING = 0;
	public static final ScrollbarLook DEFAULT_BASE_SCROLLBAR_LOOK = new ScrollbarLook();
	
	//default value
	public static final ScrollbarLook DEFAULT_HOVER_SCROLLBAR_LOOK =
	new ScrollbarLook()
	.setScrollbarCursorColor(Color.DIM_GREY);
	
	//default value
	public static final ScrollbarLook DEFAULT_SELECTION_SCROLLBAR_LOOK =
	new ScrollbarLook()
	.setScrollbarCursorColor(Color.DIM_GREY);
	
	//constants
	private static final String BORDER_THICKNESS_HEADER = "BorderThickness";
	private static final String LEFT_BORDER_THICKNESS_HEADER = "LeftBorderThickness";
	private static final String RIGHT_BORDER_THICKNESS_HEADER = "RightBorderThickness";
	private static final String TOP_BORDER_THICKNESS_HEADER = "TopBorderThickness";
	private static final String BOTTOM_BORDER_THICKNESS_HEADER = "BottomBorderThickness";
	
	//constants
	private static final String BORDER_COLOR_HEADER = "BorderColor";
	private static final String LEFT_BORDER_COLOR_HEADER = "LeftBorderColor";
	private static final String RIGHT_BORDER_COLOR_HEADER = "RightBorderColor";
	private static final String TOP_BORDER_COLOR_HEADER = "TopBorderColor";
	private static final String BOTTOM_BORDER_COLOR_HEADER = "BottomBorderColor";
	
	//constants
	private static final String BACKGROUND_COLOR_GRADIENT_HEADER = "BackgroundColorGradient";
	private static final String BACKGROUND_IMAGE_HEADER = "BackgroundImage";
	
	//constants
	private static final String PADDING_HEADER = "Padding";
	private static final String LEFT_PADDING_HEADER = "LeftPadding";
	private static final String RIGHT_PADDING_HEADER = "RightPadding";
	private static final String TOP_PADDING_HEADER = "TopPadding";
	private static final String BOTTOM_PADDING_HEADER = "BottomPadding";
	
	//constants
	private static final String BASE_SCROLLBAR_LOOK_HEADER = "BaseScrollbarLook";
	private static final String HOVER_SCROLLBAR_LOOK_HEADER = "HoverScrollbarLook";
	private static final String SELECTION_SCROLLBAR_LOOK_HEADER = "SelectionScrollbarLook";
	
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
	//TODO: Create Union type.
	private Color backgroundColor;
	private ColorGradient backgroundColorGradient;
	private Image backgroundImage;
	
	//optional attributes
	private NonNegativeInteger leftPadding;
	private NonNegativeInteger rightPadding;
	private NonNegativeInteger topPadding;
	private NonNegativeInteger bottomPadding;
	
	//optional attributes
	private ScrollbarLook baseScrollbarLook;
	private ScrollbarLook hoverScrollbarLook;
	private ScrollbarLook selectionScrollbarLook;
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link BorderWidgetLook}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
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
				setBorderColors(Color.createFromSpecification(attribute));
				break;
			case LEFT_BORDER_COLOR_HEADER:
				setLeftBorderColor(Color.createFromSpecification(attribute));
				break;
			case RIGHT_BORDER_COLOR_HEADER:
				setRightBorderColor(Color.createFromSpecification(attribute));
				break;
			case BOTTOM_BORDER_COLOR_HEADER:
				setBottomBorderColor(Color.createFromSpecification(attribute));
				break;
			case TOP_BORDER_COLOR_HEADER:
				setTopBorderColor(Color.createFromSpecification(attribute));
				break;
			case PascalCaseNameCatalogue.BACKGROUND_COLOR:
				setBackgroundColor(Color.createFromSpecification(attribute));
				break;
			case BACKGROUND_COLOR_GRADIENT_HEADER:
				setBackgroundColorGradient(ColorGradient.createFromSpecification(attribute));
				break;
			case BACKGROUND_IMAGE_HEADER:
				setBackgroundImage(Image.createFromSpecification(attribute));
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
			case BASE_SCROLLBAR_LOOK_HEADER:
				setBaseScrollbarLook(ScrollbarLook.createFromSpecification(attribute));
				break;
			case HOVER_SCROLLBAR_LOOK_HEADER:
				setHoverScrollbarLook(ScrollbarLook.createFromSpecification(attribute));
				break;
			case SELECTION_SCROLLBAR_LOOK_HEADER:
				setSelectionScrollbarLook(ScrollbarLook.createFromSpecification(attribute));
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
	@Override
	public List<Node> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		fillUpBorderThicknessesSpecifications(attributes);
		fillUpBorderColorsSpecifications(attributes);
		
		if (hasBackgroundColor()) {
			attributes.addAtEnd(backgroundColor.getSpecificationAs(PascalCaseNameCatalogue.BACKGROUND_COLOR));
		}
		else if (hasBackgroundColorGradient()) {
			attributes.addAtEnd(backgroundColorGradient.getSpecificationAs(BACKGROUND_COLOR_GRADIENT_HEADER));
		}
		else if (hasBackgroundImage()) {
			attributes.addAtEnd(backgroundImage.getSpecificationAs(BACKGROUND_IMAGE_HEADER));
		}
		
		fillUpScrollbarLooksSpecifications(attributes);
		
		fillUpPaddingSpecifications(attributes);	
				
		return attributes;
	}
	
	 //method
	 /**
	 * @return the recursive or default background color
	 * of the current {@link BorderWidgetLook}.
	 */
	public final Color getRecursiveOrDefaultBackgroundColor() {
		
		if (hasBackgroundColor()) {
			return backgroundColor;
		}
		
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultBackgroundColor();
		}
		
		return DEFAULT_BACKGROUND_COLOR;
	}
	
	//method
	/**
	 * @return the recursive or default background color gradient
	 * of the current {@link BorderWidgetLook}.
	 */
	public final ColorGradient getRecursiveOrDefaultBackgroundColorGradient() {
		
		if (hasBackgroundColorGradient()) {
			return backgroundColorGradient;
		}
		
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultBackgroundColorGradient();
		}
		
		return DEFAULT_BACKGROUND_COLOR_GRADIENT;
	}
	
	//method
	/**
	 * @return the recursive or default background image
	 * of the current {@link BorderWidgetLook}.
	 */
	public final Image getRecursiveOrDefaultBackgroundImage() {
		
		if (hasBackgroundImage()) {
			return backgroundImage;
		}
		
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultBackgroundImage();
		}
		
		return DEFAULT_BACKGROUND_IMAGE;
	}
	
	//method
	/**
	 * @return the recursive or default base scrollbar look of the current {@link BorderWidgetLook}.
	 */
	public final ScrollbarLook getRecursiveOrDefaultBaseScrollbarLook() {
		
		//Handles the case that the current border widget look has a base scrollbar look.
		if (hasBaseScrollbarLook()) {
			return baseScrollbarLook;
		}
		
		//Handles the case that the current border widget look
		//does not have a base scrollbar look but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultBaseScrollbarLook();
		}
		
		//Handles the case that the current border widget look
		//does not have a base scrollbar look or base look.
		return DEFAULT_BASE_SCROLLBAR_LOOK;
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
		//does not have a bottom border color but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultBottomBorderColor();
		}
		
		//Handles the case that the current border widget look
		//does not have a bottom border color or base look.
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
		//does not have a bottom border thickness but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultBottomBorderThickness();
		}
		
		//Handles the case that the current border widget look
		//does not have a bottom border thickness or base look.
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
		//does not have a bottom padding but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultBottomPadding();
		}
		
		//Handles the case that the current border widget look
		//does not have a bottom padding or base look.
		return DEFAULT_PADDING;
	}
	
	//method
	/**
	 * @return the recursive or default hover scrollbar look of the current {@link BorderWidgetLook}.
	 */
	public final ScrollbarLook getRecursiveOrDefaultHoverScrollbarLook() {
		
		//Handles the case that the current border widget look has a hover scrollbar look.
		if (hasHoverScrollbarLook()) {
			return hoverScrollbarLook;
		}
		
		//Handles the case that the current border widget look
		//does not have a hover scrollbar look but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultHoverScrollbarLook();
		}
		
		//Handles the case that the current border widget look
		//does not have a hover scrollbar look or base look.
		return DEFAULT_HOVER_SCROLLBAR_LOOK;
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
		//does not have a left border color but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultLeftBorderColor();
		}
		
		//Handles the case that the current border widget look
		//does not have a left border color or base look.
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
		//does not have a left border thickness but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultLeftBorderThickness();
		}
		
		//Handles the case that the current border widget look
		//does not have a left border thickness or base look.
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
		//does not have a left padding but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultLeftPadding();
		}
		
		//Handles the case that the current border widget look
		//does not have a left padding or base look.
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
		//does not have a right border color but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultRightBorderColor();
		}
		
		//Handles the case if the current {@link BorderWidgetLook}
		//does not have a right border color or base look.
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
		//does not have a right border thickness but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultRightBorderThickness();
		}
		
		//Handles the case that the current border widget look
		//does not have a right border thickness or base look.
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
		//does not have a right padding but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultRightPadding();
		}
		
		//Handles the case that the current border widget look
		//does not have a right padding or base look.
		return DEFAULT_PADDING;
	}
	
	//method
	/**
	 * @return the recursive or default selection scrollbar look of the current {@link BorderWidgetLook}.
	 */
	public final ScrollbarLook getRecursiveOrDefaultSelectionScrollbarLook() {
		
		//Handles the case that the current border widget look has a selection scrollbar look.
		if (hasSelectionScrollbarLook()) {
			return selectionScrollbarLook;
		}
		
		//Handles the case that the current border widget look
		//does not have a selection scrollbar look but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultSelectionScrollbarLook();
		}
		
		//Handles the case that the current border widget look
		//does not have a selection scrollbar look or base look.
		return DEFAULT_SELECTION_SCROLLBAR_LOOK;
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
		//does not have a top border color but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultTopBorderColor();
		}
		
		//Handles the case that the current border widget look
		//does not have a top border color or base look.
		return DEFAULT_BORDER_COLOR;
	}
	
	//method
	/**
	 * @return the recursive or default top border thickness of the current {@link BorderWidgetLook}.
	 */
	public final int getRecursiveOrDefaultTopBorderThickness() {
		
		//Handles the case that the current border widget look does not have a top border thickness.
		if (hasTopBorderThickness()) {
			return topBorderThickness.getValue();
		}
		
		//Handles the case that the current border widget look
		//does not have a top border thickness but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultTopBorderThickness();
		}
		
		//Handles the case that the current border widget look
		//does not have a top border thickness or base look.
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
		//does not have a top padding but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultTopPadding();
		}
		
		//Handles the case that the current border widget look
		//does not have a top padding or base look.
		return DEFAULT_PADDING;
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a recursive background color.
	 */
	public final boolean hasRecursiveBackgroundColor() {
		
		if (hasBackgroundColor()) {
			return true;
		}
		
		if (hasBaseLook()) {
			return getRefBaseLook().hasRecursiveBackgroundColor();
		}
		
		return false;
	}
	 
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a recursive background color gradient.
	 */
	public final boolean hasRecursiveBackgroundColorGradient() {
		
		if (hasBackgroundColorGradient()) {
			return true;
		}
		
		if (hasBaseLook()) {
			return getRefBaseLook().hasRecursiveBackgroundColorGradient();
		}
		
		return false;
	}
	 
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a recursive background image.
	 */
	public final boolean hasRecursiveBackgroundImage() {
		
		if (hasBackgroundImage()) {
			return true;
		}
		
		if (hasBaseLook()) {
			return getRefBaseLook().hasRecursiveBackgroundImage();
		}
		
		return false;
	}
	
	//method
	/**
	 * Removes any background of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeAnyBackground() {
		
		backgroundColor = null;
		backgroundColorGradient = null;
		backgroundImage = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the base {@link ScrollbarLook} of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBaseScrollbarLook() {
		
		baseScrollbarLook = null;
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the bottom border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBottomBorderColor() {
		
		bottomBorderColor = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the bottom border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBottomBorderThickness() {
		
		bottomBorderThickness = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the bottom padding of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBottomPadding() {
		
		bottomPadding = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the hover {@link ScrollbarLook} of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeHoverScrollbarLook() {
		
		hoverScrollbarLook = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the left border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeLeftBorderColor() {
		
		leftBorderColor = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the left border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeLeftBorderThickness() {
		
		leftBorderThickness = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the left padding of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeLeftPadding() {
		
		leftPadding = null;
		
		return asConcreteType();
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
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the right border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeRightBorderColor() {
		
		rightBorderColor = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the right border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeRightBorderThickness() {
		
		rightBorderThickness = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the right padding of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeRightPadding() {
		
		rightPadding = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the {@link ScrollbarLook}s of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeScrollbarLooks() {
		
		removeBaseScrollbarLook();
		removeHoverScrollbarLook();
		removeSelectionScrollbarLook();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the selection {@link ScrollbarLook} of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeSelectionScrollbarLook() {
		
		selectionScrollbarLook = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the top border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeTopBorderColor() {
		
		topBorderColor = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the top border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeTopBorderThickness() {
		
		topBorderThickness = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the top padding of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeTopPadding() {
		
		topPadding = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BWL reset() {
		
		//Calls method of the base class.
		super.reset();
		
		removeBorderThicknesses();
		removeBorderColors();
		removeAnyBackground();
		removeScrollbarLooks();
		removePaddings();
				
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the background color of the current {@link BorderWidgetLook}.
	 * Removes any former background of the current {@link BorderWidgetLook}.
	 * 
	 * @param backgroundColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NullArgumentException if the given background color is null.
	 */
	public final BWL setBackgroundColor(final Color backgroundColor) {
		
		Validator.suppose(backgroundColor).thatIsNamed(VariableNameCatalogue.BACKGROUND_COLOR).isNotNull();
		
		removeAnyBackground();
		this.backgroundColor = backgroundColor;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the background color gradient of the current {@link BorderWidgetLook}.
	 * Removes any former background of the current {@link BorderWidgetLook}.
	 * 
	 * @param backgroundColorGradient
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NullArgumentException if the given background color is null.
	 */
	public final BWL setBackgroundColorGradient(final ColorGradient backgroundColorGradient) {
		
		Validator.suppose(backgroundColorGradient).thatIsNamed("background color gradient").isNotNull();
		
		removeAnyBackground();
		this.backgroundColorGradient = backgroundColorGradient;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the background image of the current {@link BorderWidgetLook}.
	 * Removes any former background of the current {@link BorderWidgetLook}.
	 * 
	 * @param backgroundImage
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NullArgumentException if the given background image is null.
	 */
	public final BWL setBackgroundImage(final Image backgroundImage) {
		
		Validator.suppose(backgroundImage).thatIsNamed("background image").isNotNull();
		
		removeAnyBackground();
 		this.backgroundImage = backgroundImage;
 		
 		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the base scrollbar look of the current {@link BorderWidgetLook}.
	 * 
	 * @param baseScrollbarLook
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NullArgumentException if the given base scrollbar look is null.
	 */
	public final BWL setBaseScrollbarLook(final ScrollbarLook baseScrollbarLook) {
		
		//Checks if the given base scrollbar look is not null.
		Validator
		.suppose(baseScrollbarLook)
		.thatIsNamed("base scrollbar look")
		.isNotNull();
		
		//Sets the base scrollbar look of the current border widget look.
		this.baseScrollbarLook = baseScrollbarLook;
		
		return asConcreteType();
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
		
		Validator
		.suppose(borderColor)
		.thatIsNamed("border color")
		.isNotNull();
		
		setLeftBorderColor(borderColor);
		setRightBorderColor(borderColor);
		setTopBorderColor(borderColor);
		setBottomBorderColor(borderColor);
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the hover scrollbar look of the current {@link BorderWidgetLook}.
	 * 
	 * @param hoverScrollbarLook
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NullArgumentException if the given hover scrollbar look is null.
	 */
	public final BWL setHoverScrollbarLook(final ScrollbarLook hoverScrollbarLook) {
		
		//Checks if the given hover scrollbar look is not null.
		Validator
		.suppose(hoverScrollbarLook)
		.thatIsNamed("hover scrollbar look")
		.isNotNull();
		
		//Sets the hover scrollbar look of the current border widget look.
		this.hoverScrollbarLook = hoverScrollbarLook;
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the selection scrollbar look of the current {@link BorderWidgetLook}.
	 * 
	 * @param selectionScrollbarLook
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NullArgumentException if the given selection scrollbar look is null.
	 */
	public final BWL setSelectionScrollbarLook(final ScrollbarLook selectionScrollbarLook) {
		
		//Checks if the given selection scrollbar look is not null.
		Validator
		.suppose(selectionScrollbarLook)
		.thatIsNamed("selection scrollbar look")
		.isNotNull();
		
		//Sets the selection scrollbar look of the current border widget look.
		this.selectionScrollbarLook = selectionScrollbarLook;
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
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
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Fills up the specification of the border colors of the current {@link BorderWidgetLook} into the given list.
	 * 
	 * @param list
	 */
	private void fillUpBorderColorsSpecifications(final List<Node> list) {
		if (hasABorderColor() && hasSameBorderColorAtEachSide()) {
			list.addAtEnd(leftBorderColor.getSpecificationAs(BORDER_COLOR_HEADER));
		}
		else {
			
			if (hasLeftBorderColor()) {
				list.addAtEnd(leftBorderColor.getSpecificationAs(LEFT_BORDER_COLOR_HEADER));
			}
			
			if (hasRightBorderColor()) {
				list.addAtEnd(rightBorderColor.getSpecificationAs(RIGHT_BORDER_COLOR_HEADER));
			}
			
			if (hasTopBorderColor()) {
				list.addAtEnd(topBorderColor.getSpecificationAs(TOP_BORDER_COLOR_HEADER));
			}
			
			if (hasBottomBorderColor()) {
				list.addAtEnd(bottomBorderColor.getSpecificationAs(BOTTOM_BORDER_COLOR_HEADER));
			}
		}
	}
	
	//method
	/**
	 * Fills up the specification of the border thicknesses of the current {@link BorderWidgetLook} into the given list.
	 * 
	 * @param list
	 */
	private void fillUpBorderThicknessesSpecifications(final List<Node> list) {
		if (hasABorderThickness() && hasSameBorderThicknessAtEachSide()) {
			list.addAtEnd(leftBorderThickness.getSpecificationAs(BORDER_THICKNESS_HEADER));
		}
		else {
			
			if (hasLeftBorderThickness()) {
				list.addAtEnd(leftBorderThickness.getSpecificationAs(LEFT_BORDER_THICKNESS_HEADER));
			}
			
			if (hasRightBorderThickness()) {
				list.addAtEnd(rightBorderThickness.getSpecificationAs(RIGHT_BORDER_THICKNESS_HEADER));
			}
			
			if (hasTopBorderThickness()) {
				list.addAtEnd(topBorderThickness.getSpecificationAs(TOP_BORDER_THICKNESS_HEADER));
			}
			
			if (hasBottomBorderThickness()) {
				list.addAtEnd(bottomBorderThickness.getSpecificationAs(BOTTOM_BORDER_THICKNESS_HEADER));
			}
		}
	}
	
	//method
	/**
	 * Fills up the specification of the paddings of the current {@link BorderWidgetLook} into the given list.
	 * 
	 * @param list
	 */
	private void fillUpPaddingSpecifications(final List<Node> list) {
		if (hasAPadding() && hasSamePaddingAtEachSide()) {
			list.addAtEnd(leftPadding.getSpecificationAs(PADDING_HEADER));
		}
		else {
			
			if (hasLeftPadding()) {
				list.addAtEnd(leftPadding.getSpecificationAs(LEFT_PADDING_HEADER));
			}
			
			if (hasRightPadding()) {
				list.addAtEnd(rightPadding.getSpecificationAs(RIGHT_PADDING_HEADER));
			}
			
			if (hasTopPadding()) {
				list.addAtEnd(topPadding.getSpecificationAs(TOP_PADDING_HEADER));
			}
			
			if (hasBottomPadding()) {
				list.addAtEnd(bottomPadding.getSpecificationAs(BOTTOM_PADDING_HEADER));
			}
		}
	}
	
	//method
	/**
	 * Fills up the specifications of the scrollbar looks of the current {@link BorderWidgetLook} into the given list.
	 * 
	 * @param list
	 */
	private void fillUpScrollbarLooksSpecifications(final List<Node> list) {
		
		if (hasBaseScrollbarLook()) {
			list.addAtEnd(baseScrollbarLook.getSpecificationAs(BASE_SCROLLBAR_LOOK_HEADER));
		}
		
		if (hasHoverScrollbarLook()) {
			list.addAtEnd(hoverScrollbarLook.getSpecificationAs(HOVER_SCROLLBAR_LOOK_HEADER));
		}
		
		if (hasSelectionScrollbarLook()) {
			list.addAtEnd(selectionScrollbarLook.getSpecificationAs(SELECTION_SCROLLBAR_LOOK_HEADER));
		}
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
	 * @return true if the current {@link BorderWidgetLook} has a background color.
	 */
	private boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a background color gradient.
	 */
	private boolean hasBackgroundColorGradient() {
		return (backgroundColorGradient != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a background image.
	 */
	private boolean hasBackgroundImage() {
		return (backgroundImage != null);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a base scrollbar look.
	 */
	private boolean hasBaseScrollbarLook() {
		return (baseScrollbarLook != null);
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
	 * @return true if the current {@link BorderWidgetLook} has a hover scrollbar look.
	 */
	private boolean hasHoverScrollbarLook() {
		return (hoverScrollbarLook != null);
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
		return (leftPadding != null);
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
	private boolean hasSameBorderColorAtEachSide() {
		
		//Handles the case that the current border widget look does not have a border color.
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
		
		//Handles the case that the current border widget look does not have a border thickness.
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
		
		//Handles the case that the current border widget look does not have a paddings.
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
			&& hasRightPadding() && rightPadding.equals(leftPadding)
			&& hasTopPadding() && topPadding.equals(leftPadding)
			&& hasBottomPadding() && bottomPadding.equals(leftPadding)
		);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a selection scrollbar look.
	 */
	private boolean hasSelectionScrollbarLook() {
		return (selectionScrollbarLook != null);
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
