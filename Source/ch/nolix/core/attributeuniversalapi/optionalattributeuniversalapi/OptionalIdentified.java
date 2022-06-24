//package declaration
package ch.nolix.core.attributeuniversalapi.optionalattributeuniversalapi;

import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link OptionalIdentified} can have an id.
 * 
 * @author Silvan Wyss
 * @date 2019-09-29
 */
@AllowDefaultMethodsAsDesignPattern
public interface OptionalIdentified {
	
	//method declaration
	/**
	 * @return the id of the current {@link OptionalIdentified}.
	 */
	long getId();
	
	//method
	/**
	 * @return the id of the current {@link OptionalIdentified} as {@link String}.
	 */
	default String getIdAsString() {
		return String.valueOf(getId());
	}
	
	//method
	/**
	 * @return the id of the current {@link OptionalIdentified} in quotes.
	 */
	default String getIdInQuotes() {
		return ("'" + getIdAsString() + "'");
	}
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalIdentified} has an id.
	 */
	boolean hasId();
	
	//method
	/**
	 * @param id
	 * @return true if the current {@link OptionalIdentified} has the given id.
	 */
	default boolean hasId(final long id) {
		return (hasId() && getId() == id);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the current {@link OptionalIdentified} has the same id as the given object.
	 */
	default boolean hasSameIdAs(final OptionalIdentified object) {
		return (object != null && object.hasId() && hasId(object.getId()));
	}
}
