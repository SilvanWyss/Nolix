package ch.nolix.elementtutorial.guitutorial.basetutorial;

//own imports
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;

/**
 * The {@link FrameBackgroundColorTutorial} is a tutorial for a {@link Frame}s.
 * Of the {@link FrameBackgroundColorTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-24
 */
public final class FrameBackgroundColorTutorial {
	
	/**
	 * Creates a {@link Frame} and sets a background {@link Color} to it.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new Frame()
		.setTitle("Frame background color tutorial")
		.setBackgroundColor(Color.LAVENDER);
	}
	
	/**
	 * Prevents that an instance of the {@link FrameBackgroundColorTutorial} can be created.
	 */
	private FrameBackgroundColorTutorial() {}
}
