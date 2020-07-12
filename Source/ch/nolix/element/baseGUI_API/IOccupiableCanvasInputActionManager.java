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
	public default OCIAM setLeftMouseButtonClickActiononFreeArea(
		final IElementTaker<OCIAM> leftMouseButtonClickAction
	) {
		
		Validator.assertThat(leftMouseButtonClickAction).thatIsNamed("left mouse button click action").isNotNull();
		
		return
		setLeftMouseButtonClickAction(
			ociam -> {
				if (freeAreaIsUnderCursor()) {
					leftMouseButtonClickAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setLeftMouseButtonPressActiononFreeArea(
		final IElementTaker<OCIAM> leftMouseButtonPressAction
	) {
		
		Validator.assertThat(leftMouseButtonPressAction).thatIsNamed("left mouse button press action").isNotNull();
		
		return
		setLeftMouseButtonPressAction(
			ociam -> {
				if (freeAreaIsUnderCursor()) {
					leftMouseButtonPressAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setLeftMouseButtonReleaseActiononFreeArea(
		final IElementTaker<OCIAM> leftMouseButtonReleaseAction
	) {
		
		Validator.assertThat(leftMouseButtonReleaseAction).thatIsNamed("left mouse button release action").isNotNull();
		
		return
		setLeftMouseButtonReleaseAction(
			ociam ->  {
				if (freeAreaIsUnderCursor()) {
					leftMouseButtonReleaseAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setMouseMoveActiononFreeArea(final IElementTaker<OCIAM> mouseMoveAction) {
		
		Validator.assertThat(mouseMoveAction).thatIsNamed("mouse move action").isNotNull();
		
		return
		setMouseMoveAction(
			ociam -> {
				if (freeAreaIsUnderCursor()) {
					mouseMoveAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setMouseWheelClickActiononFreeArea(
		final IElementTaker<OCIAM> mouseWheelClickAction
	) {
		
		Validator.assertThat(mouseWheelClickAction).thatIsNamed("mouse wheel click action").isNotNull();
		
		return
		setMouseWheelClickAction(
			ociam -> {
				if (freeAreaIsUnderCursor()) {
					mouseWheelClickAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setMouseWheelPressActiononFreeArea(
		final IElementTaker<OCIAM> mouseWheelPressAction
	) {
		
		Validator.assertThat(mouseWheelPressAction).thatIsNamed("mouse wheel press action").isNotNull();
		
		return
		setMouseWheelPressAction(
			ociam -> {
				if (freeAreaIsUnderCursor()) {
					mouseWheelPressAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setMouseWheelReleaseActiononFreeArea(
		final IElementTaker<OCIAM> mouseWheelReleaseAction
	) {
		
		Validator.assertThat(mouseWheelReleaseAction).thatIsNamed("mouse wheel release action").isNotNull();
		
		return
		setMouseWheelReleaseAction(
			ociam ->  {
				if (freeAreaIsUnderCursor()) {
					mouseWheelReleaseAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setRightMouseButtonClickActiononFreeArea(
		final IElementTaker<OCIAM> rightMouseButtonClickAction
	) {
		
		Validator.assertThat(rightMouseButtonClickAction).thatIsNamed("right mouse button click action").isNotNull();
		
		return
		setRightMouseButtonClickAction(
			ociam -> {
				if (freeAreaIsUnderCursor()) {
					rightMouseButtonClickAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setRightMouseButtonPressActiononFreeArea(
		final IElementTaker<OCIAM> rightMouseButtonPressAction
	) {
		
		Validator.assertThat(rightMouseButtonPressAction).thatIsNamed("right mouse button press action").isNotNull();
		
		return
		setRightMouseButtonPressAction(
			ociam -> {
				if (freeAreaIsUnderCursor()) {
					rightMouseButtonPressAction.run(ociam);
				}
			}
		);
	}
	
	//method
	public default OCIAM setRightMouseButtonReleaseActiononFreeArea(
		final IElementTaker<OCIAM> rightMouseButtonReleaseAction
	) {
		
		Validator.assertThat(rightMouseButtonReleaseAction).thatIsNamed("right mouse button release action").isNotNull();
		
		return
		setRightMouseButtonReleaseAction(
			ociam ->  {
				if (freeAreaIsUnderCursor()) {
					rightMouseButtonReleaseAction.run(ociam);
				}
			}
		);
	}
}
