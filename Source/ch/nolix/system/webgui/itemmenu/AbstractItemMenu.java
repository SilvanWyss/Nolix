package ch.nolix.system.webgui.itemmenu;

import java.util.function.Consumer;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;
import ch.nolix.system.element.property.MultiValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.guiproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public abstract class AbstractItemMenu<M extends IItemMenu<M, S>, S extends IItemMenuStyle<S>>
extends Control<M, S> implements IItemMenu<M, S> {

  private static final String ITEM_HEADER = PascalCaseVariableCatalog.ITEM;

  private final MultiValue<IItemMenuItem<?>> items = new MultiValue<>(
    ITEM_HEADER,
    this::addItem,
    ItemMenuItem::fromSpecification,
    IItemMenuItem::getSpecification);

  private Consumer<IItemMenuItem<?>> selectAction;

  @Override
  public final M addBlankItem() {
    return addItem(ItemMenuItem.createBlankItem());
  }

  @Override
  public final M addItem(IItemMenuItem<?> item, IItemMenuItem<?>... items) {

    final var allItems = ContainerView.forElementAndArray(item, items);

    for (final var i : allItems) {

      assertCanAddItem(i);

      i.internalSetParentMenu(this);
      this.items.add(i);
    }

    return asConcrete();
  }

  @Override
  public final M addItemWithIdAndText(final String id, final String text) {
    return addItem(ItemMenuItem.withIdAndText(id, text));
  }

  @Override
  public final M addItemWithIdAndTextAndSelectAction(final String id, String text, final Runnable selectAction) {
    return addItem(ItemMenuItem.withIdAndTextAndSelectAction(id, text, selectAction));
  }

  @Override
  public final M addItemWithIdAndTextAndSelectAction(
    final String id,
    final String text,
    final Consumer<IItemMenuItem<?>> selectAction) {
    return addItem(ItemMenuItem.withIdAndTextAndSelectAction(id, text, selectAction));
  }

  @Override
  public final M addItemWithText(final String text, final String... texts) {

    final var allTexts = ContainerView.forElementAndArray(text, texts);

    for (final var t : allTexts) {
      addItem(ItemMenuItem.withText(t));
    }

    return asConcrete();
  }

  @Override
  public final M addItemWithTextAndSelectAction(final String text, final Runnable selectAction) {
    return addItem(ItemMenuItem.withTextAndSelectAction(text, selectAction));
  }

  @Override
  public final M addItemWithTextAndSelectAction(
    final String text,
    final Consumer<IItemMenuItem<?>> selectAction) {
    return addItem(ItemMenuItem.withTextAndSelectAction(text, selectAction));
  }

  @Override
  public final boolean blankItemIsSelected() {
    return (containsBlankItem() && getStoredBlankItem().isSelected());
  }

  @Override
  public final void clear() {
    items.clear();
  }

  @Override
  public final boolean containsBlankItem() {
    return getStoredItems().containsAny(IItemMenuItem::isBlank);
  }

  @Override
  public final boolean containsItemWithId(final String id) {
    return getStoredItems().containsAny(i -> i.hasId(id));
  }

  @Override
  public final boolean containsItemWithText(final String text) {
    return getStoredItems().containsAny(i -> i.getText().equals(text));
  }

  @Override
  public boolean containsSelectedItem() {
    return getStoredItems().containsAny(IItemMenuItem::isSelected);
  }

  @Override
  public final String getIdByItemText(final String itemText) {
    return getStoredItemByText(itemText).getId();
  }

  @Override
  public final IContainer<IControl<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final IContainer<IItemMenuItem<?>> getStoredItems() {
    return items.getStoredValues();
  }

  @Override
  public final IItemMenuItem<?> getStoredSelectedItem() {
    return getStoredItems().getStoredFirst(IItemMenuItem::isSelected);
  }

  @Override
  public final String getTextByItemId(final String itemId) {
    return getStoredItemById(itemId).getText();
  }

  @Override
  public final String getUserInput() {

    if (isEmpty()) {
      return StringCatalog.EMPTY_STRING;
    }

    return getStoredItems().getStoredFirst(IItemMenuItem::isSelected).getText();
  }

  @Override
  public final boolean hasRole(final String role) {
    return false;
  }

  @Override
  public final boolean isEmpty() {
    return getStoredItems().isEmpty();
  }

  @Override
  public final void removeSelectAction() {
    selectAction = null;
  }

  @Override
  public void runHtmlEvent(final String htmlEvent) {
    GlobalValidator.assertThat(htmlEvent).thatIsNamed("HTML event").isEqualTo("onchange");
  }

  @Override
  public final M selectBlankItem() {

    getStoredBlankItem().select();

    return asConcrete();
  }

  @Override
  public final M selectFirstItem() {

    getStoredFirstItem().select();

    return asConcrete();
  }

  @Override
  public final M selectItemById(final String id) {

    getStoredItemById(id).select();

    return asConcrete();
  }

  @Override
  public final M selectItemByText(final String text) {

    getStoredItemByText(text).select();

    return asConcrete();
  }

  @Override
  public final M setSelectAction(final Runnable selectAction) {

    GlobalValidator.assertThat(selectAction).thatIsNamed("select action").isNotNull();

    return setSelectAction(i -> selectAction.run());
  }

  @Override
  public final M setSelectAction(final Consumer<IItemMenuItem<?>> selectAction) {

    GlobalValidator.assertThat(selectAction).thatIsNamed("select action").isNotNull();

    this.selectAction = selectAction;

    return asConcrete();
  }

  @Override
  public final M setUserInput(final String userInput) {

    if (userInput.isEmpty()) {
      getStoredItems().forEach(IItemMenuItem::unselect);
    } else {
      getStoredItemByText(userInput).select();
    }

    return asConcrete();
  }

  @Override
  public final void internalRunOptionalSelectActionForItem(final IItemMenuItem<?> item) {
    if (hasSelectAction()) {
      selectAction.accept(item);
    }
  }

  @Override
  protected final void resetControl() {

    clear();
    removeSelectAction();

    setCursorIcon(CursorIcon.HAND);
  }

  private void assertCanAddItem(final IItemMenuItem<?> item) {
    assertDoesNotContainItemWithId(item.getId());
    assertDoesNotContainItemWithText(item.getText());
  }

  private void assertDoesNotContainItemWithId(final String id) {
    if (containsItemWithId(id)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains already an item with the id '" + id + "'");
    }
  }

  private void assertDoesNotContainItemWithText(final String text) {
    if (containsItemWithText(text)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains already an item with the text '" + text + "'");
    }
  }

  private IItemMenuItem<?> getStoredBlankItem() {
    return getStoredItems().getStoredFirst(IItemMenuItem::isBlank);
  }

  private IItemMenuItem<?> getStoredFirstItem() {
    return getStoredItems().getStoredFirst();
  }

  private IItemMenuItem<?> getStoredItemById(final String itemId) {
    return getStoredItems().getStoredFirst(i -> i.hasId(itemId));
  }

  private IItemMenuItem<?> getStoredItemByText(final String itemText) {
    return getStoredItems().getStoredFirst(i -> i.getText().equals(itemText));
  }

  private boolean hasSelectAction() {
    return (selectAction != null);
  }
}
