//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.specificationAPI.IElementEnum;

//enum
public enum GridLineType implements IElementEnum {
	InnerLines,
	InnerAndOuterLines;

	//static method
	public static GridLineType createFromSpecification(final DocumentNodeoid specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
