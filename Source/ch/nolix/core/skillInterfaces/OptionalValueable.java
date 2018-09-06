//package declaration
package ch.nolix.core.skillInterfaces;

//interface
/**
 * A {@link OptionalValueable} is a {@link Valueable}
 * whose value is optional.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 30
 * @param <OV> The type of a {@link OptionalValueable}.
 * @param <V> The type of the value of a {@link OptionalValueable}.
 */
public interface OptionalValueable<OV extends OptionalValueable<OV, V>, V>
extends Valueable<OV, V> {

	//abstract method
	/**
	 * @return true if the current {@link OptionalValueable} has a value.
	 */
	public abstract boolean hasValue();
	
	//abstract method
	/**
	 * Removes the value of the current {@link OptionalValueable}.
	 * 
	 * @return the current {@link OptionalValueable}.
	 */
	public abstract OV removeValue();
}
