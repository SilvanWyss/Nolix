package ch.nolix.systemtutorial.guitutorial.widgettutorial;

import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.widget.CheckBox;

/**
 * The {@link CheckBoxTutorial} is a tutorial for {@link CheckBox}s.
 * Of the {@link CheckBoxTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-05-26
 */
public final class CheckBoxTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link CheckBox}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Checkbox tutorial");
		
		//Creates a CheckBox.
		final var checkBox = new CheckBox();
		
		//Adds the CheckBox to the Frame.
		frame.pushLayerWithWidget(checkBox);
	}
	
	/**
	 * Prevents that an instance of the {@link CheckBoxTutorial} can be created.
	 */
	private CheckBoxTutorial() {}
}
