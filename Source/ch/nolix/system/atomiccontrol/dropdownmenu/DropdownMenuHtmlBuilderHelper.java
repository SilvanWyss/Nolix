/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.dropdownmenu;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.html.htmlmodel.HtmlAttribute;
import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.baseapi.html.htmlmodel.IHtmlAttribute;
import ch.nolix.baseapi.html.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.atomiccontrol.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.atomiccontrol.itemmenu.IItemMenuItem;

/**
 * @author Silvan Wyss
 */
public final class DropdownMenuHtmlBuilderHelper {
  private DropdownMenuHtmlBuilderHelper() {
  }

  public static ExtendedIterable<IHtmlElement> createHtmlChildElementsForDropdownMenu(
    final IDropdownMenu dropdownMenu) {
    return createHtmlElementsFromItems(dropdownMenu.getStoredItems());
  }

  private static ExtendedIterable<IHtmlElement> createHtmlElementsFromItems(
    final ExtendedIterable<? extends IItemMenuItem<?>> items) {
    return items.to(DropdownMenuHtmlBuilderHelper::createHtmlElementForItem);
  }

  private static IHtmlElement createHtmlElementForItem(final IItemMenuItem<?> item) {
    return HtmlElement.withTypeAndAttributesAndInnerText(
      HtmlElementTypeCatalog.OPTION,
      DropdownMenuHtmlBuilderHelper.createHtmlAttributesForDropdownMenuItem(item),
      item.getText());
  }

  private static ExtendedIterable<IHtmlAttribute> createHtmlAttributesForDropdownMenuItem(
    final IItemMenuItem<?> dropdownMenuItem) {
    final ILinkedList<IHtmlAttribute> htmlAttributes = LinkedList.createEmpty();

    if (dropdownMenuItem.isSelected()) {
      htmlAttributes.addAtEnd(HtmlAttribute.withNameAndValue("selected", "selected"));
    }

    return htmlAttributes;
  }
}
