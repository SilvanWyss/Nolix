//package declaration
package ch.nolix.coreapi.webapi.cssapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface ICSS<
	R extends ICSSRule<P>,
	P extends ICSSProperty
> {
	
	//method declaration
	IContainer<R> getRefRules();
}
