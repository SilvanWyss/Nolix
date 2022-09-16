//package declaration
package ch.nolix.coreapi.documentapi.cssapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.documentapi.htmlapi.IHTMLAttribute;

//interface
public interface ICSS<R extends ICSSRule<A>, A extends IHTMLAttribute> {
	
	//method declaration
	IContainer<R> getRefRules();
}
