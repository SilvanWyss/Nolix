package ch.nolix.systemtutorial.guitutorial.widgettutorial;

import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.main.Frame;
import ch.nolix.system.gui.widget.CheckBox;
import ch.nolix.system.gui.widget.WidgetLookState;

/**
 * The {@link CheckBoxWithCustomLookTutorial} is a tutorial for {@link CheckBox}s.
 * Of the {@link CheckBoxWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-18
 */
public final class CheckBoxWithCustomLookTutorial {
	/**
	 * Creates a {@link Frame} with a {@link CheckBox}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Checkbox with custom look tutorial");
		
		//Creates a CheckBox.
		final var checkBox = new CheckBox();
		
		//Configures the look of the CheckBox.
		checkBox.onLook(l -> l.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER));
		
		//Adds the CheckBox to the Frame.
		frame.pushLayerWithRootWidget(checkBox);
	}
	
	/**
	 * Prevents that an instance of the {@link CheckBoxWithCustomLookTutorial} can be created.
	 */
	private CheckBoxWithCustomLookTutorial() {}
}
