//package declaration
package ch.nolix.systemapi.guiapi.structureproperty;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
/**
 * @author Silvan Wyss
 * @date 2019-05-18
 */
public enum ExtendedContentPosition implements Specified {
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
	public static ExtendedContentPosition fromSpecification(final INode<?> specification) {
		return valueOf(GlobalStringHelper.toUpperSnakeCase(specification.getSingleChildNodeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<INode<?>> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
