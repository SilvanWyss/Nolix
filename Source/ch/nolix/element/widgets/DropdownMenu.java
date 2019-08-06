//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.containers.List;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.element.GUI.Layer;
import ch.nolix.element.GUI_API.Widget;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementEnums.ExtendedContentPosition;

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
		
		selectionMenu = new SelectionMenu(getItems().to(i -> i.getText()));
		
		selectionMenu.setSelectCommand(
			i -> { select(i); getParentGUI().removeTopLayer(); selectionMenu = null; }
		);
		
		for (final var i : getItems()) {
			i.getRefLabel().setLeftMouseButtonPressCommand(
				() -> { select(i); getParentGUI().removeTopLayer(); selectionMenu = null; }
			);
		}
		
		selectionMenu
		.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.WHITE))
		.setProposalWidth(originHorizontalStack.getWidth())
		.setProposalHeight(200);		
		
		originHorizontalStack.recalculate();
		selectionMenu.recalculate();
		
		getParentGUI().addLayerOnTop(
			new Layer()
			.setContentPosition(ExtendedContentPosition.Free)
			.setFreeContentPosition(
				getXPositionOnGUI(),
				getYPositionOnGUI() + originHorizontalStack.getHeight()
			)
			.setRootWidget(selectionMenu.setFocused())
		);
		
		recalculate();
		
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
	protected void fillUpChildWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected void fillUpWidgetsForPainting(final List<Widget<?, ?>> list) {
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
	@Override
	protected void noteSelectItem(final ItemMenuItem item) {
		originLabel.setText(item.getText());
	}
}
