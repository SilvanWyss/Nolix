package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.element.elementenum.ContentPosition;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.HorizontalLine;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.WidgetLookState;

/**
 * The {@link HorizontalLineWithCustomLookTutorial} is a tutorial for {@link HorizontalLineTutorial}s.
 * Of the {@link HorizontalLineWithCustomLookTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2021-05-21
 * @lines 60
 */
public final class HorizontalLineWithCustomLookTutorial {
	
	/**
	 * Creates a {@link HorizontalStack} with 3 {@link Label}s and 2 {@link HorizontalLine}s.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("HorizontalLine with custom look tutorial");
	
		//Creates a VerticalStack with 3 Labels and 2 HorizontalLines.
		final var verticalStack =
		new VerticalStack()
		.addWidget(
			new Label().setText("Lorem ipsum dolor sit amet, consectetur adipisici elit"),
			new HorizontalLine(),
			new Label().setText("Ut enim ad minim veniam"),
			new HorizontalLine(),
			new Label().setText("quis nostrud exercitation")
		);
		
		//Configures the look of the VerticalStack.
		verticalStack
		.setContentPosition(ContentPosition.TOP)
		.setElementMargin(50)
		.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 1)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.BASE, 50)
		);
		
		//Adds the VerticalStack to the Frame.
		frame.addLayerOnTop(verticalStack);
	}
	
	/**
	 * Prevents that an instance of the {@link HorizontalLineWithCustomLookTutorial} can be created.
	 */
	private HorizontalLineWithCustomLookTutorial() {}
}
