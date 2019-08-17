package ch.nolix.elementTutorial.widgetsTutorial;

import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.widgets.Button;

/**
 * The {@link ButtonTutorial} is a tutorial for {@link Button}s.
 * Of the {@link ButtonTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 */
public final class ButtonTutorial {
	
	/**
	 * Creates a {@link FrameVisualizer} with a {@link Button}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Button Tutorial");
		
		//Creates a Button.
		final var button = new Button("Quit");
		button.setLeftMouseButtonReleaseCommand(() -> frame.close());
		
		//Configures the look of the button.
		button
		.setMinWidth(200)
		.setCustomCursorIcon(CursorIcon.Hand)
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
			.setPaddings(5)
			.setTextSize(50)
			.setTextColor(Color.DARK_BLUE)
		)
		.applyOnHoverLook(hl -> hl.setBackgroundColor(Color.LIGHT_GREY))
		.applyOnFocusLook(fl -> fl.setBackgroundColor(Color.LIGHT_GREY));
		
		//Adds the button to the frame.
		frame.addLayerOnTop(button);
	}
	
	/**
	 * Avoids that an instance of the {@link ButtonTutorial} can be created.
	 */
	private ButtonTutorial() {}
}
