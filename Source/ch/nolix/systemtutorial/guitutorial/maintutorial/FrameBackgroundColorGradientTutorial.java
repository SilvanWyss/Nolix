package ch.nolix.systemtutorial.guitutorial.maintutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.color.ColorGradient;
import ch.nolix.system.gui.main.Frame;

/**
 * The {@link FrameBackgroundColorGradientTutorial} is a tutorial for a {@link Frame}s.
 * Of the {@link FrameBackgroundColorGradientTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-27
 */
public final class FrameBackgroundColorGradientTutorial {
	
	/**
	 * Creates a {@link Frame} and sets a background {@link ColorGradient} to it.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new Frame()
		.setTitle("Frame background color gradient tutorial")
		.setBackgroundColorGradient(new ColorGradient(Color.SKY_BLUE, Color.WHITE));
	}
	
	/**
	 * Prevents that an instance of the {@link FrameBackgroundColorGradientTutorial} can be created.
	 */
	private FrameBackgroundColorGradientTutorial() {}
}
