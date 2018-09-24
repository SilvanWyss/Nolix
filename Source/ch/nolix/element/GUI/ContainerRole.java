//package declaration
package ch.nolix.element.GUI;

import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.specificationAPI.ISpecifiedEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public enum ContainerRole implements ISpecifiedEnum {
	OverallContainer,
	MainContainer;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ContainerRole} from the given specification.
	 */
	public static ContainerRole createFromSpecification(final DocumentNodeoid specification) {
		return ContainerRole.valueOf(specification.getOneAttributeAsString());
	}
}
