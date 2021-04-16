package ch.nolix.elementtutorial.containerwidgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.Grid;
import ch.nolix.element.gui.containerwidget.GridType;
import ch.nolix.element.gui.widget.Area;
import ch.nolix.element.gui.widget.Label;

/**
 * The {@link GridTutorial} is a tutorial for {@link Grid}s.
 * Of the {@link GridTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 60
 */
public final class GridTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Grid}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Grid Tutorial");
		
		//Creates a Grid
		final var grid =
		new Grid()
		.setWidget(1, 1, new Label().setText("A"))
		.setWidget(1, 2, new Label().setText("B"))
		.setWidget(1, 3, new Label().setText("C"))
		.setWidget(1, 4, new Label().setText("D"))
		.setWidget(2, 1, new Area().setSize(200, 100).setBackgroundColor(Color.BLUE))
		.setWidget(2, 3, new Area().setSize(200, 100).setBackgroundColor(Color.BLUE))
		.setWidget(3, 2, new Area().setSize(200, 100).setBackgroundColor(Color.BLUE))
		.setWidget(3, 4, new Area().setSize(200, 100).setBackgroundColor(Color.BLUE))
		.setWidget(4, 1, new Area().setSize(200, 100).setBackgroundColor(Color.BLUE))
		.setWidget(4, 3, new Area().setSize(200, 100).setBackgroundColor(Color.BLUE));
		
		//Configures the look of the Grid.
		grid.onLook(
			l ->
			l
			.setBorderThicknessesForState(WidgetLookState.NORMAL, 5)
			.setBackgroundColorForState(WidgetLookState.NORMAL, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.NORMAL, 20)
			.setGridTypeForState(WidgetLookState.NORMAL, GridType.INNER_LINES)
			.setElementMarginForState(WidgetLookState.NORMAL, 10)
		);
		
		//Adds the Grid to the Frame.
		frame.addLayerOnTop(grid);
	}
	
	/**
	 * Avoids that an instance of the {@link GridTutorial} can be created.
	 */
	private GridTutorial() {}
}
