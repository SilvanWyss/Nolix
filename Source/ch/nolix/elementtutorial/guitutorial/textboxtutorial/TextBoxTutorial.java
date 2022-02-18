package ch.nolix.elementtutorial.guitutorial.textboxtutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.textbox.TextBox;

/**
 * The {@link TextBoxTutorial} is a tutorial for {@link TextBox}s.
 * Of the {@link TextBoxTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-05-13
 */
public final class TextBoxTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link TextBox}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("TextBox tutorial");
		
		//Creates a TextBox.
		final var textBox = new TextBox();
		
		//Adds the TextBox to the Frame.
		frame.addLayerOnTop(textBox);
	}
	
	/**
	 * Prevents that an instance of the {@link TextBoxTutorial} can be created.
	 */
	private TextBoxTutorial() {}
}
