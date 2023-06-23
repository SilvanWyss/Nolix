//package declaration
package ch.nolix.system.webgui.main;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.web.html.HTMLAttribute;
import ch.nolix.core.web.html.HTMLAttributeNameCatalogue;
import ch.nolix.core.web.html.HTMLElement;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.htmlapi.HtmlElementTypeCatalogue;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLAttribute;
import ch.nolix.coreapi.webapi.htmlapi.IHTMLElement;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUI;
import ch.nolix.systemapi.webguiapi.mainapi.IWebGUIContent;

//class
final class WebGUIContent implements IWebGUIContent {
	
	//constant
	private static final String ROOT_DIV_ID = "root";
	
	//static method
	public static WebGUIContent forWebGUI(final IWebGUI<?> parentWebGUI) {
		return new WebGUIContent(parentWebGUI);
	}
	
	//attribute
	private final IWebGUI<?> parentWebGUI;
	
	//constructor
	private WebGUIContent(final IWebGUI<?> parentWebGUI) {
		
		GlobalValidator.assertThat(parentWebGUI).thatIsNamed("parent web GUI").isNotNull();
		
		this.parentWebGUI = parentWebGUI;
	}
	
	//method
	@Override
	public IHTMLElement<?, ?> toHTMLElement() {
		return
		HTMLElement.withTypeAndAttributesAndChildElements(
			HtmlElementTypeCatalogue.DIV,
			getHTMLAttributes(),
			getHTMLChildElements()
		);
	}
	
	//method
	@Override
	public String toHTMLString() {
		return toHTMLElement().toString();
	}
	
	//method
	private IContainer<? extends IHTMLAttribute> getHTMLAttributes() {
		return LinkedList.withElements(HTMLAttribute.withNameAndValue(HTMLAttributeNameCatalogue.ID, ROOT_DIV_ID));
	}
	
	//method
	private IContainer<IHTMLElement<?, ?>> getHTMLChildElements() {
		return parentWebGUI.getOriLayers().to(ILayer::toHTMLElement);
	}
}
