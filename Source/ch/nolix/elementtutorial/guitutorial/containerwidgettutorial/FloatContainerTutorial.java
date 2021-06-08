package ch.nolix.elementtutorial.guitutorial.containerwidgettutorial;

import ch.nolix.element.elementenum.ContentPosition;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.FloatContainer;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.WidgetLookState;

/**
 * The {@link FloatContainerTutorial} is a tutorial for {@link FloatContainer}s.
 * Of the {@link FloatContainerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-06-01
 * @lines 70
 */
public final class FloatContainerTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link FloatContainer}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = 
		new Frame()
		.setTitle("Float Container tutorial");
		
		//Creates a FloatConatiner.
		final var floatContainer =
		new FloatContainer()
		.addWidget(
			new Label().setText("A"),
			new Label().setText("B"),
			new Label().setText("C"),
			new Label().setText("D"),
			new Label().setText("E"),
			new Label().setText("F")
		);
		
		//Configures the look of the FloatContainer.
		floatContainer.onLook(
			l ->
			l
			.setBorderThicknessForState(WidgetLookState.BASE, 5)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.BASE, 20)
		);
		
		//Configures the look of the child Widgets of the FloatContainer.
		for (final var cw : floatContainer.getChildWidgets()) {
			cw
			.as(Label.class)
			.setProposalWidth(220)
			.setProposalHeight(100)
			.setContentPosition(ContentPosition.CENTER)
			.onLook(
				l ->
				l
				.setBackgroundColorForState(WidgetLookState.BASE, Color.BLUE)
				.setTextSizeForState(WidgetLookState.BASE, 50)
			);
		}
		
		//Adds the FloatContainer to the frame.
		frame.addLayerOnTop(floatContainer);
	}
	
	/**
	 * Prevents that an instance of the {@link FloatContainerTutorial} can be created.
	 */
	private FloatContainerTutorial() {}
}
