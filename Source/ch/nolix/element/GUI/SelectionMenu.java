//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidStateException.InvalidStateException;

//class
public final class SelectionMenu
extends BorderWidget<SelectionMenu, SelectionMenuLook> 
implements Clearable<SelectionMenu> {
	
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
	public void addOrChangeAttribute(final Specification attribute) {
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.ITEM:
				addItem(SelectionMenuItem.createFromSpecification(attribute));
				break;
			default:				
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
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
	public CursorIcon getActiveCursorIcon() {
		
		if (menu.isUnderCursor()) {
			return menu.getActiveCursorIcon();
		}
		
		return getCursorIcon();
	}
	
	//method
	public List<StandardSpecification> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		getItems().forEach(
			i -> attributes.addAtEnd(i.getSpecificationAs(PascalCaseNameCatalogue.ITEM))
		);
		
		return attributes;
	}
	
	//method
	public ReadContainer<Widget<?, ?>> getRefWidgets() {
		return new ReadContainer<Widget<?, ?>>();
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
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	//method
	public void noteLeftMouseButtonPress() {
		
		super.noteLeftMouseButtonPress();
		
		if (menu.isUnderCursor()) {
			menu.getRefWidgets().forEach(w -> w.noteAnyLeftMouseButtonPress());
		}
	}
	
	//method
	public void noteMouseMove() {
		
		super.noteMouseMove();
		
		menu.getRefWidgets().forEach(w -> w.noteAnyMouseMove());
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
	protected SelectionMenuLook createWidgetLook() {
		return new SelectionMenuLook();
	}
	
	//method
	protected int getContentAreaHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getItems().getSumByInt(i -> i.getHeight());
	}
	
	//method
	protected int getContentAreaWidth() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return items.getMaxInt(i -> i.getRefLabel().getWidth());
	}
	
	//method
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
				new Argument(this),
				new ErrorPredicate(
					"contains an item with the text '" + text + "'."
				)
			);
		}
	}
}
