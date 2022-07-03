//package declaration
package ch.nolix.systemapi.guiapi.structureproperty;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
/**
 * A {@link DirectionInRectangle} defines the way between two points of a rectangle.
 * A {@link DirectionInRectangle} does not (!) depend on the order of the start point and end point.
 * 
 * @author Silvan Wyss
 * @date 2017-09-16
 */
public enum DirectionInRectangle implements Specified {
	HORIZONTAL,
	VERTICAL,
	DIAGONAL_UP,
	DIAGONAL_DOWN;

	//method
	/**
	 * @param specification
	 * @return a new {@link DirectionInRectangle} from the given specification.
	 */
	public static DirectionInRectangle fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<INode<?>> list) {
		list.addAtEnd(Node.withHeader(name()));
	}
}
