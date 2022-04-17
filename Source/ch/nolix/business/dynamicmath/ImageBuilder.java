//package declaration
package ch.nolix.business.dynamicmath;

//own imports
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
	private final MutableImage image;
	private final JobPool jobPool = new JobPool();
	
	//multi-attribute
	private final LinkedList<IFuture> futures = new LinkedList<>();
	
	//constructor
	public ImageBuilder(final Fractal fractal) {
		
		Validator.assertThat(fractal).thatIsNamed(Fractal.class).isNotNull();		
		
		image =
		MutableImage.withWidthAndHeightAndColor(fractal.getWidthInPixel(), fractal.getHeightInPixel(), Color.WHITE);
		
		new FractalVisualizer().startFillImage(image, fractal, jobPool, LINES_PER_THREAD);
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
}
