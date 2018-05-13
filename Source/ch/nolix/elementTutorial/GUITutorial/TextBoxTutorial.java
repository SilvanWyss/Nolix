//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link TextBoxTutorial} provides a tutorial for the {@link TextBox}.
 * Of the {@link TextBoxTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 50
 */
public final class TextBoxTutorial {

	//main method
	/**
	 * Creates a {@link Frame} with a {@link TextBox}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a text box.
		final var textBox =
		new TextBox()		
		.setWidth(500);
		
		//Configures the look of the text box.
		textBox
		.getRefBaseLook()
		.setBorderThicknesses(5)
		.setBorderColors(Color.DARK_BLUE)
		.setBackgroundColor(Color.ALICE_BLUE)
		.setPaddings(10)
		.setTextSize(30);
		
		//Creates a frame with the text box.
		new Frame()
		.setTitle("Text Box Tutorial")
		.setRootWidget(textBox);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link TextBoxTutorial} can be created.
	 */
	private TextBoxTutorial() {}
}
