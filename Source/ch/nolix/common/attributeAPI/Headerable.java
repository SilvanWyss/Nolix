//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link Headerable} is a {@link Headered} whose header can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2018-04
 * @lines 20
 * @param <H> The type of a {@link Headerable}.
 */
public interface Headerable<H extends Headered> extends Headered {
	
	//abstract method
	/**
	 * Sets the header of the current {@link Headerable}.
	 * 
	 * @param header
	 * @return the current {@link Headerable}.
	 */
	public abstract H setHeader(String header);
}
