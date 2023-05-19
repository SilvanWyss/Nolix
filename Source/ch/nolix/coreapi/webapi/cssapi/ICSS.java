//package declaration
package ch.nolix.coreapi.webapi.cssapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ICSS<
	R extends ICSSRule<P>,
	P extends ICSSProperty
> {
	
	//method declaration
	IContainer<R> getOriRules();
	
	//method declaration
	String toStringWithoutEnclosingBrackets();
}
