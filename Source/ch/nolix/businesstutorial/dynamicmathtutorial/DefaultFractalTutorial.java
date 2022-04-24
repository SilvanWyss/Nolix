package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.dynamicmath.FractalBuilder;
import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.widget.ImageWidget;

public final class DefaultFractalTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		new Frame()
		.setTitle("Default Fractal tutorial")
		.addLayerOnTop(
			new ImageWidget().setImage(new FractalBuilder().build().toImage())
		);
	}
	
	private DefaultFractalTutorial() {}
}
