package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.textFormat.Font;
import ch.nolix.element.widget.Area;
import ch.nolix.element.widget.Grid;
import ch.nolix.element.widget.GridLineType;
import ch.nolix.element.widget.Label;

/**
 * The {@link GridTutorial} is a tutorial for {@link Grid}s.
 * Of the {@link GridTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 */
public final class GridTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Grid}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Grid Tutorial");
		
		//Creates a Grid
		final var grid =
		new Grid()
		.setWidget(1, 1, new Label("A"))
		.setWidget(1, 2, new Label("B"))
		.setWidget(1, 3, new Label("C"))
		.setWidget(1, 4, new Label("D"))
		.setWidget(2, 1, new Area(200, 100, Color.LIGHT_GREEN))
		.setWidget(2, 3, new Area(200, 100, Color.LIGHT_GREEN))
		.setWidget(3, 2, new Area(200, 100, Color.LIGHT_GREEN))
		.setWidget(3, 4, new Area(200, 100, Color.LIGHT_GREEN))
		.setWidget(4, 1, new Area(200, 100, Color.LIGHT_GREEN))
		.setWidget(4, 3, new Area(200, 100, Color.LIGHT_GREEN));
		
		//Configures the look of the grid.
		grid.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.WHITE_SMOKE)
			.setPaddings(20)
			.setLineType(GridLineType.InnerLines)
			.setElementMargin(10)
			.setTextFont(Font.Console)
		);
		
		//Adds the grid to the frame.
		frame.setRootWidget(grid);
	}
	
	/**
	 * Avoids that an instance of the {@link GridTutorial} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private GridTutorial() {
		throw new UninstantiableClassException(getClass());
	}
}
