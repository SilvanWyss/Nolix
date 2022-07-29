package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.SelectionMenu;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

/**
 * The {@link SelectionMenuWithCustomLookTutorial} is a tutorial for {@link SelectionMenu}s.
 * Of the {@link SelectionMenuWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-19
 */
public final class SelectionMenuWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link SelectionMenu}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("SelectionMenu with custom look tutorial");
		
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
		
		//Configures the look of the SelectionMenu.
		selectionMenu
		.setMaxHeight(200)
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
		
		//Adds the SelectionMenu to the Frame.
		frame.pushLayerWithRootWidget(selectionMenu);
	}
	
	/**
	 * Prevents that an instance of the {@link SelectionMenuWithCustomLookTutorial} can be created.
	 */
	private SelectionMenuWithCustomLookTutorial() {}
}
