package ch.nolix.system.webgui.itemmenu.base;

import ch.nolix.systemapi.webgui.itemmenu.baseapi.IItemMenu;
import ch.nolix.systemapi.webgui.itemmenu.baseapi.IItemMenuExaminer;
import ch.nolix.systemapi.webgui.itemmenu.baseapi.IItemMenuItem;

/**
 * @author Silvan Wyss
 * @version 2025-02-09
 */
public final class ItemMenuExaminer implements IItemMenuExaminer {
  @Override
  public boolean canAddItem(final IItemMenu<?, ?> itemMenu, final IItemMenuItem<?> item) {
    return itemMenu != null
    && item != null
    && !itemMenu.containsItemWithId(item.getId())
    && !itemMenu.containsItemWithText(item.getText());
  }
}
