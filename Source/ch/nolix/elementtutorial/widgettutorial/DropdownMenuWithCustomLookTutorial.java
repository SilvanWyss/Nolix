package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.DropdownMenu;

/**
 * The {@link DropdownMenuWithCustomLookTutorial} is a tutorial for {@link DropdownMenu}s.
 * Of the {@link DropdownMenuWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-21
 * @lines 60
 */
public final class DropdownMenuWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link DropdownMenu}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("DropdownMenu with custom look Tutorial");
		
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
		
		//Configures the look of the DropdownMenu.
		dropdownMenu
		.onLook(l -> l.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER))
		.onItemLook(
			il ->
			il
			.setPaddingForState(WidgetLookState.BASE, 10)
			.setBackgroundColorForState(WidgetLookState.HOVER, Color.SKY_BLUE)
		)
		.onSelectedItemLook(
			sil ->
			sil
			.setPaddingForState(WidgetLookState.BASE, 10)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.BLUE)
		);
		
		//Adds the DropdownMenu to the Frame.
		frame.addLayerOnTop(dropdownMenu);
	}
	
	/**
	 * Prevents that an instance of the {@link DropdownMenuWithCustomLookTutorial} can be created.
	 */
	private DropdownMenuWithCustomLookTutorial() {}
}
