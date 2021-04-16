package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.Button;

/**
 * The {@link ButtonTutorial} is a tutorial for {@link Button}s.
 * Of the {@link ButtonTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-05-19
 * @lines 60
 */
public final class ButtonTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Button}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Button Tutorial");
		
		//Creates a Button.
		final var button = new Button().setText("Change background color");
		
		//Defines a left mouse button release action for the Button.
		button.setLeftMouseButtonReleaseAction(
			() -> {
				if (frame.getBackgroundColor().equals(Color.WHITE)) {
					frame.setBackgroundColor(Color.YELLOW);
				} else {
					frame.setBackgroundColor(Color.WHITE);
				}
			}
		);
		
		//Configures the look of the Button.
		button
		.setCustomCursorIcon(CursorIcon.HAND)
		.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 5)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setBackgroundColorForState(WidgetLookState.HOVER, Color.SKY_BLUE)
			.setPaddingForState(WidgetLookState.BASE, 5)
			.setTextSizeForState(WidgetLookState.BASE, 50)
		);
		
		//Adds the Button to the Frame.
		frame.addLayerOnTop(button);
	}
	
	/**
	 * Avoids that an instance of the {@link ButtonTutorial} can be created.
	 */
	private ButtonTutorial() {}
}
