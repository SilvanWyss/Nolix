package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.widget.InnerGUI;
import ch.nolix.element.gui.widget.Label;

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
		new Frame()
		.setTitle("InnerGUI Tutorial")
		.addLayerOnTop(
			new HorizontalStack()
			.addWidget(
				new InnerGUI()
				.onLook(
					l ->
					l
					.setBorderThicknessForState(WidgetLookState.BASE, 5)
					.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
				)
				.setTitle("Inner GUI 1")
				.addLayerOnTop(new Label().setText("A").onLook(l -> l.setTextSizeForState(WidgetLookState.BASE, 100))),
				new InnerGUI()
				.onLook(
					l ->
					l
					.setBorderThicknessForState(WidgetLookState.BASE, 5)
					.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
				)
				.setTitle("Inner GUI 2")
				.addLayerOnTop(new Label().setText("B").onLook(l -> l.setTextSizeForState(WidgetLookState.BASE, 100)))
			)
		);
	}
	
	/**
	 * Prevents that an instance of the {@link InnerGUITutorial} can be created.
	 */
	private InnerGUITutorial() {}
}
