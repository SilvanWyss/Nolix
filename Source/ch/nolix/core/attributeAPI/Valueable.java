//package declaration
package ch.nolix.core.attributeAPI;

//interface
/**
 * A {@link Valueable} is a {@link Valued} whose value can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 20
 * @param <VA> The type of a {@link Valueable}.
 * @param <V> The type of the value of a {@link Valueable}.
 */
public interface Valueable<VA extends Valueable<VA, V>, V> extends Valued<VA, V> {
	
	//abstract method
	/**
	 * Sets the value of the current {@link Valueable}.
	 * 
	 * @param value
	 * @return the current {@link Valueable}.
	 */
	public abstract VA setValue(V value);
}
