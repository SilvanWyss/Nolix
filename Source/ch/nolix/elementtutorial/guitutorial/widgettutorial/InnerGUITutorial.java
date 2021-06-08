package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.widget.InnerGUI;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.WidgetLookState;

/**
 * The {@link InnerGUITutorial} is a tutorial for {@link InnerGUI}s.
 * Of the {@link InnerGUITutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2020-12-14
 * @lines 40
 */
public final class InnerGUITutorial {
	
	/**
	 * Creates a {@link Frame} with 2 {@link InnerGUI}s.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("InnerGUI tutorial");
		
		//Creates InnerGUIs.
		final var innerGUI1 =
		new InnerGUI()
		.setTitle("Inner GUI 1")
		.addLayerOnTop(new Label().setText("A").onLook(l -> l.setTextSizeForState(WidgetLookState.BASE, 100)));
		final var innerGUI2 =
		new InnerGUI()
		.setTitle("Inner GUI 2")
		.addLayerOnTop(new Label().setText("B").onLook(l -> l.setTextSizeForState(WidgetLookState.BASE, 100)));
		
		//Adds the InnerGUIs to the Frame.
		frame.addLayerOnTop(new HorizontalStack().addWidget(innerGUI1, innerGUI2));
	}
	
	/**
	 * Prevents that an instance of the {@link InnerGUITutorial} can be created.
	 */
	private InnerGUITutorial() {}
}
