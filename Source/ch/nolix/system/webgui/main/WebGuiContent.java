//package declaration
package ch.nolix.system.webgui.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.web.html.HtmlAttribute;
import ch.nolix.core.web.html.HtmlAttributeNameCatalogue;
import ch.nolix.core.web.html.HtmlElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGui;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGuiContent;

//class
final class WebGuiContent implements IWebGuiContent {
	
	//constant
	private static final String ROOT_DIV_ID = "root";
	
	//static method
	public static WebGuiContent forWebGui(final IWebGui<?> parentWebGui) {
		return new WebGuiContent(parentWebGui);
	}
	
	//attribute
	private final IWebGui<?> parentWebGui;
	
	//constructor
	private WebGuiContent(final IWebGui<?> parentWebGui) {
		
		GlobalValidator.assertThat(parentWebGui).thatIsNamed("parent web GUI").isNotNull();
		
		this.parentWebGui = parentWebGui;
	}
	
	//method
	@Override
	public IHtmlElement toHtmlElement() {
		return
		HtmlElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			getHtmlAttributes(),
			getHtmlChildElements()
		);
	}
	
	//method
	@Override
	public String toHtmlString() {
		return toHtmlElement().toString();
	}
	
	//method
	private IContainer<? extends IHtmlAttribute> getHtmlAttributes() {
		return LinkedList.withElement(HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.ID, ROOT_DIV_ID));
	}
	
	//method
	private IContainer<IHtmlElement> getHtmlChildElements() {
		return parentWebGui.getStoredLayers().to(ILayer::toHtmlElement);
	}
}
