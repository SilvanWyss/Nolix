//package declaration
package ch.nolix.element.task;

import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.specificationAPI.ISpecifiedEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 30
 */
public enum TaskSize implements ISpecifiedEnum {
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
