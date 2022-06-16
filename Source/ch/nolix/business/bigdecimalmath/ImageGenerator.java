//package declaration
package ch.nolix.business.bigdecimalmath;

//Java import
import java.math.BigDecimal;

import ch.nolix.businessapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.businessapi.bigdecimalmathapi.IFractal;
import ch.nolix.businessapi.bigdecimalmathapi.IFractalHelper;
import ch.nolix.businessapi.bigdecimalmathapi.IImageGenerator;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.futureuniversalapi.IFuture;
import ch.nolix.core.programcontrol.jobpool.JobPool;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;

//class
public final class ImageGenerator implements IImageGenerator {
	
	//constant
	private static final int IMAGE_ROWS_PER_THREAD = 10;
	
	//static attribute
	private static final IFractalHelper fractalHelper = new FractalHelper();
	
	//attribute
	private final IFractal fractal;
	
	//attribute
	private final BigDecimal squaredMinMagnitudeForDivergence;
	
	//attribute
	private final MutableImage image;
	
	//multi-attribute
	private final IContainer<IFuture> futures;
	
	//constructor
	public ImageGenerator(final IFractal fractal) {
		
		GlobalValidator.assertThat(fractal).thatIsNamed(Fractal.class).isNotNull();		
		
		this.fractal = fractal;
		
		squaredMinMagnitudeForDivergence = fractalHelper.getSquaredMinMagnitudeForDivergenceOf(fractal);
		
		image =
		MutableImage.withWidthAndHeightAndColor(fractal.getWidthInPixel(), fractal.getHeightInPixel(), Color.WHITE);
		
		futures = startFillImageAndGetFutures();
	}
	
	//method
	@Override
	public boolean caughtError() {
		return futures.containsAny(IFuture::caughtError);
	}
	
	//method
	@Override
	public Throwable getError() {
		
		final var futureWithError = futures.getOptionalRefFirst(IFuture::caughtError);
		
		if (futureWithError.isEmpty()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.ERROR);
		}
		
		return futureWithError.getRefElement().getError();
	}
	
	//method
	@Override
	public MutableImage getRefImage() {
		return image;
	}
	
	//method
	@Override
	public boolean isFinished() {
		return futures.containsOnly(IFuture::isFinished);
	}
	
	//method
	@Override
	public void waitUntilIsFinished() {
		futures.forEach(IFuture::waitUntilIsFinished);
	}
	
	//method
	@Override
	public void waitUntilIsFinished(final int timeoutInMilliseconds) {
		
		final var startTimeInMilliseconds = System.currentTimeMillis();
		
		GlobalSequencer.waitAsLongAs(
			() -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds
			&& isRunning()
		);
		
		if (!isFinished()) {
			throw new InvalidArgumentException(this, "reached timeout before having finished");
		}
	}
	
	//method
	private void fillImageRow(final int y) {
		for (var x = 1; x <= image.getWidth(); x++) {
			fillImagePixel(x, y);
		}
	}
	
	//method
	private void fillImageRows(final int startImageRow, final int endImageRow) {
		for (var y = startImageRow; y <= endImageRow; y++) {
			fillImageRow(y);
		}
	}
	
	//method
	private void fillImagePixel(final int x, final int y) {
		
		final var color =
		Color.createAverageFrom(
			getColorOfPixel(x - 0.75, y - 0.75),
			getColorOfPixel(x - 0.75, y - 0.25),
			getColorOfPixel(x - 0.25, y - 0.75),
			getColorOfPixel(x - 0.25, y - 0.25)
		);
		
		image.setPixel(x, y, color);
	}
	
	//method
	private Color getColorOfPixel(final double x, final double y) {
		
		final var z = getComplexNumberOfPixel(x, y);
		
		final var iterationCount =
		getIterationCountForComplexNumberUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(z);
		
		return fractal.getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(iterationCount);
	}
	
	//method
	private IComplexNumber getComplexNumberOfPixel(final double x, final double y) {
		return
		new ComplexNumber(
			fractalHelper.getMinXOf(fractal).add(fractalHelper.getUnitsForHorizontalPixelCount(fractal, x)),
			fractalHelper.getMinYOf(fractal).add(fractalHelper.getUnitsForVerticalPixelCount(fractal, y))
		);
	}
	
	//method
	private int getIterationCountForComplexNumberUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(
		final IComplexNumber complexNumber
	) {
		return
		fractalHelper.getIterationCountForComplexNumberUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(
			fractal,
			complexNumber,
			squaredMinMagnitudeForDivergence
		);
	}
	
	//method
	private LinkedList<IFuture> startFillImageAndGetFutures() {
		
		final var lFutures = new LinkedList<IFuture>();
		
		final var jobPool = new JobPool();
		
		final var heightInpixel = fractal.getHeightInPixel();		
		for (var y = 1; y < heightInpixel; y += IMAGE_ROWS_PER_THREAD) {
			
			final var startImageRow = y;
			final var endImageRow = Calculator.getMin(heightInpixel, y + IMAGE_ROWS_PER_THREAD - 1);
			
			lFutures.addAtEnd(
				jobPool.enqueue(() -> fillImageRows(startImageRow, endImageRow))
			);
		}
		
		return lFutures;
	}
}
