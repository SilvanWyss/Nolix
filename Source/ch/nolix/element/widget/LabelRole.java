//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 40
 */
public enum LabelRole implements IElement {
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
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(StringHelper.toPascalCase(toString())));
	}
}
