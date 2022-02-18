package ch.nolix.elementtutorial.guitutorial.textboxtutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.textbox.TextBox;
import ch.nolix.element.gui.widget.TextMode;

/**
 * The {@link TextBoxForPasswordTutorial} is a tutorial for {@link TextBox}s.
 * Of the {@link TextBoxForPasswordTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-06-29
 */
public final class TextBoxForPasswordTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link TextBox} for passwords.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("TextBox for password tutorial");
		
		//Creates a TextBox for passwords.
		final var textBox = new TextBox().setTextMode(TextMode.SECRET);
		
		//Adds the TextBox to the Frame.
		frame.addLayerOnTop(textBox);
	}
	
	/**
	 * Prevents that an instance of the {@link TextBoxForPasswordTutorial} can be created.
	 */
	private TextBoxForPasswordTutorial() {}
}
