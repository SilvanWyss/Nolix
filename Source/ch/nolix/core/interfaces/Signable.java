//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A signable object is a signed object whose info string can be set dynamically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <S> - The concrete type of a signable object.
 */
public interface Signable<S extends Signable<S>>
extends Signed {
		
	//abstract method
	/**
	 * Sets the info string of this signable object.
	 * 
	 * @param infoString
	 * @return this signable object.
	 */
	public abstract S setInfoString(String infoString);
}
