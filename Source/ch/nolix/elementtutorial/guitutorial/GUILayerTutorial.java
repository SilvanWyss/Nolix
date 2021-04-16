package ch.nolix.elementtutorial.guitutorial;

import ch.nolix.element.elementenum.ExtendedContentPosition;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Button;

/**
 * The {@link GUILayerTutorial} is a tutorial for {@link Layer}s.
 * Of the {@link GUILayerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2019-05-22
 * @lines 70
 */
public final class GUILayerTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Button}.
	 * The button will add another {@link Layer} to the {@link Frame} when it is clicked on.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates Frame.
		final var frame =
		new Frame()
		.setTitle("GUILayer Tutorial")
		.setBackgroundColor(Color.LAVENDER);
		
		//Creates dialogLayer.
		final var dialogLayer =
		new Layer()
		.setContentPosition(ExtendedContentPosition.CENTER)
		.setRootWidget(
			new VerticalStack()
			.addWidget(
				new Button()
				.setText("Close")
				.setLeftMouseButtonReleaseAction(frame::removeTopLayer)
			)
			.onLook(
				l ->
				l
				.setBorderThicknessesForState(WidgetLookState.NORMAL, 5)
				.setBackgroundColorForState(WidgetLookState.NORMAL,Color.WHITE)
				.setLeftPaddingForState(WidgetLookState.NORMAL,200)
				.setRightPaddingForState(WidgetLookState.NORMAL,200)
				.setTopPaddingForState(WidgetLookState.NORMAL,200)
				.setBottomPaddingForState(WidgetLookState.NORMAL,5)
			)
		);
		
		//Creates showButton.
		final var showButton = new Button()
		.setText("Show")
		.setLeftMouseButtonReleaseAction(() -> frame.addLayerOnTop(dialogLayer));
		
		//Adds the showButton to the Frame.
		frame.addLayerOnTop(ExtendedContentPosition.CENTER, showButton);
	}
	
	/**
	 * Avoids that an instance of the {@link GUILayerTutorial} can be created.
	 */
	private GUILayerTutorial() {}
}
