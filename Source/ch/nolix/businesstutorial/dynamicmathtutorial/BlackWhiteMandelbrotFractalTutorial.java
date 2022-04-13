package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.dynamicmath.ComplexNumber;
import ch.nolix.business.dynamicmath.FractalBuilder;
import ch.nolix.business.dynamicmath.SequenceDefinedBy1Predecessor;
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.ImageWidget;

public final class BlackWhiteMandelbrotFractalTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		@SuppressWarnings("resource")
		final var frame =
		new Frame()
		.setTitle("Black White Mandelrbrot Fractal tutorial")
		.addLayerOnTop(
			new ImageWidget()
			.setImage(
				new FractalBuilder()
				.setRealComponentInterval(-2.0, 1.0)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(500)
				.setHeightInPixel(500)
				.setSequenceCreator(
					c ->
					new SequenceDefinedBy1Predecessor<>(
						new ComplexNumber(0.0, 0.0),
						p -> p.getPower2().getSum(c),
						IComplexNumber::getMagnitude
					)
				)
				.setMinMagnitudeForDivergence(2.5)
				.setMaxIterationCount(100)
				.setColorFunction(i -> Color.WHITE)
				.setBigDecimalScale(20)
				.build()
				.startImageBuild()
				.getRefImage()
			)
		);
		
		//Refreshes the Frame as long as it is alive.
		Sequencer.asLongAs(frame::isOpen).afterAllMilliseconds(100).run(frame::refresh);
	}
	
	private BlackWhiteMandelbrotFractalTutorial() {}
}
