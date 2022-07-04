//package declaration
package ch.nolix.core.attributeuniversalapi.mutablemandatoryattributeuniversalapi;

import ch.nolix.coreapi.attributeuniversalapi.mandatoryattributeuniversalapi.IdentifiedByString;

//interface
/**
 * A {@link IdentifiableByString} is a {@link IdentifiedByString} whose id can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
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
