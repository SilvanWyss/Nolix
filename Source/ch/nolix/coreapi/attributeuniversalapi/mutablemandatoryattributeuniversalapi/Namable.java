//package declaration
package ch.nolix.coreapi.attributeuniversalapi.mutablemandatoryattributeuniversalapi;

import ch.nolix.coreapi.attributeuniversalapi.mandatoryattributeuniversalapi.Named;

//interface
/**
 * A {@link Namable} is a {@link Named} whose name can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <N> is the type of a {@link Namable}.
 */
public interface Namable<N extends Namable<N>> extends Named {
	
	//method declaration
	/**
	 * Sets the name of the current {@link Namable}.
	 * 
	 * @param name
	 * @return the current {@link Namable}.
	 */
	N setName(String name);
}
