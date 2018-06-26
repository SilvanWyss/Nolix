//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.SelectionMenu;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link SelectionMenuTutorial} provides a tutorial for a {@link SelectionMenu}.
 * Of the {@link SelectionMenuTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 60
 */
public final class SelectionMenuTutorial {

	//main method
	/**
	 * Creates a {@link Frame} with a {@link SelectionMenu}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a selection menu.
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
		
		//Configures the look of the selection menu.
			selectionMenu.setMaxHeight(200);
		
			selectionMenu
			.getRefBaseLook()
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.ALICE_BLUE)
			.setItemPadding(5);
		
		//Creates a frame with the selection menu.
		new Frame()
		.setTitle("Selection Menu Tutorial")
		.setRootWidget(selectionMenu);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link SelectionMenuTutorial} can be created.
	 */
	private SelectionMenuTutorial() {}
}
