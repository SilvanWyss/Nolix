//package declaration
package ch.nolix.system.baseguiclient;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 30
 */
public enum BaseFrontGUIClientGUIType implements IElement {
	WIDGET_GUI,
	CANVAS_GUI;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link BaseFrontGUIClientGUIType} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static BaseFrontGUIClientGUIType fromSpecification(final BaseNode specification) {
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
