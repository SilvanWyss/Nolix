//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.entity2.Property;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.NonNegativeInteger;

//class
public final class SelectionMenuLook
extends BorderWidgetLook<SelectionMenuLook> {

	//default value
	public static final int DEFAULT_ITEM_PADDING = ValueCatalogue.SMALL_WIDGET_PADDING;
	
	//default value
	public static final SelectionMenuItemLook DEFAULT_BASE_ITEM_LOOK =
	new SelectionMenuItemLook();
	
	//default value
	public static final SelectionMenuItemLook DEFAULT_HOVER_ITEM_LOOK =
	new SelectionMenuItemLook()
	.setBackgroundColor(Color.LIGHT_GREY);
	
	//default value
	public static final SelectionMenuItemLook DEFAULT_SELECTION_ITEM_LOOK =
	new SelectionMenuItemLook()
	.setBackgroundColor(Color.GREY);
	
	//constants
	private static final String ITEM_PADDING_HEADER = "ItemPadding";
	private static final String NORMAL_ITEM_LOOK_HEADER = "NormalItemLook";
	private static final String HOVER_ITEM_LOOK_HEADER = "HoverItemLook";
	private static final String SELECTION_ITEM_LOOK_HEADER = "SelectionItemLook";
	
	//attribute
	private final Property<NonNegativeInteger> itemPaddingProperty =
	new Property<NonNegativeInteger>(
		ITEM_PADDING_HEADER,
		new NonNegativeInteger(),
		s -> NonNegativeInteger.createFromSpecification(s)
	);
	
	//attribute
	private final Property<SelectionMenuItemLook> baseItemLookProperty =
	new Property<SelectionMenuItemLook>(
		NORMAL_ITEM_LOOK_HEADER,
		DEFAULT_BASE_ITEM_LOOK,
		s -> SelectionMenuItemLook.createFromSpecification(s)
	);
	
	//attribute
	private final Property<SelectionMenuItemLook> hoverItemLookProperty =
	new Property<SelectionMenuItemLook>(
		HOVER_ITEM_LOOK_HEADER,
		DEFAULT_HOVER_ITEM_LOOK,
		s -> SelectionMenuItemLook.createFromSpecification(s)
	);
	
	//attribute
	private final Property<SelectionMenuItemLook> selectionItemLookProperty =
	new Property<SelectionMenuItemLook>(
		SELECTION_ITEM_LOOK_HEADER,
		DEFAULT_SELECTION_ITEM_LOOK,
		s -> SelectionMenuItemLook.createFromSpecification(s)
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
