/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webatomiccontrol.dropdownmenu;

import ch.nolix.base.web.htmlelementmodel.HtmlElement;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.systemapi.webatomiccontrol.dropdownmenu.IDropdownMenu;
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
