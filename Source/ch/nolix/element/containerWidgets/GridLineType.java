//package declaration
package ch.nolix.element.containerWidgets;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementAPI.IElementEnum;

//enum
public enum GridLineType implements IElementEnum {
	InnerLines,
	InnerAndOuterLines;

	//static method
	public static GridLineType fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
