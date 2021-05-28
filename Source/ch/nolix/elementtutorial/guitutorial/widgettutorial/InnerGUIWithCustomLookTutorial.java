package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.widget.InnerGUI;
import ch.nolix.element.gui.widget.Label;

/**
 * The {@link InnerGUIWithCustomLookTutorial} is a tutorial for {@link InnerGUI}s.
 * Of the {@link InnerGUIWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-21
 * @lines 60
 */
public final class InnerGUIWithCustomLookTutorial {
	
	/**
	 * Creates a {@link Frame} with 2 {@link InnerGUI}s.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("InnerGUI with custom look tutorial");
		
		//Creates InnerGUIs.
		final var innerGUI1 =
		new InnerGUI()
		.setTitle("Inner GUI 1")
		.addLayerOnTop(new Label().setText("A").onLook(l -> l.setTextSizeForState(WidgetLookState.BASE, 100)));
		final var innerGUI2 =
		new InnerGUI()
		.setTitle("Inner GUI 2")
		.addLayerOnTop(new Label().setText("B").onLook(l -> l.setTextSizeForState(WidgetLookState.BASE, 100)));
		
		//Configures the look of the InnerGUIs.
		innerGUI1.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 1)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
		);
		innerGUI2.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 1)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
		);
		
		//Adds the InnerGUIs to the Frame.
		frame.addLayerOnTop(new HorizontalStack().addWidget(innerGUI1, innerGUI2));
	}
	
	/**
	 * Prevents that an instance of the {@link InnerGUIWithCustomLookTutorial} can be created.
	 */
	private InnerGUIWithCustomLookTutorial() {}
}
