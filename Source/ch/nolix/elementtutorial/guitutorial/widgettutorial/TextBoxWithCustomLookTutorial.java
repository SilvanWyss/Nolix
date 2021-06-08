package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.TextBox;
import ch.nolix.element.gui.widget.WidgetLookState;

/**
 * The {@link TextBoxWithCustomLookTutorial} is a tutorial for {@link TextBox}s.
 * Of the {@link TextBoxWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-21
 * @lines 50
 */
public final class TextBoxWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link TextBox}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
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
