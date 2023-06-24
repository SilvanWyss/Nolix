//package declaration
package ch.nolix.coreapi.webapi.htmlapi;

//interface
public interface HtmlElementTransformable {
	
	//method declaration
	IHtmlElement<?, ?> toHtmlElement();
	
	//method declaration
	String toHtmlString();
}
