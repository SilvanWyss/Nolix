package ch.nolix.techtutorial.dynamicmathtutorial;

import ch.nolix.common.implprovider.GlobalImplProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.tech.dynamicmath.DynamicMathImplRegistrator;
import ch.nolix.techapi.dynamicmathapi.IComplexNumber;
import ch.nolix.techapi.dynamicmathapi.IComplexNumberFactory;
import ch.nolix.techapi.dynamicmathapi.IFractalBuilder;

public final class CustomFractalTutorial2 {
	
	public static void main(String[] args) {
		
		//Registers an implementation of the dynamicmathapi at the GlobalImplProvider.
		new DynamicMathImplRegistrator().registerImplementationTo(GlobalImplProvider.getRefInstance());
		
		final var maxIterationCount = 500;
		final var j = GlobalImplProvider.ofInterface(IComplexNumberFactory.class).createInstance().create(-0.8, 0.15);
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame()
		.setTitle("Custom Fractal Tutorial 2")
		.addLayerOnTop(
			new ImageWidget()
			.setImage(
				GlobalImplProvider.ofInterface(IFractalBuilder.class).createInstance()
				.setRealComponentInterval(-1.0, 1.0)
				.setImaginaryComponentInterval(-1.0, 1.0)
				.setWidthInPixel(800)
				.setStartValuesFunction(c -> new IComplexNumber[]{c})
				.setNextValueFunctionFor1Predecessor((p, c) -> p.getPower(6).getSum(j))
				.setMinMagnitudeForConvergence(2.5)
				.setMaxIterationCount(maxIterationCount)
				.setColorFunction(
					i -> {
						
						if (i < maxIterationCount) {
							return new Color(i % 256, i % 256, i % 256);
						}
						
						return Color.BLACK;
					}
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
	
	private CustomFractalTutorial2() {}
}
