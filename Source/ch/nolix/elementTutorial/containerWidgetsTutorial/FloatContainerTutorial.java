package ch.nolix.elementTutorial.containerWidgetsTutorial;

//own imports
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.containerWidgets.FloatContainer;
import ch.nolix.element.elementEnums.ContentPosition;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.widgets.Label;

/**
 * The {@link FloatContainerTutorial} is a tutorial for {@link FloatContainer}s.
 * Of the {@link FloatContainerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 */
public final class FloatContainerTutorial {
	
	/**
	 * Creates a {@link FrameVisualizer} with a {@link FloatContainerTutorial}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Float Container Tutorial");
		
		//Creates a FloatConatiner.
		final var floatContainer =
		new FloatContainer(
			new Label("A"),
			new Label("B"),
			new Label("C"),
			new Label("D"),
			new Label("E"),
			new Label("F")
		);
		
		//Configures the look of the floatContainer.
		floatContainer.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
			.setPaddings(20)
			.setProposeContentWidth(1000)
		);
		
		//Configures the look of the child Widgets of the floatContainer.
		for (final var cw : floatContainer.getChildWidgets()) {
			cw
			.as(Label.class)
			.setProposalSize(220, 100)
			.setContentPosition(ContentPosition.CENTER)
			.applyOnBaseLook(
				bl ->
				bl.setBackgroundColor(Color.LIGHT_GREEN)
				.setTextSize(50)
				.setTextColor(Color.DARK_GREEN)
			);
		}
		
		//Adds the floatContainer to the frame.
		frame.addLayerOnTop(floatContainer);
	}
	
	/**
	 * Avoids that an instance of the {@link FloatContainerTutorial} can be created.
	 */
	private FloatContainerTutorial() {}
}
