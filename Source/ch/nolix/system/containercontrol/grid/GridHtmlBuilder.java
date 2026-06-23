/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.containercontrol.grid;

import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.baseapi.html.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.containercontrol.grid.IGrid;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class GridHtmlBuilder implements IControlHtmlBuilder<IGrid> {
  /**
   * {@inheritDoc}
   */
  @Override
  public IHtmlElement createHtmlElementForControl(final IGrid control) {
    final var type = HtmlElementTypeCatalog.DIV;
    final var childElements = GridHtmlBuilderHelper.createHtmlElementForTableOfGrid(control);

    return HtmlElement.withTypeAndChildElement(type, childElements);
  }
}
