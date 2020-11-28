package ch.nolix.techAPITutorial.genericMathTutorial;

import ch.nolix.common.instanceProvider.CentralInstanceProvider;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Label;
import ch.nolix.tech.genericMath.Registrator;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

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
