package ch.nolix.systemtutorial.dynamicmathtutorial;

import ch.nolix.businessapi.dynamicmathapi.IComplexNumberFactory;
import ch.nolix.businessapi.dynamicmathapi.IFractalBuilder;
import ch.nolix.common.programcontrol.sequencer.Sequencer;
import ch.nolix.common.provider.implprovider.GlobalImplProvider;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.system.dynamicmath.DynamicMathImplRegistrator;

public final class BlackWhiteMandelbrotFractalTutorial {
	
	public static void main(String[] args) {
		
		//Registers an implementation of the dynamicmathapi at the GlobalImplProvider.
		new DynamicMathImplRegistrator().registerImplementationTo(GlobalImplProvider.getRefInstance());
		
		final var maxIterationCount = 100;
		
		//Creates a Frame that shows a realtime-generated image of a Fractal.
		final var frame =
		new Frame()
		.setTitle("Black White Mandelrbrot Fractal tutorial")
		.addLayerOnTop(
			new ImageWidget()
			.setImage(
				GlobalImplProvider.ofInterface(IFractalBuilder.class).createInstance()
				.setRealComponentInterval(-2.5, 1.0)
				.setImaginaryComponentInterval(-1.5, 1.5)
				.setWidthInPixel(800)
				.setStartValues(
					GlobalImplProvider.ofInterface(IComplexNumberFactory.class).createInstance().create(0.0, 0.0)
				)
				.setNextValueFunctionFor1Predecessor((p, c) -> p.getPower2().getSum(c))
				.setMinMagnitudeForConvergence(2.5)
				.setMaxIterationCount(maxIterationCount)
				.setColorFunction(
					i -> {
						
						if (i < maxIterationCount) {
							return Color.WHITE;
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
	
	private BlackWhiteMandelbrotFractalTutorial() {}
}
