//package declaration
package ch.nolix.element.elementEnums;

//own imports
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 30
 */
public enum DirectionOfRotation implements IElementEnum {
	Forward,
	Backward;
	
	//constant
	public static final String TYPE_NAME = "DirectionOfRotation";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link DirectionOfRotation} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static DirectionOfRotation createFromSpecification(final DocumentNodeoid specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
