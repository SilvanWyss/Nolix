//package declaration
package ch.nolix.element.gui.widget;

import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.layerelement.LayerProperty;

//class
public final class OldTextItemMenuLook extends OldBorderWidgetLook<OldTextItemMenuLook> {
	
	//constant
	public static final int DEFAULT_ITEM_PADDING = 0;
	
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
		Node::withAttribute
	);
	
	//attribute
	private final LayerProperty<OldTextItemMenuItemLook> baseItemLook =
	new LayerProperty<>(
		NORMAL_ITEM_LOOK_HEADER,
		new OldTextItemMenuItemLook(),
		OldTextItemMenuItemLook::fromSpecification,
		OldTextItemMenuItemLook::getSpecification
	);
	
	//attribute
	private final LayerProperty<OldTextItemMenuItemLook> hoverItemLookProperty =
	new LayerProperty<>(
		HOVER_ITEM_LOOK_HEADER,
		new OldTextItemMenuItemLook(),
		OldTextItemMenuItemLook::fromSpecification,
		OldTextItemMenuItemLook::getSpecification
	);
	
	//attribute
	private final LayerProperty<OldTextItemMenuItemLook> selectionItemLookProperty =
	new LayerProperty<>(
		SELECTION_ITEM_LOOK_HEADER,
		new OldTextItemMenuItemLook(),
		OldTextItemMenuItemLook::fromSpecification,
		OldTextItemMenuItemLook::getSpecification
	);
	
	//method
	public int getRecursiveOrDefaultItemPadding() {
		return itemPadding.getRecursiveOrDefaultValue();
	}
	
	//method
	public OldTextItemMenuItemLook getRefRecursiveOrDefaultBaseItemLook() {
		return baseItemLook.getRecursiveOrDefaultValue();
	}
	
	//method
	public OldTextItemMenuItemLook getRefRecursiveOrDefaultHoverItemLook() {
		return hoverItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public OldTextItemMenuItemLook getRefRecursiveOrDefaultSelectionItemLook() {
		return selectionItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public OldTextItemMenuLook setBaseItemLook(final OldTextItemMenuItemLook itemMenuItemLook) {
		
		baseItemLook.setValue(itemMenuItemLook);
		
		return this;
	}
	
	//method
	public OldTextItemMenuLook setHoverItemLook(final OldTextItemMenuItemLook hoverMenuItemLook) {
		
		hoverItemLookProperty.setValue(hoverMenuItemLook);
		
		return this;
	}
	
	//method
	public OldTextItemMenuLook setItemPadding(final int itemPadding) {
		
		Validator.assertThat(itemPadding).thatIsNamed("item padding").isNotNegative();
		
		this.itemPadding.setValue(itemPadding);
		
		return this;
	}
	
	//method
	public OldTextItemMenuLook setSelectionItemLook(final OldTextItemMenuItemLook itemMenuItemLook) {
		
		selectionItemLookProperty.setValue(itemMenuItemLook);
		
		return this;
	}
}
