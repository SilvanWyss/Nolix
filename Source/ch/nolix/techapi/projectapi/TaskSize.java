//package declaration
package ch.nolix.techapi.projectapi;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementapi.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 20
 */
public enum TaskSize implements IElementEnum {
	Small,
	Medium,
	Big;

	//static method
	/**
	 * @param specification
	 * @return a new {@link TaskSize} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static TaskSize fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeHeader());
	}
}
