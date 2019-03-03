package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.core.classProvider.ClassProvider;
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.ImageWidget;
import ch.nolix.element.color.Color;
import ch.nolix.tech.genericMath.GenericMathRegistrator;
import ch.nolix.techAPI.genericMathAPI.IClosedIntervalFactory;
import ch.nolix.techAPI.genericMathAPI.IComplexNumberFactory;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

public class MandelbrotFractalTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		GenericMathRegistrator.register();
		
		final var maxIterationCount = 100;
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame(
			"Mandelrbrot Fractal Tutorial",
			new ImageWidget(
				ClassProvider.create(IFractalBuilder.class)
				.setRealComponentInterval(ClassProvider.create(IClosedIntervalFactory.class).create(-2.5, 1.0))
				.setImaginaryComponentInterval(ClassProvider.create(IClosedIntervalFactory.class).create(-1.5, 1.5))
				.setWidthInPixel(800)
				.setSequencesStartValues(ClassProvider.create(IComplexNumberFactory.class).create(0.0, 0.0))
				.setSequencesNextValueFunction((z, c) -> z.get(0).getSquare().getSum(c))
				.setSequencesMinDivergenceMagnitude(3.0)
				.setSequencesMaxIterationCount(maxIterationCount)
				.setColorFunction(
					i ->
					i < maxIterationCount ?
					new Color(0,(i * i) % Color.MAX_COLOR_COMPONENT, (10 * i) % 255) : Color.BLACK
				)
				.setBigDecimalScale(20)
				.build()
				.startImageBuild()
				.getRefImage()
			)
		);
		
		//Refreshes the frame as long as it is alive.
		Sequencer.asLongAs(() -> frame.isAlive()).afterAllMilliseconds(100).run(() -> frame.refresh());
	}
	
	private MandelbrotFractalTutorial() {
		throw new UninstantiableClassException(MandelbrotFractalTutorial.class);
	}
}
