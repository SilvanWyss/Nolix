package ch.nolix.elementtutorial.guitutorial.basetutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.color.ColorGradient;

/**
 * The {@link FrameBackgroundColorGradientTutorial} is a tutorial for a {@link Frame}s.
 * Of the {@link FrameBackgroundColorGradientTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-27
 * @lines 30
 */
public final class FrameBackgroundColorGradientTutorial {
	
	/**
	 * Creates a {@link Frame} and sets a background {@link ColorGradient} to it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Frame()
		.setTitle("Frame background color gradient Tutorial")
		.setBackgroundColorGradient(new ColorGradient(Color.SKY_BLUE, Color.WHITE));
	}
	
	/**
	 * Prevents that an instance of the {@link FrameBackgroundColorGradientTutorial} can be created.
	 */
	private FrameBackgroundColorGradientTutorial() {}
}
