//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.gui.WidgetLook;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 1690
 * @param <BWL> is the type of a {@link BorderWidgetLook}.
 */
public abstract class BorderWidgetLook<BWL extends BorderWidgetLook<BWL>> extends WidgetLook<BWL> {
	
	//constants
	public static final int DEFAULT_BORDER_THICKNESS = 0;
	public static final Color DEFAULT_BORDER_COLOR = Color.BLACK;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//constant
	public static final ColorGradient DEFAULT_BACKGROUND_COLOR_GRADIENT =
	ColorGradient.VERTICAL_BLACK_WHITE_COLOR_GRADIENT;
	
	//constants
	public static final Image DEFAULT_BACKGROUND_IMAGE = new Image(200, 100, Color.BLACK);
	public static final int DEFAULT_PADDING = 0;
	public static final BorderWidgetScrollBarLook DEFAULT_BASE_SCROLL_BAR_LOOK = new BorderWidgetScrollBarLook();
	
	//constant
	public static final BorderWidgetScrollBarLook DEFAULT_HOVER_SCROLL_BAR_LOOK =
	new BorderWidgetScrollBarLook()
	.setScrollCursorColor(Color.DIM_GREY);
	
	//constant
	public static final BorderWidgetScrollBarLook DEFAULT_SELECTION_SCROLL_BAR_LOOK =
	new BorderWidgetScrollBarLook()
	.setScrollCursorColor(Color.DIM_GREY);
	
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
	private static final String BASE_SCROLL_BAR_LOOK_HEADER = "BaseScrollBarLook";
	private static final String HOVER_SCROLL_BAR_LOOK_HEADER = "HoverScrollBarLook";
	private static final String SELECTION_SCROLL_BAR_LOOK_HEADER = "SelectionScrollBarLook";
	
	//optional attributes
	private int leftBorderThickness = -1;
	private int rightBorderThickness = -1;
	private int topBorderThickness = -1;
	private int bottomBorderThickness = -1;
	
	//optional attributes
	private Color leftBorderColor;
	private Color rightBorderColor;
	private Color topBorderColor;
	private Color bottomBorderColor;
	
	//optional attributes
	private Color backgroundColor;
	private ColorGradient backgroundColorGradient;
	private Image backgroundImage;
	
	//optional attributes
	private int leftPadding = -1;
	private int rightPadding = -1;
	private int topPadding = -1;
	private int bottomPadding = -1;
	
	//optional attributes
	private BorderWidgetScrollBarLook baseScrollBarLook;
	private BorderWidgetScrollBarLook hoverScrollBarLook;
	private BorderWidgetScrollBarLook selectionScrollBarLook;
	
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
				setBorderColors(Color.fromSpecification(attribute));
				break;
			case LEFT_BORDER_COLOR_HEADER:
				setLeftBorderColor(Color.fromSpecification(attribute));
				break;
			case RIGHT_BORDER_COLOR_HEADER:
				setRightBorderColor(Color.fromSpecification(attribute));
				break;
			case BOTTOM_BORDER_COLOR_HEADER:
				setBottomBorderColor(Color.fromSpecification(attribute));
				break;
			case TOP_BORDER_COLOR_HEADER:
				setTopBorderColor(Color.fromSpecification(attribute));
				break;
			case PascalCaseCatalogue.BACKGROUND_COLOR:
				setBackgroundColor(Color.fromSpecification(attribute));
				break;
			case BACKGROUND_COLOR_GRADIENT_HEADER:
				setBackgroundColorGradient(ColorGradient.fromSpecification(attribute));
				break;
			case BACKGROUND_IMAGE_HEADER:
				setBackgroundImage(Image.fromSpecification(attribute));
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
			case BASE_SCROLL_BAR_LOOK_HEADER:
				setBaseScrollBarLook(BorderWidgetScrollBarLook.fromSpecification(attribute));
				break;
			case HOVER_SCROLL_BAR_LOOK_HEADER:
				setHoverScrollBarLook(BorderWidgetScrollBarLook.fromSpecification(attribute));
				break;
			case SELECTION_SCROLL_BAR_LOOK_HEADER:
				setSelectionScrollBarLook(BorderWidgetScrollBarLook.fromSpecification(attribute));
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		fillUpBorderThicknessesSpecifications(list);
		fillUpBorderColorsSpecifications(list);
		
		if (hasBackgroundColor()) {
			list.addAtEnd(backgroundColor.getSpecificationAs(PascalCaseCatalogue.BACKGROUND_COLOR));
		} else if (hasBackgroundColorGradient()) {
			list.addAtEnd(backgroundColorGradient.getSpecificationAs(BACKGROUND_COLOR_GRADIENT_HEADER));
		} else if (hasBackgroundImage()) {
			list.addAtEnd(backgroundImage.getSpecificationAs(BACKGROUND_IMAGE_HEADER));
		}
		
		fillUpScrollBarLooksSpecifications(list);
		
		fillUpPaddingSpecifications(list);	
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
	 * @return the recursive or default base scroll bar look of the current {@link BorderWidgetLook}.
	 */
	public final BorderWidgetScrollBarLook getRecursiveOrDefaultBaseScrollBarLook() {
		
		//Handles the case that the current border widget look has a base scroll bar look.
		if (hasBaseScrollBarLook()) {
			return baseScrollBarLook;
		}
		
		//Handles the case that the current border widget look
		//does not have a base scroll bar look but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultBaseScrollBarLook();
		}
		
		//Handles the case that the current border widget look
		//does not have a base scroll bar look or base look.
		return DEFAULT_BASE_SCROLL_BAR_LOOK;
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
			return bottomBorderThickness;
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
			return bottomPadding;
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
	 * @return the recursive or default hover scroll bar look of the current {@link BorderWidgetLook}.
	 */
	public final BorderWidgetScrollBarLook getRecursiveOrDefaultHoverScrollBarLook() {
		
		//Handles the case that the current border widget look has a hover scroll bar look.
		if (hasHoverScrollBarLook()) {
			return hoverScrollBarLook;
		}
		
		//Handles the case that the current border widget look
		//does not have a hover scroll bar look but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultHoverScrollBarLook();
		}
		
		//Handles the case that the current border widget look
		//does not have a hover scroll bar look or base look.
		return DEFAULT_HOVER_SCROLL_BAR_LOOK;
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
			return leftBorderThickness;
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
			return leftPadding;
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
			return rightBorderThickness;
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
			return rightPadding;
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
	 * @return the recursive or default selection scroll bar look of the current {@link BorderWidgetLook}.
	 */
	public final BorderWidgetScrollBarLook getRecursiveOrDefaultSelectionScrollBarLook() {
		
		//Handles the case that the current border widget look has a selection scroll bar look.
		if (hasSelectionScrollBarLook()) {
			return selectionScrollBarLook;
		}
		
		//Handles the case that the current border widget look
		//does not have a selection scroll bar look but a base look.
		if (hasBaseLook()) {
			return getRefBaseLook().getRecursiveOrDefaultSelectionScrollBarLook();
		}
		
		//Handles the case that the current border widget look
		//does not have a selection scroll bar look or base look.
		return DEFAULT_SELECTION_SCROLL_BAR_LOOK;
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
			return topBorderThickness;
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
			return topPadding;
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
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the base {@link BorderWidgetScrollBarLook} of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBaseScrollBarLook() {
		
		baseScrollBarLook = null;
		
		return asConcrete();
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
		
		return asConcrete();
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
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the bottom border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBottomBorderColor() {
		
		bottomBorderColor = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the bottom border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBottomBorderThickness() {
		
		bottomBorderThickness = -1;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the bottom padding of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeBottomPadding() {
		
		bottomPadding = -1;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the hover {@link BorderWidgetScrollBarLook} of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeHoverScrollBarLook() {
		
		hoverScrollBarLook = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the left border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeLeftBorderColor() {
		
		leftBorderColor = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the left border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeLeftBorderThickness() {
		
		leftBorderThickness = -1;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the left padding of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeLeftPadding() {
		
		leftPadding = -1;
		
		return asConcrete();
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
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the right border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeRightBorderColor() {
		
		rightBorderColor = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the right border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeRightBorderThickness() {
		
		rightBorderThickness = -1;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the right padding of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeRightPadding() {
		
		rightPadding = -1;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the {@link BorderWidgetScrollBarLook}s of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeScrollBarLooks() {
		
		removeBaseScrollBarLook();
		removeHoverScrollBarLook();
		removeSelectionScrollBarLook();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the selection {@link BorderWidgetScrollBarLook} of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeSelectionScrollBarLook() {
		
		selectionScrollBarLook = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the top border color of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeTopBorderColor() {
		
		topBorderColor = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the top border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeTopBorderThickness() {
		
		topBorderThickness = -1;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the top padding of the current {@link BorderWidgetLook}.
	 * 
	 * @return the current {@link BorderWidgetLook}.
	 */
	public final BWL removeTopPadding() {
		
		topPadding = -1;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the background color of the current {@link BorderWidgetLook}.
	 * Removes any former background of the current {@link BorderWidgetLook}.
	 * 
	 * @param backgroundColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws ArgumentIsNullException if the given background color is null.
	 */
	public final BWL setBackgroundColor(final Color backgroundColor) {
		
		Validator.assertThat(backgroundColor).thatIsNamed(LowerCaseCatalogue.BACKGROUND_COLOR).isNotNull();
		
		removeAnyBackground();
		this.backgroundColor = backgroundColor;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the background color gradient of the current {@link BorderWidgetLook}.
	 * Removes any former background of the current {@link BorderWidgetLook}.
	 * 
	 * @param backgroundColorGradient
	 * @return the current {@link BorderWidgetLook}.
	 * @throws ArgumentIsNullException if the given background color is null.
	 */
	public final BWL setBackgroundColorGradient(final ColorGradient backgroundColorGradient) {
		
		Validator.assertThat(backgroundColorGradient).thatIsNamed("background color gradient").isNotNull();
		
		removeAnyBackground();
		this.backgroundColorGradient = backgroundColorGradient;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the background image of the current {@link BorderWidgetLook}.
	 * Removes any former background of the current {@link BorderWidgetLook}.
	 * 
	 * @param backgroundImage
	 * @return the current {@link BorderWidgetLook}.
	 * @throws ArgumentIsNullException if the given background image is null.
	 */
	public final BWL setBackgroundImage(final Image backgroundImage) {
		
		Validator.assertThat(backgroundImage).thatIsNamed("background image").isNotNull();
		
		removeAnyBackground();
 		this.backgroundImage = backgroundImage;
 		
 		return asConcrete();
	}
	
	//method
	/**
	 * Sets the base scroll bar look of the current {@link BorderWidgetLook}.
	 * 
	 * @param baseScrollBarLook
	 * @return the current {@link BorderWidgetLook}.
	 * @throws ArgumentIsNullException if the given base scroll bar look is null.
	 */
	public final BWL setBaseScrollBarLook(final BorderWidgetScrollBarLook baseScrollBarLook) {
		
		//Asserts that the given base scroll bar look is not null.
		Validator
		.assertThat(baseScrollBarLook)
		.thatIsNamed("base scroll bar look")
		.isNotNull();
		
		//Sets the base scroll bar look of the current border widget look.
		this.baseScrollBarLook = baseScrollBarLook;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the border colors of the current {@link BorderWidgetLook}.
	 * 
	 * @param borderColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws ArgumentIsNullException if the given border color is null.
	 */
	public final BWL setBorderColors(final Color borderColor) {
		
		Validator
		.assertThat(borderColor)
		.thatIsNamed("border color")
		.isNotNull();
		
		setLeftBorderColor(borderColor);
		setRightBorderColor(borderColor);
		setTopBorderColor(borderColor);
		setBottomBorderColor(borderColor);
		
		return asConcrete();
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
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the bottom border color of the current {@link BorderWidgetLook}.
	 * 
	 * @param bottomBorderColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws ArgumentIsNullException if the given bottom border color is null.
	 */
	public final BWL setBottomBorderColor(final Color bottomBorderColor) {
		
		//Asserts that the given bottom border color is not null.
		Validator
		.assertThat(bottomBorderColor)
		.thatIsNamed("bottom border color")
		.isNotNull();
		
		//Sets the bottom border color of the current {@link BorderWidgetLook}.
		this.bottomBorderColor = bottomBorderColor;
		
		return asConcrete();
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
		
		Validator.assertThat(bottomBorderThickness).thatIsNamed("bottom border thickness").isNotNegative();
		
		this.bottomBorderThickness = bottomBorderThickness;
		
		return asConcrete();
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
		
		Validator.assertThat(bottomPadding).thatIsNamed("bottom padding").isNotNegative();
		
		this.bottomPadding = bottomPadding;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the hover scroll bar look of the current {@link BorderWidgetLook}.
	 * 
	 * @param hoverScrollBarLook
	 * @return the current {@link BorderWidgetLook}.
	 * @throws ArgumentIsNullException if the given hover scroll bar look is null.
	 */
	public final BWL setHoverScrollBarLook(final BorderWidgetScrollBarLook hoverScrollBarLook) {
		
		//Asserts that the given hover scroll bar look is not null.
		Validator
		.assertThat(hoverScrollBarLook)
		.thatIsNamed("hover scroll bar look")
		.isNotNull();
		
		//Sets the hover scroll bar look of the current border widget look.
		this.hoverScrollBarLook = hoverScrollBarLook;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the left border color of the current {@link BorderWidgetLook}.
	 * 
	 * @param leftBorderColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws ArgumentIsNullException if the given left border color is null.
	 */
	public final BWL setLeftBorderColor(final Color leftBorderColor) {
		
		//Asserts that the given left border color is not null.
		Validator
		.assertThat(leftBorderColor)
		.thatIsNamed("left border color")
		.isNotNull();
		
		//Sets the left border color of the current {@link BorderWidgetLook}.
		this.leftBorderColor = leftBorderColor;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the left border thickness of the current {@link BorderWidgetLook}.
	 * 
	 * @param leftBorderThickness
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NegativeArgumentException if the given leftBorderThicknesss is negative.
	 */
	public final BWL setLeftBorderThickness(final int leftBorderThickness) {
		
		Validator.assertThat(leftBorderThickness).thatIsNamed("left border thickness").isNotNegative();
		
		this.leftBorderThickness = leftBorderThickness;
		
		return asConcrete();
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
		
		Validator.assertThat(leftPadding).thatIsNamed("left padding").isNotNegative();
		
		this.leftPadding = leftPadding;
		
		return asConcrete();
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
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the right border color of the current {@link BorderWidgetLook}.
	 * 
	 * @param rightBorderColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws ArgumentIsNullException if the given right border color is null.
	 */
	public final BWL setRightBorderColor(final Color rightBorderColor) {
		
		//Asserts that the given right border color is not null.
		Validator
		.assertThat(rightBorderColor)
		.thatIsNamed("right border color")
		.isNotNull();
		
		//Sets the right border color of the current {@link BorderWidgetLook}.
		this.rightBorderColor = rightBorderColor;
		
		return asConcrete();
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
		
		Validator.assertThat(rightBorderThickness).thatIsNamed("right border thickness").isNotNegative();
		
		this.rightBorderThickness = rightBorderThickness;
		
		return asConcrete();
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
		
		Validator.assertThat(rightPadding).thatIsNamed("right padding").isNotNegative();
		
		this.rightPadding = rightPadding;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the selection scroll bar look of the current {@link BorderWidgetLook}.
	 * 
	 * @param selectionScrollBarLook
	 * @return the current {@link BorderWidgetLook}.
	 * @throws ArgumentIsNullException if the given selection scroll bar look is null.
	 */
	public final BWL setSelectionScrollBarLook(final BorderWidgetScrollBarLook selectionScrollBarLook) {
		
		//Asserts that the given selection scroll bar look is not null.
		Validator
		.assertThat(selectionScrollBarLook)
		.thatIsNamed("selection scroll bar look")
		.isNotNull();
		
		//Sets the selection scroll bar look of the current border widget look.
		this.selectionScrollBarLook = selectionScrollBarLook;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the top border color of the current {@link BorderWidgetLook}.
	 * 
	 * @param topBorderColor
	 * @return the current {@link BorderWidgetLook}.
	 * @throws ArgumentIsNullException if the given top border color is null.
	 */
	public final BWL setTopBorderColor(final Color topBorderColor) {
		
		//Asserts that the given top border color is not null.
		Validator
		.assertThat(topBorderColor)
		.thatIsNamed("top border color")
		.isNotNull();
		
		//Sets the top border color of the current {@link BorderWidgetLook}.
		this.topBorderColor = topBorderColor;
		
		return asConcrete();
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
		
		Validator.assertThat(topBorderThickness).thatIsNamed("top border thickness").isNotNegative();
		
		this.topBorderThickness = topBorderThickness;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the top padding of the current {@link BorderWidgetLook}.
	 * 
	 * @param topPadding
	 * @return the current {@link BorderWidgetLook}.
	 * @throws NegativeArgumentException if the given topPadding is negative.
	 */
	public final BWL setTopPadding(final int topPadding) {
		
		Validator.assertThat(topPadding).thatIsNamed("top padding").isNotNegative();
		
		this.topPadding = topPadding;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Fills up the specification of the border colors of the current {@link BorderWidgetLook} into the given list.
	 * 
	 * @param list
	 */
	private void fillUpBorderColorsSpecifications(final LinkedList<Node> list) {
		if (hasABorderColor() && hasSameBorderColorAtEachSide()) {
			list.addAtEnd(leftBorderColor.getSpecificationAs(BORDER_COLOR_HEADER));
		} else {
			
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
	private void fillUpBorderThicknessesSpecifications(final LinkedList<Node> list) {
		if (hasABorderThickness() && hasSameBorderThicknessAtEachSide()) {
			list.addAtEnd(Node.withHeaderAndAttribute(BORDER_THICKNESS_HEADER, leftBorderThickness));
		} else {
			
			if (hasLeftBorderThickness()) {
				list.addAtEnd(Node.withHeaderAndAttribute(LEFT_BORDER_THICKNESS_HEADER, leftBorderThickness));
			}
			
			if (hasRightBorderThickness()) {
				list.addAtEnd(Node.withHeaderAndAttribute(RIGHT_BORDER_THICKNESS_HEADER, rightBorderThickness));
			}
			
			if (hasTopBorderThickness()) {
				list.addAtEnd(Node.withHeaderAndAttribute(TOP_BORDER_THICKNESS_HEADER, topBorderThickness));
			}
			
			if (hasBottomBorderThickness()) {
				list.addAtEnd(Node.withHeaderAndAttribute(BOTTOM_BORDER_THICKNESS_HEADER, bottomBorderThickness));
			}
		}
	}
	
	//method
	/**
	 * Fills up the specification of the paddings of the current {@link BorderWidgetLook} into the given list.
	 * 
	 * @param list
	 */
	private void fillUpPaddingSpecifications(final LinkedList<Node> list) {
		if (hasAPadding() && hasSamePaddingAtEachSide()) {
			list.addAtEnd(Node.withHeaderAndAttribute(PADDING_HEADER, leftPadding));
		} else {
			
			if (hasLeftPadding()) {
				list.addAtEnd(Node.withHeaderAndAttribute(LEFT_PADDING_HEADER, leftPadding));
			}
			
			if (hasRightPadding()) {
				list.addAtEnd(Node.withHeaderAndAttribute(RIGHT_PADDING_HEADER, rightPadding));
			}
			
			if (hasTopPadding()) {
				list.addAtEnd(Node.withHeaderAndAttribute(TOP_PADDING_HEADER, topPadding));
			}
			
			if (hasBottomPadding()) {
				list.addAtEnd(Node.withHeaderAndAttribute(BOTTOM_PADDING_HEADER, bottomPadding));
			}
		}
	}
	
	//method
	/**
	 * Fills up the specifications of the scroll bar looks of the current {@link BorderWidgetLook} into the given list.
	 * 
	 * @param list
	 */
	private void fillUpScrollBarLooksSpecifications(final LinkedList<Node> list) {
		
		if (hasBaseScrollBarLook()) {
			list.addAtEnd(baseScrollBarLook.getSpecificationAs(BASE_SCROLL_BAR_LOOK_HEADER));
		}
		
		if (hasHoverScrollBarLook()) {
			list.addAtEnd(hoverScrollBarLook.getSpecificationAs(HOVER_SCROLL_BAR_LOOK_HEADER));
		}
		
		if (hasSelectionScrollBarLook()) {
			list.addAtEnd(selectionScrollBarLook.getSpecificationAs(SELECTION_SCROLL_BAR_LOOK_HEADER));
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
	 * @return true if the current {@link BorderWidgetLook} has a base scroll bar look.
	 */
	private boolean hasBaseScrollBarLook() {
		return (baseScrollBarLook != null);
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
		return (bottomBorderThickness != -1);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a bottom padding.
	 */
	private boolean hasBottomPadding() {
		return (bottomPadding != -1);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a hover scroll bar look.
	 */
	private boolean hasHoverScrollBarLook() {
		return (hoverScrollBarLook != null);
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
		return (leftBorderThickness != -1);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a left padding.
	 */
	private boolean hasLeftPadding() {
		return (leftPadding != -1);
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
		return (rightBorderThickness != -1);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a right padding.
	 */
	private boolean hasRightPadding() {
		return (rightPadding != -1);
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
			&& leftBorderThickness == rightBorderThickness
			&& leftBorderThickness == topBorderThickness
			&& leftBorderThickness == bottomBorderThickness
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
			&& hasRightPadding() && rightPadding == leftPadding
			&& hasTopPadding() && topPadding == leftPadding
			&& hasBottomPadding() && bottomPadding == leftPadding
		);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a selection scroll bar look.
	 */
	private boolean hasSelectionScrollBarLook() {
		return (selectionScrollBarLook != null);
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
		return (topBorderThickness != -1);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetLook} has a top padding.
	 */
	private boolean hasTopPadding() {
		return (topPadding != -1);
	}
}
