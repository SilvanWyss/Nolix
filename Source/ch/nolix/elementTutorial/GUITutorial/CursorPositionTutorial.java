package ch.nolix.elementTutorial.GUITutorial;

import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.elementEnums.ExtendedContentPosition;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.Label;

public final class CursorPositionTutorial {
	
	public static void main(String[] args) {
		
		final var frame = new Frame("Mouse position Tutorial");
		
		final var cursorPositionLabel = new Label();
		frame.addLayerOnTop(ExtendedContentPosition.LeftTop, new HorizontalStack(new Label("Cursor position:"), cursorPositionLabel));
		
		Sequencer
		.asLongAs(frame::isOpen)
		.runInBackground(
			() ->
			cursorPositionLabel.setText(frame.getViewAreaCursorXPosition() + ", " + frame.getViewAreaCursorYPosition())
		);
	}
	
	private CursorPositionTutorial() {}
}
