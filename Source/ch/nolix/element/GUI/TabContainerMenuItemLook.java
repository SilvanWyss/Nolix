//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.entity2.Entity;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.NonNegativeInteger;
import ch.nolix.element.intData.TextSize;
import ch.nolix.primitive.validator2.Validator;

//class
public final class TabContainerMenuItemLook
extends Entity<SelectionMenuItemLook> {
	
	//default values
	public static final int DEFAULT_MIN_WIDTH = ValueCatalogue.MEDIUM_WIDGET_WIDTH;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.LIGHT_GREY;
	public static final int DEFAULT_TEXT_SIZE = ValueCatalogue.MEDIUM_TEXT_SIZE;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constant
	private static final String MIN_WIDTH_HEADER = "MinWidth";
	
	//static method
	public static TabContainerMenuItemLook createFromSpecification(
		final Specification specification
	) {
		
		final var tabContainerMenuItemLook = new TabContainerMenuItemLook();
		tabContainerMenuItemLook.reset(specification);
		
		return tabContainerMenuItemLook;
	}
	
	//optional attributes
	private NonNegativeInteger minWidth;
	private Color backgroundColor;
	private NonNegativeInteger padding;
	private NonNegativeInteger textSize;
	private Color textColor = new Color();
	
	//method
	public void addOrChangeAttribute(final Specification attribute) {
		switch (attribute.getHeader()) {
			case MIN_WIDTH_HEADER:
				setMinWidth(attribute.getOneAttributeAsInt());
			case PascalCaseNameCatalogue.PADDING:
				setPadding(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseNameCatalogue.BACKGROUND_COLOR:
				setBackgroundColor(Color.createFromSpecification(attribute));
				break;
			case TextSize.TYPE_NAME:
				setTextSize(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseNameCatalogue.TEXT_COLOR:
				setTextColor(new Color(attribute.getOneAttributeAsString()));
				break;
			default:
				
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this tab container menu item structure
	 */
	public List<StandardSpecification> getAttributes() {
		
		final var attributes = new List<StandardSpecification>();
		
		if (hasMinWidth()) {
			attributes.addAtEnd(minWidth.getSpecificationAs(MIN_WIDTH_HEADER));
		}
		
		if (hasBackgroundColor()) {
			attributes.addAtEnd(backgroundColor.getSpecificationAs(PascalCaseNameCatalogue.BACKGROUND_COLOR));
		}
		
		if (hasPadding()) {
			attributes.addAtEnd(padding.getSpecificationAs(PascalCaseNameCatalogue.PADDING));
		}
		
		if (hasTextSize()) {
			attributes.addAtEnd(textSize.getSpecificationAs(PascalCaseNameCatalogue.TEXT_SIZE));
		}
		
		if (hasTextColor()) {
			attributes.addAtEnd(textColor.getSpecificationAs(PascalCaseNameCatalogue.TEXT_COLOR));
		}
		
		return attributes;
	}
	
	//method
	public Color getRecursiveOrDefaultBackgroundColor() {
		
		if (hasBackgroundColor()) {
			return backgroundColor;
		}
		
		return DEFAULT_BACKGROUND_COLOR;
	}
	
	//method
	public int getRecursiveOrDefaultMinWidth() {
		
		if (hasMinWidth()) {
			return minWidth.getValue();
		}
		
		return DEFAULT_MIN_WIDTH;
	}
	
	//method
	public int getRecursiveOrDefaultPadding() {
		
		if (hasPadding()) {
			return padding.getValue();
		}
		
		return 0;
	}
	
	//method
	public Color getRecursiveOrDefaultTextColor() {
		
		if (hasTextColor()) {
			return textColor;
		}
		
		return DEFAULT_TEXT_COLOR;
	}
	
	//method
	public int getRecursiveOrDefaultTextSize() {
		
		if (hasTextSize()) {
			return textSize.getValue();
		}
		
		return DEFAULT_TEXT_SIZE;
	}
	
	//method
	public String getType() {
		return getClass().getSimpleName();
	}
	
	//method
	public boolean hasRecursiveBackgroundColor() {
		return hasBackgroundColor();
	}
	
	//method
	public boolean hasRecursiveMinWidth() {
		return hasMinWidth();
	}
	
	//method
	public TabContainerMenuItemLook removeBackgroundColor() {
		
		backgroundColor = null;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook removeMinWidth() {
		
		minWidth = null;	
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook removePadding() {
		
		padding = null;	
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook removeTextColor() {
		
		textColor = null;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook removeTextSize() {
		
		textSize = null;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setBackgroundColor(final Color backgroundColor) {
		
		Validator
		.suppose(backgroundColor)
		.thatIsNamed(PascalCaseNameCatalogue.BACKGROUND_COLOR)
		.isNotNull();
		
		this.backgroundColor = backgroundColor;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setMinWidth(final int minWidth) {
		
		this.minWidth = new NonNegativeInteger(minWidth);
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setPadding(final int padding) {
		
		this.padding = new NonNegativeInteger(padding);
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setTextColor(final Color textColor) {
		
		Validator
		.suppose(textColor)
		.thatIsNamed(PascalCaseNameCatalogue.TEXT_COLOR)
		.isNotNull();
		
		this.textColor = textColor;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setTextSize(final int textSize) {
		
		this.textSize = new NonNegativeInteger(textSize);
		
		return this;
	}
	
	//method
	private boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	//method
	private boolean hasMinWidth() {
		return (minWidth != null);
	}
	
	//method
	private boolean hasPadding() {
		return (padding != null);
	}
	
	//method
	private boolean hasTextColor() {
		return (textColor != null);
	}
	
	//method
	private boolean hasTextSize() {
		return (textSize != null);
	}
}
