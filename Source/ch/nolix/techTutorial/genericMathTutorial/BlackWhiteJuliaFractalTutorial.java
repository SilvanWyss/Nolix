package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.core.classProvider.CentralClassProvider;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.tech.genericMath.GenericMathRegistrator;
import ch.nolix.techAPI.genericMathAPI.IComplexNumber;
import ch.nolix.techAPI.genericMathAPI.IComplexNumberFactory;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

public final class BlackWhiteJuliaFractalTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		GenericMathRegistrator.register();
		
		final var maxIterationCount = 100;
		final var j = CentralClassProvider.create(IComplexNumberFactory.class).create(-0.8, 0.15);
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame(
			"Black White Julia Fractal Tutorial",
			new ImageWidget(
				CentralClassProvider.create(IFractalBuilder.class)
				.setRealComponentInterval(-2.0, 2.0)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(800)
				.setStartValuesFunction(c -> new IComplexNumber[]{c})
				.setNextValueFunctionFor1Predecessor((p, c) -> p.getPower2().getSum(j))
				.setMinMagnitudeForConvergence(2.5)
				.setMaxIterationCount(maxIterationCount)
				.setColorFunction(i -> i < maxIterationCount ? Color.WHITE : Color.BLACK)
				.setBigDecimalScale(20)
				.build()
				.startImageBuild()
				.getRefImage()
			)
		);
		
		//Refreshes the frame as long as it is alive.
		Sequencer.asLongAs(() -> frame.isOpen()).afterAllMilliseconds(100).run(() -> frame.refresh());
	}
	
	private BlackWhiteJuliaFractalTutorial() {}
}
