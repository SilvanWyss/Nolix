//package declaration
package ch.nolix.coreapi.webapi.htmlapi;

//interface
public interface HtmlElementTransformable {
	
	//method declaration
	IHtmlElement<?, ?> toHTMLElement();
	
	//method declaration
	String toHTMLString();
}
