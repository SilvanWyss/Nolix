//package declaration
package ch.nolix.element.baseGUI_API;

//own imports
import ch.nolix.common.canvasAPI.IOccupiableHoverableCanvas;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.validator.Validator;

//interface
public interface IOccupiableCanvasInputActionManager<OCIAM extends IOccupiableCanvasInputActionManager<OCIAM>>
extends IInputActionManager<OCIAM>, IOccupiableHoverableCanvas {
	
	//method
	public default OCIAM setLeftMouseButtonClickActionOnFreeContentArea(
		final IElementTaker<OCIAM> leftMouseButtonClickAction
	) {
		
		Validator.assertThat(leftMouseButtonClickAction).thatIsNamed("left mouse button click action").isNotNull();
		
		return
		setLeftMouseButtonClickAction(
			ociam -> {
				if (freeContentAreaIsUnderCursor()) {
					leftMouseButtonClickAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setLeftMouseButtonPressActionOnFreeContentArea(
		final IElementTaker<OCIAM> leftMouseButtonPressAction
	) {
		
		Validator.assertThat(leftMouseButtonPressAction).thatIsNamed("left mouse button press action").isNotNull();
		
		return
		setLeftMouseButtonPressAction(
			ociam -> {
				if (freeContentAreaIsUnderCursor()) {
					leftMouseButtonPressAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setLeftMouseButtonReleaseActionOnFreeContentArea(
		final IElementTaker<OCIAM> leftMouseButtonReleaseAction
	) {
		
		Validator.assertThat(leftMouseButtonReleaseAction).thatIsNamed("left mouse button release action").isNotNull();
		
		return
		setLeftMouseButtonReleaseAction(
			ociam ->  {
				if (freeContentAreaIsUnderCursor()) {
					leftMouseButtonReleaseAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setMouseMoveActionOnFreeContentArea(final IElementTaker<OCIAM> mouseMoveAction) {
		
		Validator.assertThat(mouseMoveAction).thatIsNamed("mouse move action").isNotNull();
		
		return
		setMouseMoveAction(
			ociam -> {
				if (freeContentAreaIsUnderCursor()) {
					mouseMoveAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setMouseWheelClickActionOnFreeContentArea(
		final IElementTaker<OCIAM> mouseWheelClickAction
	) {
		
		Validator.assertThat(mouseWheelClickAction).thatIsNamed("mouse wheel click action").isNotNull();
		
		return
		setMouseWheelClickAction(
			ociam -> {
				if (freeContentAreaIsUnderCursor()) {
					mouseWheelClickAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setMouseWheelPressActionOnFreeContentArea(
		final IElementTaker<OCIAM> mouseWheelPressAction
	) {
		
		Validator.assertThat(mouseWheelPressAction).thatIsNamed("mouse wheel press action").isNotNull();
		
		return
		setMouseWheelPressAction(
			ociam -> {
				if (freeContentAreaIsUnderCursor()) {
					mouseWheelPressAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setMouseWheelReleaseActionOnFreeContentArea(
		final IElementTaker<OCIAM> mouseWheelReleaseAction
	) {
		
		Validator.assertThat(mouseWheelReleaseAction).thatIsNamed("mouse wheel release action").isNotNull();
		
		return
		setMouseWheelReleaseAction(
			ociam ->  {
				if (freeContentAreaIsUnderCursor()) {
					mouseWheelReleaseAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setRightMouseButtonClickActionOnFreeContentArea(
		final IElementTaker<OCIAM> rightMouseButtonClickAction
	) {
		
		Validator.assertThat(rightMouseButtonClickAction).thatIsNamed("right mouse button click action").isNotNull();
		
		return
		setRightMouseButtonClickAction(
			ociam -> {
				if (freeContentAreaIsUnderCursor()) {
					rightMouseButtonClickAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setRightMouseButtonPressActionOnFreeContentArea(
		final IElementTaker<OCIAM> rightMouseButtonPressAction
	) {
		
		Validator.assertThat(rightMouseButtonPressAction).thatIsNamed("right mouse button press action").isNotNull();
		
		return
		setRightMouseButtonPressAction(
			ociam -> {
				if (freeContentAreaIsUnderCursor()) {
					rightMouseButtonPressAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setRightMouseButtonReleaseActionOnFreeContentArea(
		final IElementTaker<OCIAM> rightMouseButtonReleaseAction
	) {
		
		Validator.assertThat(rightMouseButtonReleaseAction).thatIsNamed("right mouse button release action").isNotNull();
		
		return
		setRightMouseButtonReleaseAction(
			ociam ->  {
				if (freeContentAreaIsUnderCursor()) {
					rightMouseButtonReleaseAction.run(ociam);
				}
			}
		);
	}
}
