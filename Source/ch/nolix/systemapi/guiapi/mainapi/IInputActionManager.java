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
	default IAM setKeyPressAction(final IElementTaker<Key> keyPressAction) {
		
		if (keyPressAction == null) {
			throw new IllegalArgumentException("key press action");
		}
		
		return setKeyPressAction((iam, k) -> keyPressAction.run(k));
	}
	
	//method declaration
	IAM setKeyPressAction(final I2ElementTaker<IAM, Key> keyPressAction);
	
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
