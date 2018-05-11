//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.entity2.Property;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.NonNegativeInteger;

//class
public final class TabContainerLook
extends BorderWidgetLook<TabContainerLook> {
	
	//default values
	public static final int DEFAULT_MENU_MARGIN = 0;
	public static final int DEFAULT_MENU_ITEM_MARGIN = 0;
	
	//default value
	private static final TabContainerMenuItemLook DEFAULT_BASE_MENU_ITEM_LOOK =
	new TabContainerMenuItemLook()
	.setPadding(5);
	
	//default value
	private static final TabContainerMenuItemLook DEFAULT_HOVER_MENU_ITEM_LOOK =
	new TabContainerMenuItemLook()
	.setPadding(5)
	.setBackgroundColor(Color.LIGHT_GREY);
	
	//default value
	private static final TabContainerMenuItemLook DEFAULT_SELECTION__MENU_ITEM_LOOK =
	new TabContainerMenuItemLook()
	.setPadding(5)
	.setBackgroundColor(Color.DARK_GREY);
	
	//constant
	private static final String MENU_MARGIN_HEADER = "MenuMargin";
	private static final String MENU_ITEM_MARGIN_HEADER = "MenuItemMargin";
	
	//constants
	private static final String BASE_MENU_ITEM_LOOK_HEADER = "BaseMenuItemLook";
	private static final String HOVER_MENU_ITEM_LOOK_HEADER = "HoverMenuItemLook";
	private static final String SELECTION_MENU_ITEM_LOOK_HEADER = "SelectionMenuItemLook";
	
	//attribute
	private final Property<NonNegativeInteger> menuItemMarginProperty =
	new Property<NonNegativeInteger>(
		MENU_ITEM_MARGIN_HEADER,
		new NonNegativeInteger(DEFAULT_MENU_ITEM_MARGIN),
		s -> NonNegativeInteger.createFromSpecification(s)
	);
	
	//attribute
	private final Property<NonNegativeInteger> menuMarginProperty =
	new Property<NonNegativeInteger>(
		MENU_MARGIN_HEADER,
		new NonNegativeInteger(DEFAULT_MENU_MARGIN),
		s -> NonNegativeInteger.createFromSpecification(s)
	);
	
	//attribute
	private final Property<TabContainerMenuItemLook> baseMenuItemLookProperty =
	new Property<TabContainerMenuItemLook>(
		BASE_MENU_ITEM_LOOK_HEADER,
		DEFAULT_BASE_MENU_ITEM_LOOK,
		s -> TabContainerMenuItemLook.createFromSpecification(s)
	);
	
	//attribute
	private final Property<TabContainerMenuItemLook> hoverMenuItemLookProperty =
	new Property<TabContainerMenuItemLook>(
		HOVER_MENU_ITEM_LOOK_HEADER,
		DEFAULT_HOVER_MENU_ITEM_LOOK,
		s -> TabContainerMenuItemLook.createFromSpecification(s)
	);
	
	//attribute
	private final Property<TabContainerMenuItemLook> selectionMenuItemLookProperty =
	new Property<TabContainerMenuItemLook>(
		SELECTION_MENU_ITEM_LOOK_HEADER,
		DEFAULT_SELECTION__MENU_ITEM_LOOK,
		s -> TabContainerMenuItemLook.createFromSpecification(s)
	);
	
	//method
	public TabContainerMenuItemLook getRefRecursiveOrDefaultBaseMenuItemLook() {
		return baseMenuItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public TabContainerMenuItemLook getRefRecursiveOrDefaultHoverMenuItemLook() {
		return hoverMenuItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public int getRecursiveOrDefaultMenuItemMargin() {
		return menuItemMarginProperty.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	public int getRecursiveOrDefaultMenuMargin() {
		return menuMarginProperty.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	public TabContainerMenuItemLook getRefRecursiveOrDefaultSelectionMenuItemLook() {
		return selectionMenuItemLookProperty.getRecursiveOrDefaultValue();
	}
	
	//method
	public TabContainerLook removeBaseMenuItemLook() {
		
		baseMenuItemLookProperty.removeValue();
		
		return this;
	}
	
	//method
	public TabContainerLook removeHoverMenuItemLook() {
		
		hoverMenuItemLookProperty.removeValue();
		
		return this;
	}
	
	//method
	public TabContainerLook removeMenuItemMargin() {
		
		menuItemMarginProperty.removeValue();
		
		return this;		
	}
	
	//method
	public TabContainerLook removeMenuMargin() {
		
		menuMarginProperty.removeValue();
		
		return this;		
	}
	
	//method
	public TabContainerLook removeSelectionMenuItemLook() {
		
		selectionMenuItemLookProperty.removeValue();
		
		return this;
	}
	
	//method
	public TabContainerLook setBaseMenuItemLook(final TabContainerMenuItemLook baseMenuItemLook) {
		
		baseMenuItemLookProperty.setValue(baseMenuItemLook);
		
		return this;
	}
	
	//method
	public TabContainerLook setHoverMenuItemLook(final TabContainerMenuItemLook hoverMenuItemLook) {
		
		hoverMenuItemLookProperty.setValue(hoverMenuItemLook);
		
		return this;
	}
	
	//method
	public TabContainerLook setMenuItemMargin(final int menuItemMargin) {
		
		menuItemMarginProperty.setValue(new NonNegativeInteger(menuItemMargin));
		
		return this;
	}
	
	//method
	public TabContainerLook setMenuMargin(final int menuMargin) {
		
		menuMarginProperty.setValue(new NonNegativeInteger(menuMargin));
		
		return this;
	}
	
	//method
	public TabContainerLook setSelectionMenuItemLook(final TabContainerMenuItemLook selectionMenuItemLook) {
		
		selectionMenuItemLookProperty.setValue(selectionMenuItemLook);
		
		return this;
	}
}
