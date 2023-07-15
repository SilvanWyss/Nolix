//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.system.element.mutableelement.MultiValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ItemMenu<
	IM extends IItemMenu<IM, IMS>,
	IMS extends IItemMenuStyle<IMS>
>
extends Control<IM, IMS> implements IItemMenu<IM, IMS> {
	
	//constant
	private static final String ITEM_HEADER = PascalCaseCatalogue.ITEM;
	
	//attribute
	private final MultiValue<IItemMenuItem<?>> items =
	new MultiValue<>(
		ITEM_HEADER,
		this::addItem,
		ItemMenuItem::fromSpecification,
		IItemMenuItem::getSpecification
	);
	
	//optional attribute
	private IElementTaker<IItemMenuItem<?>> selectAction;
	
	//method
	@Override
	public final IM addBlankItem() {
		return addItem(ItemMenuItem.createBlankItem());
	}
	
	//method
	@Override
	public final IM addItem(final IItemMenuItem<?>... items) {
		
		for (final var i : items) {
			
			assertCanAddItem(i);
			
			i.technicalSetParentMenu(this);
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
	public final boolean blankItemIsSelected() {
		return (containsBlankItem() && getStoredBlankItem().isSelected());
	}
	
	//method
	@Override
	public final void clear() {
		items.clear();
	}
	
	//method
	@Override
	public final boolean containsBlankItem() {
		return getStoredItems().containsAny(IItemMenuItem::isBlank);
	}
	
	//method
	@Override
	public final boolean containsItemWithId(final String id) {
		return getStoredItems().containsAny(i -> i.hasId(id));
	}
	
	//method
	@Override
	public final boolean containsItemWithText(final String text) {
		return getStoredItems().containsAny(i -> i.getText().equals(text));
	}
	
	//method
	@Override
	public boolean containsSelectedItem() {
		return getStoredItems().containsAny(IItemMenuItem::isSelected);
	}
	
	//method
	@Override
	public final String getIdByItemText(final String itemText) {
		return getStoredItemByText(itemText).getId();
	}
	
	//method
	@Override
	public final IContainer<IControl<?, ?>> getStoredChildControls() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public final IContainer<IItemMenuItem<?>> getStoredItems() {
		return items.getStoredValues();
	}
	
	//method
	@Override
	public final IItemMenuItem<?> getStoredSelectedItem() {
		return getStoredItems().getStoredFirst(IItemMenuItem::isSelected);
	}
	
	//method
	@Override
	public final String getTextByItemId(final String itemId) {
		return getStoredItemById(itemId).getText();
	}
	
	//method
	@Override
	public final String getUserInput() {
		
		if (isEmpty()) {
			return StringCatalogue.EMPTY_STRING;
		}
		
		return getStoredItems().getStoredFirst(IItemMenuItem::isSelected).getText();
	}
	
	//method
	@Override
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public final boolean isEmpty() {
		return getStoredItems().isEmpty();
	}
	
	//method
	@Override
	public final void removeSelectAction() {
		selectAction = null;
	}
	
	//method
	@Override
	public void runHtmlEvent(final String htmlEvent) {
		GlobalValidator.assertThat(htmlEvent).thatIsNamed("HTML event").isEqualTo("onchange");
	}
	
	//method
	@Override
	public final IM selectBlankItem() {
		
		getStoredBlankItem().select();
				
		return asConcrete();
	}
	
	//method
	@Override
	public final IM selectFirstItem() {
		
		getStoredFirstItem().select();
		
		return asConcrete();
	}
	
	//method
	@Override
	public final IM selectItemById(final String id) {
		
		getStoredItemById(id).select();
		
		return asConcrete();
	}
	
	//method
	@Override
	public final IM selectItemByText(final String text) {
		
		getStoredItemByText(text).select();
		
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
	public final IM setUserInput(final String userInput) {
		
		if (userInput.isEmpty()) {
			getStoredItems().forEach(IItemMenuItem::unselect);
		} else {
			getStoredItemByText(userInput).select();
		}
		
		return asConcrete();
	}
	
	//method
	@Override
	public final void technicalRunOptionalSelectActionForItem(final IItemMenuItem<?> item) {
		if (hasSelectAction()) {
			selectAction.run(item);
		}
	}
	
	//method
	@Override
	protected final void resetControl() {
		
		clear();
		removeSelectAction();
		
		setCursorIcon(CursorIcon.HAND);
	}
	
	//method
	private void assertCanAddItem(final IItemMenuItem<?> item) {
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
	private IItemMenuItem<?> getStoredBlankItem() {
		return getStoredItems().getStoredFirst(IItemMenuItem::isBlank);
	}
	
	//method
	private IItemMenuItem<?> getStoredFirstItem() {
		return getStoredItems().getStoredFirst();
	}
	
	//method
	private IItemMenuItem<?> getStoredItemById(final String itemId) {
		return getStoredItems().getStoredFirst(i -> i.hasId(itemId));
	}
	
	//method
	private IItemMenuItem<?> getStoredItemByText(final String itemText) {
		return getStoredItems().getStoredFirst(i -> i.getText().equals(itemText));
	}
	
	//method
	private boolean hasSelectAction() {
		return (selectAction != null);
	}
}
