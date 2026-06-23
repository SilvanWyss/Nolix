/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.dropdownmenu;

import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.systemapi.atomiccontrol.dropdownmenu.IDropdownMenu;
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
