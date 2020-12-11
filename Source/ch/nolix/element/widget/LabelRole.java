//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 40
 */
public enum LabelRole implements IElementEnum {
	TITLE,
	SUB_TITLE,
	LEVEL1_HEADER,
	LEVEL2_HEADER,
	LEVEL3_HEADER,
	LEVEL4_HEADER,
	PARAGRAPH_HEADER,
	MAIN_TEXT,
	ERROR_TEXT;

	//static method
	/**
	 * @param specification
	 * @return a new {@link LabelRole} from the given specification.
	 */
	public static LabelRole fromSpecification(
		final BaseNode specification
	) {
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		return Node.withHeader(StringHelper.toPascalCase(toString())).intoList();
	}
}
