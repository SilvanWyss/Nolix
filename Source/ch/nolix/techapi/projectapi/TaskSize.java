//package declaration
package ch.nolix.techapi.projectapi;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 40
 */
public enum TaskSize implements IElementEnum {
	SMALL,
	MEDIUM,
	BIG;

	//static method
	/**
	 * @param specification
	 * @return a new {@link TaskSize} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static TaskSize fromSpecification(final BaseNode specification) {
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
