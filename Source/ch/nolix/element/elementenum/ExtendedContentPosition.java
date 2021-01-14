//package declaration
package ch.nolix.element.elementenum;

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
 * @date 2019-05-18
 * @lines 50
 */
public enum ExtendedContentPosition implements IElement {
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
	
	//constant
	public static final String TYPE_NAME = "ExtendedContentPosition";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ExtendedContentPosition} from the given specification.
	 * @throws InvalidArgumentException
	 * if the given specification does not represent a {@link ExtendedContentPosition}.
	 */
	public static ExtendedContentPosition fromSpecification(final BaseNode specification) {
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		return Node.withHeader(StringHelper.toPascalCase(toString())).intoList();
	}
}
