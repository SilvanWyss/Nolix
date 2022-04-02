//package declaration
package ch.nolix.element.elementenum;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.elementapi.baseapi.IElement;

//enum
/**
 * A direction defines the way
 * from a square's point to another point of the square.
 * 
 * A direction depends on the order of the start point and the end point.
 * 
 * @author Silvan Wyss
 * @date 2017-09-16
 */
public enum Direction implements IElement<Direction> {
	LEFT_TO_RIGHT,
	RIGHT_TO_LEFT,
	TOP_TO_BOTTOM,
	BOTTOM_TO_TOP,
	TOP_LEFT_TO_BOTTOM_RIGHT,
	BOTTOM_RIGHT_TO_TOP_LEFT,
	TOP_RIGHT_TO_BOTTOM_LEFT,
	BOTTOM_LEFT_TO_TOP_RIGHT;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
