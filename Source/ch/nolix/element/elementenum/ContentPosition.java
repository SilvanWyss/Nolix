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
 * @month 2016-08
 * @lines 40
 */
public enum ContentPosition implements IElementEnum {
	LEFT_TOP,
	LEFT,
	LEFT_BOTTOM,
	TOP,
	CENTER,
	BOTTOM,
	RIGHT_TOP,
	RIGHT,
	RIGHT_BOTTOM;
	
	//constant
	public static final String TYPE_NAME = "ContentPosition";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ContentPosition} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
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
		return new Node(StringHelper.toPascalCase(toString())).intoList();
	}
}
