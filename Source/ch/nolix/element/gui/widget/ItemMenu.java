//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.base.MultiValue;
import ch.nolix.element.base.SubElement;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.input.Key;

//class
public abstract class ItemMenu<TIM extends ItemMenu<TIM>> extends BorderWidget<TIM, ItemMenuLook>
implements Clearable {
	
	//constant
	private static final String ITEM_HEADER = "Item";
	
	//attribute
	private final MultiValue<ItemMenuItem> items =
	new MultiValue<>(
		PascalCaseCatalogue.ITEM,
		this::addItem,
		ItemMenuItem::fromSpecification,
		ItemMenuItem::getSpecification
	);
	
	//attribute
	private final SubElement<LabelLook> itemLook = new SubElement<>(ITEM_HEADER, new LabelLook());
	
	//optional attribute
	private IElementTaker<ItemMenuItem> selectAction;
	
	//method
	public final TIM addEmtyItem() {
		return addItem(StringCatalogue.EMPTY_STRING);
	}
	
	//method
	public final TIM addItem(final String item) {
		return addItem(new ItemMenuItem(item));
	}
	
	//method
	public final TIM addItem(final String... items) {
		return addItems(ReadContainer.forArray(items));
	}
	
	//method
	public final TIM addItem(final String item, final IElementTaker<ItemMenu<?>> selectAction) {
		return addItem(new ItemMenuItem(item, selectAction));
	}
	
	//method
	public final TIM addItem(final String id, final String item) {
		return addItem(new ItemMenuItem(id, item));
	}
	
	//method
	public final TIM addItem(final String id, final String item, final IElementTaker<ItemMenu<?>> selectAction) {
		return addItem(new ItemMenuItem(id, item, selectAction));
	}
	
	//method
	public final TIM addItem(final ItemMenuItem item) {
		
		Validator.assertThat(item).thatIsNamed(LowerCaseCatalogue.ITEM).isNotNull();
		
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
	public final TIM addItems(final Iterable<String> items) {
		
		items.forEach(this::addItem);
		
		return asConcrete();
	}
	
	//method
	@Override
	public final void clear() {
		unselectItems();
		items.clear();
		noteClear();
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
		
		assertContainsSelectedItem();
		
		return getRefItems().getRefFirst(ItemMenuItem::isSelected);
	}
	
	//method
	public final boolean hasSelectAction() {
		return (selectAction != null);
	}
	
	//method
	@Override
	public final boolean isEmpty() {
		return items.isEmpty();
	}
	
	//method
	public final void removeSelectAction() {
		selectAction = null;
	}
	
	//method
	public final void selectEmptyItem() {
		selectItem(StringCatalogue.EMPTY_STRING);
	}
	
	//method
	public final void selectFirstItem() {
		selectItem(getRefFirstItem());
	}
	
	//method
	public final void selectItem(final String item) {
		selectItem(getRefItems().getRefFirst(i -> i.hasText(item)));
	}
		
	//method
	public final void selectItemById(final String id) {
		selectItem(getRefItems().getRefFirst(i -> i.hasId(id)));
	}
	
	//method
	public final TIM setSelectAction(final IAction selectAction) {
		
		Validator.assertThat(selectAction).thatIsNamed("select action").isNotNull();
		
		return setSelectAction(i -> selectAction.run());
	}
	
	//method
	public final TIM setSelectAction(final IElementTaker<ItemMenuItem> selectAction) {
		
		Validator.assertThat(selectAction).thatIsNamed("select action").isNotNull();
		
		this.selectAction = selectAction;
		
		return asConcrete();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public final TIM unselectItems() {
		
		final var selectedItem = items.getRefFirstOrNull(ItemMenuItem::isSelected);
		
		if (selectedItem != null) {
			selectedItem.unselect();
		}
		
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
	protected final IContainer<Label> getRefItemLables() {
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
	protected abstract void noteSelectItem(ItemMenuItem item);
	
	//method
	@Override
	protected final void recalculateBorderWidget() {
		
		recalculateTextItemMenu();
		
		for (final var il : getRefItemLables()) {
			il.getRefLook().setFrom(getRefItemLook());
		}
	}
	
	//method declaration
	protected abstract void recalculateTextItemMenu();
	
	//method
	@Override
	protected final void resetBorderWidgetConfiguration() {}
	
	//method
	@Override
	protected final void resetBorderWidget() {
		clear();
		removeSelectAction();
	}
	
	//method
	protected final void selectItem(final ItemMenuItem item) {
				
		unselectItems();
		
		item.select();
		runProbableSelectActionFor(item);
		noteSelectItem(item);
	}
			
	//method
	private void assertContainsSelectedItem() {
		if (!containsSelectedItem()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "selected item");
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
	//For a better performance, this implementation does not use all comfortable methods.
	private void runProbableSelectActionFor(final ItemMenuItem item) {
		if (selectAction != null) {
			selectAction.run(item);
		}
	}
}
