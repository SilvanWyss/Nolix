//package declaration
package ch.nolix.element.task;

//own imports
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 30
 */
public enum TaskSize implements IElementEnum {
	Small,
	Medium,
	Big;

	//static method
	/**
	 * @param specification
	 * @return a new task size from the given specification.
	 * @throws InvalidArgumentException
	 * if the given specification is not valid.
	 */
	public static TaskSize createFromSpecification(
		final DocumentNodeoid specification
	) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
