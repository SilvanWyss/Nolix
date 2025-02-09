package ch.nolix.system.webgui.itemmenu;

import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuExaminer;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;

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
