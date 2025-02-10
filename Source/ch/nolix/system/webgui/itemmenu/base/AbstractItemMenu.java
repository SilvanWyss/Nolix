package ch.nolix.system.webgui.itemmenu.base;

import java.util.function.Consumer;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;
import ch.nolix.system.element.property.MultiValue;
import ch.nolix.system.webgui.main.Control;
import ch.nolix.systemapi.guiapi.guiproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenuItem;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenuSearcher;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenuStyle;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenuValidator;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

public abstract class AbstractItemMenu<M extends IItemMenu<M, S>, S extends IItemMenuStyle<S>>
extends Control<M, S> implements IItemMenu<M, S> {

  private static final String ITEM_HEADER = PascalCaseVariableCatalog.ITEM;

  private static final IItemMenuSearcher ITEM_MENU_SEARCHER = new ItemMenuSearcher();

  private static final IItemMenuValidator ITEM_MENU_VALIDATOR = new ItemMenuValidator();

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

      ITEM_MENU_VALIDATOR.assertCanAddItem(this, i);

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
    return //
    containsBlankItem()
    && ITEM_MENU_SEARCHER.getStoredBlankItem(this).isSelected();
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
  public final boolean containsSelectedItem() {
    return getStoredItems().containsAny(IItemMenuItem::isSelected);
  }

  @Override
  public final String getIdByItemText(final String itemText) {
    return ITEM_MENU_SEARCHER.getStoredItemByText(this, itemText).getId();
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
    return ITEM_MENU_SEARCHER.getStoredItemById(this, itemId).getText();
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
  public final void runHtmlEvent(final String htmlEvent) {
    GlobalValidator.assertThat(htmlEvent).thatIsNamed("HTML event").isEqualTo("onchange");
  }

  @Override
  public final M selectBlankItem() {

    final var blankItem = ITEM_MENU_SEARCHER.getStoredBlankItem(this);

    blankItem.select();

    return asConcrete();
  }

  @Override
  public final M selectFirstItem() {

    final var firstItem = ITEM_MENU_SEARCHER.getStoredFirstItem(this);

    firstItem.select();

    return asConcrete();
  }

  @Override
  public final M selectItemById(final String id) {

    final var item = ITEM_MENU_SEARCHER.getStoredItemById(this, id);

    item.select();

    return asConcrete();
  }

  @Override
  public final M selectItemByText(final String text) {

    final var item = ITEM_MENU_SEARCHER.getStoredItemByText(this, text);

    item.select();

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
      selectItemByText(userInput);
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

  private boolean hasSelectAction() {
    return (selectAction != null);
  }
}
