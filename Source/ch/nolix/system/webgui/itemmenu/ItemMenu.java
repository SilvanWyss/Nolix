//package declaration
package ch.nolix.system.webgui.itemmenu;

import ch.nolix.core.commontype.constant.StringCatalogue;
//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.system.element.mutableelement.MultiValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ItemMenu<
	IM extends ItemMenu<IM, IMS>,
	IMS extends IItemMenuStyle<IMS>
>
extends Control<IM, IMS> implements IItemMenu<IM, IMS, ItemMenuItem> {
	
	//constant
	private static final String ITEM_HEADER = PascalCaseCatalogue.ITEM;
	
	//attribute
	private final MultiValue<ItemMenuItem> items =
	new MultiValue<>(
		ITEM_HEADER,
		this::addItem,
		ItemMenuItem::fromSpecification,
		ItemMenuItem::getSpecification
	);
	
	//optional attribute
	private IElementTaker<IItemMenuItem<?>> selectAction;
	
	//method
	@Override
	public final IM addBlankItem() {
		//TODO: Implement.
		return asConcrete();
	}
	
	//method
	@Override
	public final IM addItem(final ItemMenuItem... items) {
		
		for (final var i : items) {
			
			assertCanAddItem(i);
			
			i.internalSetParentMenu(this);
			this.items.add(i);
		}
		
		return asConcrete();
	}
	
	//method
	@Override
	public final IM addItemWithIdAndText(final String id, final String text) {
		return addItem(ItemMenuItem.withIdAndText(id, text));
	}
	
	//method
	@Override
	public final IM addItemWithIdAndTextAndSelectAction(final String id, String text, final IAction selectAction) {
		return addItem(ItemMenuItem.withIdAndTextAndSelectAction(id, text, selectAction));
	}
	
	//method
	@Override
	public final IM addItemWithIdAndTextAndSelectAction(
		final String id,
		final String text,
		final IElementTaker<IItemMenuItem<?>> selectAction
	) {
		return addItem(ItemMenuItem.withIdAndTextAndSelectAction(id, text, selectAction));
	}
	
	//method
	@Override
	public final IM addItemWithText(final String... texts) {
		
		for (final var t : texts) {
			addItem(ItemMenuItem.withText(t));
		}
		
		return asConcrete();
	}
	
	//method
	@Override
	public final IM addItemWithTextAndSelectAction(final String text, final IAction selectAction) {
		return addItem(ItemMenuItem.withTextAndSelectAction(text, selectAction));
	}
	
	//method
	@Override
	public final IM addItemWithTextAndSelectAction(
		final String text,
		final IElementTaker<IItemMenuItem<?>> selectAction
	) {
		return addItem(ItemMenuItem.withTextAndSelectAction(text, selectAction));
	}

	//method
	@Override
	public final void clear() {
		items.clear();
	}
	
	//method
	@Override
	public final boolean containsBlankItem() {
		return getRefItems().containsAny(IItemMenuItem::isBlank);
	}
	
	//method
	@Override
	public final boolean containsItemWithId(final String id) {
		return getRefItems().containsAny(i -> i.hasId(id));
	}
	
	//method
	@Override
	public final boolean containsItemWithText(final String text) {
		return getRefItems().containsAny(i -> i.getText().equals(text));
	}
	
	//method
	@Override
	public final String getIdByItemText(final String itemText) {
		return getRefItemByItemText(itemText).getId();
	}
	
	//method
	@Override
	public final IContainer<IControl<?, ?>> getRefChildControls() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public final IContainer<ItemMenuItem> getRefItems() {
		return items.getRefValues();
	}
	
	//method
	@Override
	public final String getTextByItemId(final String itemId) {
		return getRefItemByItemId(itemId).getText();
	}
	
	//method
	@Override
	public String getUserInput() {
		
		if (isEmpty()) {
			return StringCatalogue.EMPTY_STRING;
		}
		
		return getRefItems().getRefFirst(IItemMenuItem::isSelected).getText();
	}
	
	//method
	@Override
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public final boolean hasSelectAction() {
		return (selectAction != null);
	}
	
	//method
	@Override
	public final boolean isEmpty() {
		return getRefItems().isEmpty();
	}
	
	//method
	@Override
	public final void noteKeyPress(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteKeyRelease(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteKeyTyping(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteLeftMouseButtonRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteMouseWheelPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteMouseWheelRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteRightMouseButtonPress() {
		//Does nothing.
	}
	
	//method
	@Override
	public final void noteRightMouseButtonRelease() {
		//Does nothing.
	}
	
	//method
	@Override
	public void removeSelectAction() {
		selectAction = null;
	}
	
	//method
	@Override
	public final IM selectFirstItem() {
		
		getRefFirstItem().select();
		
		return asConcrete();
	}
	
	//method
	@Override
	public final IM setSelectAction(final IAction selectAction) {
		
		GlobalValidator.assertThat(selectAction).thatIsNamed("select action").isNotNull();
		
		return setSelectAction(i -> selectAction.run());
	}
	
	//method
	@Override
	public final IM setSelectAction(final IElementTaker<IItemMenuItem<?>> selectAction) {
		
		GlobalValidator.assertThat(selectAction).thatIsNamed("select action").isNotNull();
		
		this.selectAction = selectAction;
		
		return asConcrete();
	}
	
	//method
	@Override
	public IM setUserInput(final String userInput) {
		
		if (userInput.isEmpty()) {
			getRefItems().forEach(IItemMenuItem::unselect);
		} else {
			getRefItemByItemText(userInput).select();
		}
		
		return asConcrete();
	}
	
	//method
	@Override
	protected final void resetControl() {
		
		clear();
		
		removeSelectAction();
	}
	
	//method
	final void internalRunOptionalSelectActionForItem(final ItemMenuItem item) {
		if (hasSelectAction()) {
			selectAction.run(item);
		}
	}
	
	//method
	private void assertCanAddItem(final ItemMenuItem item) {
		assertDoesNotContainItemWithId(item.getId());
		assertDoesNotContainItemWithText(item.getText());
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
	private void assertDoesNotContainItemWithText(final String text) {
		if (containsItemWithText(text)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,
				"contains already an item with the text '" + text + "'"
			);
		}
	}
	
	//method
	private ItemMenuItem getRefFirstItem() {
		return getRefItems().getRefFirst();
	}
	
	//method
	private ItemMenuItem getRefItemByItemId(final String itemId) {
		return getRefItems().getRefFirst(i -> i.hasId(itemId));
	}
	
	//method
	private ItemMenuItem getRefItemByItemText(final String itemText) {
		return getRefItems().getRefFirst(i -> i.getText().equals(itemText));
	}
}
