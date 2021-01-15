package ch.nolix.techtutorial.dynamicmathtutorial;

//own imports
import ch.nolix.common.instanceprovider.CentralInstanceProvider;
import ch.nolix.element.containerwidget.VerticalStack;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.Downloader;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Label;
import ch.nolix.tech.genericmath.Registrator;
import ch.nolix.techapi.dynamicmathapi.IFractalBuilder;

public final class SaveFractalAsPNGTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		Registrator.register();
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Save Fractal as PNG Tutorial")
		.addLayerOnTop(
			new Label()
			.setText("Please wait...")
			.applyOnBaseLook(bl -> bl.setTopPadding(100))
		);
		
		//Creates an image of a Fractal.
		final var image = CentralInstanceProvider.create(IFractalBuilder.class).build().toImage();
		
		//Lets the Frame show the image.
		frame.addLayerOnTop(
			new VerticalStack()
			.addWidget(
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
