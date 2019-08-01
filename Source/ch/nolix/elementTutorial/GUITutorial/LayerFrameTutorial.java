//package declaration
package ch.nolix.elementTutorial.GUITutorial;

import ch.nolix.element.GUI.LayerFrame;
import ch.nolix.element.color.Color;
import ch.nolix.element.frameVisualizer.FrameVisualizer;

/**
 * The {@link LayerFrameTutorial} provides a tutorial for a {@link FrameVisualizer}.
 * Of the {@link LayerFrameTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 */
public final class LayerFrameTutorial {
	
	/**
	 * Creates a {@link FrameVisualizer} with an alice blue background.
	 */
	public static void main(String[] args) {
		new LayerFrame()
		.setTitle("Frame Tutorial")
		.setBackgroundColor(Color.ALICE_BLUE);
	}
	
	/**
	 * Avoids that an instance of the {@link LayerFrameTutorial} can be created.
	 */
	private LayerFrameTutorial() {}
}
