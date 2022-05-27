//package declaration
package ch.nolix.systemapi.guiapi.baseapi;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionuniversalapi.IAction;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.core.hoveruniversalapi.IOccupiableHoverableCanvas;

//interface
public interface IOccupiableCanvasInputActionManager<OCIAM extends IOccupiableCanvasInputActionManager<OCIAM>>
extends IInputActionManager<OCIAM>, IOccupiableHoverableCanvas {
	
	//method
	default OCIAM setLeftMouseButtonClickActionOnFreeArea(
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
	default OCIAM setLeftMouseButtonPressActionOnFreeArea(final IAction leftMouseButtonPressAction) {
		
		Validator.assertThat(leftMouseButtonPressAction).thatIsNamed("left mouse button press action").isNotNull();
		
		return setLeftMouseButtonPressActionOnFreeArea(ociam -> leftMouseButtonPressAction.run());
	}
	
	//method
	default OCIAM setLeftMouseButtonPressActionOnFreeArea(
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
	default OCIAM setLeftMouseButtonReleaseActionOnFreeArea(
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
	default OCIAM setMouseMoveActionOnFreeArea(final IElementTaker<OCIAM> mouseMoveAction) {
		
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
	default OCIAM setMouseWheelClickActionOnFreeArea(
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
	default OCIAM setMouseWheelPressActionOnFreeArea(
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
	default OCIAM setMouseWheelReleaseActionOnFreeArea(
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
	default OCIAM setRightMouseButtonClickActionOnFreeArea(
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
	default OCIAM setRightMouseButtonPressActionOnFreeArea(
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
	default OCIAM setRightMouseButtonReleaseActionOnFreeArea(
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
