package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.core.instanceProvider.CentralInstanceProvider;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widgets.ImageWidget;
import ch.nolix.tech.genericMath.Registrator;
import ch.nolix.techAPI.genericMathAPI.IComplexNumber;
import ch.nolix.techAPI.genericMathAPI.IComplexNumberFactory;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

public final class CustomFractalTutorial2 {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		Registrator.register();
		
		final var maxIterationCount = 500;
		final var j = CentralInstanceProvider.create(IComplexNumberFactory.class).create(-0.8, 0.15);
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame(
			"Custom Fractal Tutorial 2",
			new ImageWidget(
				CentralInstanceProvider.create(IFractalBuilder.class)
				.setRealComponentInterval(-1.0, 1.0)
				.setImaginaryComponentInterval(-1.0, 1.0)
				.setWidthInPixel(800)
				.setStartValuesFunction(c -> new IComplexNumber[]{c})
				.setNextValueFunctionFor1Predecessor((p, c) -> p.getPower(6).getSum(j))
				.setMinMagnitudeForConvergence(2.5)
				.setMaxIterationCount(maxIterationCount)
				.setColorFunction(i -> i < maxIterationCount ? new Color(i % 256, i % 256, i % 256) : Color.BLACK)
				.setBigDecimalScale(20)
				.build()
				.startImageBuild()
				.getRefImage()
			)
		);
		
		//Refreshes the frame as long as it is alive.
		Sequencer.asLongAs(() -> frame.isOpen()).afterAllMilliseconds(100).run(() -> frame.refresh());
	}
	
	private CustomFractalTutorial2() {}
}
