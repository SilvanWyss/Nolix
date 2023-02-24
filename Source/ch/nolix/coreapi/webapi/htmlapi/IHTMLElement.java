//package declaration
package ch.nolix.coreapi.webapi.htmlapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IHTMLElement<HTMLE extends IHTMLElement<HTMLE, A>, A extends IHTMLAttribute> {
	
	//method declaration
	boolean containsAttributes();
	
	//method declaration
	boolean containsChildElements();
	
	//method declaration
	String getInnerText();
	
	//method declaration
	IContainer<A> getRefAttributes();
	
	//method declaration
	IContainer<HTMLE> getRefChildElements();
	
	//method declaration
	String getType();
}
