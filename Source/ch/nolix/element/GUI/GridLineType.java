//package declaration
package ch.nolix.element.GUI;

import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.specificationAPI.ISpecifiedEnum;

//enum
public enum GridLineType implements ISpecifiedEnum {
	InnerLines,
	InnerAndOuterLines;

	//static method
	public static GridLineType createFromSpecification(final DocumentNodeoid specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
