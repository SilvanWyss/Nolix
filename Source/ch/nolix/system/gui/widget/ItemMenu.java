//package declaration
package ch.nolix.system.gui.widget;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.GlobalCalculator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.core.skilluniversalapi.Clearable;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionuniversalapi.IAction;
import ch.nolix.coreapi.functionuniversalapi.IElementTaker;
import ch.nolix.system.element.MultiValue;
import ch.nolix.system.element.SubElement;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

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
	public final IM addItem(final String item, final IAction selectAction) {
		return addItem(new ItemMenuItem(item, selectAction));
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
		return getRefItems().containsAny(ItemMenuItem::isEmptyItem);
	}
	
	//method
	public final boolean containsItem(final String text) {
		return getRefItems().containsAny(i -> i.hasText(text));
	}
	
	//method
	public final boolean containsItemWithId(final String id) {
		return getRefItems().containsAny(i -> i.hasId(id));
	}
	
	//method
	public final boolean containsSelectedItem() {
		return getRefItems().containsAny(ItemMenuItem::isSelected);
	}
	
	//method
	public final boolean emptyItemIsSelected() {
		return (containsSelectedItem() && getRefSelectedItem().getText().isEmpty());
	}
	
	//method
	public final int getIndexOfEmptyItem() {
		return getRefItems().getIndexOfFirstOccurrenceOf(getRefEmptyItem());
	}
	
	//method
	public final int getIndexOfSelectedItem() {
		return getRefItems().getIndexOfFirstOccurrenceOf(getRefSelectedItem());
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
		return items.getRefValues();
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
	public final IM selectEmptyItem() {
		
		getRefEmptyItem().select();
		
		return asConcrete();
	}
	
	//method
	public final IM selectFirstItem() {
		
		getRefFirstItem().select();
		
		return asConcrete();
	}
	
	//method
	public final IM selectItem(final String item) {
		
		getRefItems().getRefFirst(i -> i.hasText(item)).select();
		
		return asConcrete();
	}
		
	//method
	public final IM selectItemById(final String id) {
		
		getRefItems().getRefFirst(i -> i.hasId(id)).select();
		
		return asConcrete();
	}
	
	//method
	public final IM setSelectAction(final IAction selectAction) {
		
		GlobalValidator.assertThat(selectAction).thatIsNamed("select action").isNotNull();
		
		return setSelectAction(i -> selectAction.run());
	}
	
	//method
	public final IM setSelectAction(final IElementTaker<ItemMenuItem> selectAction) {
		
		GlobalValidator.assertThat(selectAction).thatIsNamed("select action").isNotNull();
		
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
	protected final void fillUpChildWidgets(final LinkedList<IWidget<?, ?>> list) {}
	
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
	protected final void noteKeyDownOnSelfWhenFocused(final Key key) {}
	
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
		resetItemMenu();
	}
	
	//method
	@Override
	protected final void resetBorderWidgetConfiguration() {
		getRefItemLook().reset();
		getRefSelectedItemLook().reset();
	}
	
	//method declaration
	protected abstract void resetItemMenu();
	
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
			throw ArgumentHasAttributeException.forArgumentAndAttributeName(this, "empty item");
		}
	}
		
	//method
	private void assertDoesNotContainItemWithText(final String text) {
		if (containsItem(text)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,
				"contains already an item with the text '" + text + "'"
			);
		}
	}
	
	//method
	private void assertDoesNotContainItemWithId(final String id) {
		if (containsItemWithId(id)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,
				"contains already an item with the id '" + id + "'"
			);
		}
	}
	
	//method
	private ItemMenuItem getRefEmptyItem() {
		return getRefItems().getRefFirst(ItemMenuItem::isEmptyItem);
	}
	
	//method
	private void recalculateSizes() {
		final var itemLabels = getRefItemLabels();
		final var itemWidth = GlobalCalculator.getMax(10, itemLabels.getMaxIntOrDefaultValue(Label::getNaturalWidth, 10));
		for (final var il : itemLabels) {
			il.setMinWidth(itemWidth);
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
