/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.atomiccontrol.link;

import ch.nolix.base.web.htmlmodel.HtmlElement;
import ch.nolix.baseapi.web.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.baseapi.web.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.atomiccontrol.link.ILink;
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
