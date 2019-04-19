//package declaration
package ch.nolix.element.GUI;

import ch.nolix.core.layerEntity.LayerProperty;
import ch.nolix.element.core.NonNegativeInteger;

//class
public final class SelectionMenuLook
extends BorderWidgetLook<SelectionMenuLook> {

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
	private final LayerProperty<SelectionMenuItemLook> baseItemLookProperty =
	new LayerProperty<SelectionMenuItemLook>(
		NORMAL_ITEM_LOOK_HEADER,
		new SelectionMenuItemLook(),
		s -> SelectionMenuItemLook.createFromSpecification(s),
		bilp -> bilp.getSpecification()
	);
	
	//attribute
	private final LayerProperty<SelectionMenuItemLook> hoverItemLookProperty =
	new LayerProperty<SelectionMenuItemLook>(
		HOVER_ITEM_LOOK_HEADER,
		new SelectionMenuItemLook(),
		s -> SelectionMenuItemLook.createFromSpecification(s),
		hilp -> hilp.getSpecification()
	);
	
	//attribute
	private final LayerProperty<SelectionMenuItemLook> selectionItemLookProperty =
	new LayerProperty<SelectionMenuItemLook>(
		SELECTION_ITEM_LOOK_HEADER,
		new SelectionMenuItemLook(),
		s -> SelectionMenuItemLook.createFromSpecification(s),
		silp -> silp.getSpecification()
	);
	
	//method
	public int getRecursiveOrDefaultItemPadding() {
		return itemPaddingProperty.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	public SelectionMenuItemLook getRefRecursiveOrDefaultBaseItemLook() {
		return baseItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public SelectionMenuItemLook getRefRecursiveOrDefaultHoverItemLook() {
		return hoverItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public SelectionMenuItemLook getRefRecursiveOrDefaultSelectionItemLook() {
		return selectionItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public SelectionMenuLook setBaseItemLook(final SelectionMenuItemLook selectionMenuItemLook) {
		
		baseItemLookProperty.setValue(selectionMenuItemLook);
		
		return this;
	}
	
	//method
	public SelectionMenuLook setHoverItemLook(final SelectionMenuItemLook hoverMenuItemLook) {
		
		hoverItemLookProperty.setValue(hoverMenuItemLook);
		
		return this;
	}
	
	//method
	public SelectionMenuLook setItemPadding(final int itemPadding) {
		
		itemPaddingProperty.setValue(new NonNegativeInteger(itemPadding));
		
		return this;
	}
	
	//method
	public SelectionMenuLook setSelectionItemLook(final SelectionMenuItemLook selectionMenuItemLook) {
		
		selectionItemLookProperty.setValue(selectionMenuItemLook);
		
		return this;
	}
}
