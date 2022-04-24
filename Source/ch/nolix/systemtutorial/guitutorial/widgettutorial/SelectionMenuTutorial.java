package ch.nolix.systemtutorial.guitutorial.widgettutorial;

import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.widget.SelectionMenu;

/**
 * The {@link SelectionMenuTutorial} is a tutorial for {@link SelectionMenu}s.
 * Of the {@link SelectionMenuTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-05-05
 */
public final class SelectionMenuTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link SelectionMenu}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("SelectionMenu tutorial");
		
		//Creates a SelectionMenu.
		final var selectionMenu =
		new SelectionMenu()
		.addItem(
			"Gottfried Wilhelm Leibniz",
			"Immanuel Kant",
			"Johann Gottlieb Fichte",
			"Georg Wilehlm Friedrich Hegel",
			"Arthur Schopenhauer",
			"Johann Gottfried Herder",
			"Karl Marx",
			"Friedrich Nietzsche",
			"Ludwig Wittgenstein",
			"Theodor W. Adorno",
			"Jürgen Habermas",
			"Richard David Precht"
		);
						
		//Adds the SelectionMenu to the Frame.
		frame.addLayerOnTop(selectionMenu);
	}
	
	/**
	 * Prevents that an instance of the {@link SelectionMenuTutorial} can be created.
	 */
	private SelectionMenuTutorial() {}
}
