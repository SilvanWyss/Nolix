//package declaration
package ch.nolix.system.elementenum;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.elementapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @date 2019-05-18
 */
public enum ExtendedContentPosition implements IElement<ExtendedContentPosition> {
	TOP_LEFT,
	TOP,
	TOP_RIGHT,
	LEFT,
	CENTER,
	RIGHT,
	BOTTOM_LEFT,
	BOTTOM,
	BOTTOM_RIGHT,
	FREE;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ExtendedContentPosition} from the given specification.
	 * @throws InvalidArgumentException
	 * if the given specification does not represent a {@link ExtendedContentPosition}.
	 */
	public static ExtendedContentPosition fromSpecification(final BaseNode specification) {
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
