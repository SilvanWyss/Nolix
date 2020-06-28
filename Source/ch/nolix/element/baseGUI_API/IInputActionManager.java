//package declaration
package ch.nolix.element.baseGUI_API;

//own import
import ch.nolix.common.functionAPI.IElementTaker;

//interface
public interface IInputActionManager<IAM extends IInputActionManager<IAM>> {
	
	//method declaration
	public abstract IAM setLeftMouseButtonClickAction(IElementTaker<IAM> leftMouseButtonClickAction);
	
	//method declaration
	public abstract IAM setLeftMouseButtonPressAction(IElementTaker<IAM> leftMouseButtonPressAction);
	
	//method declaration
	public abstract IAM setLeftMouseButtonReleaseAction(IElementTaker<IAM> leftMouseButtonReleaseAction);
	
	//method declaration
	public abstract IAM setMouseMoveAction(IElementTaker<IAM> mouseMoveAction);
	
	//method declaration
	public abstract IAM setMouseWheelClickAction(IElementTaker<IAM> mouseWheelClickAction);
	
	//method declaration
	public abstract IAM setMouseWheelPressAction(IElementTaker<IAM> mouseWheelPressAction);
	
	//method declaration
	public abstract IAM setMouseWheelReleaseAction(IElementTaker<IAM> mouseWheelReleaseAction);
	
	//method declaration
	public abstract IAM setRightMouseButtonClickAction(IElementTaker<IAM> rightMouseButtonClickAction);
	
	//method declaration
	public abstract IAM setRightMouseButtonPressAction(IElementTaker<IAM> rightMouseButtonPressAction);
	
	//method declaration
	public abstract IAM setRightMouseButtonReleaseAction(IElementTaker<IAM> rightMouseButtonReleaseAction);
}
