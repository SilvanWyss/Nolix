package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.core.classProvider.ClassProvider;
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.ImageWidget;
import ch.nolix.element.GUI.Label;
import ch.nolix.tech.genericMath.GenericMathRegistrator;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

public final class DefaultFractalTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		GenericMathRegistrator.register();
		
		//Creates a Frame.
		final var frame =
		new Frame(
			"Default Fractal Tutorial",
			new Label("Please wait...").applyOnBaseLook(bl -> bl.setTopPadding(100))
		);
		
		//Sets the image of a Fractal to the frame. The creation of the image may last a few seconds.
		frame.setRootWidget(new ImageWidget(ClassProvider.create(IFractalBuilder.class).build().toImage()));
	}
	
	private DefaultFractalTutorial() {
		throw new UninstantiableClassException(DefaultFractalTutorial.class);
	}
}
