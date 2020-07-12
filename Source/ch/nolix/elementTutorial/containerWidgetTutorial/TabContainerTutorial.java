package ch.nolix.elementTutorial.containerWidgetTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.containerWidget.TabContainer;
import ch.nolix.element.containerWidget.TabContainerTab;
import ch.nolix.element.widget.Area;

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
		final var frame = new Frame("Tab Container Tutorial");
		
		//Creates a TabContainer.
		final var tabContainer =
		new TabContainer()
		.addTab(
			new TabContainerTab("A", new Area(500, 200, Color.BLUE)),
			new TabContainerTab("B", new Area(500, 200, Color.GREEN)),
			new TabContainerTab("C", new Area(500, 200, Color.BLUE)),
			new TabContainerTab("D", new Area(500, 200, Color.GREEN))
		);
		
		//Configures the look of the TabContainer.
		tabContainer.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBackgroundColor(Color.LAVENDER)
			.setPaddings(20)
		);
		
		//Adds the TabContainer to the Frame.
		frame.addLayerOnTop(tabContainer);
	}
	
	/**
	 * Avoids that an instance of the {@link TabContainerTutorial} can be created.
	 */
	private TabContainerTutorial() {}
}
