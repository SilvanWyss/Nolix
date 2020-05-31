//package declaration
package ch.nolix.element.widget;

import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MultiProperty;

//class
public abstract class ItemMenu<IM extends ItemMenu<IM>> extends BorderWidget<IM, ItemMenuLook>
implements Clearable<IM> {
	
	//constant
	private static final String SELECTED_ITEM_HEADER = "SelectedItem";
	
	//attribute
	private final MultiProperty<ItemMenuItem> items =
	new MultiProperty<>(
		PascalCaseNameCatalogue.ITEM,
		i -> addItem(i),
		s -> ItemMenuItem.fromSpecification(s),
		i -> i.getSpecification()
	);
	
	//optional attribute
	private IElementTaker<ItemMenuItem> selectCommand;
	
	//method
	public final IM addItem(final ItemMenuItem item) {
		
		supposeDoesNotContainItemWithText(item.getText());
		
		item.setParentMenu(this);
		items.add(item);
		noteAddItem(item);
		
		return asConcrete();
	}
	
	//method
	public final IM addItem(final ItemMenuItem... items) {
		
		for (final var i : items) {
			addItem(i);
		}
		
		return asConcrete();
	}
	
	//method
	public IM addItems(final Iterable<String> texts) {
		
		texts.forEach(t -> addItem(new ItemMenuItem(t)));
		
		return asConcrete();
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
	public IM addItem(final String text, final IElementTaker<ItemMenu<?>> selectCommand) {
		return addItem(new ItemMenuItem(text, selectCommand));
	}
	
	//method
	public IM addItem(final String id, final String text, final IElementTaker<ItemMenu<?>> selectCommand) {
		return addItem(new ItemMenuItem(id, text, selectCommand));
	}
	
	//method
	public IM addItem(final String id, final String text) {
		return addItem(new ItemMenuItem(id, text));
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
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
		
		return asConcrete();
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
	public final LinkedList<Node> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (containsSelectedItem()) {
			attributes.addAtEnd(new Node(SELECTED_ITEM_HEADER,	getSelectedItemText()));
		}
		
		return attributes;
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
	public final boolean hasSelectCommand() {
		return (selectCommand != null);
	}
	
	//method
	@Override
	public final boolean isEmpty() {
		return items.isEmpty();
	}
	
	//method
	@Override
	public final void recalculateSelf() {
				
		super.recalculateSelf();
		
		final var look = getRefLook();
		final var contentWidth = Calculator.getMax(1, getContentAreaWidth());
		
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
	public final IM removeSelectCommand() {
		
		selectCommand = null;
		
		return asConcrete();
	}
	
	//method
	@Override
	public IM reset() {
		
		super.reset();
		
		clear();
		removeSelectCommand();
		
		return asConcrete();
	}
	
	//method
	public final IM selectItemById(final String id) {
		
		select(items.getRefFirst(i -> i.hasId(id)));
		
		return asConcrete();
	}
	
	//method
	public final IM selectItemByText(final String text) {
		
		select(items.getRefFirst(i -> i.hasText(text)));
		
		return asConcrete();
	}
	
	//method
	public final IM selectFirstItem() {
		
		select(items.getRefFirst());
		
		return asConcrete();
	}
	
	//method
	public final IM setSelectCommand(final IElementTaker<ItemMenuItem> selectCommand) {
		
		this.selectCommand = Validator.assertThat(selectCommand).thatIsNamed("select command").isNotNull().andReturn();
		
		return asConcrete();
	}
	
	//method
	public final IM unselectAllItems() {
		
		if (containsSelectedItem()) {
			getSelectedItem().unselect();
		}
		
		return asConcrete();
	}
	
	//method
	@Override
	protected final ItemMenuLook createLook() {
		return new ItemMenuLook();
	}
	
	//method
	protected final LinkedList<Label> getRefItemLables() {
		
		final var itemLabels = new LinkedList<Label>();
		
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
	
	//method declaration
	protected abstract void noteAddItem(final ItemMenuItem item);
	
	//method declaration
	protected abstract void noteClear();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method declaration
	protected abstract void noteSelectItem(ItemMenuItem item);
	
	//method
	protected final void select(final ItemMenuItem item) {
		
		//For better performance, this implementation does not use all comfortable methods.
			final var selectedItem = items.getRefFirstOrNull(i -> i.isSelected());
			
			if (selectedItem != null) {
				selectedItem.unselect();
			}
			
			items.forEach(i -> i.unselect());
		
			if (selectCommand != null) {
				selectCommand.run(item);
			}
			
		item.select();
		noteSelectItem(item);
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
