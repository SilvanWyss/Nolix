package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.core.classProvider.ClassProvider;
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.ImageWidget;
import ch.nolix.element.color.Color;
import ch.nolix.tech.genericMath.GenericMathRegistrator;
import ch.nolix.techAPI.genericMathAPI.IComplexNumber;
import ch.nolix.techAPI.genericMathAPI.IComplexNumberFactory;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

public final class CustomFractalTutorial2 {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		GenericMathRegistrator.register();
		
		final var maxIterationCount = 500;
		final var j = ClassProvider.create(IComplexNumberFactory.class).create(-0.8, 0.15);
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame(
			"Black White Julia Fractal Tutorial",
			new ImageWidget(
				ClassProvider.create(IFractalBuilder.class)
				.setRealComponentInterval(-1.0, 1.0)
				.setImaginaryComponentInterval(-1.0, 1.0)
				.setWidthInPixel(800)
				.setSequencesStartValuesFunction(c -> new IComplexNumber[]{c})
				.setSequencesNextValueFunctionFor1Predecessor((p, c) -> p.getPower(6).getSum(j))
				.setSequencesMinDivergenceMagnitude(2.5)
				.setSequencesMaxIterationCount(maxIterationCount)
				.setColorFunction(i -> i < maxIterationCount ? new Color(i % 256, i % 256, i % 256) : Color.BLACK)
				.setBigDecimalScale(20)
				.build()
				.startImageBuild()
				.getRefImage()
			)
		);
		
		//Refreshes the frame as long as it is alive.
		Sequencer.asLongAs(() -> frame.isAlive()).afterAllMilliseconds(100).run(() -> frame.refresh());
	}
	
	private CustomFractalTutorial2() {
		throw new UninstantiableClassException(CustomFractalTutorial2.class);
	}
}
