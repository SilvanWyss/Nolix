//package declaration
package ch.nolix.coreapi.webapi.cssapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ICss<
	R extends ICssRule<P>,
	P extends ICssProperty
> {
	
	//method declaration
	IContainer<R> getOriRules();
	
	//method declaration
	String toStringWithoutEnclosingBrackets();
}
