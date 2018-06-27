//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.element.GUI.Area;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.Grid;
import ch.nolix.element.GUI.GridLineType;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.color.Color;
import ch.nolix.element.font.TextFont;

//class
/**
 * The {@link GridTutorial} provides a tutorial for a {@link Grid}.
 * Of the {@link GridTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 60
 */
public final class GridTutorial {

	//main method
	/**
	 * Creates a {@link Frame} with a {@link Grid}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a grid
		final var grid =
		new Grid()
		.setWidget(1, 1, new Label("Sokrates"))
		.setWidget(1, 2, new Label("Platon"))
		.setWidget(1, 3, new Label("Aristoteles"))
		.setWidget(1, 4, new Label("Demokrit"))
		.setWidget(2, 1, new Area().setBackgroundColor(Color.DARK_BLUE).setWidth(200).setHeight(100))
		.setWidget(2, 3, new Area().setBackgroundColor(Color.DARK_BLUE).setWidth(200).setHeight(100))
		.setWidget(3, 2, new Area().setBackgroundColor(Color.DARK_BLUE).setWidth(200).setHeight(100))
		.setWidget(3, 4, new Area().setBackgroundColor(Color.DARK_BLUE).setWidth(200).setHeight(100))
		.setWidget(4, 1, new Area().setBackgroundColor(Color.DARK_BLUE).setWidth(200).setHeight(100))
		.setWidget(4, 3, new Area().setBackgroundColor(Color.DARK_BLUE).setWidth(200).setHeight(100));
		
		//Configures the look of the grid.
		grid
		.getRefBaseLook()
		.setBorderThicknesses(5)
		.setBorderColors(Color.DARK_BLUE)
		.setPaddings(20)
		.setLineType(GridLineType.InnerLines)
		.setElementMargin(10)
		.setTextFont(TextFont.Console);
		
		//Creates a frame with the grid.
		new Frame()
		.setTitle("Grid Tutorial")
		.setRootWidget(grid);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link GridTutorial} can be created.
	 */
	private GridTutorial() {}
}
