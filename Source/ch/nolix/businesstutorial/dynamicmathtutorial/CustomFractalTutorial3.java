package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.dynamicmath.DynamicMathImplRegistrator;
import ch.nolix.businessapi.dynamicmathapi.IFractalBuilder;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.core.provider.implprovider.GlobalImplProvider;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.ImageWidget;

public final class CustomFractalTutorial3 {
	
	public static void main(String[] args) {
		
		//Registers an implementation of the dynamicmathapi at the GlobalImplProvider.
		new DynamicMathImplRegistrator().registerImplementationTo(GlobalImplProvider.getRefInstance());
		
		final var maxIterationCount = 100;
		
		//TODO: final var j = GlobalImplProvider.ofInterface(IComplexNumberFactory.class).createInstance().createComplexNumber(-0.745, 0.25);
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		@SuppressWarnings("resource")
		final var frame =
		new Frame()
		.setTitle("Custom Fractal Tutorial 3")
		.addLayerOnTop(
			new ImageWidget()
			.setImage(
				GlobalImplProvider.ofInterface(IFractalBuilder.class).createInstance()
				.setRealComponentInterval(-1.5, 1.5)
				.setImaginaryComponentInterval(-1.0, 1.0)
				.setWidthInPixel(800)
				//TODO: .setStartValuesFunction(c -> new IComplexNumber[]{c})
				//TODO: .setNextValueFunctionFor1Predecessor((p, c) -> p.getPower2().getSum(j))
				.setMinMagnitudeForDivergence(2.5)
				.setMaxIterationCount(maxIterationCount)
				.setColorFunction(
					i -> {
						
						if (i < maxIterationCount) {
							return Color.withRedValueAndGreenValueAndBlueValue((10 * i) % 256, i % 256, (20 * i) % 256);
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
	
	private CustomFractalTutorial3() {}
}
