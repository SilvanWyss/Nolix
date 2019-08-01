package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.core.instanceProvider.CentralInstanceProvider;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.element.GUI.LayerFrame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widgets.ImageWidget;
import ch.nolix.tech.genericMath.Registrator;
import ch.nolix.techAPI.genericMathAPI.IComplexNumberFactory;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

public class BlackWhiteMandelbrotFractalTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		Registrator.register();
		
		final var maxIterationCount = 100;
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new LayerFrame(
			"Black White Mandelrbrot Fractal Tutorial",
			new ImageWidget(
				CentralInstanceProvider.create(IFractalBuilder.class)
				.setRealComponentInterval(-2.5, 1.0)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(800)
				.setStartValues(CentralInstanceProvider.create(IComplexNumberFactory.class).create(0.0, 0.0))
				.setNextValueFunctionFor1Predecessor((p, c) -> p.getPower2().getSum(c))
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
	
	private BlackWhiteMandelbrotFractalTutorial() {}
}
