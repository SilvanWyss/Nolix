package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.system.gui.containerwidget.HorizontalStack;
import ch.nolix.system.gui.widget.InnerGUI;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

/**
 * The {@link InnerGUITutorial} is a tutorial for {@link InnerGUI}s.
 * Of the {@link InnerGUITutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2020-12-14
 */
public final class InnerGUITutorial {
	
	/**
	 * Creates a {@link Frame} with 2 {@link InnerGUI}s.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("InnerGUI tutorial");
		
		//Creates an InnerGUI.
		@SuppressWarnings("resource")
		final var innerGUI1 =
		new InnerGUI()
		.setTitle("Inner GUI 1")
		.pushLayerWithRootWidget(new Label().setText("A").onLook(l -> l.setTextSizeForState(ControlState.BASE, 100)));
		
		//Creates a second InnerGUI.
		@SuppressWarnings("resource")
		final var innerGUI2 =
		new InnerGUI()
		.setTitle("Inner GUI 2")
		.pushLayerWithRootWidget(new Label().setText("B").onLook(l -> l.setTextSizeForState(ControlState.BASE, 100)));
		
		//Adds the InnerGUIs to the Frame.
		frame.pushLayerWithRootWidget(new HorizontalStack().addWidget(innerGUI1, innerGUI2));
	}
	
	/**
	 * Prevents that an instance of the {@link InnerGUITutorial} can be created.
	 */
	private InnerGUITutorial() {}
}
