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
 */
public interface Freezable {
	
	//abstract method
	/**
	 * Freezes this freezable object.
	 */
	public abstract void freeze();
	
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
