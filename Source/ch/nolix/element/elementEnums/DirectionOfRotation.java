//package declaration
package ch.nolix.element.elementEnums;

//own imports
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementAPI.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 40
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
	public static DirectionOfRotation fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
	
	//method
	/**
	 * @return 1 if the current {@link DirectionOfRotation} is {@link DirectionOfRotation#Forward},
	 * -1 if the current {@link DirectionOfRotation} is {@link DirectionOfRotation#Backward}.
	 */
	public int toInt() {
		
		//Enumerates the current DirectionOfRotation.
		switch (this) {
			case Forward:
				return 1;
			case Backward:
				return -1;
			default:
				throw new InvalidArgumentException(this);
		}
	}
}
