/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.dropdownmenu;

import ch.nolix.base.web.htmlmodel.HtmlElement;
import ch.nolix.baseapi.web.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.systemapi.control.dropdownmenu.IDropdownMenu;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class DropdownMenuHtmlBuilder implements IControlHtmlBuilder<IDropdownMenu> {
  /**
   * {@inheritDoc}
   */
  @Override
  public HtmlElement createHtmlElementForControl(final IDropdownMenu dropdownMenu) {
    final var type = HtmlElementTypeCatalog.SELECT;
    final var childElements = DropdownMenuHtmlBuilderHelper.createHtmlChildElementsForDropdownMenu(dropdownMenu);

    return HtmlElement.withTypeAndChildElements(type, childElements);
  }
}
