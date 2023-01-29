//package declaration
package ch.nolix.coreapi.webapi.cssapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ICSSRule<P extends ICSSProperty> {
	
	//method declaration
	IContainer<P> getRefProperties();
	
	//method declaration
	IContainer<String> getSelectors();
}
