//package declaration
package ch.nolix.coreapi.documentapi.cssapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLAttribute;

//interface
public interface ICSSRule<A extends IHTMLAttribute> {
	
	//method declaration
	IContainer<A> getRefAttributes();
	
	//method declaration
	IContainer<String> getSelectors();
}
