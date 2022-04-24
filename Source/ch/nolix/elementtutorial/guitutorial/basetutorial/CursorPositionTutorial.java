package ch.nolix.elementtutorial.guitutorial.basetutorial;

import ch.nolix.system.elementenum.ExtendedContentPosition;
import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.base.Layer;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.WidgetLookState;

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
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Cursor position tutorial");
		
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
