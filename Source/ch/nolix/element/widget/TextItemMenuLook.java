//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.ValueCatalogue;
import ch.nolix.element.layerElement.LayerProperty;

//class
public final class TextItemMenuLook extends BorderWidgetLook<TextItemMenuLook> {
	
	//constant
	public static final int DEFAULT_ITEM_PADDING = ValueCatalogue.SMALL_WIDGET_PADDING;
	
	//constants
	private static final String ITEM_PADDING_HEADER = "ItemPadding";
	private static final String NORMAL_ITEM_LOOK_HEADER = "NormalItemLook";
	private static final String HOVER_ITEM_LOOK_HEADER = "HoverItemLook";
	private static final String SELECTION_ITEM_LOOK_HEADER = "SelectionItemLook";
	
	//attribute
	private final LayerProperty<Integer> itemPadding =
	new LayerProperty<>(
		ITEM_PADDING_HEADER,
		DEFAULT_ITEM_PADDING,
		BaseNode::getOneAttributeAsInt,
		ip -> Node.withOneAttribute(ip)
	);
	
	//attribute
	private final LayerProperty<TextItemMenuItemLook> baseItemLook =
	new LayerProperty<>(
		NORMAL_ITEM_LOOK_HEADER,
		new TextItemMenuItemLook(),
		s -> TextItemMenuItemLook.fromSpecification(s),
		bil -> bil.getSpecification()
	);
	
	//attribute
	private final LayerProperty<TextItemMenuItemLook> hoverItemLookProperty =
	new LayerProperty<>(
		HOVER_ITEM_LOOK_HEADER,
		new TextItemMenuItemLook(),
		s -> TextItemMenuItemLook.fromSpecification(s),
		hilp -> hilp.getSpecification()
	);
	
	//attribute
	private final LayerProperty<TextItemMenuItemLook> selectionItemLookProperty =
	new LayerProperty<>(
		SELECTION_ITEM_LOOK_HEADER,
		new TextItemMenuItemLook(),
		s -> TextItemMenuItemLook.fromSpecification(s),
		silp -> silp.getSpecification()
	);
	
	//method
	public int getRecursiveOrDefaultItemPadding() {
		return itemPadding.getRecursiveOrDefaultValue();
	}
	
	//method
	public TextItemMenuItemLook getRefRecursiveOrDefaultBaseItemLook() {
		return baseItemLook.getRecursiveOrDefaultValue();
	}
	
	//method
	public TextItemMenuItemLook getRefRecursiveOrDefaultHoverItemLook() {
		return hoverItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public TextItemMenuItemLook getRefRecursiveOrDefaultSelectionItemLook() {
		return selectionItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public TextItemMenuLook setBaseItemLook(final TextItemMenuItemLook itemMenuItemLook) {
		
		baseItemLook.setValue(itemMenuItemLook);
		
		return this;
	}
	
	//method
	public TextItemMenuLook setHoverItemLook(final TextItemMenuItemLook hoverMenuItemLook) {
		
		hoverItemLookProperty.setValue(hoverMenuItemLook);
		
		return this;
	}
	
	//method
	public TextItemMenuLook setItemPadding(final int itemPadding) {
		
		Validator.assertThat(itemPadding).thatIsNamed("item padding").isNotNegative();
		
		this.itemPadding.setValue(itemPadding);
		
		return this;
	}
	
	//method
	public TextItemMenuLook setSelectionItemLook(final TextItemMenuItemLook itemMenuItemLook) {
		
		selectionItemLookProperty.setValue(itemMenuItemLook);
		
		return this;
	}
}
