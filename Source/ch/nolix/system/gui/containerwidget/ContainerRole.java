//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public enum ContainerRole implements Specified {
	OVERALL_CONTAINTER,
	MAIN_CONTENT_CONTAINER,
	DIALOG_CONTAINER,
	TITLE_CONTAINER;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ContainerRole} from the given specification.
	 */
	public static ContainerRole fromSpecification(final BaseNode specification) {
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
