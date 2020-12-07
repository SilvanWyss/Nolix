//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
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
		tabContainerMenuItemLook.reset(specification);
		
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
			case PascalCaseNameCatalogue.PADDING:
				setPadding(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseNameCatalogue.BACKGROUND_COLOR:
				setBackgroundColor(Color.fromSpecification(attribute));
				break;
			case PascalCaseNameCatalogue.TEXT_SIZE:
				setTextSize(attribute.getOneAttributeAsInt());
				break;
			case PascalCaseNameCatalogue.TEXT_COLOR:
				setTextColor(new Color(attribute.getOneAttributeHeader()));
				break;
			default:
				
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	@Override
	public LinkedList<Node> getAttributes() {
		
		final var attributes = new LinkedList<Node>();
		
		if (hasMinWidth()) {
			attributes.addAtEnd(
				new Node(MIN_WIDTH_HEADER, minWidth)
			);
		}
		
		if (hasBackgroundColor()) {
			attributes.addAtEnd(
				backgroundColor.getSpecificationAs(PascalCaseNameCatalogue.BACKGROUND_COLOR)
			);
		}
		
		if (hasPadding()) {
			attributes.addAtEnd(
				new Node(PascalCaseNameCatalogue.PADDING, padding)
			);
		}
		
		if (hasTextSize()) {
			attributes.addAtEnd(
				new Node(PascalCaseNameCatalogue.TEXT_SIZE, textSize)
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
		.thatIsNamed(PascalCaseNameCatalogue.BACKGROUND_COLOR)
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
		
		Validator.assertThat(padding).thatIsNamed(VariableNameCatalogue.PADDING).isPositive();
		
		this.padding = padding;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setTextColor(final Color textColor) {
		
		Validator
		.assertThat(textColor)
		.thatIsNamed(PascalCaseNameCatalogue.TEXT_COLOR)
		.isNotNull();
		
		this.textColor = textColor;
		
		return this;
	}
	
	//method
	public TabContainerMenuItemLook setTextSize(final int textSize) {
		
		Validator.assertThat(textSize).thatIsNamed(VariableNameCatalogue.TEXT_SIZE).isPositive();
		
		this.textSize = textSize;
		
		return this;
	}
}
