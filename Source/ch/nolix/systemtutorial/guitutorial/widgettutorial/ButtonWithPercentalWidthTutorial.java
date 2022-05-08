package ch.nolix.systemtutorial.guitutorial.widgettutorial;

import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.widget.Button;

/**
 * The {@link ButtonWithPercentalWidthTutorial} is a tutorial for {@link Button}s.
 * Of the {@link ButtonWithPercentalWidthTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2022-05-08
 */
public final class ButtonWithPercentalWidthTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Button}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Button tutorial");
		
		//Creates a Button.
		final var button = new Button().setText("Quit").setLeftMouseButtonPressAction(frame::close);
		
		//Sets a percental width to the Button.
		button.setProposalWidthInPercentOfGUIViewAreaWidth(0.5);
		
		//Adds the Button to the Frame.
		frame.pushLayer(button);
	}
	
	/**
	 * Prevents that an instance of the {@link ButtonWithPercentalWidthTutorial} can be created.
	 */
	private ButtonWithPercentalWidthTutorial() {}
}
