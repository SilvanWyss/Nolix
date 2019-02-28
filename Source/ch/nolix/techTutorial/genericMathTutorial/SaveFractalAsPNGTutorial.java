package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.element.GUI.Downloader;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.ImageWidget;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.tech.genericMath.FractalBuilder;

public class SaveFractalAsPNGTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var frame =
		new Frame(
			"Save Fractal as PNG Tutorial",
			new Label("Please wait...").applyOnBaseLook(bl -> bl.setTopPadding(100))
		);
		
		final var image = new FractalBuilder().build().toImage();
		
		frame.setRootWidget(
			new VerticalStack(
				new Downloader()
				.setText("Save as image")
				.setFileProvider(() -> image.toPNG()),
				new ImageWidget(image)
			)
		);
	}
}
