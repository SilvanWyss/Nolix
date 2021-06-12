//package declaration
package ch.nolix.element.elementenum;

//own imports
import ch.nolix.common.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.elementapi.IElement;

//enum
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 * @lines 40
 */
public enum ContentPosition implements IElement<ContentPosition> {
	TOP_LEFT,
	TOP,
	TOP_RIGHT,
	LEFT,
	CENTER,
	RIGHT,
	BOTTOM_LEFT,
	BOTTOM,
	BOTTOM_RIGHT;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ContentPosition} from the given specification.
	 * @throws InvalidArgumentException if the given specification does not represent a {@link ContentPosition}.
	 */
	public static ContentPosition fromSpecification(final BaseNode specification) {
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
