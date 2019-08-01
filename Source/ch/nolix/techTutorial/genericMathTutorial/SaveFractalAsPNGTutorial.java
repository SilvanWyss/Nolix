package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.core.instanceProvider.CentralInstanceProvider;
import ch.nolix.element.GUI.LayerFrame;
import ch.nolix.element.widgets.Downloader;
import ch.nolix.element.widgets.ImageWidget;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.VerticalStack;
import ch.nolix.tech.genericMath.Registrator;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

public class SaveFractalAsPNGTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		Registrator.register();
		
		//Creates a Frame.
		final var frame =
		new LayerFrame(
			"Save Fractal as PNG Tutorial",
			new Label("Please wait...").applyOnBaseLook(bl -> bl.setTopPadding(100))
		);
		
		//Creates an image of a Fractal.
		final var image = CentralInstanceProvider.create(IFractalBuilder.class).build().toImage();
		
		//Lets the frame show the image.
		frame.addLayerOnTop(
			new VerticalStack(
				new Downloader()
				.setText("Save as PNG")
				.setFileProvider(() -> image.toPNG()),
				new ImageWidget(image)
			)
		);
	}
	
	private SaveFractalAsPNGTutorial() {}
}
