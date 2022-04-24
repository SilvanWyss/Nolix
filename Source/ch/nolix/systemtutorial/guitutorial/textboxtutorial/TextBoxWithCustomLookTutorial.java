package ch.nolix.systemtutorial.guitutorial.textboxtutorial;

import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.textbox.TextBox;
import ch.nolix.system.gui.widget.WidgetLookState;

/**
 * The {@link TextBoxWithCustomLookTutorial} is a tutorial for {@link TextBox}s.
 * Of the {@link TextBoxWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-21
 */
public final class TextBoxWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link TextBox}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("TextBox with custom look tutorial");
		
		//Creates a TextBox.
		final var textBox = new TextBox();
		
		//Configures the look of the TextBox.
		textBox
		.setProposalWidth(200)
		.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 1)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.BASE, 5)
		);
		
		//Adds the TextBox to the Frame.
		frame.addLayerOnTop(textBox);
	}
	
	/**
	 * Prevents that an instance of the {@link TextBoxWithCustomLookTutorial} can be created.
	 */
	private TextBoxWithCustomLookTutorial() {}
}
