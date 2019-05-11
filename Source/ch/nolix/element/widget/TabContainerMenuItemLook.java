//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.layerEntity.LayerEntity;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.NonNegativeInteger;

//class
public final class TabContainerMenuItemLook
extends LayerEntity<SelectionMenuItemLook> {
	
	//constant
	public static final String TYPE_NAME = "MenuItemLook";
	
	//default values
	public static final int DEFAULT_MIN_WIDTH = ValueCatalogue.MEDIUM_WIDGET_WIDTH;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.LIGHT_GREY;
	public static final int DEFAULT_PADDING = 0;
	public static final int DEFAULT_TEXT_SIZE = ValueCatalogue.MEDIUM_TEXT_SIZE;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constant
	private static final String MIN_WIDTH_HEADER = "MinWidth";
	
	//static method
	public static TabContainerMenuItemLook createFromSpecification(
		final DocumentNodeoid specification
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
	private Color textColor;
	
	//method
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		switch (attribute.getHeader()) {
			case MIN_WIDTH_HEADER:
				setMinWidth(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseNameCatalogue.PADDING:
				setPadding(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseNameCatalogue.BACKGROUND_COLOR:
				setBackgroundColor(Color.createFromSpecification(attribute));
				break;
			case PascalCaseNameCatalogue.TEXT_SIZE:
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
	@Override
	public List<DocumentNode> getAttributes() {
		
		final var attributes = new List<DocumentNode>();
		
		if (hasMinWidth()) {
			attributes.addAtEnd(
				minWidth.getSpecificationAs(MIN_WIDTH_HEADER)
			);
		}
		
		if (hasBackgroundColor()) {
			attributes.addAtEnd(
				backgroundColor.getSpecificationAs(PascalCaseNameCatalogue.BACKGROUND_COLOR)
			);
		}
		
		if (hasPadding()) {
			attributes.addAtEnd(
				padding.getSpecificationAs(PascalCaseNameCatalogue.PADDING)
			);
		}
		
		if (hasTextSize()) {
			attributes.addAtEnd(
				textSize.getSpecificationAs(PascalCaseNameCatalogue.TEXT_SIZE)
			);
		}
		
		if (hasTextColor()) {
			attributes.addAtEnd(
				textColor.getSpecificationAs(PascalCaseNameCatalogue.TEXT_COLOR)
			);
		}
		
		return attributes;
	}
	
	//method
	public Color getOwnOrDefaultBackgroundColor() {
		
		if (hasBackgroundColor()) {
			return backgroundColor;
		}
		
		return DEFAULT_BACKGROUND_COLOR;
	}
	
	//method
	public int getOwnOrDefaultMinWidth() {
		
		if (hasMinWidth()) {
			return minWidth.getValue();
		}
		
		return DEFAULT_MIN_WIDTH;
	}
	
	//method
	public int getOwnOrDefaultPadding() {
		
		if (hasPadding()) {
			return padding.getValue();
		}
		
		return DEFAULT_PADDING;
	}
	
	//method
	public Color getOwnOrDefaultTextColor() {
		
		if (hasTextColor()) {
			return textColor;
		}
		
		return DEFAULT_TEXT_COLOR;
	}
	
	//method
	public int getOwnOrDefaultTextSize() {
		
		if (hasTextSize()) {
			return textSize.getValue();
		}
		
		return DEFAULT_TEXT_SIZE;
	}
	
	//method
	@Override
	public String getType() {
		return TYPE_NAME;
	}
	
	//method
	public boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	//method
	public boolean hasMinWidth() {
		return (minWidth != null);
	}
	
	//method
	public boolean hasPadding() {
		return (padding != null);
	}
	
	//method
	private boolean hasTextColor() {
		return (textColor != null);
	}
	
	//method
	public boolean hasTextSize() {
		return (textSize != null);
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
}
