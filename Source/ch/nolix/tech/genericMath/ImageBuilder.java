//package declaration
package ch.nolix.tech.genericMath;

//Java import
import java.math.BigDecimal;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.sequencer.Future;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;
import ch.nolix.techAPI.genericMathAPI.IComplexNumber;
import ch.nolix.techAPI.genericMathAPI.IImageBuilder;

//class
public final class ImageBuilder implements IImageBuilder {
	
	//attributes
	private final Fractal fractal;
	private final Image image;
	
	//TODO: Use a JobPool.
	//multi-attribute
	private final LinkedList<Future> futures = new LinkedList<>();
	
	//constructor
	public ImageBuilder(Fractal fractal) {
		
		Validator.assertThat(fractal).thatIsNamed(Fractal.class).isNotNull();		
						
		Sequencer.runInBackground(() -> fillImage());
		
		this.fractal = fractal;
		image = new Image(fractal.getWidthInPixel(), fractal.getHeightInPixel(), Color.WHITE);
	}
	
	//method
	@Override
	public boolean caughtError() {
		return futures.contains(f -> f.caughtError());
	}
	
	//method
	@Override
	public Throwable getError() {
		
		final var futureWithError = futures.getRefFirstOrNull(f -> f.caughtError());
		
		if (futureWithError == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ERROR);
		}
		
		return futureWithError.getError();
	}
	
	//method
	@Override
	public Image getRefImage() {
		return image;
	}
	
	//method
	@Override
	public boolean isFinished() {
		
		futures.removeAll(f -> f.isFinished());
		
		return futures.containsAny();
	}
	
	//method
	@Override
	public void waitUntilIsFinished() {
		futures.forEach(f -> f.waitUntilIsFinished());
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
		futures.forEach(f -> f.waitUntilIsFinishedSuccessfully());
	}
	
	//method
	private void fillImage() {
		
		Sequencer.waitUntil(() -> image != null);
		
		final var heightInpixel = fractal.getHeightInPixel();		
		final var linesPerThread = 10;
		
		for (var y = 1; y <= heightInpixel - linesPerThread; y += linesPerThread) {
			final var y_ = y;
			futures.addAtEnd(Sequencer.runInBackground(() -> fillLines(y_, y_ + linesPerThread - 1)));
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
						z -> z.getSquaredMagnitude()
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
