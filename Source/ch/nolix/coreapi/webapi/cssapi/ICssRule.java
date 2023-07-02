//package declaration
package ch.nolix.coreapi.webapi.cssapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ICssRule<P extends ICssProperty> {
	
	//method declaration
	IContainer<P> getOriProperties();
	
	//method declaration
	IContainer<String> getSelectors();
}
