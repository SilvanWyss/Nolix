package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.dynamicmath.FractalBuilder;
import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.widget.Downloader;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Label;

public final class SaveFractalAsPNGTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame =
		new Frame()
		.setTitle("Save Fractal as PNG tutorial")
		.addLayerOnTop(new Label().setText("Please wait..."));
		
		//Creates an Image of a Fractal.
		final var image = new FractalBuilder().build().toImage();
		
		//Adds the Image to the Frame.
		frame.addLayerOnTop(
			new VerticalStack()
			.add(
				new Downloader()
				.setText("Save as PNG")
				.setFileProvider(image::toPNG),
				new ImageWidget()
				.setImage(image)
			)
		);
	}
	
	private SaveFractalAsPNGTutorial() {}
}
