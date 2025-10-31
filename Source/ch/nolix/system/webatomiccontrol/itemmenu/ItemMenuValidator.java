package ch.nolix.system.webatomiccontrol.itemmenu;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenu;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuExaminer;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuItem;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuValidator;

/**
 * @author Silvan Wyss
 * @version 2025-02-09
 */
public final class ItemMenuValidator implements IItemMenuValidator {
  private static final IItemMenuExaminer ITEM_MENU_EXAMINER = new ItemMenuExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public void assertCanAddItem(IItemMenu<?, ?> itemMenu, IItemMenuItem<?> item) {
    if (!ITEM_MENU_EXAMINER.canAddItem(itemMenu, item)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        itemMenu,
        "item menu",
        "cannot add the given item '" + item + "'");
    }
  }
}
