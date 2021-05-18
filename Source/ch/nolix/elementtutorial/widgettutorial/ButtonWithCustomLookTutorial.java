package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.Button;

/**
 * The {@link ButtonWithCustomLookTutorial} is a tutorial for {@link Button}s.
 * Of the {@link ButtonWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-18
 * @lines 60
 */
public final class ButtonWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Button}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("Button with custom look Tutorial");
		
		//Creates a Button.
		final var button = new Button().setText("Change background color");
		
		//Defines a left mouse button press action for the Button.
		button.setLeftMouseButtonPressAction(
			() -> {
				if (frame.getBackgroundColor().equals(Color.WHITE)) {
					frame.setBackgroundColor(Color.BLUE);
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
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setBackgroundColorForState(WidgetLookState.HOVER, Color.SKY_BLUE)
			.setPaddingForState(WidgetLookState.BASE, 20)
		);
		
		//Adds the Button to the Frame.
		frame.addLayerOnTop(button);
	}
	
	/**
	 * Prevents that an instance of the {@link ButtonWithCustomLookTutorial} can be created.
	 */
	private ButtonWithCustomLookTutorial() {}
}
