//package declaration
package ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi;

import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IdentifiedByString} has an id that is a {@link String}.
 * 
 * @author Silvan Wyss
 * @date 2019-06-10
 */
@AllowDefaultMethodsAsDesignPattern
public interface IdentifiedByString {
	
	//method declaration
	/**
	 * @return the id of the current {@link IdentifiedByString}.
	 */
	String getId();
	
	//method
	/**
	 * @return the id of the current {@link IdentifiedByString} in quotes.
	 */
	default String getIdInQuotes() {
		return ("'" + getId() + "'");
	}
	
	//method
	/**
	 * @param id
	 * @return true if the current {@link IdentifiedByString} has the given id. 
	 */
	default boolean hasId(final String id) {
		return getId().equals(id);
	}
}
