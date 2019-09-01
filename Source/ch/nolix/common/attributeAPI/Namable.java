//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link Namable} is a {@link Named} whose name can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 20
 * @param <N> The type of a {@link Namable}.
 */
public interface Namable<N extends Namable<N>> extends Named {
	
	//abstract method
	/**
	 * Sets the name of the current {@link Namable}.
	 * 
	 * @param name
	 * @return the current {@link Namable}.
	 */
	public abstract N setName(String name);
}
