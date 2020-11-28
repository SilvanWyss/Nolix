//package declaration
package ch.nolix.element.elementenum;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElementEnum;

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
public enum UniDirection implements IElementEnum {
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
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		return new Node(StringHelper.toPascalCase(toString())).intoList();
	}
}
