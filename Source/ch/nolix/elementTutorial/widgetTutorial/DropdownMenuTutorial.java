package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.DropdownMenu;

/**
 * The {@link DropdownMenuTutorial} is a tutorial for {@link DropdownMenu}s.
 * Of the {@link DropdownMenuTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
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
		final var frame =
		new Frame()
		.setTitle("DropdownMenu Tutorial");
		
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
		
		//Configures the look of the dropdownMenu.
		dropdownMenu.applyOnBaseLook(
			bl ->
			bl			
			.setBorderThicknesses(5)
			.setBackgroundColor(Color.LAVENDER)
			.setItemPadding(5)
		);
		
		//Adds the dropdownMenu to the frame.
		frame.addLayerOnTop(dropdownMenu);
	}
	
	/**
	 * Avoids that an instance of the {@link DropdownMenuTutorial} can be created.
	 */
	private DropdownMenuTutorial() {}
}
