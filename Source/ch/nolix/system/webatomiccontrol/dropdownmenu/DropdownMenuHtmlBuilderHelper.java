/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.dropdownmenu;

import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.base.web.htmlelementmodel.HtmlElement;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.baseapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.baseapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuItem;

/**
 * @author Silvan Wyss
 */
public final class DropdownMenuHtmlBuilderHelper {
  private DropdownMenuHtmlBuilderHelper() {
  }

  public static IContainer<IHtmlElement> createHtmlChildElementsForDropdownMenu(
    final IDropdownMenu dropdownMenu) {
    return createHtmlElementsFromItems(dropdownMenu.getStoredItems());
  }

  private static IContainer<IHtmlElement> createHtmlElementsFromItems(
    final IContainer<? extends IItemMenuItem<?>> items) {
    return items.to(DropdownMenuHtmlBuilderHelper::createHtmlElementForItem);
  }

  private static IHtmlElement createHtmlElementForItem(final IItemMenuItem<?> item) {
    return HtmlElement.withTypeAndAttributesAndInnerText(
      HtmlElementTypeCatalog.OPTION,
      DropdownMenuHtmlBuilderHelper.createHtmlAttributesForDropdownMenuItem(item),
      item.getText());
  }

  private static IContainer<IHtmlAttribute> createHtmlAttributesForDropdownMenuItem(
    final IItemMenuItem<?> dropdownMenuItem) {
    final ILinkedList<IHtmlAttribute> htmlAttributes = LinkedList.createEmpty();

    if (dropdownMenuItem.isSelected()) {
      htmlAttributes.addAtEnd(HtmlAttribute.withNameAndValue("selected", "selected"));
    }

    return htmlAttributes;
  }
}
