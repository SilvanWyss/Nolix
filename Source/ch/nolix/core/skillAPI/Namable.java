//package declaration
package ch.nolix.core.skillAPI;

//interface
/**
 * A namable object is a named object
 * whose name can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 30
 * @param <N> The type of a namable object.
 */
public interface Namable<N extends Namable<N>> extends Named {
	
	//abstract method
	/**
	 * Sets the name of this namable object.
	 * 
	 * @param name
	 * @return this namable object.
	 */
	public abstract N setName(String name);
}
