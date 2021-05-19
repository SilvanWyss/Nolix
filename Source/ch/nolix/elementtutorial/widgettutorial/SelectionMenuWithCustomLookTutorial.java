package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.SelectionMenu;

/**
 * The {@link SelectionMenuWithCustomLookTutorial} is a tutorial for {@link SelectionMenu}s.
 * Of the {@link SelectionMenuWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-19
 * @lines 70
 */
public final class SelectionMenuWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link SelectionMenu}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("SelectionMenu with custom look Tutorial");
		
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
		
		//Adds the SelectionMenu to the Frame.
		frame.addLayerOnTop(selectionMenu);
	}
	
	/**
	 * Prevents that an instance of the {@link SelectionMenuWithCustomLookTutorial} can be created.
	 */
	private SelectionMenuWithCustomLookTutorial() {}
}
