//package declaration
package ch.nolix.core.interfaces;

//interface
/**
 * A freezable object can be frozen. A frozen object can not be mutated anymore.
 * A frozen object cannot be unfrozen.
 * A frozen object can be passed as parameter and is safe from any try to be mutated.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 * @param <F> The type of a freezable object.
 */
public interface Freezable<F extends Freezable<F>> {
	
	//abstract method
	/**
	 * Freezes this freezable object.
	 * 
	 * @return this freezable object.
	 */
	public abstract F freeze();
	
	//default method
	/**
	 * @return true if this freezable object is frozen.
	 */
	public default boolean isFrozen() {
		return !isMutable();
	}
	
	//abstract method
	/**
	 * @return true if this freezable object is mutable.
	 */
	public abstract boolean isMutable();
}
