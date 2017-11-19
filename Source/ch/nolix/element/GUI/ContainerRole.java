//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.SpecifiedByClassNameAndOneAttribute;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public enum ContainerRole implements SpecifiedByClassNameAndOneAttribute {
	OverallContainer,
	MainContainer;

	//method
	/**
	 * @return the attribute of this container role.
	 */
	public StandardSpecification getAttribute() {
		return StandardSpecification.createSpecificationWithHeaderOnly(toString());
	}
}
