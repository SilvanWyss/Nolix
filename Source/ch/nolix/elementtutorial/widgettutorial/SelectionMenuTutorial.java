package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.SelectionMenu;

/**
 * The {@link SelectionMenuTutorial} is a tutorial for {@link SelectionMenu}s.
 * Of the {@link SelectionMenuTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-05-05
 * @lines 60
 */
public final class SelectionMenuTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link SelectionMenu}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("SelectionMenu Tutorial");
		
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
			"J�rgen Habermas",
			"Richard David Precht"
		);
		
		//Configures the look of the SelectionMenu.
		selectionMenu
		.setProposalHeight(200)
		.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 5)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
		)
		.onItemLook(il -> il.setPaddingForState(WidgetLookState.BASE, 5))
		.onSelectedItemLook(il -> il.setPaddingForState(WidgetLookState.BASE, 5));
		
		//Adds the SelectionMenu to the Frame.
		frame.addLayerOnTop(selectionMenu);
	}
	
	/**
	 * Prevents that an instance of the {@link SelectionMenuTutorial} can be created.
	 */
	private SelectionMenuTutorial() {}
}
