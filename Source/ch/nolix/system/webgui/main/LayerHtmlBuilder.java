package ch.nolix.system.webgui.main;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.html.IHtmlAttribute;
import ch.nolix.coreapi.web.html.IHtmlElement;
import ch.nolix.system.webgui.layertool.LayerTool;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;

public final class LayerHtmlBuilder {

  private static final LayerTool LAYER_TOOL = new LayerTool();

  public IHtmlElement getHtmlElementForLayer(final ILayer<?> layer) {
    return HtmlElement.withTypeAndAttributesAndChildElements(
      HtmlElementTypeCatalog.DIV,
      getHtmlAttributesForLayer(layer),
      getHtmlChildElementsForLayer(layer));
  }

  private IContainer<IHtmlAttribute> getHtmlAttributesForLayer(final ILayer<?> layer) {
    return ImmutableList.withElement(LAYER_TOOL.createIdHtmlAttributeForLayer(layer));
  }

  private IContainer<IHtmlElement> getHtmlChildElementsForLayer(final ILayer<?> layer) {

    if (layer.isEmpty()) {
      return ImmutableList.createEmpty();
    }

    return ImmutableList.withElement(getContentHtmlElementForLayer(layer));
  }

  private IHtmlElement getContentHtmlElementForLayer(final ILayer<?> layer) {
    return layer.getStoredRootControl().getHtml();
  }
}
