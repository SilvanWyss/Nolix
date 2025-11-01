package ch.nolix.system.webgui.main;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.web.htmlelementmodel.HtmlAttribute;
import ch.nolix.core.web.htmlelementmodel.HtmlElement;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.html.HtmlAttributeNameCatalog;
import ch.nolix.coreapi.web.html.HtmlElementTypeCatalog;
import ch.nolix.coreapi.web.htmlelementmodel.IHtmlElement;
import ch.nolix.systemapi.webgui.main.ILayer;
import ch.nolix.systemapi.webgui.main.IWebGui;

public final class WebGuiHtmlBuilder {
  public IHtmlElement createHtmlForWebGui(final IWebGui<?> webGui) {
    return //
    HtmlElement.withTypeAndAttributesAndChildElements(
      HtmlElementTypeCatalog.DIV,
      ImmutableList.withElements(HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalog.ID, "root")),
      createLayerHtmlElementsForWebGui(webGui));
  }

  private IContainer<? extends IHtmlElement> createLayerHtmlElementsForWebGui(final IWebGui<?> webGui) {
    return webGui.getStoredLayers().to(ILayer::getHtml);
  }
}
