//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeuniversalapi;

//interface
/**
 * A {@link OptionalFixedIdentified} can have an fixed Id.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
public interface OptionalFixedIdentified {
	
	//method declaration
	/**
	 * @return the fixed id of the current {@link OptionalFixedIdentified}.
	 * @throws RuntimeException if the current {@link OptionalFixedIdentified} does not have a fixed id.
	 */
	String getFixedId();
	
	//method declaration
	/**
	 * @return the fixed id of the current {@link OptionalFixedIdentified} in quotes.
	 * @throws RuntimeException if the current {@link OptionalFixedIdentified} does not have a fixed id.
	 */
	String getFixedIdInQuotes();
	
	//method declaration
	/**
	 * @return true if the current {@link OptionalFixedIdentified} has a fixed id.
	 */
	boolean hasFixedId();
	
	//method declaration
	/**
	 * @param fixedId
	 * @return true if the current {@link OptionalFixedIdentified} has the given fixedId.
	 */
	boolean hasId(String fixedId);
}
