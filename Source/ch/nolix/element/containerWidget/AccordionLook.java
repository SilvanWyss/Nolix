//package declaration
package ch.nolix.element.containerWidget;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.GUI.ValueCatalogue;
import ch.nolix.element.color.Color;
import ch.nolix.element.layerElement.LayerProperty;
import ch.nolix.element.widget.BorderWidgetLook;

//class
public final class AccordionLook extends BorderWidgetLook<AccordionLook> {
	
	//constant
	public static final Color DEFAULT_TAB_HEADER_BACKGROUND_COLOR = Color.WHITE;
	public static final int DEFAULT_TAB_HEADER_TEXT_SIZE = ValueCatalogue.MEDIUM_TEXT_SIZE;
	public static final Color DEFAULT_TAB_HEADER_TEXT_COLOR = Color.BLACK;
	public static final Color DEFAULT_TAB_BACKGROUND_COLOR = Color.WHITE;
	
	//constants
	private static final String TAB_HEADER_BACKGROUND_COLOR_HEADER = "TabHeaderBackgroundColor";
	private static final String TAB_HEADER_TEXT_SIZE_HEADER = "TabHeaderTextSize";
	private static final String TAB_HEADER_TEXT_COLOR_HEADER = "TabHeaderTextColor";
	private static final String TAB_BACKGROUND_COLOR_HEADER = "TabBackgroundColor";
	
	//attribute
	private final LayerProperty<Color> tabHeaderBackgroundColor =
	new LayerProperty<>(
		TAB_HEADER_BACKGROUND_COLOR_HEADER,
		DEFAULT_TAB_HEADER_BACKGROUND_COLOR,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//attribute
	private final LayerProperty<Integer> tabHeaderTextSize =
	new LayerProperty<>(
		TAB_HEADER_TEXT_SIZE_HEADER,
		DEFAULT_TAB_HEADER_TEXT_SIZE,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attribute
	private final LayerProperty<Color> tabHeaderTextColor =
	new LayerProperty<>(
		TAB_HEADER_TEXT_COLOR_HEADER,
		DEFAULT_TAB_HEADER_TEXT_COLOR,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//attribute
	private final LayerProperty<Color> tabBackgroundColor =
	new LayerProperty<>(
		TAB_BACKGROUND_COLOR_HEADER,
		DEFAULT_TAB_BACKGROUND_COLOR,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//method
	public Color getRecursiveOrDefaultTabBackgroundColor() {
		return tabBackgroundColor.getRecursiveOrDefaultValue();
	}
	
	//method
	public Color getRecursiveOrDefaultTabHeaderBackgroundColor() {
		return tabHeaderBackgroundColor.getRecursiveOrDefaultValue();
	}
	
	//method
	public Color getRecursiveOrDefaultTabHeaderTextColor() {
		return tabHeaderTextColor.getRecursiveOrDefaultValue();
	}
	
	//method
	public int getRecursiveOrDefaultTabHeaderTextSize() {
		return tabHeaderTextSize.getRecursiveOrDefaultValue();
	}
	
	//method
	public boolean hasRecursiveTabBackgroundColor() {
		return tabBackgroundColor.hasRecursiveValue();
	}
	
	//method
	public boolean hasRecursiveTabHeaderBackgroundColor() {
		return tabHeaderBackgroundColor.hasRecursiveValue();
	}
	
	//method
	public boolean hasRecursiveTabHeaderTextColor() {
		return tabHeaderTextColor.hasRecursiveValue();
	}
	
	//method
	public boolean hasRecursiveTabHeaderTextSize() {
		return tabHeaderTextSize.hasRecursiveValue();
	}
	
	//method
	public AccordionLook removeTabBackgroundColor() {
		
		tabBackgroundColor.removeValue();
		
		return this;
	}
	
	//method
	public AccordionLook removeTabHeaderBackgroundColor() {
		
		tabHeaderBackgroundColor.removeValue();
		
		return this;
	}
	
	//method
	public AccordionLook removeTabHeaderTextColor() {
		
		tabHeaderTextColor.removeValue();
		
		return this;
	}
	
	//method
	public AccordionLook removeTabHeaderTextSize() {
		
		tabHeaderTextSize.removeValue();
		
		return this;
	}
	
	//method
	public AccordionLook setTabBackgroundColor(final Color tabBackgroundColor) {
		
		this.tabBackgroundColor.setValue(tabBackgroundColor);
		
		return this;
	}
	
	//method
	public AccordionLook setTabHeaderBackgroundColor(final Color tabHeaderBackgroundColor) {
		
		this.tabHeaderBackgroundColor.setValue(tabHeaderBackgroundColor);
		
		return this;
	}
	
	//method
	public AccordionLook setTabHeaderTextColor(final Color tabHeaderTextColor) {
		
		this.tabHeaderTextColor.setValue(tabHeaderTextColor);
		
		return this;
	}
	
	//method
	public AccordionLook setTabHeaderTextSize(final int tabHeaderTextSize) {
		
		this.tabHeaderTextSize.setValue(tabHeaderTextSize);
		
		return this;
	}
}
