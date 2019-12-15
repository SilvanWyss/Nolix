//package declaration
package ch.nolix.element.baseAPI;

import ch.nolix.common.node.BaseNode;

//interface
/**
 * A {@link IRequestableElement} is a {@link IElement}
 * that can be requested if it can have an attribute of a given type.
 * 
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 30
 */
public interface IRequestableElement extends IElement {
	
	//default method
	/**
	 * @param attribute
	 * @return true if the current {@link IRequestableElement}
	 * can have an attribute of the same type as the given attribute.
	 */
	public default boolean canHaveAttributeOfType(final BaseNode attribute) {
		return (attribute.hasHeader() && canHaveAttributeOfType(attribute.getHeader()));
	}
	
	//method declaration
	/**
	 * @param type
	 * @return true if the current {@link IRequestableElement} can have an attribute of the given type
	 */
	public abstract boolean canHaveAttributeOfType(String type);
}
