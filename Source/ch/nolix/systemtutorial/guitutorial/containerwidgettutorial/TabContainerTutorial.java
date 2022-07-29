package ch.nolix.systemtutorial.guitutorial.containerwidgettutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.TabContainer;
import ch.nolix.system.gui.widget.Area;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

/**
 * The {@link TabContainerTutorial} is a tutorial for {@link TabContainer}s.
 * Of the {@link TabContainerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-05-10
 */
public final class TabContainerTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link TabContainer}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("TabContainer tutorial");
		
		//Creates a TabContainer.
		final var tabContainer =
		new TabContainer()
		.addTab("A", new Area().setSize(500,  200).setBackgroundColor(Color.PINK))
		.addTab("B", new Area().setSize(450,  200).setBackgroundColor(Color.ORANGE))
		.addTab("C", new Area().setSize(400,  200).setBackgroundColor(Color.PINK))
		.addTab("D", new Area().setSize(350,  200).setBackgroundColor(Color.ORANGE));
		
		//Configures the look of the TabContainer.
		tabContainer
		.getRefStyle()
		.setBorderThicknessForState(ControlState.BASE, 5)
		.setBackgroundColorForState(ControlState.BASE, Color.LAVENDER);
		
		//Adds the TabContainer to the Frame.
		frame.pushLayerWithRootWidget(tabContainer);
	}
	
	/**
	 * Prevents that an instance of the {@link TabContainerTutorial} can be created.
	 */
	private TabContainerTutorial() {}
}
