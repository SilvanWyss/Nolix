package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.widget.CheckBox;

/**
 * The {@link CheckBoxTutorial} is a tutorial for {@link CheckBox}s.
 * Of the {@link CheckBoxTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-05-26
 * @lines 30
 */
public final class CheckBoxTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link CheckBox}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("Checkbox Tutorial");
		
		//Creates a CheckBox.
		final var checkBox = new CheckBox();
		
		//Adds the CheckBox to the Frame.
		frame.addLayerOnTop(checkBox);
	}
	
	/**
	 * Prevents that an instance of the {@link CheckBoxTutorial} can be created.
	 */
	private CheckBoxTutorial() {}
}
