//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.entity2.Property;
import ch.nolix.element.core.NonNegativeInteger;

//class
public final class TabContainerLook
extends BorderWidgetLook<TabContainerLook> {
	
	//default value
	public static final int DEFAULT_MENU_MARGIN = 0;
	public static final int DEFAULT_MENU_ITEM_MARGIN = ValueCatalogue.MEDIUM_WIDGET_MARGIN;
	
	//constant
	private static final String MENU_MARGIN_HEADER = "MenuMargin";
	private static final String MENU_ITEM_MARGIN_HEADER = "MenuItemMargin";
	
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
	
	//method
	public int getRecursiveOrDefaultMenuItemMargin() {
		return menuItemMarginProperty.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	public int getRecursiveOrDefaultMenuMargin() {
		return menuMarginProperty.getRecursiveOrDefaultValue().getValue();
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
	public TabContainerLook setMenuItemMargin(final int menuItemMargin) {
		
		menuItemMarginProperty.setValue(new NonNegativeInteger(menuItemMargin));
		
		return this;
	}
	
	//method
	public TabContainerLook setMenuMargin(final int menuMargin) {
		
		menuMarginProperty.setValue(new NonNegativeInteger(menuMargin));
		
		return this;
	}
}
