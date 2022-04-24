//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//class
public final class SelectionMenu extends ItemMenu<SelectionMenu> {
	
	//attribute
	private final VerticalStack mainVerticalStack = new VerticalStack();
	
	//constructor
	public SelectionMenu() {
		
		reset();
		
		getRefLook().setBorderThicknessForState(WidgetLookState.BASE, 1);
		getRefItemLook().setBackgroundColorForState(WidgetLookState.HOVER, Color.LIGHT_GREY);
		getRefSelectedItemLook().setBackgroundColorForState(WidgetLookState.BASE, Color.GREY);
	}
	
	//method
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(mainVerticalStack);
	}
		
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		return getRefItems().getSumByInt(ItemMenuItem::getHeight);
	}
	
	//method
	@Override
	protected int getNaturalContentAreaWidth() {
		return getRefItems().getMaxIntOrZero(ItemMenuItem::getWidth);
	}
	
	//method
	@Override
	protected void noteAddItem(final ItemMenuItem itemMenuItem) {
		mainVerticalStack.add(itemMenuItem.getRefLabel());
	}
	
	//method
	@Override
	protected void noteClear() {
		mainVerticalStack.clear();
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		
		var itemUnderCursor = getRefItems().getRefFirstOrNull(i -> i.getRefLabel().isUnderCursor());
		
		if (itemUnderCursor != null) {
			itemUnderCursor.select();
		}
	}
	
	//method
	@Override
	protected void noteSelectItem2(final ItemMenuItem item) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final IPainter painter, final ItemMenuLook itemMenuLook) {}
	
	//method
	@Override
	protected void recalculateItemMenu() {}
	
	//method
	@Override
	protected void resetItemMenu() {
		mainVerticalStack.reset();
	}
}
