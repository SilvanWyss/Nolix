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
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ItemMenu<
	IM extends ItemMenu<IM, IMS>,
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
		return (containsBlankItem() && getRefBlankItem().isSelected());
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
		return getRefItemByText(itemText).getId();
	}
	
	//method
	@Override
	public final IContainer<IControl<?, ?>> getRefChildControls() {
		return new ImmutableList<>();
	}
	
	//method
	@Override
	public final IContainer<IItemMenuItem<?>> getRefItems() {
		return items.getRefValues();
	}
	
	//method
	@Override
	public final IItemMenuItem<?> getRefSelectedItem() {
		return getRefItems().getRefFirst(IItemMenuItem::isSelected);
	}
	
	//method
	@Override
	public final String getTextByItemId(final String itemId) {
		return getRefItemById(itemId).getText();
	}
	
	//method
	@Override
	public final String getUserInput() {
		
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
	public final boolean isEmpty() {
		return getRefItems().isEmpty();
	}
	
	//method
	@Override
	public final void removeSelectAction() {
		selectAction = null;
	}
	
	//method
	@Override
	public void runHTMLEvent(final String pHTMLEvent) {
		GlobalValidator.assertThat(pHTMLEvent).thatIsNamed("HTML event").isEqualTo("onchange");
	}
	
	//method
	@Override
	public final IM selectBlankItem() {
		
		getRefBlankItem().select();
				
		return asConcrete();
	}
	
	//method
	@Override
	public final IM selectFirstItem() {
		
		getRefFirstItem().select();
		
		return asConcrete();
	}
	
	//method
	@Override
	public final IM selectItemById(final String id) {
		
		getRefItemById(id).select();
		
		return asConcrete();
	}
	
	//method
	@Override
	public final IM selectItemByText(final String text) {
		
		getRefItemByText(text);
		
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
			getRefItems().forEach(IItemMenuItem::unselect);
		} else {
			getRefItemByText(userInput).select();
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
	private IItemMenuItem<?> getRefBlankItem() {
		return getRefItems().getRefFirst(IItemMenuItem::isBlank);
	}
	
	//method
	private IItemMenuItem<?> getRefFirstItem() {
		return getRefItems().getRefFirst();
	}
	
	//method
	private IItemMenuItem<?> getRefItemById(final String itemId) {
		return getRefItems().getRefFirst(i -> i.hasId(itemId));
	}
	
	//method
	private IItemMenuItem<?> getRefItemByText(final String itemText) {
		return getRefItems().getRefFirst(i -> i.getText().equals(itemText));
	}
	
	//method
	private boolean hasSelectAction() {
		return (selectAction != null);
	}
}
