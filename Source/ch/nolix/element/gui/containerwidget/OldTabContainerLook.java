//package declaration
package ch.nolix.element.gui.containerwidget;

import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.widget.OldBorderWidgetLook;
import ch.nolix.element.layerelement.LayerProperty;

//class
public final class OldTabContainerLook extends OldBorderWidgetLook<OldTabContainerLook> {
	
	//constants
	public static final int DEFAULT_MENU_MARGIN = 0;
	public static final int DEFAULT_MENU_ITEM_MARGIN = 0;
	
	//constants
	private static final String MENU_MARGIN_HEADER = "MenuMargin";
	private static final String MENU_ITEM_MARGIN_HEADER = "MenuItemMargin";
	private static final String BASE_MENU_ITEM_LOOK_HEADER = "BaseMenuItemLook";
	private static final String HOVER_MENU_ITEM_LOOK_HEADER = "HoverMenuItemLook";
	private static final String SELECTION_MENU_ITEM_LOOK_HEADER = "SelectionMenuItemLook";
	
	//attribute
	private final LayerProperty<Integer> menuItemMargin =
	new LayerProperty<>(
		MENU_ITEM_MARGIN_HEADER,
		DEFAULT_MENU_ITEM_MARGIN,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
	
	//attribute
	private final LayerProperty<Integer> menuMargin =
	new LayerProperty<>(
		MENU_MARGIN_HEADER,
		DEFAULT_MENU_MARGIN,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
	
	//attribute
	private final LayerProperty<OldTabContainerMenuItemLook> baseMenuItemLook =
	new LayerProperty<>(
		BASE_MENU_ITEM_LOOK_HEADER,
		new OldTabContainerMenuItemLook(),
		OldTabContainerMenuItemLook::fromSpecification,
		OldTabContainerMenuItemLook::getSpecification
	);
	
	//attribute
	private final LayerProperty<OldTabContainerMenuItemLook> hoverMenuItemLook =
	new LayerProperty<>(
		HOVER_MENU_ITEM_LOOK_HEADER,
		new OldTabContainerMenuItemLook(),
		OldTabContainerMenuItemLook::fromSpecification,
		OldTabContainerMenuItemLook::getSpecification
	);
	
	//attribute
	private final LayerProperty<OldTabContainerMenuItemLook> selectionMenuItemLook =
	new LayerProperty<>(
		SELECTION_MENU_ITEM_LOOK_HEADER,
		new OldTabContainerMenuItemLook(),
		OldTabContainerMenuItemLook::fromSpecification,
		OldTabContainerMenuItemLook::getSpecification
	);
	
	//method
	public OldTabContainerMenuItemLook getRefRecursiveOrDefaultBaseMenuItemLook() {
		return baseMenuItemLook.getRecursiveOrDefaultValue();
	}
	
	//method
	public OldTabContainerMenuItemLook getRefRecursiveOrDefaultHoverMenuItemLook() {
		return hoverMenuItemLook.getRecursiveOrDefaultValue();
	}
	
	//method
	public int getRecursiveOrDefaultMenuItemMargin() {
		return menuItemMargin.getRecursiveOrDefaultValue();
	}
	
	//method
	public int getRecursiveOrDefaultMenuMargin() {
		return menuMargin.getRecursiveOrDefaultValue();
	}
	
	//method
	public OldTabContainerMenuItemLook getRefRecursiveOrDefaultSelectionMenuItemLook() {
		return selectionMenuItemLook.getRecursiveOrDefaultValue();
	}
	
	//method
	public OldTabContainerLook removeBaseMenuItemLook() {
		
		baseMenuItemLook.removeValue();
		
		return this;
	}
	
	//method
	public OldTabContainerLook removeHoverMenuItemLook() {
		
		hoverMenuItemLook.removeValue();
		
		return this;
	}
	
	//method
	public OldTabContainerLook removeMenuItemMargin() {
		
		menuItemMargin.removeValue();
		
		return this;
	}
	
	//method
	public OldTabContainerLook removeMenuMargin() {
		
		menuMargin.removeValue();
		
		return this;
	}
	
	//method
	public OldTabContainerLook removeSelectionMenuItemLook() {
		
		selectionMenuItemLook.removeValue();
		
		return this;
	}
	
	//method
	public OldTabContainerLook setBaseMenuItemLook(final OldTabContainerMenuItemLook baseMenuItemLook) {
		
		this.baseMenuItemLook.setValue(baseMenuItemLook);
		
		return this;
	}
	
	//method
	public OldTabContainerLook setHoverMenuItemLook(final OldTabContainerMenuItemLook hoverMenuItemLook) {
		
		this.hoverMenuItemLook.setValue(hoverMenuItemLook);
		
		return this;
	}
	
	//method
	public OldTabContainerLook setMenuItemMargin(final int menuItemMargin) {
		
		Validator.assertThat(menuItemMargin).thatIsNamed("menu item margin").isNotNegative();
		
		this.menuItemMargin.setValue(menuItemMargin);
		
		return this;
	}
	
	//method
	public OldTabContainerLook setMenuMargin(final int menuMargin) {
		
		Validator.assertThat(menuMargin).thatIsNamed("menu margin").isNotNegative();
		
		this.menuMargin.setValue(menuMargin);
		
		return this;
	}
	
	//method
	public OldTabContainerLook setSelectionMenuItemLook(final OldTabContainerMenuItemLook selectionMenuItemLook) {
		
		this.selectionMenuItemLook.setValue(selectionMenuItemLook);
		
		return this;
	}
}
