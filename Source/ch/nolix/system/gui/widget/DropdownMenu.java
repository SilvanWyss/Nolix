//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.HorizontalStack;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

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
		
		getRefLook().setBorderThicknessForState(ControlState.BASE, 1);
		getRefItemLook().setBackgroundColorForState(ControlState.HOVER, Color.LIGHT_GREY);
		getRefSelectedItemLook().setBackgroundColorForState(ControlState.BASE, Color.GREY);
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
	protected void fillUpWidgetsForPainting(final LinkedList<IWidget<?, ?>> list) {
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
	protected void noteAddItem(final ItemMenuItem item) {
		//Does nothing.
	}

	//method
	@Override
	protected void noteClear() {
		selectedItemLabel.emptyText();
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteSelectItem2(final ItemMenuItem item) {
		selectedItemLabel.setText(item.getText());
	}
	
	//method
	@Override
	protected void paintContentArea(final IPainter painter, final ItemMenuLook itemMenuLook) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void recalculateItemMenu() {
		selectedItemLabel.setMinWidth(calculateSelectedItemLabelMinWidth());
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
	private int calculateSelectedItemLabelMinWidth() {
		
		if (isEmpty()) {
			
			if (!hasMinWidth() && !hasProposalWidth()) {
				return 100;
			}
			
			return (getTargetWidth() - expandButton.getWidth());
		}
		
		if (!hasMinWidth() && !hasProposalWidth()) {
			return getRefItemLabels().getRefFirst().getMinWidth();
		}
		
		return (getTargetWidth() - expandButton.getWidth());
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
		.setMaxHeight(getParentGUI().getViewAreaHeight() - getYPositionOnGUIViewArea() - getHeight())
		.addItems(getRefItems().to(ItemMenuItem::getText))
		.setCustomCursorIcon(CursorIcon.EDIT);
		
		//Recalculates the expandedDropdownMenu.
		expandedDropdownMenu.recalculate();
		
		//Selects the corresponding selected item on the expandedDropDownMenu.
		if (containsSelectedItem()) {
			expandedDropdownMenu.selectItem(getRefSelectedItem().getText());
		}
		
		//Sets the select action of the expandedDropdownMenu.
		expandedDropdownMenu.setSelectAction(this::selectItemAndCollapseWhenMenuIsExpandedAndContainsSelectedItem);
		
		//Configures the look of the expandedDropdownMenu.
		expandedDropdownMenu.getRefLook().setFrom(getRefLook());
		expandedDropdownMenu.getRefItemLook().setFrom(getRefItemLook());
		expandedDropdownMenu.getRefSelectedItemLook().setFrom(getRefSelectedItemLook());
		expandedDropdownMenu.getRefLook().setBorderThicknessForState(ControlState.BASE, 1);
		
		//Recalculates the expandedDropdownMenu again twice.
		expandedDropdownMenu.recalculate();
		expandedDropdownMenu.recalculate();
		
		//Scrolls to the selected item on the expandedDropDownMenu.
		if (containsSelectedItem()) {
			final var showAreaYPositionOnScrolledArea = (getIndexOfSelectedItem() - 1) * getRefFirstItem().getHeight();
			expandedDropdownMenu.setShowAreaYPositionOnScrolledArea(showAreaYPositionOnScrolledArea);
		}
		
		//Adds the expandedDropdownMenu on the top of the GUI of the current DropdownMenu.
		getParentGUI().pushLayer(
			new Layer()
			.setConfigurationNotAllowed()
			.setFreeContentPosition(
				getXPositionOnGUIViewArea() + getWidth() - expandedDropdownMenu.getWidth(),
				getYPositionOnGUIViewArea() + getHeight()
			)
			.setRootWidget(expandedDropdownMenu.setFocused())
			.setLeftMouseButtonPressActionOnFreeArea(this::collapse)
			.setKeyPressAction(this::noteKeyDownWhenExpanded)
		);
	}
	
	//method
	private void noteKeyDownWhenExpanded(final Key key) {
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
