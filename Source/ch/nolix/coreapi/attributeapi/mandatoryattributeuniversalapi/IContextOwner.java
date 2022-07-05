//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi;

import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IContextOwner} has a context.
 * 
 * @author Silvan Wyss
 * @date 2022-03-18
 * @param <C> is the type of the context of a {@link IContextOwner}.
 */
@AllowDefaultMethodsAsDesignPattern
public interface IContextOwner<C> {
	
	//method declaration
	/**
	 * @return the context of the current {@link IContextOwner}.
	 */
	C getRefContext();
}
