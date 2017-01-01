//package declaration
package ch.nolix.common.interfaces;

//interface
/**
 * A freezable object can be frozen, that it can not be mutated anymore. A freezable object cannot be unfrozen.
 * So, a freezable object can be given as parameter and is safe of any try of mutation.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public interface Freezable {
	
	//abstract method
	/**
	 * Freezes this freezable object.
	 */
	public abstract void freeze();
}
