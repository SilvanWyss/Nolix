//package declaration
package ch.nolix.systemapi.guiapi.structureproperty;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
/**
 * A {@link DirectionInRectangle} defines the way between two points of a square.
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
	public static DirectionInRectangle fromSpecification(final BaseNode specification) {
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
