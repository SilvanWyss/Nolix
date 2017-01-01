//package declaration
package ch.nolix.common.interfaces;

//interface
/**
 * A namable object is a named object whose name can be set dynamically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 * @param <N> - The concrete type of the namable object.
 */
public interface Namable<N extends Namable<N>>
extends Named {
	
	//abstract method
	/**
	 * Sets the name of this namable object.
	 * 
	 * @param name
	 * @return this namable object.
	 */
	public abstract N setName(String name);
}
