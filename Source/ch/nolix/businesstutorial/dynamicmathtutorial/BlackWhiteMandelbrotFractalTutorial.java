package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.bigdecimalmath.ComplexNumber;
import ch.nolix.business.bigdecimalmath.ComplexSequenceDefinedBy1Predecessor;
import ch.nolix.business.bigdecimalmath.FractalBuilder;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.ImageWidget;

public final class BlackWhiteMandelbrotFractalTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		@SuppressWarnings("resource")
		final var frame =
		new Frame()
		.setTitle("Black white Mandelbrot Fractal tutorial")
		.pushLayerWithRootWidget(
			new ImageWidget()
			.setImage(
				new FractalBuilder()
				.setRealComponentInterval(-2.0, 1.0)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(500)
				.setHeightInPixel(500)
				.setSequenceCreator(
					z ->
					new ComplexSequenceDefinedBy1Predecessor(
						new ComplexNumber(0.0, 0.0),
						p -> p.getPower2().getSum(z)
					)
				)
				.setMinMagnitudeForDivergence(10.0)
				.setMaxIterationCount(50)
				.setColorFunction(i -> Color.WHITE)
				.setBigDecimalScale(10)
				.build()
				.startImageGeneration()
				.getRefImage()
			)
		);
		
		//Refreshes the Frame as long as it is alive.
		GlobalSequencer.asLongAs(frame::isOpen).afterAllMilliseconds(100).run(frame::refresh);
	}
	
	private BlackWhiteMandelbrotFractalTutorial() {}
}
