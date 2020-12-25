package ch.nolix.elementtutorial.widgettutorial;

import ch.nolix.element.color.Color;
import ch.nolix.element.containerwidget.HorizontalStack;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.InnerGUI;
import ch.nolix.element.widget.Label;

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
				.applyOnBaseLook(bl ->	bl.setBorderThicknesses(5).setBackgroundColor(Color.LAVENDER))
				.setTitle("Inner GUI 1")
				.addLayerOnTop(new Label().setText("A").applyOnBaseLook(bl -> bl.setTextSize(100))),
				new InnerGUI()
				.applyOnBaseLook(bl ->	bl.setBorderThicknesses(5).setBackgroundColor(Color.LAVENDER))
				.setTitle("Inner GUI 2")
				.addLayerOnTop(new Label().setText("B").applyOnBaseLook(bl -> bl.setTextSize(100)))
			)
		);
	}
	
	/**
	 * Avoids that an instance of the {@link InnerGUITutorial} can be created.
	 */
	private InnerGUITutorial() {}
}
