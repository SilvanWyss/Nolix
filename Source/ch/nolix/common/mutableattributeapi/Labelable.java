//package declaration
package ch.nolix.common.mutableattributeapi;

//own imports
import ch.nolix.common.attributeapi.Labeled;

//interface
/**
 * A {@link Labelable} is a {@link Labeled} whose info string can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <S> is the type of a {@link Labelable}.
 */
public interface Labelable<S extends Labelable<S>> extends Labeled {
	
	//method declaration
	/**
	 * Sets the info string of the current {@link Labelable}.
	 * 
	 * @param infoString
	 * @return the current {@link Labelable}.
	 */
	S setInfoString(String infoString);
}
