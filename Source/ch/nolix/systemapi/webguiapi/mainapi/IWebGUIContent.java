//package declaration
package ch.nolix.systemapi.webguiapi.mainapi;

//own imports
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLElement;

//interface
public interface IWebGUIContent {
	
	//method declaration
	String toHTML();
	
	//method declaration
	IHTMLElement<?, ?> toHTMLElement();
}
