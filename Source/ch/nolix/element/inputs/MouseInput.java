//package declaration
package ch.nolix.element.inputs;

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
	MouseWheelButtonPress,
	MouseWheelButtonRelease,
	MouseWheelButtonClick,
	ForwardMouseWheelRotationStep,
	BackwardMouseWheelRotationStep
}
