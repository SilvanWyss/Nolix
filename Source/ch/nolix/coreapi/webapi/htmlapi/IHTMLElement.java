//package declaration
package ch.nolix.coreapi.webapi.htmlapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IHTMLElement<CE extends IHTMLElement<CE, A>, A extends IHTMLAttribute> {
	
	//method declaration
	boolean containsAttributes();
	
	//method declaration
	boolean containsChildElements();
	
	//method declaration
	String getInnerText();
	
	//method declaration
	IContainer<A> getRefAttributes();
	
	//method declaration
	IContainer<CE> getRefChildElements();
	
	//method declaration
	String getType();
}
