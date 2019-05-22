//package declaration
package ch.nolix.element.widget;

import ch.nolix.core.layerEntity.LayerProperty;
import ch.nolix.element.core.NonNegativeInteger;

//class
public final class ItemMenuLook
extends BorderWidgetLook<ItemMenuLook> {

	//default value
	public static final int DEFAULT_ITEM_PADDING = ValueCatalogue.SMALL_WIDGET_PADDING;
	
	//constants
	private static final String ITEM_PADDING_HEADER = "ItemPadding";
	private static final String NORMAL_ITEM_LOOK_HEADER = "NormalItemLook";
	private static final String HOVER_ITEM_LOOK_HEADER = "HoverItemLook";
	private static final String SELECTION_ITEM_LOOK_HEADER = "SelectionItemLook";
	
	//attribute
	private final LayerProperty<NonNegativeInteger> itemPaddingProperty =
	new LayerProperty<NonNegativeInteger>(
		ITEM_PADDING_HEADER,
		new NonNegativeInteger(),
		s -> NonNegativeInteger.createFromSpecification(s),
		ipp -> ipp.getSpecification()
	);
	
	//attribute
	private final LayerProperty<ItemMenuItemLook> baseItemLookProperty =
	new LayerProperty<ItemMenuItemLook>(
		NORMAL_ITEM_LOOK_HEADER,
		new ItemMenuItemLook(),
		s -> ItemMenuItemLook.createFromSpecification(s),
		bilp -> bilp.getSpecification()
	);
	
	//attribute
	private final LayerProperty<ItemMenuItemLook> hoverItemLookProperty =
	new LayerProperty<ItemMenuItemLook>(
		HOVER_ITEM_LOOK_HEADER,
		new ItemMenuItemLook(),
		s -> ItemMenuItemLook.createFromSpecification(s),
		hilp -> hilp.getSpecification()
	);
	
	//attribute
	private final LayerProperty<ItemMenuItemLook> selectionItemLookProperty =
	new LayerProperty<ItemMenuItemLook>(
		SELECTION_ITEM_LOOK_HEADER,
		new ItemMenuItemLook(),
		s -> ItemMenuItemLook.createFromSpecification(s),
		silp -> silp.getSpecification()
	);
	
	//method
	public int getRecursiveOrDefaultItemPadding() {
		return itemPaddingProperty.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	public ItemMenuItemLook getRefRecursiveOrDefaultBaseItemLook() {
		return baseItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public ItemMenuItemLook getRefRecursiveOrDefaultHoverItemLook() {
		return hoverItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public ItemMenuItemLook getRefRecursiveOrDefaultSelectionItemLook() {
		return selectionItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public ItemMenuLook setBaseItemLook(final ItemMenuItemLook itemMenuItemLook) {
		
		baseItemLookProperty.setValue(itemMenuItemLook);
		
		return this;
	}
	
	//method
	public ItemMenuLook setHoverItemLook(final ItemMenuItemLook hoverMenuItemLook) {
		
		hoverItemLookProperty.setValue(hoverMenuItemLook);
		
		return this;
	}
	
	//method
	public ItemMenuLook setItemPadding(final int itemPadding) {
		
		itemPaddingProperty.setValue(new NonNegativeInteger(itemPadding));
		
		return this;
	}
	
	//method
	public ItemMenuLook setSelectionItemLook(final ItemMenuItemLook itemMenuItemLook) {
		
		selectionItemLookProperty.setValue(itemMenuItemLook);
		
		return this;
	}
}
