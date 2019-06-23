//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.color.Color;

//class
public final class SelectionMenu extends ItemMenu<SelectionMenu> {
	
	//attribute
	private final VerticalStack menu = new VerticalStack();
	
	//constructor
	public SelectionMenu() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	public SelectionMenu(final ItemMenuItem... items) {
		
		this();
		
		addItem(items);
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
		return getSelectedItem().getId();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public void noteLeftMouseButtonPressOnViewArea() {
		
		var selectedItem = getItems().getRefFirstOrNull(i -> i.getRefLabel().isUnderCursor());
		
		if (selectedItem != null) {
			select(selectedItem);
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
			new ItemMenuItemLook()
			.setBackgroundColor(Color.LIGHT_GREY)
		)
		.setSelectionItemLook(
			new ItemMenuItemLook()
			.setBackgroundColor(Color.GREY)
		);
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final List<Widget<?, ?>> list) {
		list.addAtEnd(menu);
	}
	
	//method
	@Override
	protected void fillUpConfigurableChildWidgets(final List<Widget<?, ?>> list) {}
		
	//method
	@Override
	protected int getContentAreaHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getItems().getSumByInt(i -> i.getHeight());
	}
	
	//method
	@Override
	protected int getContentAreaWidth() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getItems().getMaxByInt(i -> i.getRefLabel().getWidth());
	}
	
	//method
	@Override
	protected void noteAddItem(final ItemMenuItem itemMenuItem) {
		menu.addWidget(itemMenuItem.getRefLabel());
	}

	//method
	@Override
	protected void noteClear() {
		menu.clear();
	}
	
	//method
	@Override
	protected void noteSelectItem(ItemMenuItem item) {}
}
