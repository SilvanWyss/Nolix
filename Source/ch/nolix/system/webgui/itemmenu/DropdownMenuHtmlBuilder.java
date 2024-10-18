package ch.nolix.system.webgui.itemmenu;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;

public final class DropdownMenuHtmlBuilder implements IControlHtmlBuilder<IDropdownMenu> {

  @Override
  public HtmlElement createHtmlElementForControl(final IDropdownMenu dropdownMenu) {
    return HtmlElement.withTypeAndChildElements(
      HtmlElementTypeCatalogue.SELECT,
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
      HtmlElementTypeCatalogue.OPTION,
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
