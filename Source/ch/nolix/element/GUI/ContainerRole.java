//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.ISpecifiedEnum;

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
	public static ContainerRole createFromSpecification(final Specification specification) {
		return ContainerRole.valueOf(specification.getOneAttributeAsString());
	}
}
