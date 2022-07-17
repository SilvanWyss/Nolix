package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

/**
 * The {@link ButtonWithCustomLookTutorial} is a tutorial for {@link Button}s.
 * Of the {@link ButtonWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-18
 */
public final class ButtonWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Button}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Button with custom look tutorial");
		
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
			.setBackgroundColorForState(ControlState.BASE, Color.LAVENDER)
			.setBackgroundColorForState(ControlState.HOVER, Color.SKY_BLUE)
			.setPaddingForState(ControlState.BASE, 10)
		);
		
		//Adds the Button to the Frame.
		frame.pushLayerWithRootWidget(button);
	}
	
	/**
	 * Prevents that an instance of the {@link ButtonWithCustomLookTutorial} can be created.
	 */
	private ButtonWithCustomLookTutorial() {}
}
