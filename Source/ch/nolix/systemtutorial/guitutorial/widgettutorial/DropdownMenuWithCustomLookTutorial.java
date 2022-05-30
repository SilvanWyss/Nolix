package ch.nolix.systemtutorial.guitutorial.widgettutorial;

import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.DropdownMenu;
import ch.nolix.system.gui.widget.WidgetLookState;

/**
 * The {@link DropdownMenuWithCustomLookTutorial} is a tutorial for {@link DropdownMenu}s.
 * Of the {@link DropdownMenuWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-21
 */
public final class DropdownMenuWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link DropdownMenu}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("DropdownMenu with custom look tutorial");
		
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
		frame.pushLayerWithWidget(dropdownMenu);
	}
	
	/**
	 * Prevents that an instance of the {@link DropdownMenuWithCustomLookTutorial} can be created.
	 */
	private DropdownMenuWithCustomLookTutorial() {}
}
