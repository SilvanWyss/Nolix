//package declaration
package ch.nolix.element.input;

//own import
import ch.nolix.element.smartElementAPI.ISmartElementEnum;

//enum
public enum MouseInput implements IInput<MouseInput>, ISmartElementEnum<MouseInput> {
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
	BackwardMouseWheelRotationStep
}
