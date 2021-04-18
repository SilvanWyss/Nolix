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
 * @date 2018-05-10
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
		final var frame = new Frame().setTitle("TabContainer Tutorial");
		
		//Creates a TabContainer.
		final var tabContainer =
		new TabContainer()
		.addTab(
			new TabContainerTab("A", new Area().setSize(500, 200).setBackgroundColor(Color.PINK)),
			new TabContainerTab("B", new Area().setSize(450, 200).setBackgroundColor(Color.ORANGE)),
			new TabContainerTab("C", new Area().setSize(400, 200).setBackgroundColor(Color.PINK)),
			new TabContainerTab("D", new Area().setSize(350, 200).setBackgroundColor(Color.ORANGE))
		);
		
		//Configures the look of the TabContainer.
		tabContainer
		.getRefLook()
		.setBorderThicknessForState(WidgetLookState.BASE, 5)
		.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER);
		
		//Adds the TabContainer to the Frame.
		frame.addLayerOnTop(tabContainer);
	}
	
	/**
	 * Prevents that an instance of the {@link TabContainerTutorial} can be created.
	 */
	private TabContainerTutorial() {}
}
