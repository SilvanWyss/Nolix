package ch.nolix.system.webgui.itemmenu.dropdownmenu;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.html.IHtmlAttribute;
import ch.nolix.coreapi.web.html.IHtmlElement;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.baseapi.IItemMenuItem;
import ch.nolix.systemapi.webguiapi.itemmenuapi.dropdownmenuapi.IDropdownMenu;

public final class DropdownMenuHtmlBuilder implements IControlHtmlBuilder<IDropdownMenu> {

  @Override
  public HtmlElement createHtmlElementForControl(final IDropdownMenu dropdownMenu) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalog.SELECT,
      createHtmlChildElementsForDropdownMenu(dropdownMenu));
  }

  private IContainer<IHtmlElement> createHtmlChildElementsForDropdownMenu(
    final IDropdownMenu dropdownMenu) {
    return createHtmlElementsFromItems(dropdownMenu.getStoredItems());
  }

  private IContainer<IHtmlElement> createHtmlElementsFromItems(
    final IContainer<? extends IItemMenuItem<?>> items) {
    return items.to(this::createHtmlElementForItem);
  }

  private IHtmlElement createHtmlElementForItem(final IItemMenuItem<?> item) {
    return HtmlElement.withTypeAndAttributesAndInnerText(
      HtmlElementTypeCatalog.OPTION,
      createHtmlAttributesForItem(item),
      item.getText());
  }

  private IContainer<IHtmlAttribute> createHtmlAttributesForItem(final IItemMenuItem<?> item) {

    final ILinkedList<IHtmlAttribute> htmlAttributes = LinkedList.createEmpty();

    if (item.isSelected()) {
      htmlAttributes.addAtEnd(HtmlAttribute.withNameAndValue("selected", "selected"));
    }

    return htmlAttributes;
  }
}
