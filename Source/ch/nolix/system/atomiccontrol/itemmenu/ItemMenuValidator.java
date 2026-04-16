/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.itemmenu;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenu;
import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenuExaminer;
import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenuItem;
import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenuValidator;

/**
 * @author Silvan Wyss
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
