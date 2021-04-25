//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.painterapi.IPainter;

//class
public final class SelectionMenu extends ItemMenu<SelectionMenu> {
		
	//attribute
	private final VerticalStack mainVerticalStack = new VerticalStack();
	
	//constructor
	public SelectionMenu() {
		
		reset();
		
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
		mainVerticalStack.addWidget(itemMenuItem.getRefLabel());
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
}
