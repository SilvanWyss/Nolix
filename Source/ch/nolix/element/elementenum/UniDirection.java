//package declaration
package ch.nolix.element.elementenum;

import ch.nolix.common.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.elementapi.IElement;

//enum
/**
 * A {@link UniDirection} defines the way between two points of a square.
 * 
 * A {@link UniDirection} does not (!) depend on the order of the start point and end point.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 40
 */
public enum UniDirection implements IElement {
	HORIZONTAL,
	VERTICAL,
	DIAGONAL_UP,
	DIAGONAL_DOWN;

	//method
	/**
	 * @param specification
	 * @return a new {@link UniDirection} from the given specification.
	 */
	public static UniDirection fromSpecification(final BaseNode specification) {
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
