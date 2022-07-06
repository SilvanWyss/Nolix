//package declaration
package ch.nolix.systemapi.guiapi.processproperty;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum MouseInputType {
	MOUSE_MOVE,
	LEFT_MOUSE_BUTTON_PRESS,
	LEFT_MOUSE_BUTTON_RELEASE,
	LEFT_MOUSE_BUTTON_CLICK,
	RIGHT_MOUSE_BUTTON_PRESS,
	RIGHT_MOUSE_BUTTON_RELEASE,
	RIGHT_MOUSE_BUTTON_CLICK,
	MOUSE_WHEEL_PRESS,
	MOUSE_WHEEL_RELEASE,
	MOUSE_WHEEL_CLICK,
	FORWARD_MOUSE_WHEEL_ROTATION_STEP,
	BACKWARD_MOUSE_WHEEL_ROTATION_STEP;
	
	//static method
	public static MouseInputType createMouseWheelRotationStepFrom(final RotationDirection rotationDirection) {
		switch (rotationDirection) {
			case FORWARD:
				return FORWARD_MOUSE_WHEEL_ROTATION_STEP;
			case BACKWARD:
				return BACKWARD_MOUSE_WHEEL_ROTATION_STEP;
			default:
				throw new IllegalArgumentException("The given rotation direction is not valid.");
		}
	}
	
	//static method
	public static MouseInputType fromSpecification(final INode<?> specification) {
		return valueOf(specification.getSingleChildNodeHeader());
	}
}
