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

public final class JuliaFractalTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		GenericMathRegistrator.register();
		
		final var maxIterationCount = 100;
		final var j = CentralClassProvider.create(IComplexNumberFactory.class).create(-0.8, 0.15);
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame(
			"Julia Fractal Tutorial",
			new ImageWidget(
				CentralClassProvider.create(IFractalBuilder.class)
				.setRealComponentInterval(-2.0, 2.0)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(800)
				.setSequencesStartValuesFunction(c -> new IComplexNumber[]{c})
				.setSequencesNextValueFunctionFor1Predecessor((p, c) -> p.getSquare().getSum(j))
				.setSequencesMinDivergenceMagnitude(2.5)
				.setSequencesMaxIterationCount(maxIterationCount)
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
		Sequencer.asLongAs(() -> frame.isAlive()).afterAllMilliseconds(100).run(() -> frame.refresh());
	}
	
	private JuliaFractalTutorial() {}
}
