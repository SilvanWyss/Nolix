package ch.nolix.systemtutorial.dynamicmathtutorial;

import ch.nolix.businessapi.dynamicmathapi.IFractalBuilder;
import ch.nolix.common.implprovider.GlobalImplProvider;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Label;
import ch.nolix.system.dynamicmath.DynamicMathImplRegistrator;

public final class DefaultFractalTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation of the dynamicmathapi at the GlobalImplProvider.
		new DynamicMathImplRegistrator().registerImplementationTo(GlobalImplProvider.getRefInstance());
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("Default Fractal Tutorial")
		.addLayerOnTop(new Label().setText("Please wait...").applyOnBaseLook(bl -> bl.setTopPadding(100)));
		
		//Sets the Image of a Fractal to the Frame. The creation of the Image may last a few seconds.
		frame.addLayerOnTop(
			new ImageWidget()
			.setImage(GlobalImplProvider.ofInterface(IFractalBuilder.class).createInstance().build().toImage())
		);
	}
	
	private DefaultFractalTutorial() {}
}
