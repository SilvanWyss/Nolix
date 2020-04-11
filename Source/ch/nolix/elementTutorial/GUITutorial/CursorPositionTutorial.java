package ch.nolix.elementTutorial.GUITutorial;

import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.elementEnums.ExtendedContentPosition;
import ch.nolix.element.widgets.Label;

/**
 * The {@link CursorPositionTutorial} is a tutorial for {@link Frame}s.
 * Of the {@link CursorPositionTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2020-01
 */
public final class CursorPositionTutorial {
	
	/**
	 * Creates a {@link Frame} that displays the cursor position.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Cursor Position Tutorial");
		
		//Creates cursorPositionLabel.
		final var cursorPositionLabel = new Label();
		
		//Configures the look of the cursorPositionLabel.
		cursorPositionLabel.applyOnBaseLook(bl -> bl.setPaddings(5));
		
		//Adds the cursorPositionLabel to the Frame at the left top.
		frame.addLayerOnTop(ExtendedContentPosition.LeftTop, cursorPositionLabel);
		
		//Updates the cursorPositionLabel as long as the Frame is open.
		Sequencer
		.asLongAs(frame::isOpen)
		.runInBackground(
			() ->
			cursorPositionLabel.setText(
				"Cursor position: " + frame.getViewAreaCursorXPosition() + ", " + frame.getViewAreaCursorYPosition()
			)
		);
	}
	
	/**
	 * Avoids that an instance of the {@link CursorPositionTutorial} can be created.
	 */
	private CursorPositionTutorial() {}
}
