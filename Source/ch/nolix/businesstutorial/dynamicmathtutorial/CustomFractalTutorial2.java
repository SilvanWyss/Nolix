package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.dynamicmath.ComplexNumber;
import ch.nolix.business.dynamicmath.FractalBuilder;
import ch.nolix.business.dynamicmath.SequenceDefinedBy1Predecessor;
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.ImageWidget;

public final class CustomFractalTutorial2 {
	
	public static void main(String[] args) {
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		@SuppressWarnings("resource")
		final var frame =
		new Frame()
		.setTitle("Custom Fractal Tutorial 2")
		.addLayerOnTop(
			new ImageWidget()
			.setImage(
				new FractalBuilder()
				.setRealComponentInterval(-1.0, 1.0)
				.setImaginaryComponentInterval(-1.0, 1.0)
				.setWidthInPixel(500)
				.setHeightInPixel(500)
				.setSequenceCreator(
					z -> new SequenceDefinedBy1Predecessor<>(
						z,
						p -> p.getPower(6).getSum(new ComplexNumber(-0.8, 0.15)),
						IComplexNumber::getSquaredMagnitude
					)
				)
				.setMinMagnitudeForDivergence(10.0)
				.setMaxIterationCount(50)
				.setColorFunction(
					i -> Color.withRedValueAndGreenValueAndBlueValue((2 * i) % 256, (3 * i) % 256, (4 * i) % 256)
				)
				.setBigDecimalScale(10)
				.build()
				.startImageBuild()
				.getRefImage()
			)
		);
		
		//Refreshes the Frame as long as it is alive.
		Sequencer.asLongAs(frame::isOpen).afterAllMilliseconds(100).run(frame::refresh);
	}
	
	private CustomFractalTutorial2() {}
}
