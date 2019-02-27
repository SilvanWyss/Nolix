package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.ImageWidget;
import ch.nolix.element.GUI.Label;
import ch.nolix.tech.genericMath.FractalBuilder;

public final class DefaultFractalTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a frame.
		final var frame =
		new Frame(
			"Default Fractal Tutorial",
			new Label("Please wait...").applyOnBaseLook(bl -> bl.setTopPadding(100))
		);
		
		//Sets the image of a fractal to the frame. The creation of the image may last a bit longer.
		frame.setRootWidget(new ImageWidget(new FractalBuilder().build().toImage()));
	}
	
	private DefaultFractalTutorial() {}
}
