//package declaration
package ch.nolix.element.widget;

import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.element.GUI.Layer;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementEnum.ExtendedContentPosition;

//class
public final class DropdownMenu extends ItemMenu<DropdownMenu> {
	
	//attributes
	private final Label originLabel = new Label();
	private final Button expandButton = new Button(" v ").setLeftMouseButtonReleaseCommand(() -> expand());
	private final HorizontalStack originHorizontalStack = new HorizontalStack(originLabel, expandButton);
	
	//optional attribute
	private SelectionMenu selectionMenu;
	
	//constructor
	public DropdownMenu() {		
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	public DropdownMenu(final Iterable<String> items) {
		
		resetAndApplyDefaultConfiguration();
		
		addItems(items);
	}
	
	//constructor
	public DropdownMenu(final String... items) {
		
		resetAndApplyDefaultConfiguration();
		
		addItem(items);
	}

	//method
	public DropdownMenu expand() {
		
		if (isExpanded()) {
			throw new InvalidArgumentException(this, "is already expanded");
		}
		
		selectionMenu = new SelectionMenu(getItems().to(ItemMenuItem::getText));
		
		selectionMenu.setSelectCommand(
			i -> { select(i); getParentGUI().removeTopLayer(); selectionMenu = null; }
		);
		
		selectionMenu
		.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.WHITE))
		.setProposalWidth(originHorizontalStack.getWidth())
		.setProposalHeight(200);		
		
		originHorizontalStack.recalculateSelf();
		selectionMenu.recalculateSelf();
		
		getParentGUI().addLayerOnTop(
			new Layer()
			.setContentPosition(ExtendedContentPosition.Free)
			.setFreeContentPosition(
				getXPositionOnGUI(),
				getYPositionOnGUI() + originHorizontalStack.getHeight()
			)
			.setRootWidget(selectionMenu.setFocused())
		);
		
		recalculateSelf();
		
		return this;
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	public boolean isExpanded() {
		return (selectionMenu != null);
	}
	
	//method
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		
		originLabel.setProposalWidth(200);
		originHorizontalStack.getRefBaseLook().setBackgroundColor(Color.WHITE);
		expandButton.getRefHoverLook().setBackgroundColor(Color.LIGHT_GREY);
		
		getRefBaseLook()
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
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(originHorizontalStack);
	}
	
	//method
	@Override
	protected int getContentAreaHeight() {
		return originHorizontalStack.getHeight();
	}
	
	//method
	@Override
	protected int getContentAreaWidth() {
		return originHorizontalStack.getWidth();
	}
	
	//method
	@Override
	protected void noteAddItem(ItemMenuItem item) {
		if (isExpanded()) {
			selectionMenu.addItem(item.getText());
		}
	}
	
	//method
	@Override
	protected void noteClear() {
		originLabel.setText(StringCatalogue.EMPTY_STRING);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteSelectItem(final ItemMenuItem item) {
		originLabel.setText(item.getText());
	}
}
