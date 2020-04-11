package ch.nolix.elementTutorial.GUITutorial;

import ch.nolix.element.GUI.Layer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementEnums.ExtendedContentPosition;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.VerticalStack;

/**
 * The {@link GUILayerTutorial} provides a tutorial for {@link Layer}s.
 * Of the {@link GUILayerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2019-05
 */
public class GUILayerTutorial {
	
	/**
	 * Creates a {@link FrameVisualizer} with a {@link Button}.
	 * The button will add a second {@link Layer} to the frame when it is clicked on.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("GUILayer Tutorial");
		
		//Configures the look of the frame.
		frame.setBackgroundColor(Color.ALICE_BLUE);
		
		//Creates dialogLayer.
		final var dialogLayer = new Layer(
			ExtendedContentPosition.Center,
			new VerticalStack(
				new HorizontalStack(
					new Button("Close").setLeftMouseButtonReleaseCommand(() -> frame.removeTopLayer()),
					new Button("Close").setLeftMouseButtonReleaseCommand(() -> frame.removeTopLayer())
				)
				.setElementMargin(200)
			)
			.applyOnBaseLook(
				bl ->
				bl
				.setBorderThicknesses(5)
				.setBorderColors(Color.DARK_BLUE)
				.setBackgroundColor(Color.WHITE)
				.setLeftPadding(10)
				.setRightPadding(10)
				.setTopPadding(200)
				.setBottomPadding(10)
			)
		);
		
		//Creates showButton.
		final var showButton = new Button("Show");
		showButton.setLeftMouseButtonReleaseCommand(() -> 	frame.addLayerOnTop(dialogLayer));
		
		//Adds the showButton to the frame.
		frame.addLayerOnTop(ExtendedContentPosition.Center, showButton);
	}
	
	/**
	 * Avoids that an instance of the {@link GUILayerTutorial} can be created.
	 */
	private GUILayerTutorial() {}
}
