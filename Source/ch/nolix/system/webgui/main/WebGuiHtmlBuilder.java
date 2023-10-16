//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlAttributeNameCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebGuiHtmlBuilder {

  //method
  public IHtmlElement createHtmlForWebGui(final IWebGui<?> webGui) {
    return HtmlElement.withTypeAndAttributesAndChildElements(
        HtmlElementTypeCatalogue.DIV,
        ReadContainer.forElement(HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.ID, "root")),
        createLayerHtmlElementsForWebGui(webGui));
  }

  //method
  private IContainer<? extends IHtmlElement> createLayerHtmlElementsForWebGui(final IWebGui<?> webGui) {
    return webGui.getStoredLayers().to(ILayer::getHtml);
  }
}
