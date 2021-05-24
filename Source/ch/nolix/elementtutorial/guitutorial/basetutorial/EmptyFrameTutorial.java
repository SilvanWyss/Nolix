package ch.nolix.elementtutorial.guitutorial.basetutorial;

import ch.nolix.element.gui.base.Frame;

/**
 * The {@link EmptyFrameTutorial} is a tutorial for a {@link Frame}s.
 * Of the {@link EmptyFrameTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-12-01
 * @lines 20
 */
public final class EmptyFrameTutorial {
	
	/**
	 * Creates an empty {@link Frame}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Frame().setTitle("Empty Frame Tutorial");
	}
	
	/**
	 * Prevents that an instance of the {@link EmptyFrameTutorial} can be created.
	 */
	private EmptyFrameTutorial() {}
}
