//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Titled;

//interface
/**
 * A {@link Titleable} is a {@link Titled} whose title can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface Titleable extends Titled {
	
	//method declaration
	/**
	 * Sets the title of the current {@link Titleable}.
	 * 
	 * @param title
	 * @throws RuntimeException if the given title is null.
	 * @throws RuntimeException if the given title is blank.
	 */
	void setTitle(String title);
}