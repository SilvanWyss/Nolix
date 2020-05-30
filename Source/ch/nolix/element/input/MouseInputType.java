//package declaration
package ch.nolix.element.input;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.smartElementAPI.ISmartElementEnum;

//enum
public enum MouseInputType implements IInput<MouseInputType>, ISmartElementEnum<MouseInputType> {
	MouseMove,
	LeftMouseButtonPress,
	LeftMouseButtonRelease,
	LeftMouseButtonClick,
	RightMouseButtonPress,
	RightMouseButtonRelease,
	RightMouseButtonClick,
	MouseWheelPress,
	MouseWheelRelease,
	MouseWheelClick,
	ForwardMouseWheelRotationStep,
	BackwardMouseWheelRotationStep;
	
	//static method
	public static MouseInputType fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
