package ch.nolix.techapitutorial.genericmathapitutorial;

import ch.nolix.common.instanceprovider.CentralInstanceProvider;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Label;
import ch.nolix.tech.genericmath.Registrator;
import ch.nolix.techapi.genericmathapi.IFractalBuilder;

public final class DefaultFractalTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		Registrator.register();
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Default Fractal Tutorial")
		.addLayerOnTop(new Label().setText("Please wait...").applyOnBaseLook(bl -> bl.setTopPadding(100)));
		
		//Sets the image of a Fractal to the frame. The creation of the image may last a few seconds.
		frame.addLayerOnTop(
			new ImageWidget().setImage(CentralInstanceProvider.create(IFractalBuilder.class).build().toImage())
		);
	}
	
	private DefaultFractalTutorial() {}
}
