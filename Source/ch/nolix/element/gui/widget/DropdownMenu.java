//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
public final class DropdownMenu extends ItemMenu<DropdownMenu> {
	
	//attributes
	private final HorizontalStack mainHorizontalStack = new HorizontalStack();
	private final Label selectedItemLabel = new Label();
	private final Button expandButton = new Button();
	
	//optional attribute
	private SelectionMenu expandedDropdownMenu;
	
	//constructor
	public DropdownMenu() {
		
		reset();
		
		getRefItemLook().setBackgroundColorForState(WidgetLookState.HOVER, Color.LIGHT_GREY);
		getRefSelectedItemLook().setBackgroundColorForState(WidgetLookState.BASE, Color.GREY);
	}
	
	//method
	public void collapse() {
		if (menuIsExpanded()) {
			collapseMenuWhenMenuIsExpanded();
		}
	}

	//method
	public DropdownMenu expand() {
		
		if (!menuIsExpanded()) {
			expandMenuWhenMenuIsCollapsed();
		}
		
		return this;
	}
	
	//method
	public boolean menuIsExpanded() {
		return (expandedDropdownMenu != null);
	}
	
	//method
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(mainHorizontalStack);
	}
		
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		return mainHorizontalStack.getHeight();
	}
	
	//method
	@Override
	protected int getNaturalContentAreaWidth() {
		return mainHorizontalStack.getWidth();
	}
	
	//method
	@Override
	protected void noteAddItem(final ItemMenuItem item) {}

	//method
	@Override
	protected void noteClear() {
		selectedItemLabel.emptyText();
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteSelectItem2(final ItemMenuItem item) {
		selectedItemLabel.setText(item.getText());
	}
	
	//method
	@Override
	protected void paintContentArea(final IPainter painter, final ItemMenuLook itemMenuLook) {}
	
	//method
	@Override
	protected void recalculateItemMenu() {
		if (containsAny()) {
			selectedItemLabel.setMinWidth(getRefItemLabels().getRefFirst().getMinWidth());
		}
	}
	
	//method
	protected void resetItemMenu() {
		
		mainHorizontalStack.reset();
		selectedItemLabel.reset();
		expandButton.reset();
		
		mainHorizontalStack.addWidget(selectedItemLabel, expandButton);
		expandButton.setText(" v ").setLeftMouseButtonReleaseAction(this::expand);
	}
		
	//method
	private void collapseMenuWhenMenuIsExpanded() {
		expandedDropdownMenu.getParentLayer().removeSelfFromGUI();
		expandedDropdownMenu = null;
	}
	
	//method
	private void expandMenuWhenMenuIsCollapsed() {
		
		//Creates expandedDropdownMenu.
		expandedDropdownMenu =
		new SelectionMenu()
		.setMaxHeight(getParentGUI().getViewAreaHeight() - getYPositionOnGUI() - getHeight())
		.addItems(getRefItems().to(ItemMenuItem::getText))
		.setCustomCursorIcon(CursorIcon.EDIT);
		
		//Recalculates the expandedDropdownMenu.
		expandedDropdownMenu.recalculate();
		
		//Selects the corresponding selected item on the expandedDropDownMenu.
		if (containsSelectedItem()) {
			expandedDropdownMenu.selectItem(getRefSelectedItem().getText());
			
			final var showAreaYPositionOnScrolledArea = (getIndexOfSelectedItem() - 1) * getRefFirstItem().getHeight();
			expandedDropdownMenu.setShowAreaYPositionOnScrolledArea(showAreaYPositionOnScrolledArea);
		}
		
		//Sets the select action of the expandedDropdownMenu.
		expandedDropdownMenu.setSelectAction(this::selectItemAndCollapseWhenMenuIsExpandedAndContainsSelectedItem);
		
		//Configures the look of the expandedDropdownMenu.
		expandedDropdownMenu.getRefLook().setFrom(getRefLook());
		expandedDropdownMenu.getRefItemLook().setFrom(getRefItemLook());
		expandedDropdownMenu.getRefSelectedItemLook().setFrom(getRefSelectedItemLook());
		expandedDropdownMenu.getRefLook().setBorderThicknessForState(WidgetLookState.BASE, 1);
		
		//Recalculates the expandedDropdownMenu again twice.
		expandedDropdownMenu.recalculate();
		expandedDropdownMenu.recalculate();
		
		//Adds the expandedDropdownMenu on the top of the GUI of the current DropdownMenu.
		getParentGUI().addLayerOnTop(
			new Layer()
			.setConfigurationNotAllowed()
			.setFreeContentPosition(
				getXPositionOnGUI() + getWidth() - expandedDropdownMenu.getWidth(),
				getYPositionOnGUI() + getHeight()
			)
			.setRootWidget(expandedDropdownMenu.setFocused())
			.setLeftMouseButtonPressActionOnFreeArea(this::collapse)
			.setContinuousKeyPressAction(this::noteContinuousKeyPressWhenExpanded)
		);
	}
	
	//method
	private void noteContinuousKeyPressWhenExpanded(final Key key) {
		if (key == Key.ESCAPE) {
			collapse();
		}
	}
	
	//method
	private void selectItemAndCollapseWhenMenuIsExpandedAndContainsSelectedItem() {
		selectItem(expandedDropdownMenu.getSelectedItemText());
		collapse();
	}
}
