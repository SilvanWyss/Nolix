/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.base.web.htmlelementmodel.HtmlElement;
import ch.nolix.baseapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.baseapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.baseapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;

/**
 * @author Silvan Wyss
 */
public final class WebGuiHtmlBuilder {
  private WebGuiHtmlBuilder() {
  }

  public static IHtmlElement createHtmlForWebGui(final IWebGui<?> webGui) {
    final var type = HtmlElementTypeCatalog.DIV;
    final var idAttribute = HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.ID, "root");
    final var attributes = ImmutableList.withElements(idAttribute);
    final var elements = webGui.getStoredLayers().to(ILayer::getHtml);

    return HtmlElement.withTypeAndAttributesAndChildElements(type, attributes, elements);
  }
}
