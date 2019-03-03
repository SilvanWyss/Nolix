package ch.nolix.techTutorial.genericMathTutorial;

import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.ImageWidget;
import ch.nolix.element.color.Color;
import ch.nolix.tech.genericMath.ClosedInterval;
import ch.nolix.tech.genericMath.ComplexNumber;
import ch.nolix.tech.genericMath.FractalBuilder;

public class BlackWhiteMandelbrotFractalTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var maxIterationCount = 100;
		
		final var frame =
		new Frame(
			"Black White Mandelrbrot Fractal Tutorial",
			new ImageWidget(
				new FractalBuilder()
				.setRealComponentInterval(new ClosedInterval(-2.5, 1.0))
				.setImaginaryComponentInterval(new ClosedInterval(-1.5, 1.5))
				.setWidthInPixel(800)
				.setSequencesStartValues(new ComplexNumber(0.0, 0.0))
				.setSequencesNextValueFunction((z, c) -> z.get(0).getSquare().getSum(c))
				.setSequencesMinDivergenceMagnitude(2.5)
				.setSequencesMaxIterationCount(maxIterationCount)
				.setColorFunction(i -> i < maxIterationCount ? Color.WHITE : Color.BLACK)
				.setBigDecimalScale(10)
				.build()
				.startImageBuild()
				.getRefImage()
			)
		);
		
		Sequencer.asLongAs(() -> frame.isAlive()).afterAllMilliseconds(100).run(() -> frame.refresh());
	}
	
	private BlackWhiteMandelbrotFractalTutorial() {}
}
