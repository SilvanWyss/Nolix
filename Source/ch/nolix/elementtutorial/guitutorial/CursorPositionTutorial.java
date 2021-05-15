package ch.nolix.elementtutorial.guitutorial;

import ch.nolix.element.elementenum.ExtendedContentPosition;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.widget.Label;

/**
 * The {@link CursorPositionTutorial} is a tutorial for {@link Frame}s.
 * Of the {@link CursorPositionTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2020-02-01
 */
public final class CursorPositionTutorial {
	
	/**
	 * Creates a {@link Frame} that displays the cursor position.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates Frame.
		final var frame =
		new Frame()
		.setTitle("Cursor position Tutorial");
		
		//Creates cursorPositionLabel.
		final var cursorPositionLabel = new Label();
		
		//Configures the look of the cursorPositionLabel.
		cursorPositionLabel.onLook(l -> l.setPaddingForState(WidgetLookState.BASE, 5));
		
		//Creates Layer.
		final var layer =
		new Layer()
		.setContentPosition(ExtendedContentPosition.TOP_LEFT)
		.setRootWidget(cursorPositionLabel);
		
		//Sets mouse move action to Layer.
		layer.setMouseMoveAction(
			() ->
			cursorPositionLabel.setText(
				"Cursor position: x=" + frame.getCursorXPositionOnViewArea() + " y=" + frame.getCursorYPositionOnViewArea()
			)
		);
		
		//Adds the Layer to the Frame.
		frame.addLayerOnTop(layer);
	}
	
	/**
	 * Prevents that an instance of the {@link CursorPositionTutorial} can be created.
	 */
	private CursorPositionTutorial() {}
}
