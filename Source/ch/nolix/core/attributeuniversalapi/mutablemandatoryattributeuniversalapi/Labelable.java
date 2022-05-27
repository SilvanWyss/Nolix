//package declaration
package ch.nolix.core.attributeuniversalapi.mutablemandatoryattributeuniversalapi;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Labeled;

//interface
/**
 * A {@link Labelable} is a {@link Labeled} whose label can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2021-08-26
 * @param <L> is the type of a {@link Labelable}.
 */
public interface Labelable<L extends Labelable<L>> extends Labeled {
	
	//method declaration
	/**
	 * Sets the label of the current {@link Labelable}.
	 * 
	 * @param label
	 * @return the current {@link Labelable}.
	 */
	L setLabel(String label);
}
