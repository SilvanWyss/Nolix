//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi;

//interface
/**
 * A {@link FixedIdentified} has a fixed id.
 * 
 * @author Silvan Wyss
 * @date 2022-08-26
 */
public interface FixedIdentified {
	
	//method declaration
	/**
	 * @return the fixed id of the current {@link FixedIdentified}.
	 */
	String getFixedId();
	
	//method declaration
	/**
	 * @param fixedId
	 * @return true if the current {@link FixedIdentified} has the given fixedId. 
	 */
	boolean hasFixedId(String fixedId);
}
