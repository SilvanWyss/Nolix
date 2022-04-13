package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.dynamicmath.ComplexNumber;
import ch.nolix.business.dynamicmath.FractalBuilder;
import ch.nolix.business.dynamicmath.SequenceDefinedBy1Predecessor;
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.ImageWidget;

public final class BlackWhiteJuliaFractalTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		@SuppressWarnings("resource")
		final var frame =
		new Frame()
		.setTitle("Black White Julia Fractal tutorial")
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
						z,
						p -> p.getPower2().getSum(new ComplexNumber(-0.8, 0.15)),
						IComplexNumber::getSquaredMagnitude)
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
	
	private BlackWhiteJuliaFractalTutorial() {}
}
