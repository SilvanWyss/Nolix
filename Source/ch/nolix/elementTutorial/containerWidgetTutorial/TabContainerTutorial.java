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
 */
public final class TabContainerTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link TabContainer}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Tab Container Tutorial");
		
		//Creates a TabContainer.
		final var tabContainer =
		new TabContainer()
		.addTab(
			new TabContainerTab("A", new Area(500, 200, Color.LIGHT_GREEN)),
			new TabContainerTab("B", new Area(500, 200, Color.GREEN)),
			new TabContainerTab("C", new Area(500, 200, Color.LIGHT_GREEN)),
			new TabContainerTab("D", new Area(500, 200, Color.GREEN))
		);
		
		//Configures the look of the tabContainer.
		tabContainer.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
			.setPaddings(20)
		);
		
		//Adds the tabContainer to the frame.
		frame.addLayerOnTop(tabContainer);
	}
	
	/**
	 * Avoids that an instance of the {@link TabContainerTutorial} can be created.
	 */
	private TabContainerTutorial() {}
}
