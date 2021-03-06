package ch.nolix.elementtutorial.containerwidgettutorial;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.containerwidget.TabContainer;
import ch.nolix.element.containerwidget.TabContainerTab;
import ch.nolix.element.gui.base.Frame;
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
