//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;
import ch.nolix.systemapi.guiapi.universalcanvasapi.IOccupiableHoverableCanvas;

//interface
@AllowDefaultMethodsAsDesignPattern
public interface IOccupiableCanvasInputActionManager<OCIAM extends IOccupiableCanvasInputActionManager<OCIAM>>
extends IInputActionManager<OCIAM>, IOccupiableHoverableCanvas {
	
	//method
	default OCIAM setLeftMouseButtonClickActionOnFreeArea(
		final IElementTaker<OCIAM> leftMouseButtonClickAction
	) {
		
		GlobalValidator.assertThat(leftMouseButtonClickAction).thatIsNamed("left mouse button click action").isNotNull();
		
		return
		setLeftMouseButtonClickAction(
			(final OCIAM ociam) -> {
				if (freeAreaIsUnderCursor()) {
					leftMouseButtonClickAction.run(ociam);
				}
			}
		);
	}
	
	//method
	default OCIAM setLeftMouseButtonPressActionOnFreeArea(final IAction leftMouseButtonPressAction) {
		
		GlobalValidator.assertThat(leftMouseButtonPressAction).thatIsNamed("left mouse button press action").isNotNull();
		
		return setLeftMouseButtonPressActionOnFreeArea(ociam -> leftMouseButtonPressAction.run());
	}
	
	//method
	default OCIAM setLeftMouseButtonPressActionOnFreeArea(
		final IElementTaker<OCIAM> leftMouseButtonPressAction
	) {
		
		GlobalValidator.assertThat(leftMouseButtonPressAction).thatIsNamed("left mouse button press action").isNotNull();
		
		return
		setLeftMouseButtonPressAction(
			(final OCIAM ociam) -> {
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
		
		GlobalValidator.assertThat(leftMouseButtonReleaseAction).thatIsNamed("left mouse button release action").isNotNull();
		
		return
		setLeftMouseButtonReleaseAction(
			(final OCIAM ociam) -> {
				if (freeAreaIsUnderCursor()) {
					leftMouseButtonReleaseAction.run(ociam);
				}
			}
		);
	}
	
	//method
	default OCIAM setMouseMoveActionOnFreeArea(final IElementTaker<OCIAM> mouseMoveAction) {
		
		GlobalValidator.assertThat(mouseMoveAction).thatIsNamed("mouse move action").isNotNull();
		
		return
		setMouseMoveAction(
			(final OCIAM ociam) -> {
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
		
		GlobalValidator.assertThat(mouseWheelClickAction).thatIsNamed("mouse wheel click action").isNotNull();
		
		return
		setMouseWheelClickAction(
			(final OCIAM ociam) -> {
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
		
		GlobalValidator.assertThat(mouseWheelPressAction).thatIsNamed("mouse wheel press action").isNotNull();
		
		return
		setMouseWheelPressAction(
			(final OCIAM ociam) -> {
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
		
		GlobalValidator.assertThat(mouseWheelReleaseAction).thatIsNamed("mouse wheel release action").isNotNull();
		
		return
		setMouseWheelReleaseAction(
			(final OCIAM ociam) -> {
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
		
		GlobalValidator.assertThat(rightMouseButtonClickAction).thatIsNamed("right mouse button click action").isNotNull();
		
		return
		setRightMouseButtonClickAction(
			(final OCIAM ociam) -> {
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
		
		GlobalValidator.assertThat(rightMouseButtonPressAction).thatIsNamed("right mouse button press action").isNotNull();
		
		return
		setRightMouseButtonPressAction(
			(final OCIAM ociam) -> {
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
		
		GlobalValidator
		.assertThat(rightMouseButtonReleaseAction)
		.thatIsNamed("right mouse button release action")
		.isNotNull();
		
		return
		setRightMouseButtonReleaseAction(
			(final OCIAM ociam) -> {
				if (freeAreaIsUnderCursor()) {
					rightMouseButtonReleaseAction.run(ociam);
				}
			}
		);
	}
}
