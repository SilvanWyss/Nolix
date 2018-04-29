//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specificationInterfaces.ISpecifiedEnum;

//enum
public enum GridLineType implements ISpecifiedEnum {
	InnerLines,
	InnerAndOuterLines;

	//static method
	public static GridLineType createFromSpecification(final Specification specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
