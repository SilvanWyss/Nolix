package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.dynamicmath.DynamicMathImplRegistrator;
import ch.nolix.businessapi.dynamicmathapi.IFractalBuilder;
import ch.nolix.core.provider.implprovider.GlobalImplProvider;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.Label;

public final class DefaultFractalTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation of the dynamicmathapi at the GlobalImplProvider.
		new DynamicMathImplRegistrator().registerImplementationTo(GlobalImplProvider.getRefInstance());
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame =
		new Frame()
		.setTitle("Default Fractal tutorial")
		.addLayerOnTop(new Label().setText("Please wait..."));
		
		//Sets the Image of a Fractal to the Frame. The creation of the Image may last a few seconds.
		frame.addLayerOnTop(
			new ImageWidget()
			.setImage(GlobalImplProvider.ofInterface(IFractalBuilder.class).createInstance().build().toImage())
		);
	}
	
	private DefaultFractalTutorial() {}
}
