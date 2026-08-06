/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.itemmenu;

import java.util.function.Consumer;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.system.property.value.MultiValue;
import ch.nolix.system.webgui.main.AbstractControl;
import ch.nolix.systemapi.control.itemmenu.IItemMenu;
import ch.nolix.systemapi.control.itemmenu.IItemMenuItem;
import ch.nolix.systemapi.control.itemmenu.IItemMenuStyle;
import ch.nolix.systemapi.gui.model.CursorIcon;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 * @param <M> the type of a {@link AbstractItemMenu}.
 * @param <S> the type of the {@link IItemMenuStyle}s of a
 *            {@link AbstractItemMenu}.
 */
public abstract class AbstractItemMenu // NOSONAR: A AbstractItemMenu is a principal object thus it has many methods.
<M extends IItemMenu<M, S>, S extends IItemMenuStyle<S>>
extends AbstractControl<M, S> implements IItemMenu<M, S> {
  private static final String ITEM_HEADER = PascalCaseVariableNameCatalog.ITEM;

  private static final ItemMenuSearcher ITEM_MENU_SEARCHER = new ItemMenuSearcher();

  private static final ItemMenuValidator ITEM_MENU_VALIDATOR = new ItemMenuValidator();

  private final MultiValue<IItemMenuItem<?>> memberItems = //
  MultiValue.forElementsOfSameTypeWithNameAndAdderAndValueMapper(
    ITEM_HEADER,
    this::addItem,
    ItemMenuItem::fromSpecification);

  private Consumer<IItemMenuItem<?>> memberSelectAction;

  /**
   * {@inheritDoc}
   */
  @Override
  public final M addBlankItem() {
    return addItem(ItemMenuItem.createBlankItem());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M addItem(final IItemMenuItem<?> item) {
    ITEM_MENU_VALIDATOR.assertCanAddItem(this, item);

    item.internalSetParentMenu(this);
    memberItems.addValue(item);

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M addItem(final String item) {
    final var itemMenuItem = ItemMenuItem.withText(item);

    return addItem(itemMenuItem);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M addItems(final IItemMenuItem<?>... items) {
    for (final var i : items) {
      addItem(i);
    }

    return asConcrete();
  }

  @Override
  public M addItems(final String... items) {
    for (final var i : items) {
      addItem(i);
    }

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M addItemWithIdAndText(final String id, final String text) {
    return addItem(ItemMenuItem.withIdAndText(id, text));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M addItemWithIdAndTextAndSelectAction(final String id, String text, final Runnable selectAction) {
    return addItem(ItemMenuItem.withIdAndTextAndSelectAction(id, text, selectAction));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M addItemWithIdAndTextAndSelectAction(
    final String id,
    final String text,
    final Consumer<IItemMenuItem<?>> selectAction) {
    return addItem(ItemMenuItem.withIdAndTextAndSelectAction(id, text, selectAction));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M addItemWithTextAndSelectAction(final String text, final Runnable selectAction) {
    return addItem(ItemMenuItem.withTextAndSelectAction(text, selectAction));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M addItemWithTextAndSelectAction(
    final String text,
    final Consumer<IItemMenuItem<?>> selectAction) {
    return addItem(ItemMenuItem.withTextAndSelectAction(text, selectAction));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean blankItemIsSelected() {
    return //
    containsBlankItem()
    && ITEM_MENU_SEARCHER.getStoredBlankItem(this).isSelected();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void clear() {
    memberItems.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsBlankItem() {
    return getStoredItems().containsMatching(IItemMenuItem::isBlank);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsItemWithId(final String id) {
    return getStoredItems().containsMatching(i -> i.hasId(id));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsItemWithText(final String text) {
    return getStoredItems().containsMatching(i -> i.getText().equals(text));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsSelectedItem() {
    return getStoredItems().containsMatching(IItemMenuItem::isSelected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getIdByItemText(final String itemText) {
    return ITEM_MENU_SEARCHER.getStoredItemByText(this, itemText).getId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<Control<?, ?>> getStoredChildControls() {
    return ImmutableList.createEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<IItemMenuItem<?>> getStoredItems() {
    return memberItems.getStoredValues();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IItemMenuItem<?> getStoredSelectedItem() {
    return getStoredItems().getStoredFirst(IItemMenuItem::isSelected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getTextByItemId(final String itemId) {
    return ITEM_MENU_SEARCHER.getStoredItemById(this, itemId).getText();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getUserInput() {
    if (isEmpty()) {
      return StringCatalog.EMPTY_STRING;
    }

    return getStoredItems().getStoredFirst(IItemMenuItem::isSelected).getText();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasRole(final String role) {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isEmpty() {
    return getStoredItems().isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeSelectAction() {
    memberSelectAction = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void runHtmlEvent(final String htmlEvent) {
    Validator.assertThat(htmlEvent).thatIsNamed("HTML event").isEqualTo("onchange");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M selectBlankItem() {
    final var blankItem = ITEM_MENU_SEARCHER.getStoredBlankItem(this);

    blankItem.select();

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M selectFirstItem() {
    final var firstItem = ITEM_MENU_SEARCHER.getStoredFirstItem(this);

    firstItem.select();

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M selectItemById(final String id) {
    final var item = ITEM_MENU_SEARCHER.getStoredItemById(this, id);

    item.select();

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M selectItemByText(final String text) {
    final var item = ITEM_MENU_SEARCHER.getStoredItemByText(this, text);

    item.select();

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unused")
  public final M setSelectAction(final Runnable selectAction) {
    Validator.assertThat(selectAction).thatIsNamed("select action").isNotNull();

    return setSelectAction(i -> selectAction.run());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M setSelectAction(final Consumer<IItemMenuItem<?>> selectAction) {
    Validator.assertThat(selectAction).thatIsNamed("select action").isNotNull();

    memberSelectAction = selectAction;

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final M setUserInput(final String userInput) {
    if (userInput.isEmpty()) {
      getStoredItems().forEach(IItemMenuItem::unselect);
    } else {
      selectItemByText(userInput);
    }

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void internalRunOptionalSelectActionForItem(final IItemMenuItem<?> item) {
    if (hasSelectAction()) {
      memberSelectAction.accept(item);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void resetControl() {
    clear();
    removeSelectAction();

    setCursorIcon(CursorIcon.HAND);
  }

  private boolean hasSelectAction() {
    return (memberSelectAction != null);
  }
}
