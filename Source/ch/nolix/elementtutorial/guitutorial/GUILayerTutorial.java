package ch.nolix.elementtutorial.guitutorial;

import ch.nolix.element.color.Color;
import ch.nolix.element.elementenum.ExtendedContentPosition;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.gui.Layer;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.VerticalStack;

/**
 * The {@link GUILayerTutorial} is a tutorial for {@link Layer}s.
 * Of the {@link GUILayerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2019-05-22
 * @lines 60
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
		final var dialogLayer = new Layer(
			ExtendedContentPosition.CENTER,
			new VerticalStack()
			.addWidget(
				new Button()
				.setText("Close")
				.setLeftMouseButtonReleaseAction(frame::removeTopLayer)
			)
			.applyOnBaseLook(
				bl ->
				bl
				.setBorderThicknesses(5)
				.setBackgroundColor(Color.WHITE)
				.setLeftPadding(200)
				.setRightPadding(200)
				.setTopPadding(200)
				.setBottomPadding(5)
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
