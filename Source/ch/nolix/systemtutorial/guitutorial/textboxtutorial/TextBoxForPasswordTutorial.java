package ch.nolix.systemtutorial.guitutorial.textboxtutorial;

import ch.nolix.system.gui.textbox.TextBox;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.processproperty.TextMode;

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
		frame.pushLayerWithRootWidget(textBox);
	}
	
	/**
	 * Prevents that an instance of the {@link TextBoxForPasswordTutorial} can be created.
	 */
	private TextBoxForPasswordTutorial() {}
}
