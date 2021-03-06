//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.painterapi.IPainter;

//class
public final class SelectionMenu extends TextItemMenu<SelectionMenu> {
	
	//attribute
	private final VerticalStack menu = new VerticalStack();
	
	//constructor
	public SelectionMenu() {
		
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
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(menu);
	}
		
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		return getRefItems().getSumByInt(TextItemMenuItem::getHeight);
	}
	
	//method
	@Override
	protected int getNaturalContentAreaWidth() {
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final TextItemMenuLook itemMenuLook, final IPainter painter) {}
	
	//method
	@Override
	protected void recalculateTextItemMenuSelf() {}
}
