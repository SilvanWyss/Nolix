//package declaration
package ch.nolix.common.skillAPI;

//interface
/**
 * A freezable object can be frozen. A frozen object cannot be mutated anymore.
 * A frozen object cannot be unfrozen.
 * A frozen object can be passed as parameter and is safe from any try to be mutated.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 * @param <F> The type of a freezable object.
 */
public interface Freezable<F extends Freezable<F>> {
	
	//method declaration
	/**
	 * Freezes this freezable object.
	 * 
	 * @return this freezable object.
	 */
	public abstract F freeze();
	
	//method declaration
	/**
	 * @return true if this freezable object is frozen.
	 */
	public abstract boolean isFrozen();
	
	//method
	/**
	 * @return true if this freezable object is not frozen.
	 */
	public default boolean isMutable() {
		return !isFrozen();
	}
}
