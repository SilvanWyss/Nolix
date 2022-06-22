//package declaration
package ch.nolix.systemapi.guiapi.processproperty;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalStringHelper;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
public enum MouseInputType implements Specified {
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
				throw InvalidArgumentException.forArgumentNameAndArgument("rotation direction", rotationDirection);
		}
	}
	
	//static method
	public static MouseInputType fromSpecification(final BaseNode specification) {
		return valueOf(GlobalStringHelper.toUpperSnakeCase(specification.getOneAttributeHeader()));
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(GlobalStringHelper.toPascalCase(toString())));
	}
}
