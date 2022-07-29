package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.DropdownMenu;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

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
		
		//Configures the style of the DropdownMenu.
		dropdownMenu
		.onStyle(l -> l.setBackgroundColorForState(ControlState.BASE, Color.LAVENDER))
		.onItemLook(
			il ->
			il
			.setPaddingForState(ControlState.BASE, 10)
			.setBackgroundColorForState(ControlState.HOVER, Color.SKY_BLUE)
		)
		.onSelectedItemLook(
			sil ->
			sil
			.setPaddingForState(ControlState.BASE, 10)
			.setBackgroundColorForState(ControlState.BASE, Color.BLUE)
		);
		
		//Adds the DropdownMenu to the Frame.
		frame.pushLayerWithRootWidget(dropdownMenu);
	}
	
	/**
	 * Prevents that an instance of the {@link DropdownMenuWithCustomLookTutorial} can be created.
	 */
	private DropdownMenuWithCustomLookTutorial() {}
}
