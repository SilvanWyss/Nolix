//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.controlserviceapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IDropdownMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;

//class
public final class DropdownMenuHtmlBuilder implements IControlHtmlBuilder<IDropdownMenu> {

  //method
  @Override
  public HtmlElement createHtmlElementForControl(final IDropdownMenu dropdownMenu) {
    return HtmlElement.withTypeAndChildElements(
        HtmlElementTypeCatalogue.SELECT,
        createHtmlChildElementsForDropdownMenu(dropdownMenu));
  }

  //method
  private IContainer<IHtmlElement> createHtmlChildElementsForDropdownMenu(
      final IDropdownMenu dropdownMenu) {
    return createHtmlElementsFromItems(dropdownMenu.getStoredItems());
  }

  //method
  private IContainer<IHtmlElement> createHtmlElementsFromItems(
      final IContainer<? extends IItemMenuItem<?>> items) {
    return items.to(this::createHtmlElementForItem);
  }

  //method
  private IHtmlElement createHtmlElementForItem(final IItemMenuItem<?> item) {
    return HtmlElement.withTypeAndAttributesAndInnerText(
        HtmlElementTypeCatalogue.OPTION,
        createHtmlAttributesForItem(item),
        item.getText());
  }

  //method
  private IContainer<HtmlAttribute> createHtmlAttributesForItem(final IItemMenuItem<?> item) {

    final var htmlAttributes = new LinkedList<HtmlAttribute>();

    if (item.isSelected()) {
      htmlAttributes.addAtEnd(HtmlAttribute.withNameAndValue("selected", "selected"));
    }

    return htmlAttributes;
  }
}
