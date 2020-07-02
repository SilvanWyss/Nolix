package ch.nolix.elementTutorial.GUITutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.Layer;
import ch.nolix.element.elementEnum.ExtendedContentPosition;
import ch.nolix.element.widget.Label;

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
		
		//Creates Frame.
		final var frame = new Frame("Cursor position Tutorial");
		
		//Creates cursorPositionLabel.
		final var cursorPositionLabel = new Label();
		
		//Configures the look of the cursorPositionLabel.
		cursorPositionLabel.applyOnBaseLook(bl -> bl.setPaddings(5));
		
		//Creates Layer.
		final var layer = new Layer(ExtendedContentPosition.LeftTop, cursorPositionLabel);
		
		//Sets mouse move action to Layer.
		layer.setMouseMoveAction(
			() ->
			cursorPositionLabel.setText(
				"Cursor position: x=" + frame.getViewAreaCursorXPosition() + " y=" + frame.getViewAreaCursorYPosition()
			)
		);
		
		//Adds the Layer to the Frame.
		frame.addLayerOnTop(layer);
	}
	
	/**
	 * Avoids that an instance of the {@link CursorPositionTutorial} can be created.
	 */
	private CursorPositionTutorial() {}
}
