package ch.nolix.elementtutorial.widgettutorial;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.Label;

/**
 * The {@link ScrollTutorial} is a tutorial for the scroll feature of {@link BorderWidget}s.
 * Of the {@link ScrollTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 50
 */
public final class ScrollTutorial {
	
	/**
	 * Creates a {@link Frame} with a scrollable {@link Label}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Scroll Tutorial");
		
		//Creates a Label.
		final var label = new Label().setText("PLATON");
		
		//Configures the look of the Label.
		label
		.setMaxWidth(1000)
		.setMaxHeight(500)
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBackgroundColor(Color.LAVENDER)
			.setTextSize(500)
			.remainTextWhenLimited()
		);
		
		//Adds the Label to the frame.
		frame.addLayerOnTop(label);
	}
	
	/**
	 * Avoids that an instance of the {@link ScrollTutorial} can be created.
	 */
	private ScrollTutorial() {}
}
