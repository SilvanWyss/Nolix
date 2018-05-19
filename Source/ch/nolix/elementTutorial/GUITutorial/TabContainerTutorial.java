//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.element.GUI.Area;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.TabContainer;
import ch.nolix.element.GUI.TabContainerTab;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link TabContainerTutorial} provides a tutorial for the {@link TabContainer}.
 * Of the {@link TabContainerTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-4
 * @lines 70
 */
public final class TabContainerTutorial {

	//main method
	/**
	 * Creates a {@link Frame} with a {@link TabContainer}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a tab container.
		final var tabContainer =
		new TabContainer()
		.addTab(
			new TabContainerTab(
				"Tab1",
				new Area()
				.setBackgroundColor(Color.DARK_BLUE)
				.setWidth(500)
				.setHeight(200)
			),
			new TabContainerTab(
				"Tab2",
				new Area()
				.setBackgroundColor(Color.BLUE)
				.setWidth(500)
				.setHeight(200)
			),
			new TabContainerTab(
				"Tab3",
				new Area()
				.setBackgroundColor(Color.DARK_BLUE)
				.setWidth(500)
				.setHeight(200)
			),
			new TabContainerTab(
				"Tab4",
				new Area()
				.setBackgroundColor(Color.BLUE)
				.setWidth(500)
				.setHeight(200)
			)
		);
		
		//Configures the look of the tab container.
		tabContainer
		.getRefBaseLook()
		.setBorderThicknesses(5)
		.setBorderColors(Color.DARK_BLUE)
		.setBackgroundColor(Color.ALICE_BLUE)
		.setPaddings(20);
		
		//Creates a frame with the tab container.
		new Frame()
		.setTitle("Tab Container Tutorial")
		.setRootWidget(tabContainer);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link TabContainerTutorial} can be created.
	 */
	private TabContainerTutorial() {}
}
