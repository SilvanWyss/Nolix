//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlAttributeNameCatalogue;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;

//class
public final class WebGuiHtmlBuilder {
	
	//constant
	private static final String ROOT_DIV_ID = "root";
	
	//method
	public IHtmlElement createHtmlForWebGui(final IWebGui<?> webGui) {
		return
		HtmlElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			createHtmlAttributesForWebGui(),
			createGetHtmlChildElements(webGui)
		);
	}
	
	//method
	private IContainer<? extends IHtmlAttribute> createHtmlAttributesForWebGui() {
		return LinkedList.withElement(HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.ID, ROOT_DIV_ID));
	}
	
	//method
	private IContainer<? extends IHtmlElement> createGetHtmlChildElements(final IWebGui<?> webGui) {
		return webGui.getStoredLayers().to(ILayer::toHtmlElement);
	}
}
