//package declaration
package ch.nolix.coreapi.webapi.cssapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//interface
public interface ICSSRule<P extends ICSSProperty> {
	
	//method declaration
	IContainer<P> getRefProperties();
	
	//method declaration
	IContainer<String> getSelectors();
}
