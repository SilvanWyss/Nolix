//package declaration
package ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi;

import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link Labeled} has a label.
 * 
 * @author Silvan Wyss
 * @date 2021-08-26
 */
@AllowDefaultMethodsAsDesignPattern
public interface Labeled {
	
	//method declaration
	/**
	 * @return the label of the current {@link Labeled}.
	 */
	String getLabel();
	
	//method
	default String getLabelInQuotes() {
		return ("'" + getLabel()+  "'");
	}
	
	//method
	/**
	 * @param label
	 * @return true if the current {@link Labeled} has the given label.
	 */
	default boolean hasLabel(final String label) {
		return getLabel().equals(label);
	}
}
