//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.entity.MultiProperty;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.skillAPI.Clearable;

//abstract class
public abstract class ItemMenu<IM extends ItemMenu<IM>> extends BorderWidget<IM, ItemMenuLook>
implements Clearable<IM> {
	
	//constant
	private static final String SELECTED_ITEM_HEADER = "SelectedItem";
	
	//attribute
	private final MultiProperty<ItemMenuItem> items =
	new MultiProperty<ItemMenuItem>(
		PascalCaseNameCatalogue.ITEM,
		i -> addItem(i),
		s -> ItemMenuItem.createFromSpecification(s),
		i -> i.getSpecification()
	);
	
	public final IM addItem(final ItemMenuItem item) {
		
		supposeDoesNotContainItemWithText(item.getText());
		
		items.addValue(item);
		noteAddItem(item);
		
		return asConcreteType();
	}
	
	//method
	public IM addItems(final Iterable<String> texts) {
		
		texts.forEach(t -> addItem(new ItemMenuItem(t)));
		
		return asConcreteType();
	}
	
	//method
	public IM addItem(final String text) {
		return addItem(new ItemMenuItem(text));
	}
	
	//method
	public IM addItem(final String... texts) {
		return addItems(new ReadContainer<String>(texts));
	}
	
	//method
	public IM addItem(final String id, final String text) {
		return addItem(new ItemMenuItem(id, text));
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		switch (attribute.getHeader()) {
			case SELECTED_ITEM_HEADER:
				selectItemByText(attribute.getOneAttributeAsString());
				break;
			default:				
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	@Override
	public final IM clear() {
		
		unselectAllItems();
		items.clear();
		noteClear();
		
		return asConcreteType();
	}
	
	//method
	public final boolean containsItemWithId(final String id) {
		return items.contains(i -> i.hasId(id));
	}
	
	//method
	public final boolean containsItemWithText(final String text) {
		return items.contains(i -> i.hasText(text));
	}
	
	//method
	public final boolean containsSelectedItem() {
		return items.contains(i -> i.isSelected());
	}
	
	//method
	@Override
	public final List<DocumentNode> getInteractionAttributes() {
		
		final var interactionAttributes = super.getInteractionAttributes();
		
		if (containsSelectedItem()) {
			interactionAttributes.addAtEnd(new DocumentNode(SELECTED_ITEM_HEADER,	getSelectedItemText()));
		}
		
		return interactionAttributes;
	}
	
	//method
	public final IContainer<ItemMenuItem> getItems() {
		return items;
	}
	
	//method
	public final String getSelectedItemText() {
		return getSelectedItem().getText();
	}
	
	//method
	@Override
	public final boolean isEmpty() {
		return items.isEmpty();
	}
	
	//method
	public final void recalculate() {
				
		super.recalculate();
		
		final var look = getRefCurrentLook();
		final var contentWidth = getContentAreaWidth();
		
		final var baseItemLook = look.getRefRecursiveOrDefaultBaseItemLook();
		final var hoverItemLook = look.getRefRecursiveOrDefaultHoverItemLook();
		final var selectedItemLook = look.getRefRecursiveOrDefaultSelectionItemLook();
		
		for (final var i : items) {
			
			final var label = i.getRefLabel();
			
			label.setMinWidth(contentWidth);

			label
			.getRefBaseLook()
			.reset()						
			.setTextColor(baseItemLook.getRecursiveOrDefaultTextColor())
			.setPaddings(look.getRecursiveOrDefaultItemPadding())
			.setTextSize(look.getRecursiveOrDefaultTextSize());
			
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
	}
	
	//method
	public final IM selectItemById(final String id) {
		
		select(items.getRefFirst(i -> i.hasId(id)));
		
		return asConcreteType();
	}
	
	//method
	public final IM selectItemByText(final String text) {
		
		select(items.getRefFirst(i -> i.hasText(text)));
		
		return asConcreteType();
	}
	
	//method
	public final IM selectFirstItem() {
		
		select(items.getRefFirst());
		
		return asConcreteType();
	}
	
	//method
	public IM unselectAllItems() {
		
		if (containsSelectedItem()) {
			getSelectedItem().unselect();
		}
		
		return asConcreteType();
	}
	
	//method
	@Override
	protected final ItemMenuLook createWidgetLook() {
		return new ItemMenuLook();
	}
	
	//method
	protected final List<Label> getRefItemLables() {
		
		final var itemLabels = new List<Label>();
		
		for (final var i : items) {
			itemLabels.addAtEnd(i.getRefLabel());
		}
		
		return itemLabels;
	}
	
	//method
	protected ItemMenuItem getSelectedItem() {
		
		supposeContainsSelectedItem();
		
		return items.getRefFirst(i -> i.isSelected());
	}
	
	//abstract method
	protected abstract void noteAddItem(final ItemMenuItem item);
	
	//abstract method
	protected abstract void noteClear();
	
	//method
	protected final void select(final ItemMenuItem item) {
		
		//For better performance, this implementation does not use all comfortable methods.
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
			throw new InvalidArgumentException(
				this,
				"does not contain a selected item"
			);
		}
	}
	
	//method
	private void supposeDoesNotContainItemWithText(final String text) {
		if (containsItemWithText(text)) {
			throw new InvalidArgumentException(this, "contains an item with the text '" + text + "'");
		}
	}
}
