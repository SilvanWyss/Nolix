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
 * @date 2016-09-01
 * @lines 40
 */
public enum ContentPosition implements IElementEnum {
	TOP_LEFT,
	TOP,
	TOP_RIGHT,
	LEFT,
	CENTER,
	RIGHT,
	BOTTOM_LEFT,
	BOTTOM,
	BOTTOM_RIGHT;
	
	//constant
	public static final String TYPE_NAME = "ContentPosition";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ContentPosition} from the given specification.
	 * @throws InvalidArgumentException if the given specification does not represent a {@link ContentPosition}.
	 */
	public static ContentPosition fromSpecification(final BaseNode specification) {
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
