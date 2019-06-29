//package declaration
package ch.nolix.element.containerWidget;

import ch.nolix.core.layerEntity.LayerProperty;
import ch.nolix.element.core.NonNegativeInteger;
import ch.nolix.element.widget.BorderWidgetLook;

//class
public final class TabContainerLook
extends BorderWidgetLook<TabContainerLook> {
	
	//default values
	public static final int DEFAULT_MENU_MARGIN = 0;
	public static final int DEFAULT_MENU_ITEM_MARGIN = 0;
	
	//constants
	private static final String MENU_MARGIN_HEADER = "MenuMargin";
	private static final String MENU_ITEM_MARGIN_HEADER = "MenuItemMargin";
	
	//constants
	private static final String BASE_MENU_ITEM_LOOK_HEADER = "BaseMenuItemLook";
	private static final String HOVER_MENU_ITEM_LOOK_HEADER = "HoverMenuItemLook";
	private static final String SELECTION_MENU_ITEM_LOOK_HEADER = "SelectionMenuItemLook";
	
	//attribute
	private final LayerProperty<NonNegativeInteger> menuItemMargin =
	new LayerProperty<>(
		MENU_ITEM_MARGIN_HEADER,
		new NonNegativeInteger(DEFAULT_MENU_ITEM_MARGIN),
		s -> NonNegativeInteger.createFromSpecification(s),
		mim -> mim.getSpecification()
	);
	
	//attribute
	private final LayerProperty<NonNegativeInteger> menuMargin =
	new LayerProperty<>(
		MENU_MARGIN_HEADER,
		new NonNegativeInteger(DEFAULT_MENU_MARGIN),
		s -> NonNegativeInteger.createFromSpecification(s),
		mm -> mm.getSpecification()
	);
	
	//attribute
	private final LayerProperty<TabContainerMenuItemLook> baseMenuItemLook =
	new LayerProperty<>(
		BASE_MENU_ITEM_LOOK_HEADER,
		new TabContainerMenuItemLook(),
		s -> TabContainerMenuItemLook.createFromSpecification(s),
		bmil -> bmil.getSpecification()
	);
	
	//attribute
	private final LayerProperty<TabContainerMenuItemLook> hoverMenuItemLook =
	new LayerProperty<>(
		HOVER_MENU_ITEM_LOOK_HEADER,
		new TabContainerMenuItemLook(),
		s -> TabContainerMenuItemLook.createFromSpecification(s),
		hmil -> hmil.getSpecification()
	);
	
	//attribute
	private final LayerProperty<TabContainerMenuItemLook> selectionMenuItemLook =
	new LayerProperty<>(
		SELECTION_MENU_ITEM_LOOK_HEADER,
		new TabContainerMenuItemLook(),
		s -> TabContainerMenuItemLook.createFromSpecification(s),
		smil -> smil.getSpecification()
	);
	
	//method
	public TabContainerMenuItemLook getRefRecursiveOrDefaultBaseMenuItemLook() {
		return baseMenuItemLook.getRecursiveOrDefaultValue();
	}
	
	//method
	public TabContainerMenuItemLook getRefRecursiveOrDefaultHoverMenuItemLook() {
		return hoverMenuItemLook.getRecursiveOrDefaultValue();
	}
	
	//method
	public int getRecursiveOrDefaultMenuItemMargin() {
		return menuItemMargin.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	public int getRecursiveOrDefaultMenuMargin() {
		return menuMargin.getRecursiveOrDefaultValue().getValue();
	}
	
	//method
	public TabContainerMenuItemLook getRefRecursiveOrDefaultSelectionMenuItemLook() {
		return selectionMenuItemLook.getRecursiveOrDefaultValue();
	}
	
	//method
	public TabContainerLook removeBaseMenuItemLook() {
		
		baseMenuItemLook.removeValue();
		
		return this;
	}
	
	//method
	public TabContainerLook removeHoverMenuItemLook() {
		
		hoverMenuItemLook.removeValue();
		
		return this;
	}
	
	//method
	public TabContainerLook removeMenuItemMargin() {
		
		menuItemMargin.removeValue();
		
		return this;
	}
	
	//method
	public TabContainerLook removeMenuMargin() {
		
		menuMargin.removeValue();
		
		return this;
	}
	
	//method
	public TabContainerLook removeSelectionMenuItemLook() {
		
		selectionMenuItemLook.removeValue();
		
		return this;
	}
	
	//method
	public TabContainerLook setBaseMenuItemLook(final TabContainerMenuItemLook baseMenuItemLook) {
		
		this.baseMenuItemLook.setValue(baseMenuItemLook);
		
		return this;
	}
	
	//method
	public TabContainerLook setHoverMenuItemLook(final TabContainerMenuItemLook hoverMenuItemLook) {
		
		this.hoverMenuItemLook.setValue(hoverMenuItemLook);
		
		return this;
	}
	
	//method
	public TabContainerLook setMenuItemMargin(final int menuItemMargin) {
		
		this.menuItemMargin.setValue(new NonNegativeInteger(menuItemMargin));
		
		return this;
	}
	
	//method
	public TabContainerLook setMenuMargin(final int menuMargin) {
		
		this.menuMargin.setValue(new NonNegativeInteger(menuMargin));
		
		return this;
	}
	
	//method
	public TabContainerLook setSelectionMenuItemLook(final TabContainerMenuItemLook selectionMenuItemLook) {
		
		this.selectionMenuItemLook.setValue(selectionMenuItemLook);
		
		return this;
	}
}
