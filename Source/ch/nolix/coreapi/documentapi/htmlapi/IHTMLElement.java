//package declaration
package ch.nolix.coreapi.documentapi.htmlapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface IHTMLElement<CE extends IHTMLElement<CE, A>, A extends IHTMLAttribute> {
	
	//method declaration
	boolean containsAttributes();
	
	//method declaration
	boolean containsChildElements();
	
	//method declaration
	IContainer<A> getRefAttributes();
	
	//method declaration
	IContainer<CE> getRefChildElements();
	
	//method declaration
	String getType();
}
