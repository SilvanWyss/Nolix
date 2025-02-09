package ch.nolix.system.webgui.itemmenu;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuExaminer;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuValidator;

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
      InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        "item menu",
        itemMenu,
        "cannot add the given item '" + item + "'");
    }
  }
}
