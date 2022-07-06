//package declaration
package ch.nolix.systemapi.guiapi.processproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
/**
 * @author Silvan Wyss
 * @date 2019-07-28
 */
public enum RotationDirection {
	FORWARD,
	BACKWARD;
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link RotationDirection} from the given specification.
	 * @throws RuntimeException if the given specification does not represent a {@link RotationDirection}.
	 */
	public static RotationDirection fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
	
	//method
	/**
	 * @return an int representation of the current {@link RotationDirection}.
	 * 1 if the current {@link RotationDirection} is {@link RotationDirection#FORWARD},
	 * -1 if the current {@link RotationDirection} is {@link RotationDirection#BACKWARD}.
	 */
	public int toInt() {
		
		//Enumerates the current DirectionOfRotation.
		switch (this) {
			case FORWARD:
				return 1;
			case BACKWARD:
				return -1;
			default:
				throw new IllegalArgumentException("The current RotationDirection is not valid.");
		}
	}
}
