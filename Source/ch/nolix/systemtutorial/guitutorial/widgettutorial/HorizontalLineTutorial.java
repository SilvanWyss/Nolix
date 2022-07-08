package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.system.gui.containerwidget.HorizontalStack;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.main.Frame;
import ch.nolix.system.gui.widget.HorizontalLine;
import ch.nolix.system.gui.widget.Label;

/**
 * The {@link HorizontalLineTutorial} is a tutorial for {@link HorizontalLineTutorial}s.
 * Of the {@link HorizontalLineTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2020-06-13
 */
public final class HorizontalLineTutorial {
	
	/**
	 * Creates a {@link HorizontalStack} with 3 {@link Label}s and 2 {@link HorizontalLine}s.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("HorizontalLine tutorial");
	
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
		
		//Adds the VerticalStack to the Frame.
		frame.pushLayerWithRootWidget(verticalStack);
	}
	
	/**
	 * Prevents that an instance of the {@link HorizontalLineTutorial} can be created.
	 */
	private HorizontalLineTutorial() {}
}
