package ch.nolix.elementtutorial.containerwidgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.TabContainer;
import ch.nolix.element.gui.containerwidget.TabContainerTab;
import ch.nolix.element.gui.widget.Area;

/**
 * The {@link TabContainerTutorial} is a tutorial for {@link TabContainer}s.
 * Of the {@link TabContainerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 50
 */
public final class TabContainerTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link TabContainer}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Tab Container Tutorial");
		
		//Creates a TabContainer.
		final var tabContainer =
		new TabContainer()
		.addTab(
			new TabContainerTab("A", new Area().setSize(500, 200).setBackgroundColor(Color.BLUE)),
			new TabContainerTab("B", new Area().setSize(500, 200).setBackgroundColor(Color.GREEN)),
			new TabContainerTab("C", new Area().setSize(500, 200).setBackgroundColor(Color.BLUE)),
			new TabContainerTab("D", new Area().setSize(500, 200).setBackgroundColor(Color.GREEN))
		);
		
		//Configures the look of the TabContainer.
		tabContainer.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.NORMAL, 5)
			.setBackgroundColorForState(WidgetLookState.NORMAL, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.NORMAL, 20)
		);
		
		//Adds the TabContainer to the Frame.
		frame.addLayerOnTop(tabContainer);
	}
	
	/**
	 * Avoids that an instance of the {@link TabContainerTutorial} can be created.
	 */
	private TabContainerTutorial() {}
}
