//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.color.Color;

//class
public final class SelectionMenu extends TextItemMenu<SelectionMenu> {
	
	//attribute
	private final VerticalStack menu = new VerticalStack();
	
	//constructor
	public SelectionMenu() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	public SelectionMenu(final Iterable<String> items) {

		this();
		
		addItems(items);
	}
	
	//constructor
	public SelectionMenu(final String... items) {
		
		this();
		
		addItem(items);
	}
	
	//method
	public String getSelectedItemId() {
		return getRefSelectedItem().getId();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
				
		var selectedItem = getRefItems().getRefFirstOrNull(i -> i.getRefLabel().isUnderCursor());
		
		if (selectedItem != null) {
			selectItem(selectedItem);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		
		menu.setElementMargin(0);
		
		getRefBaseLook()
		.setBackgroundColor(Color.WHITE_SMOKE)
		.setHoverItemLook(
			new TextItemMenuItemLook()
			.setBackgroundColor(Color.LIGHT_GREY)
		)
		.setSelectionItemLook(
			new TextItemMenuItemLook()
			.setBackgroundColor(Color.GREY)
		);
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(menu);
	}
		
	//method
	@Override
	protected int getContentAreaHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefItems().getSumByInt(i -> i.getHeight());
	}
	
	//method
	@Override
	protected int getContentAreaWidth() {
		return getRefItems().getMaxIntOrZero(TextItemMenuItem::getWidth);
	}
	
	//method
	@Override
	protected void noteAddItem(final TextItemMenuItem itemMenuItem) {
		menu.addWidget(itemMenuItem.getRefLabel());
	}

	//method
	@Override
	protected void noteClear() {
		menu.clear();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteSelectItem(TextItemMenuItem item) {}
	
	//method
	@Override
	protected void recalculateSelfStage3() {}
}
