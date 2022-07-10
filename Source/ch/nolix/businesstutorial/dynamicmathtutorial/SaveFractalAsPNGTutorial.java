package ch.nolix.businesstutorial.dynamicmathtutorial;

//own imports
import ch.nolix.business.bigdecimalmath.FractalBuilder;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.widget.Downloader;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widgetgui.Frame;

public final class SaveFractalAsPNGTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame =
		new Frame()
		.setTitle("Save Fractal as PNG Tutorial")
		.pushLayerWithRootWidget(new Label().setText("Please wait..."));
		
		//Creates an Image of a Fractal.
		final var image = new FractalBuilder().build().toImage();
		
		//Adds the Image to the Frame.
		frame.pushLayerWithRootWidget(
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
