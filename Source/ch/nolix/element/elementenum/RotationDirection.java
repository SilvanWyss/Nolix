//package declaration
package ch.nolix.element.elementenum;

import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementapi.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2019-07
 * @lines 40
 */
public enum RotationDirection implements IElementEnum {
	Forward,
	Backward;
	
	//constant
	public static final String TYPE_NAME = "DirectionOfRotation";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link RotationDirection} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static RotationDirection fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeHeader());
	}
	
	//method
	/**
	 * @return 1 if the current {@link RotationDirection} is {@link RotationDirection#Forward},
	 * -1 if the current {@link RotationDirection} is {@link RotationDirection#Backward}.
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
