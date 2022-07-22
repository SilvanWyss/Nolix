package ch.nolix.systemtutorial.guitutorial.containerwidgettutorial;

//own imports
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.Grid;
import ch.nolix.system.gui.widget.Area;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widgetgui.Frame;
import ch.nolix.system.gui.widgetgui.GridType;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

/**
 * The {@link GridTutorial} is a tutorial for {@link Grid}s.
 * Of the {@link GridTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-05-01
 */
public final class GridTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Grid}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Grid tutorial");
		
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
			.setBorderThicknessForState(ControlState.BASE, 5)
			.setBackgroundColorForState(ControlState.BASE, Color.LAVENDER)
			.setPaddingForState(ControlState.BASE, 20)
			.setGridTypeForState(ControlState.BASE, GridType.INNER_LINES)
			.setElementMarginForState(ControlState.BASE, 10)
		);
		
		//Adds the Grid to the Frame.
		frame.pushLayerWithRootWidget(grid);
	}
	
	/**
	 * Prevents that an instance of the {@link GridTutorial} can be created.
	 */
	private GridTutorial() {}
}
