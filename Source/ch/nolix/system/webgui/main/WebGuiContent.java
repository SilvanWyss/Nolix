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
	public static WebGuiContent forWebGUI(final IWebGui<?> parentWebGUI) {
		return new WebGuiContent(parentWebGUI);
	}
	
	//attribute
	private final IWebGui<?> parentWebGUI;
	
	//constructor
	private WebGuiContent(final IWebGui<?> parentWebGUI) {
		
		GlobalValidator.assertThat(parentWebGUI).thatIsNamed("parent web GUI").isNotNull();
		
		this.parentWebGUI = parentWebGUI;
	}
	
	//method
	@Override
	public IHtmlElement<?, ?> toHtmlElement() {
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
		return LinkedList.withElements(HtmlAttribute.withNameAndValue(HtmlAttributeNameCatalogue.ID, ROOT_DIV_ID));
	}
	
	//method
	private IContainer<IHtmlElement<?, ?>> getHtmlChildElements() {
		return parentWebGUI.getOriLayers().to(ILayer::toHtmlElement);
	}
}
