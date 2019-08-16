//package declaration
package ch.nolix.element.containerWidgets;

import ch.nolix.core.node.BaseNode;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
public enum GridLineType implements IElementEnum {
	InnerLines,
	InnerAndOuterLines;

	//static method
	public static GridLineType createFromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
