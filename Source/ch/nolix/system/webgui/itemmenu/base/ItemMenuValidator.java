package ch.nolix.system.webgui.itemmenu.base;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenuExaminer;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenuItem;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenuValidator;

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
