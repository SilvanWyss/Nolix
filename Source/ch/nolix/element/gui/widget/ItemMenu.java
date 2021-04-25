//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.base.MultiValue;
import ch.nolix.element.base.SubElement;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.input.Key;

//class
public abstract class ItemMenu<IM extends ItemMenu<IM>> extends BorderWidget<IM, ItemMenuLook> implements Clearable {
	
	//constants
	private static final String ITEM_HEADER = PascalCaseCatalogue.ITEM;
	private static final String ITEM_LOOK_HEADER = "ItemLook";
	private static final String SELECTED_ITEM_LOOK_HEADER = "SelectedItemLook";
	
	//attribute
	private final MultiValue<ItemMenuItem> items =
	new MultiValue<>(
			ITEM_HEADER,
		this::addItem,
		ItemMenuItem::fromSpecification,
		ItemMenuItem::getSpecification
	);
	
	//attribute
	private final SubElement<LabelLook> itemLook = new SubElement<>(ITEM_LOOK_HEADER, new LabelLook());
	
	//attribute
	private final SubElement<LabelLook> selectedItemLook =
	new SubElement<>(SELECTED_ITEM_LOOK_HEADER, new LabelLook());
	
	//optional attribute
	private IElementTaker<ItemMenuItem> selectAction;
	
	//method
	public final IM addEmtyItem() {
		return addItem(new ItemMenuItem());
	}
	
	//method
	public final IM addItem(final String item) {
		return addItem(new ItemMenuItem(item));
	}
	
	//method
	public final IM addItem(final String... items) {
		return addItems(ReadContainer.forArray(items));
	}
	
	//method
	public final IM addItem(final String item, final IElementTaker<ItemMenuItem> selectAction) {
		return addItem(new ItemMenuItem(item, selectAction));
	}
	
	//method
	public final IM addItem(final String id, final String item) {
		return addItem(new ItemMenuItem(id, item));
	}
	
	//method
	public final IM addItem(final String id, final String item, final IElementTaker<ItemMenuItem> selectAction) {
		return addItem(new ItemMenuItem(id, item, selectAction));
	}
	
	//method
	public final IM addItem(final ItemMenuItem item) {
		
		if (item.isEmptyItem()) {
			assertDoesNotContainEmptyItem();
		}
		
		assertDoesNotContainItemWithText(item.getText());
		
		if (item.hasId()) {
			assertDoesNotContainItemWithId(item.getId());
		}
		
		item.setParentMenu(this);
		items.add(item);
		noteAddItem(item);
		
		return asConcrete();
	}
	
	//method
	public final IM addItems(final Iterable<String> items) {
		
		items.forEach(this::addItem);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final void clear() {
		items.clear();
		noteClear();
	}
	
	//method
	public final boolean containsEmptyItem() {
		return getRefItems().contains(ItemMenuItem::isEmptyItem);
	}
	
	//method
	public final boolean containsItem(final String text) {
		return getRefItems().contains(i -> i.hasText(text));
	}
	
	//method
	public final boolean containsItemWithId(final String id) {
		return getRefItems().contains(i -> i.hasId(id));
	}
	
	//method
	public final boolean containsSelectedItem() {
		return getRefItems().contains(ItemMenuItem::isSelected);
	}
	
	//method
	public final boolean emptyItemIsSelected() {
		return (containsSelectedItem() && getRefSelectedItem().getText().isEmpty());
	}
	
	//method
	public final int getIndexOfEmptyItem() {
		return getRefItems().getIndexOf(getRefEmptyItem());
	}
	
	//method
	public final int getIndexOfSelectedItem() {
		return getRefItems().getIndexOf(getRefSelectedItem());
	}
	
	//method
	public final ItemMenuItem getRefFirstItem() {
		return getRefItems().getRefFirst();
	}
	
	//method
	public final LabelLook getRefItemLook() {
		return itemLook.getSubElement();
	}
	
	//method
	public final IContainer<ItemMenuItem> getRefItems() {
		return items;
	}
	
	//method
	public final ItemMenuItem getRefSelectedItem() {
		return getRefItems().getRefFirst(ItemMenuItem::isSelected);
	}
	
	//method
	public final LabelLook getRefSelectedItemLook() {
		return selectedItemLook.getSubElement();
	}
	
	//method
	@Override
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	public final boolean hasSelectAction() {
		return (selectAction != null);
	}
	
	//method
	public String getSelectedItemId() {
		return getRefSelectedItem().getId();
	}
	
	//method
	public String getSelectedItemText() {
		return getRefSelectedItem().getText();
	}
	
	//method
	@Override
	public final boolean isEmpty() {
		return items.isEmpty();
	}
	
	//method
	public final IM onItemLook(final IElementTaker<LabelLook> itemLookMutator) {
		
		itemLookMutator.run(getRefItemLook());
		
		return asConcrete();
	}
	
	//method
	public final IM onSelectedItemLook(final IElementTaker<LabelLook> selectedItemLookMutator) {
		
		selectedItemLookMutator.run(getRefSelectedItemLook());
		
		return asConcrete();
	}
	
	//method
	public final void removeSelectAction() {
		selectAction = null;
	}
	
	//method
	public final void selectEmptyItem() {
		getRefEmptyItem().select();
	}
	
	//method
	public final void selectFirstItem() {
		getRefFirstItem().select();
	}
	
	//method
	public final void selectItem(final String item) {
		getRefItems().getRefFirst(i -> i.hasText(item)).select();
	}
		
	//method
	public final void selectItemById(final String id) {
		getRefItems().getRefFirst(i -> i.hasId(id)).select();
	}
	
	//method
	public final IM setSelectAction(final IAction selectAction) {
		
		Validator.assertThat(selectAction).thatIsNamed("select action").isNotNull();
		
		return setSelectAction(i -> selectAction.run());
	}
	
	//method
	public final IM setSelectAction(final IElementTaker<ItemMenuItem> selectAction) {
		
		Validator.assertThat(selectAction).thatIsNamed("select action").isNotNull();
		
		this.selectAction = selectAction;
		
		return asConcrete();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public final IM unselectAllItems() {
		
		getRefItems().forEach(ItemMenuItem::unselect);
		
		return asConcrete();
	}
	
	//method
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
	}
	
	//method
	@Override
	protected final ItemMenuLook createLook() {
		return new ItemMenuLook();
	}
	
	//method
	@Override
	protected final void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	protected final IContainer<Label> getRefItemLabels() {
		return getRefItems().to(ItemMenuItem::getRefLabel);
	}
	
	//method declaration
	protected abstract void noteAddItem(final ItemMenuItem item);
	
	//method declaration
	protected abstract void noteClear();
	
	//method
	@Override
	protected final void noteKeyPressOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected final void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected final void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected final void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected final void noteMouseWheelClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected final void noteMouseWheelPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected final void noteMouseWheelReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected final void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(
		final RotationDirection rotationDirection
	) {}
		
	//method
	@Override
	protected final void noteRightMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected final void noteRightMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected final void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method declaration
	protected abstract void noteSelectItem2(ItemMenuItem item);
	
	//method
	@Override
	protected final void recalculateBorderWidget() {
		
		getRefItems().forEach(ItemMenuItem::recalculate);
		
		recalculateSizes();
		
		recalculateItemMenu();
	}
	
	//method declaration
	protected abstract void recalculateItemMenu();
	
	//method
	@Override
	protected final void resetBorderWidget() {
		clear();
		removeSelectAction();
	}
	
	//method
	@Override
	protected final void resetBorderWidgetConfiguration() {
		getRefItemLook().reset();
		getRefSelectedItemLook().reset();
	}
	
	//method
	final void noteSelectItem(final ItemMenuItem item) {
		
		for (final var i : getRefItems()) {
			if (i != item && i.isSelected()) {
				i.unselect();
			}
		}
		
		runOptionalSelectActionFor(item);
		
		noteSelectItem2(item);
	}
	
	//method
	private void assertDoesNotContainEmptyItem() {
		if (containsEmptyItem()) {
			throw new ArgumentHasAttributeException(this, "empty item");
		}
	}
		
	//method
	private void assertDoesNotContainItemWithText(final String text) {
		if (containsItem(text)) {
			throw new InvalidArgumentException(this, "contains already an item with the text '" + text + "'");
		}
	}
	
	//method
	private void assertDoesNotContainItemWithId(final String id) {
		if (containsItemWithId(id)) {
			throw new InvalidArgumentException(this, "contains already an item with the id '" + id + "'");
		}
	}
	
	//method
	private ItemMenuItem getRefEmptyItem() {
		return getRefItems().getRefFirst(ItemMenuItem::isEmptyItem);
	}
	
	//method
	private void recalculateSizes() {
		final var itemLabels = getRefItemLabels();
		final var itemWidth = Calculator.getMax(10, itemLabels.getMaxIntOrDefaultValue(Label::getNaturalWidth, 10));
		for (final var il : itemLabels) {
			il.setProposalWidth(itemWidth);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void runOptionalSelectActionFor(final ItemMenuItem item) {
		if (selectAction != null) {
			selectAction.run(item);
		}
	}
}
