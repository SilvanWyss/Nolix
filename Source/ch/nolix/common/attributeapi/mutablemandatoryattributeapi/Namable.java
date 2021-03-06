//package declaration
package ch.nolix.common.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;

//interface
/**
 * A {@link Namable} is a {@link Named} whose name can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
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
