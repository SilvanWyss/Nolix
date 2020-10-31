package ch.nolix.techAPITutorial.genericMathTutorial;

import ch.nolix.common.instanceProvider.CentralInstanceProvider;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.widget.Downloader;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.tech.genericMath.Registrator;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

public class SaveFractalAsPNGTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		Registrator.register();
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Save Fractal as PNG Tutorial")
		.addLayerOnTop(new Label().setText("Please wait...").applyOnBaseLook(bl -> bl.setTopPadding(100)));
		
		//Creates an image of a Fractal.
		final var image = CentralInstanceProvider.create(IFractalBuilder.class).build().toImage();
		
		//Lets the frame show the image.
		frame.addLayerOnTop(
			new VerticalStack()
			.addWidget(
				new Downloader()
				.setText("Save as PNG")
				.setFileProvider(() -> image.toPNG()),
				new ImageWidget().setImage(image)
			)
		);
	}
	
	private SaveFractalAsPNGTutorial() {}
}
