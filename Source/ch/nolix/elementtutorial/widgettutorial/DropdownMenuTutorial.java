package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.widget.DropdownMenu;

/**
 * The {@link DropdownMenuTutorial} is a tutorial for {@link DropdownMenu}s.
 * Of the {@link DropdownMenuTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2019-05-24
 * @lines 50
 */
public final class DropdownMenuTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link DropdownMenu}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("DropdownMenu Tutorial");
		
		//Creates a DropdownMenu.
		final var dropdownMenu =
		new DropdownMenu()
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
			"Theodor W. Adorno"
		);
		
		//Adds the DropdownMenu to the Frame.
		frame.addLayerOnTop(dropdownMenu);
	}
	
	/**
	 * Prevents that an instance of the {@link DropdownMenuTutorial} can be created.
	 */
	private DropdownMenuTutorial() {}
}
