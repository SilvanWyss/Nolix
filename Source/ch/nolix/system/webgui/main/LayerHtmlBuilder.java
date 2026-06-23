/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.main;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.html.htmlmodel.HtmlAttribute;
import ch.nolix.base.html.htmlmodel.HtmlElement;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.html.htmlcatalog.HtmlAttributeNameCatalog;
import ch.nolix.baseapi.html.htmlcatalog.HtmlElementTypeCatalog;
import ch.nolix.baseapi.html.htmlmodel.IHtmlAttribute;
import ch.nolix.baseapi.html.htmlmodel.IHtmlElement;
import ch.nolix.systemapi.webgui.main.ILayer;

/**
 * @author Silvan Wyss
 */
public final class LayerHtmlBuilder {
  private LayerHtmlBuilder() {
  }

  public static IHtmlElement getHtmlElementForLayer(final ILayer layer) {
    return HtmlElement.withTypeAndAttributesAndChildElements(
      HtmlElementTypeCatalog.DIV,
      getHtmlAttributesForLayer(layer),
      getHtmlChildElementsForLayer(layer));
  }

  private static ExtendedIterable<IHtmlAttribute> getHtmlAttributesForLayer(final ILayer layer) {
    final var idHtmlAttribute = createIdHtmlAttributeForLayer(layer);

    return ImmutableList.withElements(idHtmlAttribute);
  }

  public static IHtmlAttribute createIdHtmlAttributeForLayer(final ILayer layer) {
    return HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.ID, layer.getInternalId());
  }

  private static ExtendedIterable<IHtmlElement> getHtmlChildElementsForLayer(final ILayer layer) {
    if (layer.isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElements(getContentHtmlElementForLayer(layer));
  }

  private static IHtmlElement getContentHtmlElementForLayer(final ILayer layer) {
    return layer.getStoredRootControl().getHtml();
  }
}
