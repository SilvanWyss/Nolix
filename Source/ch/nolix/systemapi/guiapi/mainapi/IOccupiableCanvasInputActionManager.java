//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;
import ch.nolix.systemapi.guiapi.universalcanvasapi.IOccupiableHoverableCanvas;

//interface
@AllowDefaultMethodsAsDesignPattern
public interface IOccupiableCanvasInputActionManager<OCIAM extends IOccupiableCanvasInputActionManager<OCIAM>>
extends IExtendedInputActionManager<OCIAM>, IOccupiableHoverableCanvas {
	
	//method
	default OCIAM setLeftMouseButtonClickActionOnFreeArea(
		final IElementTaker<OCIAM> leftMouseButtonClickAction
	) {
		
		if (leftMouseButtonClickAction == null) {
			throw new IllegalArgumentException("left mouse button click action");
		}
		
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
		
		if (leftMouseButtonPressAction == null) {
			throw new IllegalArgumentException("left mouse button press action");
		}
		
		return setLeftMouseButtonPressActionOnFreeArea(ociam -> leftMouseButtonPressAction.run());
	}
	
	//method
	default OCIAM setLeftMouseButtonPressActionOnFreeArea(
		final IElementTaker<OCIAM> leftMouseButtonPressAction
	) {
		
		if (leftMouseButtonPressAction == null) {
			throw new IllegalArgumentException("left mouse button press action");
		}
		
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
		
		if (leftMouseButtonReleaseAction == null) {
			throw new IllegalArgumentException("left mouse button release action");
		}
		
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
		
		if (mouseMoveAction == null) {
			throw new IllegalArgumentException("mouse move action");
		}
		
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
		
		if (mouseWheelClickAction == null) {
			throw new IllegalArgumentException("mouse wheel click action");
		}
		
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
		
		if (mouseWheelPressAction == null) {
			throw new IllegalArgumentException("mouse wheel press action");
		}
		
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
		
		if (mouseWheelReleaseAction == null) {
			throw new IllegalArgumentException("mouse wheel release action");
		}
		
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
		
		if (rightMouseButtonClickAction == null) {
			throw new IllegalArgumentException("right mouse button click action");
		}
		
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
		
		if (rightMouseButtonPressAction == null) {
			throw new IllegalArgumentException("right mouse button press action");
		}
		
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
				
		if (rightMouseButtonReleaseAction == null) {
			throw new IllegalArgumentException("right mouse button release action");
		}
		
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
