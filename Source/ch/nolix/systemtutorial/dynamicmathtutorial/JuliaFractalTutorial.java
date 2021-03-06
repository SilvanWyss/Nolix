package ch.nolix.systemtutorial.dynamicmathtutorial;

import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.businessapi.dynamicmathapi.IComplexNumberFactory;
import ch.nolix.businessapi.dynamicmathapi.IFractalBuilder;
import ch.nolix.common.implprovider.GlobalImplProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.system.dynamicmath.DynamicMathImplRegistrator;

public final class JuliaFractalTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation of the dynamicmathapi at the GlobalImplProvider.
		new DynamicMathImplRegistrator().registerImplementationTo(GlobalImplProvider.getRefInstance());
		
		final var maxIterationCount = 100;
		final var j = GlobalImplProvider.ofInterface(IComplexNumberFactory.class).createInstance().create(-0.8, 0.15);
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame()
		.setTitle("Julia Fractal Tutorial")
		.addLayerOnTop(
			new ImageWidget()
			.setImage(
				GlobalImplProvider.ofInterface(IFractalBuilder.class).createInstance()
				.setRealComponentInterval(-2.0, 2.0)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(800)
				.setStartValuesFunction(c -> new IComplexNumber[]{c})
				.setNextValueFunctionFor1Predecessor((p, c) -> p.getPower2().getSum(j))
				.setMinMagnitudeForConvergence(2.5)
				.setMaxIterationCount(maxIterationCount)
				.setColorFunction(
					i -> {
												
						if (i < maxIterationCount) {
							return new Color(i % 256, (10 * i) % 256, (2 * i) % 256);
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
	
	private JuliaFractalTutorial() {}
}
