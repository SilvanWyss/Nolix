/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webgui.main.ILayer;

/**
 * @author Silvan Wyss
 */
public final class LayerHtmlBuilder {
  private LayerHtmlBuilder() {
  }

  public static IHtmlElement getHtmlElementForLayer(final ILayer<?> layer) {
    return HtmlElement.withTypeAndAttributesAndChildElements(
      HtmlElementTypeCatalog.DIV,
      getHtmlAttributesForLayer(layer),
      getHtmlChildElementsForLayer(layer));
  }

  private static IContainer<IHtmlAttribute> getHtmlAttributesForLayer(final ILayer<?> layer) {
    final var idHtmlAttribute = createIdHtmlAttributeForLayer(layer);

    return ImmutableList.withElements(idHtmlAttribute);
  }

  public static IHtmlAttribute createIdHtmlAttributeForLayer(final ILayer<?> layer) {
    return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.ID, layer.getInternalId());
  }

  private static IContainer<IHtmlElement> getHtmlChildElementsForLayer(final ILayer<?> layer) {
    if (layer.isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElements(getContentHtmlElementForLayer(layer));
  }

  private static IHtmlElement getContentHtmlElementForLayer(final ILayer<?> layer) {
    return layer.getStoredRootControl().getHtml();
  }
}
