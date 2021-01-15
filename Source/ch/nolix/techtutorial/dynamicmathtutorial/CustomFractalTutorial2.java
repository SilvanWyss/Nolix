package ch.nolix.techtutorial.dynamicmathtutorial;

//own imports
import ch.nolix.common.instanceprovider.CentralInstanceProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.tech.genericmath.Registrator;
import ch.nolix.techapi.dynamicmathapi.IComplexNumber;
import ch.nolix.techapi.dynamicmathapi.IComplexNumberFactory;
import ch.nolix.techapi.dynamicmathapi.IFractalBuilder;

public final class CustomFractalTutorial2 {
	
	public static void main(String[] args) {
		
		//Registers an implementation for the GenericMathAPI at the ClassProvider.
		Registrator.register();
		
		final var maxIterationCount = 500;
		final var j = CentralInstanceProvider.create(IComplexNumberFactory.class).create(-0.8, 0.15);
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame()
		.setTitle("Custom Fractal Tutorial 2")
		.addLayerOnTop(
			new ImageWidget()
			.setImage(
				CentralInstanceProvider.create(IFractalBuilder.class)
				.setRealComponentInterval(-1.0, 1.0)
				.setImaginaryComponentInterval(-1.0, 1.0)
				.setWidthInPixel(800)
				.setStartValuesFunction(c -> new IComplexNumber[]{c})
				.setNextValueFunctionFor1Predecessor((p, c) -> p.getPower(6).getSum(j))
				.setMinMagnitudeForConvergence(2.5)
				.setMaxIterationCount(maxIterationCount)
				.setColorFunction(i -> i < maxIterationCount ? new Color(i % 256, i % 256, i % 256) : Color.BLACK)
				.setBigDecimalScale(20)
				.build()
				.startImageBuild()
				.getRefImage()
			)
		);
		
		//Refreshes the frame as long as it is alive.
		Sequencer.asLongAs(frame::isOpen).afterAllMilliseconds(100).run(frame::refresh);
	}
	
	private CustomFractalTutorial2() {}
}
