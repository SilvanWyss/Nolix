package ch.nolix.system.webgui.main;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalog;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

public final class WebGuiHtmlBuilder {

  public IHtmlElement createHtmlForWebGui(final IWebGui<?> webGui) {
    return //
    HtmlElement.withTypeAndAttributesAndChildElements(
      HtmlElementTypeCatalog.DIV,
      ImmutableList.withElement(HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.ID, "root")),
      createLayerHtmlElementsForWebGui(webGui));
  }

  private IContainer<? extends IHtmlElement> createLayerHtmlElementsForWebGui(final IWebGui<?> webGui) {
    return webGui.getStoredLayers().to(ILayer::getHtml);
  }
}
