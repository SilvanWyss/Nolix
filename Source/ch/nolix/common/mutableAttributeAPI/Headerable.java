//package declaration
package ch.nolix.common.mutableAttributeAPI;

//own import
import ch.nolix.common.attributeAPI.Headered;

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
	
	//method declaration
	/**
	 * Sets the header of the current {@link Headerable}.
	 * 
	 * @param header
	 * @return the current {@link Headerable}.
	 */
	H setHeader(String header);
}
