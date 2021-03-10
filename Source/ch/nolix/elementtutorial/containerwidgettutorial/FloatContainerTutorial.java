package ch.nolix.elementtutorial.containerwidgettutorial;

import ch.nolix.element.elementenum.ContentPosition;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.FloatContainer;
import ch.nolix.element.gui.widget.Label;

/**
 * The {@link FloatContainerTutorial} is a tutorial for {@link FloatContainer}s.
 * Of the {@link FloatContainerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
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
		.setTitle("Float Container Tutorial");
		
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
		floatContainer.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBackgroundColor(Color.LAVENDER)
			.setPaddings(20)
			.setProposeContentWidth(1000)
		);
		
		//Configures the look of the child Widgets of the FloatContainer.
		for (final var cw : floatContainer.getChildWidgets()) {
			cw
			.as(Label.class)
			.setProposalSize(220, 100)
			.setContentPosition(ContentPosition.CENTER)
			.applyOnBaseLook(
				bl ->
				bl.setBackgroundColor(Color.BLUE)
				.setTextSize(50)
			);
		}
		
		//Adds the FloatContainer to the frame.
		frame.addLayerOnTop(floatContainer);
	}
	
	/**
	 * Avoids that an instance of the {@link FloatContainerTutorial} can be created.
	 */
	private FloatContainerTutorial() {}
}
