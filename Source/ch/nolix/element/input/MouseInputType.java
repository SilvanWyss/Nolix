//package declaration
package ch.nolix.element.input;

//own imports
import ch.nolix.common.commontypehelper.StringHelper;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.elementapi.IElement;
import ch.nolix.element.elementenum.RotationDirection;

//enum
public enum MouseInputType implements IElement {
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
				throw new InvalidArgumentException("rotation direction", rotationDirection, "is not valid");
		}
	}
	
	//static method
	public static MouseInputType fromSpecification(final BaseNode specification) {
		return valueOf(StringHelper.toCapitalSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	@Override
	public LinkedList<Node> getAttributes() {
		return Node.withHeader(StringHelper.toPascalCase(toString())).intoList();
	}
}
