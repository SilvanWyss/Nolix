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
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 50
 */
public enum ExtendedContentPosition implements IElementEnum {
	LEFT_TOP,
	LEFT,
	LEFT_BOTTOM,
	TOP,
	CENTER,
	BOTTOM,
	RIGHT_TOP,
	RIGHT,
	RIGHT_BOTTOM,
	FREE;
	
	//constant
	public static final String TYPE_NAME = "ExtendedContentPosition";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ExtendedContentPosition} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
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
