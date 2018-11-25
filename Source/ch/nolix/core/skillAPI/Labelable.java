//package declaration
package ch.nolix.core.skillAPI;

//interface
/**
 * A signable object is a signed object
 * whose info string can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <S> The type of a signable object.
 */
public interface Labelable<S extends Labelable<S>> extends Labeled {
		
	//abstract method
	/**
	 * Sets the info string of this signable object.
	 * 
	 * @param infoString
	 * @return this signable object.
	 */
	public abstract S setInfoString(String infoString);
}
