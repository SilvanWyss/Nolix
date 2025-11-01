package ch.nolix.system.webgui.main;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlAttribute;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webgui.main.ILayer;

public final class LayerHtmlBuilder {
  public IHtmlElement getHtmlElementForLayer(final ILayer<?> layer) {
    return HtmlElement.withTypeAndAttributesAndChildElements(
      HtmlElementTypeCatalog.DIV,
      getHtmlAttributesForLayer(layer),
      getHtmlChildElementsForLayer(layer));
  }

  private IContainer<IHtmlAttribute> getHtmlAttributesForLayer(final ILayer<?> layer) {
    final var idHtmlAttribute = LayerHtmlBuilderHelper.createIdHtmlAttributeForLayer(layer);

    return ImmutableList.withElements(idHtmlAttribute);
  }

  private IContainer<IHtmlElement> getHtmlChildElementsForLayer(final ILayer<?> layer) {
    if (layer.isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElements(getContentHtmlElementForLayer(layer));
  }

  private IHtmlElement getContentHtmlElementForLayer(final ILayer<?> layer) {
    return layer.getStoredRootControl().getHtml();
  }
}
