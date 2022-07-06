//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.I2ElementTaker;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;
import ch.nolix.systemapi.guiapi.inputapi.Key;

//interface
@AllowDefaultMethodsAsDesignPattern
public interface IInputActionManager<IAM extends IInputActionManager<IAM>> {
	
	//method
	default IAM setKeyDownAction(final IElementTaker<Key> keyDownAction) {
		
		if (keyDownAction == null) {
			throw new IllegalArgumentException("continuous key press action");
		}
		
		return setKeyDownAction((iam, k) -> keyDownAction.run(k));
	}
	
	//method declaration
	IAM setKeyDownAction(final I2ElementTaker<IAM, Key> keyDownAction);
	
	//method
	default IAM setLeftMouseButtonClickAction(final IAction leftMouseButtonClickAction) {
		
		if (leftMouseButtonClickAction == null) {
			throw new IllegalArgumentException("left mouse button click action");
		}
		
		return setLeftMouseButtonClickAction(iam -> leftMouseButtonClickAction.run());
	}
	
	//method declaration
	IAM setLeftMouseButtonClickAction(IElementTaker<IAM> leftMouseButtonClickAction);
	
	//method
	default IAM setLeftMouseButtonPressAction(final IAction leftMouseButtonPressAction) {
		
		if (leftMouseButtonPressAction == null) {
			throw new IllegalArgumentException("left mouse button press action");
		}
		
		return setLeftMouseButtonPressAction(iam -> leftMouseButtonPressAction.run());
	}
	
	//method declaration
	IAM setLeftMouseButtonPressAction(IElementTaker<IAM> leftMouseButtonPressAction);
	
	//method
	default IAM setLeftMouseButtonReleaseAction(final IAction leftMouseButtonReleaseAction) {
		
		if (leftMouseButtonReleaseAction == null) {
			throw new IllegalArgumentException("left mouse button release action");
		}
		
		return setLeftMouseButtonReleaseAction(iam -> leftMouseButtonReleaseAction.run());
	}
	
	//method declaration
	IAM setLeftMouseButtonReleaseAction(IElementTaker<IAM> leftMouseButtonReleaseAction);
	
	//method
	default IAM setMouseMoveAction(final IAction mouseMoveAction) {
		
		if (mouseMoveAction == null) {
			throw new IllegalArgumentException("mouse move action");
		}
		
		return setMouseMoveAction(iam -> mouseMoveAction.run());
	}
	
	//method declaration
	IAM setMouseMoveAction(IElementTaker<IAM> mouseMoveAction);
	
	//method
	default IAM setMouseWheelClickAction(final IAction mouseWheelClickAction) {
		
		if (mouseWheelClickAction == null) {
			throw new IllegalArgumentException("mouse wheel click action");
		}
		
		return setMouseWheelClickAction(iam -> mouseWheelClickAction.run());
	}
	
	//method declaration
	IAM setMouseWheelClickAction(IElementTaker<IAM> mouseWheelClickAction);
	
	//method
	default IAM setMouseWheelPressAction(final IAction mouseWheelPressAction) {
		
		if (mouseWheelPressAction == null) {
			throw new IllegalArgumentException("mouse wheel press action");
		}
		
		return setMouseWheelPressAction(iam -> mouseWheelPressAction.run());
	}
	
	//method declaration
	IAM setMouseWheelPressAction(IElementTaker<IAM> mouseWheelPressAction);
	
	//method
	default IAM setMouseWheelReleaseAction(final IAction mouseWheelReleaseAction) {
		
		if (mouseWheelReleaseAction == null) {
			throw new IllegalArgumentException("mouse wheel release action");
		}
		
		return setMouseWheelReleaseAction(iam -> mouseWheelReleaseAction.run());
	}
	
	//method declaration
	IAM setMouseWheelReleaseAction(IElementTaker<IAM> mouseWheelReleaseAction);
	
	//method
	default IAM setRightMouseButtonClickAction(final IAction rightMouseButtonClickAction) {
		
		if (rightMouseButtonClickAction == null) {
			throw new IllegalArgumentException("right mouse button click action");
		}
		
		return setRightMouseButtonClickAction(iam -> rightMouseButtonClickAction.run());
	}
	
	//method declaration
	IAM setRightMouseButtonClickAction(IElementTaker<IAM> rightMouseButtonClickAction);
	
	//method
	default IAM setRightMouseButtonPressAction(final IAction rightMouseButtonPressAction) {
		
		if (rightMouseButtonPressAction == null) {
			throw new IllegalArgumentException("right mouse button press action");
		}
		
		return setRightMouseButtonPressAction(iam -> rightMouseButtonPressAction.run());
	}
	
	//method declaration
	IAM setRightMouseButtonPressAction(IElementTaker<IAM> rightMouseButtonPressAction);
	
	//method
	default IAM setRightMouseButtonReleaseAction(final IAction rightMouseButtonReleaseAction) {
		
		if (rightMouseButtonReleaseAction == null) {
			throw new IllegalArgumentException("right mouse button release action");
		}
		
		return setRightMouseButtonReleaseAction(iam -> rightMouseButtonReleaseAction.run());
	}
	
	//method declaration
	IAM setRightMouseButtonReleaseAction(IElementTaker<IAM> rightMouseButtonReleaseAction);
}
