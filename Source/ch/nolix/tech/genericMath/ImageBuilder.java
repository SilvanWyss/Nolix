//package declaration
package ch.nolix.tech.genericMath;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.futureAPI.IFuture;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.jobPool.JobPool;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;
import ch.nolix.techapi.genericmathapi.IComplexNumber;
import ch.nolix.techapi.genericmathapi.IImageBuilder;

//class
public final class ImageBuilder implements IImageBuilder {
	
	//constant
	private static final int LINES_PER_THREAD = 10;
	
	//attributes
	private final Fractal fractal;
	private final Image image;
	private final JobPool jobPool = new JobPool();
	
	//multi-attribute
	private final LinkedList<IFuture> futures = new LinkedList<>();
	
	//constructor
	public ImageBuilder(final Fractal fractal) {
		
		Validator.assertThat(fractal).thatIsNamed(Fractal.class).isNotNull();		
		
		this.fractal = fractal;
		image = new Image(fractal.getWidthInPixel(), fractal.getHeightInPixel(), Color.WHITE);
		
		fillImage();
	}
	
	//method
	@Override
	public boolean caughtError() {
		return futures.contains(IFuture::caughtError);
	}
	
	//method
	@Override
	public Throwable getError() {
		
		final var futureWithError = futures.getRefFirstOptionally(IFuture::caughtError);
		
		if (futureWithError.isEmpty()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ERROR);
		}
		
		return futureWithError.getRefElement().getError();
	}
	
	//method
	@Override
	public Image getRefImage() {
		return image;
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
			
			image.setPixel(
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
