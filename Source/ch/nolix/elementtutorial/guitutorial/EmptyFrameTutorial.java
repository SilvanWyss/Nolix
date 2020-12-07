package ch.nolix.elementtutorial.guitutorial;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.Frame;

/**
 * The {@link EmptyFrameTutorial} is a tutorial for a {@link Frame}s.
 * Of the {@link EmptyFrameTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 */
public final class EmptyFrameTutorial {
	
	/**
	 * Creates an empty {@link Frame}.
	 */
	public static void main(String[] args) {
		new Frame()
		.setTitle("Empty Frame Tutorial")
		.setBackgroundColor(Color.LAVENDER);
	}
	
	/**
	 * Avoids that an instance of the {@link EmptyFrameTutorial} can be created.
	 */
	private EmptyFrameTutorial() {}
}
