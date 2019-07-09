//package declaration
package ch.nolix.element.containerWidget;

//own imports
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.element.elementAPI.IElementEnum;

//enum
public enum GridLineType implements IElementEnum {
	InnerLines,
	InnerAndOuterLines;

	//static method
	public static GridLineType createFromSpecification(final DocumentNodeoid specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
