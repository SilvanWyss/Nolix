//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
@AllowDefaultMethodsAsDesignPattern
public interface IExtendedInputActionManager<IAM extends IExtendedInputActionManager<IAM>>
extends IInputActionManager<IAM> {
	
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
	default IAM setRightMouseButtonClickAction(final IAction rightMouseButtonClickAction) {
		
		if (rightMouseButtonClickAction == null) {
			throw new IllegalArgumentException("right mouse button click action");
		}
		
		return setRightMouseButtonClickAction(iam -> rightMouseButtonClickAction.run());
	}
	
	//method declaration
	IAM setRightMouseButtonClickAction(IElementTaker<IAM> rightMouseButtonClickAction);
}
