package ch.nolix.elementtutorial.widgettutorial;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.widget.CheckBox;

/**
 * The {@link CheckBoxTutorial} provides a tutorial for {@link CheckBox}s.
 * Of the {@link CheckBoxTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 40
 */
public final class CheckBoxTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link CheckBox}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Checkbox Tutorial");
		
		//Creates a Checkbox.
		final var checkBox = new CheckBox();
		
		//Configures the look of the checkbox.
		checkBox.applyOnBaseLook(
			bl -> 
			bl
			.setTextSize(50)
			.setLineThickness(5)
			.setBackgroundColor(Color.LAVENDER)
		);
		
		//Adds the checkbox to the frame.
		frame.addLayerOnTop(checkBox);
	}
	
	/**
	 * Avoids that an instance of the {@link CheckBoxTutorial} can be created.
	 */
	private CheckBoxTutorial() {}
}
