package ch.nolix.system.webatomiccontrol.dropdownmenu;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuItem;

/**
 * @author Silvan Wyss
 */
public final class DropdownMenuHtmlBuilderHelper {
  private DropdownMenuHtmlBuilderHelper() {
  }

  public static IContainer<IHtmlAttribute> createHtmlAttributesForDropdownMenuItem(
    final IItemMenuItem<?> dropdownMenuItem) {
    final ILinkedList<IHtmlAttribute> htmlAttributes = LinkedList.createEmpty();

    if (dropdownMenuItem.isSelected()) {
      htmlAttributes.addAtEnd(HtmlAttribute.withNameAndValue("selected", "selected"));
    }

    return htmlAttributes;
  }
}
