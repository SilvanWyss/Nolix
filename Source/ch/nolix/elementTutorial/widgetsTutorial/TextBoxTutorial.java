package ch.nolix.elementTutorial.widgetsTutorial;

import ch.nolix.element.GUI.LayerFrame;
import ch.nolix.element.color.Color;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.widgets.TextBox;

/**
 * The {@link TextBoxTutorial} is a tutorial for {@link TextBox}s.
 * Of the {@link TextBoxTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 */
public final class TextBoxTutorial {
	
	/**
	 * Creates a {@link FrameVisualizer} with a {@link TextBox}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new LayerFrame("Text Box Tutorial");
		
		//Creates a TextBox.
		final var textBox = new TextBox();
		
		//Configures the look of the textBox.
		textBox
		.setProposalWidth(200)
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
			.setPaddings(5)
		);
		
		//Adds the textBox to the frame.
		frame.addLayerOnTop(textBox);
	}
	
	/**
	 * Avoids that an instance of the {@link TextBoxTutorial} can be created.
	 */
	private TextBoxTutorial() {}
}
