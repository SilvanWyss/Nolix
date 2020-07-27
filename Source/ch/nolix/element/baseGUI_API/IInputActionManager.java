//package declaration
package ch.nolix.element.baseGUI_API;

//own imports
import ch.nolix.common.functionAPI.I2ElementTaker;
import ch.nolix.common.functionAPI.IAction;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.input.Key;

//interface
public interface IInputActionManager<IAM extends IInputActionManager<IAM>> {
	
	//method
	public default IAM setContinuousKeyPressAction(final IElementTaker<Key> continuousKeyPressAction) {
		
		Validator.assertThat(continuousKeyPressAction).thatIsNamed("continuous key press action").isNotNull();
		
		return setContinuousKeyPressAction((iam, k) -> continuousKeyPressAction.run(k));
	}
	
	//method declaration
	public abstract IAM setContinuousKeyPressAction(final I2ElementTaker<IAM, Key> continuousKeyPressAction);
	
	//method
	public default IAM setLeftMouseButtonClickAction(final IAction leftMouseButtonClickAction) {
		
		Validator.assertThat(leftMouseButtonClickAction).thatIsNamed("left mouse button click action").isNotNull();
		
		return setLeftMouseButtonClickAction(iam -> leftMouseButtonClickAction.run());
	}
	
	//method declaration
	public abstract IAM setLeftMouseButtonClickAction(IElementTaker<IAM> leftMouseButtonClickAction);
	
	//method
	public default IAM setLeftMouseButtonPressAction(final IAction leftMouseButtonPressAction) {
		
		Validator.assertThat(leftMouseButtonPressAction).thatIsNamed("left mouse button press action").isNotNull();
		
		return setLeftMouseButtonPressAction(iam -> leftMouseButtonPressAction.run());
	}
	
	//method declaration
	public abstract IAM setLeftMouseButtonPressAction(IElementTaker<IAM> leftMouseButtonPressAction);
	
	//method
	public default IAM setLeftMouseButtonReleaseAction(final IAction leftMouseButtonReleaseAction) {
		
		Validator.assertThat(leftMouseButtonReleaseAction).thatIsNamed("left mouse button release action").isNotNull();
		
		return setLeftMouseButtonReleaseAction(iam -> leftMouseButtonReleaseAction.run());
	}
	
	//method declaration
	public abstract IAM setLeftMouseButtonReleaseAction(IElementTaker<IAM> leftMouseButtonReleaseAction);
	
	//method
	public default IAM setMouseMoveAction(final IAction mouseMoveAction) {
		
		Validator.assertThat(mouseMoveAction).thatIsNamed("mouse move action").isNotNull();
		
		return setMouseMoveAction(iam -> mouseMoveAction.run());
	}
	
	//method declaration
	public abstract IAM setMouseMoveAction(IElementTaker<IAM> mouseMoveAction);
	
	//method
	public default IAM setMouseWheelClickAction(final IAction mouseWheelClickAction) {
		
		Validator.assertThat(mouseWheelClickAction).thatIsNamed("mouse wheel click action").isNotNull();
		
		return setMouseWheelClickAction(iam -> mouseWheelClickAction.run());
	}
	
	//method declaration
	public abstract IAM setMouseWheelClickAction(IElementTaker<IAM> mouseWheelClickAction);
	
	//method
	public default IAM setMouseWheelPressAction(final IAction mouseWheelPressAction) {
		
		Validator.assertThat(mouseWheelPressAction).thatIsNamed("mouse wheel press action").isNotNull();
		
		return setMouseWheelPressAction(iam -> mouseWheelPressAction.run());
	}
	
	//method declaration
	public abstract IAM setMouseWheelPressAction(IElementTaker<IAM> mouseWheelPressAction);
	
	//method
	public default IAM setMouseWheelReleaseAction(final IAction mouseWheelReleaseAction) {
		
		Validator.assertThat(mouseWheelReleaseAction).thatIsNamed("mouse wheel release action").isNotNull();
		
		return setMouseWheelReleaseAction(iam -> mouseWheelReleaseAction.run());
	}
	
	//method declaration
	public abstract IAM setMouseWheelReleaseAction(IElementTaker<IAM> mouseWheelReleaseAction);
	
	//method
	public default IAM setRightMouseButtonClickAction(final IAction rightMouseButtonClickAction) {
		
		Validator.assertThat(rightMouseButtonClickAction).thatIsNamed("right mouse button click action").isNotNull();
		
		return setRightMouseButtonClickAction(iam -> rightMouseButtonClickAction.run());
	}
	
	//method declaration
	public abstract IAM setRightMouseButtonClickAction(IElementTaker<IAM> rightMouseButtonClickAction);
	
	//method
	public default IAM setRightMouseButtonPressAction(final IAction rightMouseButtonPressAction) {
		
		Validator.assertThat(rightMouseButtonPressAction).thatIsNamed("right mouse button press action").isNotNull();
		
		return setRightMouseButtonPressAction(iam -> rightMouseButtonPressAction.run());
	}
	
	//method declaration
	public abstract IAM setRightMouseButtonPressAction(IElementTaker<IAM> rightMouseButtonPressAction);
	
	//method
	public default IAM setRightMouseButtonReleaseAction(final IAction rightMouseButtonReleaseAction) {
		
		Validator.assertThat(rightMouseButtonReleaseAction).thatIsNamed("right mouse button release action").isNotNull();
		
		return setRightMouseButtonReleaseAction(iam -> rightMouseButtonReleaseAction.run());
	}
	
	//method declaration
	public abstract IAM setRightMouseButtonReleaseAction(IElementTaker<IAM> rightMouseButtonReleaseAction);
}
