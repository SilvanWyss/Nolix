package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.SelectionMenu;

/**
 * The {@link SelectionMenuTutorial} is a tutorial for {@link SelectionMenu}s.
 * Of the {@link SelectionMenuTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 */
public final class SelectionMenuTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link SelectionMenu}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Selection Menu Tutorial");
		
		//Creates a SelectionMenu.
		final var selectionMenu =
		new SelectionMenu(
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
			"J�rgen Habermas",
			"Richard David Precht"
		);
		
		//Configures the look of the selectionMenu.
		selectionMenu
		.setMaxHeight(200)			
		.applyOnBaseLook(
			bl ->
			bl			
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
			.setItemPadding(5)
		);
		
		//Adds the selectionMenu to the frame.
		frame.setRootWidget(selectionMenu);
	}
	
	/**
	 * Avoids that an instance of the {@link SelectionMenuTutorial} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private SelectionMenuTutorial() {
		throw new UninstantiableClassException(getClass());
	}
}
