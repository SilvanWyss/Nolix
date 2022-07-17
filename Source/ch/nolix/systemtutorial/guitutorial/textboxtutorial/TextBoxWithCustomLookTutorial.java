package ch.nolix.systemtutorial.guitutorial.textboxtutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.textbox.TextBox;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

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
			.setBorderThicknessForState(ControlState.BASE, 1)
			.setBackgroundColorForState(ControlState.BASE, Color.LAVENDER)
			.setPaddingForState(ControlState.BASE, 5)
		);
		
		//Adds the TextBox to the Frame.
		frame.pushLayerWithRootWidget(textBox);
	}
	
	/**
	 * Prevents that an instance of the {@link TextBoxWithCustomLookTutorial} can be created.
	 */
	private TextBoxWithCustomLookTutorial() {}
}
