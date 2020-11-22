//package declaration
package ch.nolix.element.containerWidget;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.layerElement.LayerProperty;
import ch.nolix.element.widget.BorderWidgetLook;

//class
public final class TabContainerLook extends BorderWidgetLook<TabContainerLook> {
	
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
		Node::withOneAttribute
	);
	
	//attribute
	private final LayerProperty<Integer> menuMargin =
	new LayerProperty<>(
		MENU_MARGIN_HEADER,
		DEFAULT_MENU_MARGIN,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attribute
	private final LayerProperty<TabContainerMenuItemLook> baseMenuItemLook =
	new LayerProperty<>(
		BASE_MENU_ITEM_LOOK_HEADER,
		new TabContainerMenuItemLook(),
		TabContainerMenuItemLook::fromSpecification,
		TabContainerMenuItemLook::getSpecification
	);
	
	//attribute
	private final LayerProperty<TabContainerMenuItemLook> hoverMenuItemLook =
	new LayerProperty<>(
		HOVER_MENU_ITEM_LOOK_HEADER,
		new TabContainerMenuItemLook(),
		TabContainerMenuItemLook::fromSpecification,
		TabContainerMenuItemLook::getSpecification
	);
	
	//attribute
	private final LayerProperty<TabContainerMenuItemLook> selectionMenuItemLook =
	new LayerProperty<>(
		SELECTION_MENU_ITEM_LOOK_HEADER,
		new TabContainerMenuItemLook(),
		TabContainerMenuItemLook::fromSpecification,
		TabContainerMenuItemLook::getSpecification
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
		return menuItemMargin.getRecursiveOrDefaultValue();
	}
	
	//method
	public int getRecursiveOrDefaultMenuMargin() {
		return menuMargin.getRecursiveOrDefaultValue();
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
		
		Validator.assertThat(menuItemMargin).thatIsNamed("menu item margin").isNotNegative();
		
		this.menuItemMargin.setValue(menuItemMargin);
		
		return this;
	}
	
	//method
	public TabContainerLook setMenuMargin(final int menuMargin) {
		
		Validator.assertThat(menuMargin).thatIsNamed("menu margin").isNotNegative();
		
		this.menuMargin.setValue(menuMargin);
		
		return this;
	}
	
	//method
	public TabContainerLook setSelectionMenuItemLook(final TabContainerMenuItemLook selectionMenuItemLook) {
		
		this.selectionMenuItemLook.setValue(selectionMenuItemLook);
		
		return this;
	}
}
