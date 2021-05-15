//package declaration
package ch.nolix.common.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Headered;

//interface
/**
 * A {@link Headerable} is a {@link Headered} whose header can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2018-04-15
 * @lines 20
 * @param <H> is the type of a {@link Headerable}.
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
