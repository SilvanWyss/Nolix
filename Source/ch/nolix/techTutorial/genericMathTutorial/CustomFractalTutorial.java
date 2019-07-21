package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.core.instanceProvider.CentralInstanceProvider;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.tech.genericMath.GenericMathRegistrator;
import ch.nolix.techAPI.genericMathAPI.IComplexNumberFactory;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

public class CustomFractalTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		GenericMathRegistrator.register();
		
		final var maxIterationCount = 100;
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame(
			"Custom Fractal Tutorial",
			new ImageWidget(
				CentralInstanceProvider.create(IFractalBuilder.class)
				.setRealComponentInterval(-2.0, 1.5)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(800)
				.setStartValues(CentralInstanceProvider.create(IComplexNumberFactory.class).create(0.0, 0.0))
				.setNextValueFunctionFor1Predecessor((p, c) -> p.getPower(4).getSum(c))
				.setMinMagnitudeForConvergence(2.5)
				.setMaxIterationCount(maxIterationCount)
				.setColorFunction(
					i ->
					i < maxIterationCount ?	new Color(i % 256, (10 * i) % 256, (2 * i) % 256) : Color.BLACK
				)
				.setBigDecimalScale(20)
				.build()
				.startImageBuild()
				.getRefImage()
			)
		);
		
		//Refreshes the frame as long as it is alive.
		Sequencer.asLongAs(() -> frame.isOpen()).afterAllMilliseconds(100).run(() -> frame.refresh());
	}
	
	private CustomFractalTutorial() {}
}
