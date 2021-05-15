//package declaration
package ch.nolix.common.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.common.attributeapi.mandatoryattributeapi.IdentifiedByString;

//interface
/**
 * A {@link IdentifiableByString} is a {@link IdentifiedByString} whose id can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 * @lines 20
 * @param <IBS> is the type of a {@link IdentifiableByString}.
 */
public interface IdentifiableByString<IBS> extends IdentifiedByString {
	
	//method declaration
	/**
	 * Sets the id of the current {@link IdentifiableByString}.
	 * 
	 * @param id
	 * @return the current {@link IdentifiableByString}.
	 */
	IBS setId(long id);
}
