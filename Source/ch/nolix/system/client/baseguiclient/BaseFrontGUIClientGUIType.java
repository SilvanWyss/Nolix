//package declaration
package ch.nolix.system.client.baseguiclient;

import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.elementapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @date 2018-09-16
 */
public enum BaseFrontGUIClientGUIType implements IElement<BaseFrontGUIClientGUIType> {
	CANVAS_GUI;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link BaseFrontGUIClientGUIType} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static BaseFrontGUIClientGUIType fromSpecification(final BaseNode specification) {
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
