//package declaration
package ch.nolix.business.dynamicmath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.businessapi.dynamicmathapi.IFractal;
import ch.nolix.businessapi.dynamicmathapi.IFractalHelper;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.programcontrol.futureapi.IFuture;
import ch.nolix.core.programcontrol.jobpool.JobPool;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.MutableImage;

//class
final class FractalVisualizer {
	
	//static attribute
	private static final IFractalHelper fractalHelper = new FractalHelper();
	
	//method
	public IContainer<IFuture> startFillImage(
		final MutableImage image,
		final IFractal fractal,
		final JobPool jobPool,
		final int imageRowsPerThread
	) {
		
		Validator.assertThat(imageRowsPerThread).thatIsNamed("image rows per thread").isPositive();
		
		final var futures = new LinkedList<IFuture>();
		final var squaredMinMagnitudeForDivergence = fractalHelper.getSquaredMinMagnitudeForDivergenceOf(fractal);
		final var heightInpixel = fractal.getHeightInPixel();		
		for (var y = 1; y < heightInpixel; y += imageRowsPerThread) {
			
			final var startImageRow = y;
			final var endImageRow = Calculator.getMin(heightInpixel, y + imageRowsPerThread - 1);
			
			jobPool.enqueue(
				() -> fillImageRows(image, startImageRow, endImageRow, fractal, squaredMinMagnitudeForDivergence)
			);
		}
		return futures;
	}
	
	//method
	private void fillImageRow(
		final MutableImage image,
		final int y,
		final IFractal fractal,
		final BigDecimal squaredMinMagnitudeForDivergence
	) {
		for (var x = 1; x <= image.getWidth(); x++) {
			fillImagePixel(image, x, y, fractal, squaredMinMagnitudeForDivergence);
		}
	}
	
	//method
	private void fillImageRows(
		final MutableImage image,
		final int startImageRow,
		final int endImageRow,
		final IFractal fractal,
		final BigDecimal squaredMinMagnitudeForDivergence
	) {
		for (var y = startImageRow; y <= endImageRow; y++) {
			fillImageRow(image, y, fractal, squaredMinMagnitudeForDivergence);
		}
	}
	
	//method
	private void fillImagePixel(
		final MutableImage image,
		final int x,
		final int y,
		final IFractal fractal,
		final BigDecimal squaredMinMagnitudeForDivergence
	) {
		
		final var color =
		Color.createAverageFrom(
			getColorOfPixel(fractal, squaredMinMagnitudeForDivergence, x - 0.75, y - 0.75),
			getColorOfPixel(fractal, squaredMinMagnitudeForDivergence, x - 0.75, y - 0.25),
			getColorOfPixel(fractal, squaredMinMagnitudeForDivergence, x - 0.25, y - 0.75),
			getColorOfPixel(fractal, squaredMinMagnitudeForDivergence, x - 0.25, y - 0.25)
		);
		
		image.setPixel(x, y, color);
	}
	
	//method
	private Color getColorOfPixel(
		final IFractal fractal,
		final BigDecimal squaredMinMagnitudeForDivergence,
		final double x,
		final double y
	) {
		
		final var z = getComplexNumberOfPixel(fractal, x, y);
		
		final var iterationCount =
		fractalHelper.getIterationCountForComplexNumberUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(
			fractal,
			z,
			squaredMinMagnitudeForDivergence
		);
		
		return fractal.getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(iterationCount);
	}
	
	//method
	private IComplexNumber getComplexNumberOfPixel(final IFractal fractal, final double x, final double y) {
		return
		new ComplexNumber(
			fractalHelper.getMinXOf(fractal).add(fractalHelper.getUnitsForHorizontalPixelCount(fractal, x)),
			fractalHelper.getMinYOf(fractal).add(fractalHelper.getUnitsForVerticalPixelCount(fractal, y))
		);
	}
}
