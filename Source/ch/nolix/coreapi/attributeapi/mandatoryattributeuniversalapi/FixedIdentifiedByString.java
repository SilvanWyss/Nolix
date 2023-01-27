//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi;

//interface
/**
 * A {@link FixedIdentifiedByString} has a fixed {@link String} id.
 * 
 * @author Silvan Wyss
 * @date 2022-08-26
 */
public interface FixedIdentifiedByString {
	
	//method declaration
	/**
	 * @return the fixed id of the current {@link FixedIdentifiedByString}.
	 */
	String getFixedId();
	
	//method declaration
	/**
	 * @param fixedId
	 * @return true if the current {@link FixedIdentifiedByString} has the given fixedId. 
	 */
	boolean hasFixedId(String fixedId);
}
