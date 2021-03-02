//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.ValueCatalogue;
import ch.nolix.element.layerelement.LayerElement;
import ch.nolix.element.widget.TextItemMenuItemLook;

//class
public final class TabContainerMenuItemLook extends LayerElement<TextItemMenuItemLook> {
	
	//constant
	public static final String TYPE_NAME = "MenuItemLook";
	
	//constants
	public static final int DEFAULT_MIN_WIDTH = ValueCatalogue.MEDIUM_WIDGET_WIDTH;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.LIGHT_GREY;
	public static final int DEFAULT_PADDING = 0;
	public static final int DEFAULT_TEXT_SIZE = ValueCatalogue.MEDIUM_TEXT_SIZE;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constant
	private static final String MIN_WIDTH_HEADER = "MinWidth";
	
	//static method
	public static TabContainerMenuItemLook fromSpecification(
		final BaseNode specification
	) {
		
		final var tabContainerMenuItemLook = new TabContainerMenuItemLook();
		tabContainerMenuItemLook.resetFrom(specification);
		
		return tabContainerMenuItemLook;
	}
	
	//optional attributes
	private int minWidth = -1;
	private Color backgroundColor;
	private int padding = -1;
	private int textSize = -1;
	private Color textColor;
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		switch (attribute.getHeader()) {
			case MIN_WIDTH_HEADER:
				setMinWidth(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseCatalogue.PADDING:
				setPadding(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseCatalogue.BACKGROUND_COLOR:
				setBackgroundColor(Color.fromSpecification(attribute));
				break;
			case PascalCaseCatalogue.TEXT_SIZE:
				setTextSize(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseCatalogue.TEXT_COLOR:
				setTextColor(new Color(attribute.getOneAttributeHeader()));
				break;
			default:
				
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		super.fillUpAttributesInto(list);
		
		if (hasMinWidth()) {
			list.addAtEnd(Node.withHeaderAndAttribute(MIN_WIDTH_HEADER, minWidth));
		}
		
		if (hasBackgroundColor()) {
			list.addAtEnd(backgroundColor.getSpecificationAs(PascalCaseCatalogue.BACKGROUND_COLOR));
		}
		
		if (hasPadding()) {
			list.addAtEnd(Node.withHeaderAndAttribute(PascalCaseCatalogue.PADDING, padding));
		}
		
		if (hasTextSize()) {
			list.addAtEnd(Node.withHeaderAndAttribute(PascalCaseCatalogue.TEXT_SIZE, textSize));
		}
		
		if (hasTextColor()) {
			list.addAtEnd(textColor.getSpecificationAs(PascalCaseCatalogue.TEXT_COLOR));
		}
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
			return minWidth;
		}
		
		return DEFAULT_MIN_WIDTH;
	}
	
	//method
	public int getOwnOrDefaultPadding() {
		
		if (hasPadding()) {
			return padding;
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
			return textSize;
		}
		
		return DEFAULT_TEXT_SIZE;
	}
	
	//method
	public boolean hasBackgroundColor() {
		return (backgroundColor != null);
	}
	
	//method
	public boolean hasMinWidth() {
		return (minWidth != -1);
	}
	
	//method
	public boolean hasPadding() {
		return (padding != -1);
	}
	
	//method
	private boolean hasTextColor() {
		return (textColor != null);
	}
	
	//method
	public boolean hasTextSize() {
		return (textSize != -1);
	}
	
	//method
	public TabContainerMenuItemLook removeBackgroundColor() {
		
		backgroundColor = null;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook removeMinWidth() {
		
		minWidth = -1;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook removePadding() {
		
		padding = -1;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook removeTextColor() {
		
		textColor = null;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook removeTextSize() {
		
		textSize = -1;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setBackgroundColor(final Color backgroundColor) {
		
		Validator
		.assertThat(backgroundColor)
		.thatIsNamed(PascalCaseCatalogue.BACKGROUND_COLOR)
		.isNotNull();
		
		this.backgroundColor = backgroundColor;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setMinWidth(final int minWidth) {
		
		Validator.assertThat(minWidth).thatIsNamed("min width").isPositive();
		
		this.minWidth = minWidth;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setPadding(final int padding) {
		
		Validator.assertThat(padding).thatIsNamed(LowerCaseCatalogue.PADDING).isPositive();
		
		this.padding = padding;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setTextColor(final Color textColor) {
		
		Validator
		.assertThat(textColor)
		.thatIsNamed(PascalCaseCatalogue.TEXT_COLOR)
		.isNotNull();
		
		this.textColor = textColor;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setTextSize(final int textSize) {
		
		Validator.assertThat(textSize).thatIsNamed(LowerCaseCatalogue.TEXT_SIZE).isPositive();
		
		this.textSize = textSize;
		
		return this;
	}
	
	//method
	@Override
	protected void resetLayerElement() {}
}
