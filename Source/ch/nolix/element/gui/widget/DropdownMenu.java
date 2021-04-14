//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
public final class DropdownMenu extends TextItemMenu<DropdownMenu> {
	
	//constant
	private static final int MIN_LABEL_WIDTH = 10;
	
	//attributes
	private final HorizontalStack originHorizontalStack = new HorizontalStack();
	private final Label originLabel = new Label();
	private final Button expandButton = new Button();
	
	//optional attribute
	private SelectionMenu expandedDropdownMenu;
	
	//constructor
	public DropdownMenu() {
		
		originHorizontalStack.reset();
		originHorizontalStack
		.addWidget(originLabel, expandButton);
		
		expandButton.reset();
		expandButton.setText(" v ").setLeftMouseButtonReleaseAction(this::expand);
				
		getRefBaseLook()
		.setHoverItemLook(new OldTextItemMenuItemLook().setBackgroundColor(Color.LIGHT_GREY))
		.setSelectionItemLook(new OldTextItemMenuItemLook().setBackgroundColor(Color.GREY));
	}
	
	//method
	public DropdownMenu collapse() {
		
		if (hasMenuExpanded()) {
			collapseMenuWhenHasMenuExpanded();
		}
		
		return this;
	}

	//method
	public DropdownMenu expand() {
		
		if (!hasMenuExpanded()) {
			expandWhenCollapsed();
		}
		
		return this;
	}
	
	//method
	public boolean hasMenuExpanded() {
		return (expandedDropdownMenu != null);
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
		
	//method
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(originHorizontalStack);
	}
		
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		return originHorizontalStack.getHeight();
	}
	
	//method
	@Override
	protected int getNaturalContentAreaWidth() {
		return originHorizontalStack.getWidth();
	}
	
	//method
	@Override
	protected void noteAddItem(TextItemMenuItem item) {
		if (hasMenuExpanded()) {
			expandedDropdownMenu.addItem(item.getText());
		}
	}
	
	//method
	@Override
	protected void noteClear() {
		originLabel.setText(StringCatalogue.EMPTY_STRING);
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteSelectItem(final TextItemMenuItem item) {
		originLabel.setText(item.getText());
	}
	
	//method
	@Override
	protected void paintContentArea(final OldTextItemMenuLook itemMenuLook, final IPainter painter) {}
	
	//method
	@Override
	protected void recalculateTextItemMenuSelf() {
		applySizes();
		applyColors();
	}
	
	//method
	private void applyColors() {
		
		final var textItemMenuLook = getRefOldLook();
		
		originLabel
		.getRefBaseLook()
		.setTextFont(textItemMenuLook.getRecursiveOrDefaultTextFont())
		.setTextSize(textItemMenuLook.getRecursiveOrDefaultTextSize())
		.setTextColor(textItemMenuLook.getRecursiveOrDefaultTextColor());
		
		expandButton
		.getRefBaseLook()
		.setTextFont(textItemMenuLook.getRecursiveOrDefaultTextFont())
		.setTextSize(textItemMenuLook.getRecursiveOrDefaultTextSize())
		.setTextColor(textItemMenuLook.getRecursiveOrDefaultTextColor());
		
		if (hasMenuExpanded()) {
			expandedDropdownMenu
			.getRefBaseLook()
			.setTextFont(textItemMenuLook.getRecursiveOrDefaultTextFont())
			.setTextSize(textItemMenuLook.getRecursiveOrDefaultTextSize())
			.setTextColor(textItemMenuLook.getRecursiveOrDefaultTextColor());
		}
	}
	
	//method
	private void applySizes() {
		originLabel.setMinWidth(getRefItemLables().getMaxIntOrDefaultValue(Label::getWidth, MIN_LABEL_WIDTH));
	}
	
	//method
	private void collapseMenuWhenHasMenuExpanded() {
		expandedDropdownMenu = null;
		getParentGUI().removeTopLayer();
	}
	
	//method
	private void expandWhenCollapsed() {
		
		//Creates expandedDropdownMenu.
		expandedDropdownMenu =
		new SelectionMenu()
		.setCustomCursorIcon(CursorIcon.EDIT)
		.setMaxHeight(getParentGUI().getViewAreaHeight() - getYPositionOnGUI() - getHeight())
		.applyOnBaseLook(bl -> bl.setBorderThicknesses(1))
		.addItems(getRefItems().to(TextItemMenuItem::getText));
						
		//TODO: Analyze recalculate method.
		/*
		 * Recalculates the SelectionMenu.
		 * There are needed several recalculations, because 1 recalculation does not guarantee to update everything.
		 * There exists a necessary cycle logic.
		 */
		expandedDropdownMenu.recalculate();
		expandedDropdownMenu.recalculate();
		
		if (containsSelectedItem()) {
			
			expandedDropdownMenu.selectItem(getRefSelectedItem().getText());
			
			final var showAreaYPositionOnScrolledArea = (getIndexOfSelectedItem() - 1) * getRefFirstItem().getHeight();
			
			expandedDropdownMenu.setShowAreaYPositionOnScrolledArea(showAreaYPositionOnScrolledArea);
		}
		
		//Sets the select-action to the expandedDropdownMenu after selecting the current item.
		expandedDropdownMenu.setSelectAction(this::selectAndCollapse);
		
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
	private void selectAndCollapse(final TextItemMenuItem item) {
		selectItem(this.getRefItems().getRefFirst(i -> i.hasText(item.getText())));
		collapse();
	}
}
