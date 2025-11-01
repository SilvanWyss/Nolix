package ch.nolix.system.webatomiccontrol.dropdownmenu;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.webatomiccontrol.itemmenu.IItemMenuItem;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

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
      DropdownMenuHtmlBuilderHelper.createHtmlAttributesForDropdownMenuItem(item),
      item.getText());
  }
}
