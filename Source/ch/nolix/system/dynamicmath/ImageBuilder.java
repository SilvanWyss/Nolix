//package declaration
package ch.nolix.system.dynamicmath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.businessapi.dynamicmathapi.IImageBuilder;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.futureapi.IFuture;
import ch.nolix.core.programcontrol.jobpool.JobPool;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.MutableImage;

//class
public final class ImageBuilder implements IImageBuilder {
	
	//constant
	private static final int LINES_PER_THREAD = 10;
	
	//attributes
	private final Fractal fractal;
	private final MutableImage mutableImage;
	private final JobPool jobPool = new JobPool();
	
	//multi-attribute
	private final LinkedList<IFuture> futures = new LinkedList<>();
	
	//constructor
	public ImageBuilder(final Fractal fractal) {
		
		Validator.assertThat(fractal).thatIsNamed(Fractal.class).isNotNull();		
		
		this.fractal = fractal;
		
		mutableImage =
		MutableImage.withWidthAndHeightAndColor(fractal.getWidthInPixel(), fractal.getHeightInPixel(), Color.WHITE);
		
		fillImage();
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
		return mutableImage;
	}
	
	//method
	@Override
	public boolean isFinished() {
		return !jobPool.containsWaitingJobs();
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
		
		Sequencer.waitAsLongAs(
			() -> System.currentTimeMillis() - startTimeInMilliseconds < timeoutInMilliseconds
			&& isRunning()
		);
		
		if (!isFinished()) {
			throw new InvalidArgumentException(this, "reached timeout before having finished");
		}
	}
	
	//method
	@Override
	public void waitUntilIsFinishedSuccessfully() {
		futures.forEach(IFuture::waitUntilIsFinishedSuccessfully);
	}
	
	//method
	private void fillImage() {
		
		final var heightInpixel = fractal.getHeightInPixel();		
		
		for (var y = 1; y <= heightInpixel - LINES_PER_THREAD; y += LINES_PER_THREAD) {
			final var y_ = y;
			futures.addAtEnd(jobPool.enqueue(() -> fillLines(y_, y_ + LINES_PER_THREAD - 1)));
		}
	}
	
	//method
	private void fillLine(final int y) {
		
		final var widthInPixel = fractal.getWidthInPixel();
		final var unitsPerPixel = fractal.getUnitsPerPixel();
		
		for (var x = 1; x <= widthInPixel; x++) {
			
			final var c =
			new ComplexNumber(
				fractal.getMinRealComponent().add(unitsPerPixel.multiply(BigDecimal.valueOf(x - 1.0))),
				fractal.getMinImaginaryComponent().add(unitsPerPixel.multiply(BigDecimal.valueOf(y - 1.0))),
				fractal.getBigDecimalScale()	
			);
			
			mutableImage.setPixel(
				x,
				fractal.getHeightInPixel() - y + 1,
				fractal.getColor(
					new ImpliciteSequence<IComplexNumber>(
						1,
						fractal.getStartValues(c),
						z -> fractal.getNextValueFunction().getOutput(z, c),
						IComplexNumber::getSquaredMagnitude
					)
					.getConvergenceGrade(
						fractal.getMinMagnitudeForConvergence(),
						fractal.getMaxIterationCount()
					)
				)
			);
		}
	}
	
	//method
	private void fillLines(final int y1, final int y2) {
		for (var y = y1; y <= y2; y++) {
			fillLine(y);
		}
	}
}
