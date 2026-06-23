/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.link;

import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.baseapi.html.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.control.link.ILink;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;

/**
 * @author Silvan Wyss
 */
public final class LinkHtmlBuilder implements IControlHtmlBuilder<ILink> {
  /**
   * {@inheritDoc}
   */
  @Override
  public IHtmlElement createHtmlElementForControl(final ILink control) {
    final var type = HtmlElementTypeCatalog.A;
    final var attributes = LinkHtmlBuilderHelper.createHtmlAttributesForControl(control);
    final var innerText = control.getDisplayText();

    return HtmlElement.withTypeAndAttributesAndInnerText(type, attributes, innerText);
  }
}
