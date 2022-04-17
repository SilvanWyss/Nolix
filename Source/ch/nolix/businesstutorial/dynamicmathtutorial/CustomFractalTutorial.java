package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.dynamicmath.ComplexNumber;
import ch.nolix.business.dynamicmath.FractalBuilder;
import ch.nolix.business.dynamicmath.SequenceDefinedBy1Predecessor;
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.ImageWidget;

public final class CustomFractalTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		@SuppressWarnings("resource")
		final var frame =
		new Frame()
		.setTitle("Custom Fractal tutorial")
		.addLayerOnTop(
			new ImageWidget()
			.setImage(
				new FractalBuilder()
				.setRealComponentInterval(-1.5, 1.5)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(500)
				.setHeightInPixel(500)
				.setSequenceCreator(
					z ->
					new SequenceDefinedBy1Predecessor<>(
						new ComplexNumber(0.0, 0.0),
						p -> p.getPower4().getSum(z),
						IComplexNumber::getSquaredMagnitude
					)
				)
				.setMinMagnitudeForDivergence(2.5)
				.setMaxIterationCount(100)
				.setColorFunction(
					i -> Color.withRedValueAndGreenValueAndBlueValue(i % 256, (10 * i) % 256, (2 * i) % 256)
				)
				.setBigDecimalScale(20)
				.build()
				.startImageBuild()
				.getRefImage()
			)
		);
		
		//Refreshes the Frame as long as it is alive.
		Sequencer.asLongAs(frame::isOpen).afterAllMilliseconds(100).run(frame::refresh);
	}
	
	private CustomFractalTutorial() {}
}
