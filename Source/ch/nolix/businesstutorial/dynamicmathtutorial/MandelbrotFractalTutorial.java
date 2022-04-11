package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.dynamicmath.DynamicMathImplRegistrator;
import ch.nolix.businessapi.dynamicmathapi.IFractalBuilder;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.core.provider.implprovider.GlobalImplProvider;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.ImageWidget;

public final class MandelbrotFractalTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation of the dynamicmathapi at the GlobalImplProvider.
		new DynamicMathImplRegistrator().registerImplementationTo(GlobalImplProvider.getRefInstance());
		
		final var maxIterationCount = 100;
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		@SuppressWarnings("resource")
		final var frame =
		new Frame()
		.setTitle("Mandelrbrot Fractal tutorial")
		.addLayerOnTop(
			new ImageWidget()
			.setImage(
				GlobalImplProvider.ofInterface(IFractalBuilder.class).createInstance()
				.setRealComponentInterval(-2.5, 1.0)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(800)
				//TODO: .setStartValues(GlobalImplProvider.ofInterface(IComplexNumberFactory.class).createInstance().createComplexNumber(0.0, 0.0))
				//TODO: .setNextValueFunctionFor1Predecessor((p, c) -> p.getPower2().getSum(c))
				.setMinMagnitudeForDivergence(2.5)
				.setMaxIterationCount(maxIterationCount)
				.setColorFunction(
					i -> {
						
						if (i < maxIterationCount) {
							return Color.withRedValueAndGreenValueAndBlueValue(i % 256, (10 * i) % 256, (2 * i) % 256);
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
	
	private MandelbrotFractalTutorial() {}
}
