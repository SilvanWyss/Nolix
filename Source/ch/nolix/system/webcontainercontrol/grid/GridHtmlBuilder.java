package ch.nolix.system.webcontainercontrol.grid;

import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webcontainercontrol.grid.IGrid;
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
