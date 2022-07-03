//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public enum LabelRole implements Specified {
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
	public static LabelRole fromSpecification(final INode<?> specification) {
		return valueOf(GlobalStringHelper.toUpperSnakeCase(specification.getSingleChildNodeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<INode<?>> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
