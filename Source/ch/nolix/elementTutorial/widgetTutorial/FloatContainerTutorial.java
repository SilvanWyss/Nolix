package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.ContentPosition;
import ch.nolix.element.widget.FloatContainer;
import ch.nolix.element.widget.Label;

/**
 * The {@link FloatContainerTutorial} is a tutorial for {@link FloatContainer}s.
 * Of the {@link FloatContainerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 */
public final class FloatContainerTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link FloatContainerTutorial}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
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
			.setContentPosition(ContentPosition.Center)
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
	 * 
	 * @throws UninstantiableClassException
	 */
	private FloatContainerTutorial() {
		throw new UninstantiableClassException(getClass());
	}
}
