package ch.nolix.techapitutorial.genericmathapitutorial;

import ch.nolix.common.instanceprovider.CentralInstanceProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.tech.genericMath.Registrator;
import ch.nolix.techapi.genericmathapi.IComplexNumber;
import ch.nolix.techapi.genericmathapi.IComplexNumberFactory;
import ch.nolix.techapi.genericmathapi.IFractalBuilder;

public final class BlackWhiteJuliaFractalTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		Registrator.register();
		
		final var maxIterationCount = 100;
		final var j = CentralInstanceProvider.create(IComplexNumberFactory.class).create(-0.8, 0.15);
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame()
		.setTitle("Black White Julia Fractal Tutorial")
		.addLayerOnTop(
			new ImageWidget()
			.setImage(
				CentralInstanceProvider.create(IFractalBuilder.class)
				.setRealComponentInterval(-2.0, 2.0)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(800)
				.setStartValuesFunction(c -> new IComplexNumber[]{c})
				.setNextValueFunctionFor1Predecessor((p, c) -> p.getPower2().getSum(j))
				.setMinMagnitudeForConvergence(2.5)
				.setMaxIterationCount(maxIterationCount)
				.setColorFunction(i -> i < maxIterationCount ? Color.WHITE : Color.BLACK)
				.setBigDecimalScale(20)
				.build()
				.startImageBuild()
				.getRefImage()
			)
		);
		
		//Refreshes the frame as long as it is alive.
		Sequencer.asLongAs(frame::isOpen).afterAllMilliseconds(100).run(frame::refresh);
	}
	
	private BlackWhiteJuliaFractalTutorial() {}
}
