package ch.nolix.systemtutorial.guitutorial.maintutorial;

import ch.nolix.system.gui.widgetgui.Frame;

/**
 * The {@link EmptyFrameTutorial} is a tutorial for a {@link Frame}s.
 * Of the {@link EmptyFrameTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-12-01
 */
public final class EmptyFrameTutorial {
	
	/**
	 * Creates an empty {@link Frame}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new Frame().setTitle("Empty Frame tutorial");
	}
	
	/**
	 * Prevents that an instance of the {@link EmptyFrameTutorial} can be created.
	 */
	private EmptyFrameTutorial() {}
}
