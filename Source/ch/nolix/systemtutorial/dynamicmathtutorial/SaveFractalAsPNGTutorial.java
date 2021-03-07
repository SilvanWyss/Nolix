package ch.nolix.systemtutorial.dynamicmathtutorial;

import ch.nolix.businessapi.dynamicmathapi.IFractalBuilder;
import ch.nolix.common.provider.implprovider.GlobalImplProvider;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Downloader;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.system.dynamicmath.DynamicMathImplRegistrator;

public final class SaveFractalAsPNGTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation of the dynamicmathapi at the GlobalImplProvider.
		new DynamicMathImplRegistrator().registerImplementationTo(GlobalImplProvider.getRefInstance());
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Save Fractal as PNG Tutorial")
		.addLayerOnTop(
			new Label()
			.setText("Please wait...")
			.applyOnBaseLook(bl -> bl.setTopPadding(100))
		);
		
		//Creates an Image of a Fractal.
		final var image = GlobalImplProvider.ofInterface(IFractalBuilder.class).createInstance().build().toImage();
		
		//Lets the Frame show the Image.
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
