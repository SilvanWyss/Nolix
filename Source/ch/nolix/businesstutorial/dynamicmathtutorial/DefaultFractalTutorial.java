package ch.nolix.businesstutorial.dynamicmathtutorial;

//own imports
import ch.nolix.business.bigdecimalmath.FractalBuilder;
import ch.nolix.system.gui.main.Frame;
import ch.nolix.system.gui.widget.ImageWidget;

public final class DefaultFractalTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		new Frame()
		.setTitle("Default Fractal Tutorial")
		.pushLayerWithRootWidget(
			new ImageWidget().setImage(new FractalBuilder().build().toImage())
		);
	}
	
	private DefaultFractalTutorial() {}
}
