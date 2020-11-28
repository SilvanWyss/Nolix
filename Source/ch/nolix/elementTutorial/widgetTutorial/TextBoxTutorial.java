package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.element.color.Color;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.TextBox;

/**
 * The {@link TextBoxTutorial} is a tutorial for {@link TextBox}s.
 * Of the {@link TextBoxTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 40
 */
public final class TextBoxTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link TextBox}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("TextBox Tutorial");
		
		//Creates a TextBox.
		final var textBox = new TextBox();
		
		//Configures the look of the textBox.
		textBox
		.setProposalWidth(200)
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBackgroundColor(Color.LAVENDER)
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
