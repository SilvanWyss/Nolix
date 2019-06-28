package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.core.classProvider.CentralClassProvider;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.widget.Downloader;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.tech.genericMath.GenericMathRegistrator;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

public class SaveFractalAsPNGTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		GenericMathRegistrator.register();
		
		//Creates a Frame.
		final var frame =
		new Frame(
			"Save Fractal as PNG Tutorial",
			new Label("Please wait...").applyOnBaseLook(bl -> bl.setTopPadding(100))
		);
		
		//Creates an image of a Fractal.
		final var image = CentralClassProvider.create(IFractalBuilder.class).build().toImage();
		
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
