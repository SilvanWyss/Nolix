//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.systemapi.elementapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-09-01
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
		return valueOf(GlobalStringHelper.toUpperSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
