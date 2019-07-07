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

public final class CustomFractalTutorial3 {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		GenericMathRegistrator.register();
		
		final var maxIterationCount = 100;
		final var j = CentralClassProvider.create(IComplexNumberFactory.class).create(-0.745, 0.25);
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame(
			"Custom Fractal Tutorial 3",
			new ImageWidget(
				CentralClassProvider.create(IFractalBuilder.class)
				.setRealComponentInterval(-1.5, 1.5)
				.setImaginaryComponentInterval(-1.0, 1.0)
				.setWidthInPixel(800)
				.setSequencesStartValuesFunction(c -> new IComplexNumber[]{c})
				.setSequencesNextValueFunctionFor1Predecessor((p, c) -> p.getPower2().getSum(j))
				.setSequencesMinDivergenceMagnitude(2.5)
				.setSequencesMaxIterationCount(maxIterationCount)
				.setColorFunction(
					i -> i < maxIterationCount ?	new Color((10 * i) % 256, i % 256, (20 * i) % 256) : Color.BLACK
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
	
	private CustomFractalTutorial3() {}
}
