package ch.nolix.elementtutorial.guitutorial.containerwidgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Button;

/**
 * The {@link VerticalStackTutorial} is a tutorial for {@link VerticalStack}s.
 * Of the {@link VerticalStackTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-06-20
 * @lines 40
 */
public final class VerticalStackTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link VerticalStack}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		final var frame = new Frame().setTitle("VerticalStack tutorial");
		
		//Creates VerticalStack.
		final var verticalStack = new VerticalStack().setElementMargin(10);
		
		//Creates 4 Buttons.
		final var label1 = new Button().setText("A");
		final var label2 = new Button().setText("B");
		final var label3 = new Button().setText("C");
		final var label4 = new Button().setText("D");
		
		//Adds the Buttons to the VerticalStack.
		verticalStack.addWidget(label1, label2, label3, label4);
		
		//Adds the VerticalStack to the Frame.
		frame.addLayerOnTop(verticalStack);
	}
	
	/**
	 * Prevents that an instance of the {@link VerticalStackTutorial} can be created.
	 */
	private VerticalStackTutorial() {}
}
