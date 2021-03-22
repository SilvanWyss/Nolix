//package declaration
package ch.nolix.element.gui.containerwidget;

import ch.nolix.common.commontype.commontypehelper.GlobalStringHelper;
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
public enum ContainerRole implements IElement {
	OVERALL_CONTAINTER,
	MAINT_CONTAINER,
	DIALOG_CONTAINER,
	TITLE_CONTAINER;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ContainerRole} from the given specification.
	 */
	public static ContainerRole fromSpecification(final BaseNode specification) {
		return valueOf(GlobalStringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
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
