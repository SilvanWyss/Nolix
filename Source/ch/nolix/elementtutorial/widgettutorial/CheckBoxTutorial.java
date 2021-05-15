package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.CheckBox;

/**
 * The {@link CheckBoxTutorial} provides a tutorial for {@link CheckBox}s.
 * Of the {@link CheckBoxTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-05-26
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
		checkBox.onLook(
			l -> 
			l
			.setTextSizeForState(WidgetLookState.BASE, 50)
			.setLineThicknessForState(WidgetLookState.BASE, 5)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
		);
		
		//Adds the checkbox to the frame.
		frame.addLayerOnTop(checkBox);
	}
	
	/**
	 * Prevents that an instance of the {@link CheckBoxTutorial} can be created.
	 */
	private CheckBoxTutorial() {}
}
