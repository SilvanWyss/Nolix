//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.element.color.Color;
import ch.nolix.element.painter.IPainter;

//class
public final class SelectionMenu
extends BorderWidget<SelectionMenu, SelectionMenuLook> 
implements Clearable<SelectionMenu> {
	
	//constant
	private static final String SELECTED_ITEM_HEADER = "SelectedItem";
	
	//attribute
	private final VerticalStack menu = new VerticalStack();
	
	//multi-attribute
	private final List<SelectionMenuItem> items = new List<SelectionMenuItem>();
	
	//constructor
	public SelectionMenu() {
		reset();
		approveProperties();
		applyUsableConfiguration();
	}
	
	//constructor
	public SelectionMenu(final String... items) {
		
		this();
		
		addItem(items);
	}
	
	//method
	public SelectionMenu addItems(final Iterable<String> texts) {
		
		texts.forEach(t -> addItem(new SelectionMenuItem(t)));
		
		return this;
	}
	
	//method
	public SelectionMenu addItem(final SelectionMenuItem selectionMenuItem) {
		
		supposeDoesNotContainItem(selectionMenuItem.getText());
		
		items.addAtEnd(selectionMenuItem);
		menu.addWidget(selectionMenuItem.getRefLabel());
		
		return this;
	}
	
	//method
	public SelectionMenu addItem(final String text) {
		return addItem(new SelectionMenuItem(text));
	}
	
	//method
	public SelectionMenu addItem(final String... texts) {
		return addItems(new ReadContainer<String>(texts));
	}
	
	//method
	public SelectionMenu addItem(final int id, final String text) {
		return addItem(new SelectionMenuItem(id, text));
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.ITEM:
				addItem(SelectionMenuItem.createFromSpecification(attribute));
				break;
			case SELECTED_ITEM_HEADER:
				select(attribute.getOneAttributeAsString());
				break;
			default:				
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	@Override
	public SelectionMenu clear() {
		
		unselect();
		items.clear();
		menu.clear();
		
		return this;
	}
	
	//method
	public boolean containsItem(final int id) {
		return items.contains(i -> i.hasId(id));
	}
	
	//method
	public boolean containsItem(final String text) {
		return items.contains(i -> i.hasText(text));
	}
	
	//method
	public boolean containsSelectedItem() {
		return items.contains(i -> i.isSelected());
	}
	
	//method
	@Override
	public List<DocumentNode> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		for (final var i : getItems()) {
			attributes.addAtEnd(i.getSpecificationAs(PascalCaseNameCatalogue.ITEM));
		}
		
		return attributes;
	}
	
	//method
	@Override
	public CursorIcon getContentAreaCursorIcon() {
		
		if (menu.isUnderCursor()) {
			return menu.getCursorIcon();
		}
		
		return getCustomCursorIcon();
	}
	
	//method
	@Override
	public List<DocumentNode> getInteractionAttributes() {
		
		final var interactionAttributes = super.getInteractionAttributes();
		
		if (containsSelectedItem()) {
			interactionAttributes.addAtEnd(
				new DocumentNode(
					SELECTED_ITEM_HEADER,
					getSelectedItemText()
				)
			);
		}
		
		return interactionAttributes;
	}
	
	//method
	public int getSelectedItemId() {
		return getSelectedItem().getId();
	}
	
	//method
	public String getSelectedItemText() {
		return getSelectedItem().getText();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean keepsFocus() {
		return true;
	}
	
	//method
	@Override
	public void noteAnyMouseMove() {
		
		super.noteAnyMouseMove();
		
		menu.getRefWidgets().forEach(w -> w.noteAnyMouseMove());
	}
	
	//method
	@Override
	public void noteLeftMouseButtonPress() {
		
		super.noteLeftMouseButtonPress();
		
		if (menu.isUnderCursor()) {
			
			var selectedItem = items.getRefFirstOrNull(i -> i.getRefLabel().isUnderCursor());
			
			if (selectedItem != null) {
				select(selectedItem);
			}
		}
	}
	
	//method
	public SelectionMenu select(final int id) {
		
		select(items.getRefFirst(i -> i.hasId(id)));
		
		return this;
	}
	
	//method
	public SelectionMenu select(final String text) {
		
		select(items.getRefFirst(i -> i.hasText(text)));
		
		return this;
	}
	
	//method
	public SelectionMenu selectFirstItem() {
		
		select(items.getRefFirst());
		
		return this;
	}
	
	//method
	public SelectionMenu unselect() {
		
		if (containsSelectedItem()) {
			getSelectedItem().unselect();
		}
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyUsableConfigurationWhenConfigurationIsReset() {
		
		menu.setElementMargin(0);
		
		getRefBaseLook()
		.setHoverItemLook(
			new SelectionMenuItemLook()
			.setBackgroundColor(Color.LIGHT_GREY)
		)
		.setSelectionItemLook(
			new SelectionMenuItemLook()
			.setBackgroundColor(Color.GREY)
		);
	}
	
	//method
	@Override
	protected SelectionMenuLook createWidgetLook() {
		return new SelectionMenuLook();
	}
	
	//method
	@Override
	protected void fillUpOwnWidgets(final List<Widget<?, ?>> list) {}
	
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
		
		return items.getMaxByInt(i -> i.getRefLabel().getWidth());
	}
	
	//method
	@Override
	protected void paintContentArea(
		final SelectionMenuLook selectionMenuLook,
		final IPainter painter
	) {
		
		final var contentWidth = getContentAreaWidth();
		
		final var baseItemLook = selectionMenuLook.getRefRecursiveOrDefaultBaseItemLook();
		final var hoverItemLook = selectionMenuLook.getRefRecursiveOrDefaultHoverItemLook();
		final var selectedItemLook = selectionMenuLook.getRefRecursiveOrDefaultSelectionItemLook();
		
		for (final Widget<?, ?> w : menu.getRefWidgets()) {
			
			final var label = (Label)w;
			
			label.setMinWidth(contentWidth);

			label
			.getRefBaseLook()
			.reset()						
			.setTextColor(baseItemLook.getRecursiveOrDefaultTextColor())
			.setPaddings(selectionMenuLook.getRecursiveOrDefaultItemPadding())
			.setTextSize(selectionMenuLook.getRecursiveOrDefaultTextSize());
			
			if (baseItemLook.hasRecursiveBackgroundColor()) {
				label
				.getRefBaseLook()
				.setBackgroundColor(baseItemLook.getRecursiveOrDefaultBackgroundColor());
			}
			
			label
			.getRefHoverLook()
			.reset()
			.setBackgroundColor(hoverItemLook.getRecursiveOrDefaultBackgroundColor())
			.setTextColor(hoverItemLook.getRecursiveOrDefaultTextColor());
			
			if (hoverItemLook.hasRecursiveBackgroundColor()) {
				label
				.getRefHoverLook()
				.setBackgroundColor(hoverItemLook.getRecursiveOrDefaultBackgroundColor());
			}
			
			label
			.getRefFocusLook()
			.reset()
			.setBackgroundColor(selectedItemLook.getRecursiveOrDefaultBackgroundColor())
			.setTextColor(selectedItemLook.getRecursiveOrDefaultTextColor());
			
			if (selectedItemLook.hasRecursiveBackgroundColor()) {
				label
				.getRefFocusLook()
				.setBackgroundColor(selectedItemLook.getRecursiveOrDefaultBackgroundColor());
			}
		}
		
		menu.setPositionOnParent(0, 0);
		menu.paintUsingPositionOnParent(painter);
	}
	
	//method
	@Override
	protected void setCursorPositionOnContentArea(
		final int cursorXPositionOnContent,
		final int cursorYPositionOnContent
	) {
		menu.setParentCursorPosition(
			cursorXPositionOnContent,
			cursorYPositionOnContent
		);
	}
	
	//method
	private IContainer<SelectionMenuItem> getItems() {
		return items;
	}
	
	//method
	private SelectionMenuItem getSelectedItem() {
		
		supposeContainsSelectedItem();
		
		return items.getRefFirst(i -> i.isSelected());
	}
	
	//method
	private void select(final SelectionMenuItem item) {
		
		//For better performance, not all comfortable methods are used.
			final var selectedItem = items.getRefFirstOrNull(i -> i.isSelected());
			
			if (selectedItem != null) {
				selectedItem.unselect();
			}
			
			items.forEach(i -> i.unselect());
		
		item.select();
	}
	
	//method
	private void supposeContainsSelectedItem() {
		if (!containsSelectedItem()) {
			throw new InvalidStateException(
				this,
				"contains no selected item"
			);
		}
	}

	//method
	private void supposeDoesNotContainItem(final String text) {
		if (containsItem(text)) {
			throw new InvalidArgumentException(
				this,
				"contains an item with the text '" + text + "'"
			);
		}
	}
}
