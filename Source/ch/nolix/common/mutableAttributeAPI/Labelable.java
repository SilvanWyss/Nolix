//package declaration
package ch.nolix.common.mutableAttributeAPI;

//own import
import ch.nolix.common.attributeAPI.Labeled;

//interface
/**
 * A {@link Labelable} is a {@link Labeled} whose info string can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <S> The type of a {@link Labelable}.
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
