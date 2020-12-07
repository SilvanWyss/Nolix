package ch.nolix.techapitutorial.dynamicmathapitutorial;

//own imports
import ch.nolix.common.instanceprovider.CentralInstanceProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.tech.genericmath.Registrator;
import ch.nolix.techapi.dynamicmathapi.IComplexNumberFactory;
import ch.nolix.techapi.dynamicmathapi.IFractalBuilder;

public final class MandelbrotFractalTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		Registrator.register();
		
		final var maxIterationCount = 100;
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame()
		.setTitle("Mandelrbrot Fractal Tutorial")
		.addLayerOnTop(
			new ImageWidget()
			.setImage(
				CentralInstanceProvider.create(IFractalBuilder.class)
				.setRealComponentInterval(-2.5, 1.0)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(800)
				.setStartValues(CentralInstanceProvider.create(IComplexNumberFactory.class).create(0.0, 0.0))
				.setNextValueFunctionFor1Predecessor((p, c) -> p.getPower2().getSum(c))
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
		Sequencer.asLongAs(frame::isOpen).afterAllMilliseconds(100).run(frame::refresh);
	}
	
	private MandelbrotFractalTutorial() {}
}
