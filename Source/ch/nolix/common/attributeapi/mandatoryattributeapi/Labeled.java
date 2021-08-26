//package declaration
package ch.nolix.common.attributeapi.mandatoryattributeapi;

//interface
/**
 * A {@link Labeled} has a label.
 * 
 * @author Silvan Wyss
 * @date 2021-08-26
 * @lines 20
 */
public interface Labeled {
	
	//method declaration
	/**
	 * @return the label of the current {@link Labeled}.
	 */
	String getLabel();
	
	//method
	/**
	 * @param label
	 * @return true if the current {@link Labeled} has the given label.
	 */
	default boolean hasLabel(final String label) {
		return getLabel().equals(label);
	}
}
