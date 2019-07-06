//package declaration
package ch.nolix.tech.genericMath;

//Java import
import java.math.BigDecimal;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.sequencer.Future;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;
import ch.nolix.techAPI.genericMathAPI.IComplexNumber;
import ch.nolix.techAPI.genericMathAPI.IImageBuilder;

//class
public final class ImageBuilder implements IImageBuilder {
	
	//attributes
	private final Fractal fractal;
	private final Image image;
	
	//multi-attribute
	private final List<Future> futures = new List<>();
	
	//constructor
	public ImageBuilder(Fractal fractal) {
		
		Validator.suppose(fractal).thatIsNamed(Fractal.class).isNotNull();		
						
		Sequencer.runInBackground(() -> fillImage());
		
		this.fractal = fractal;
		image = new Image(fractal.getWidthInPixel(), fractal.getHeightInPixel(), Color.WHITE);
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
	private void fillImage() {
		
		Sequencer.waitUntil(() -> image != null);
		
		final var heightInpixel = fractal.getHeightInPixel();		
		
		final var argument
		= new ComplexNumber(
			fractal.getMinRealComponent(),
			fractal.getMinImaginaryComponent(),
			fractal.getBigDecimalScale()
		);
		
		final var linesPerThread = 10;
		
		for (var y = 1; y <= heightInpixel - linesPerThread; y += linesPerThread) {
			final var y_ = y;
			futures.addAtEnd(Sequencer.runInBackground(() -> fillLines(y_, y_ + linesPerThread, argument)));
		}
	}
	
	//method
	private void fillLine(final int y, final ComplexNumber argument) {	
		final var widthInPixel = fractal.getWidthInPixel();
		final var unitsPerPixel = fractal.getUnitsPerPixel();
		for (var x = 1; x <= widthInPixel; x++) {
		
			final var c =
			argument.getSum(
				new ComplexNumber(
						unitsPerPixel.multiply(BigDecimal.valueOf(x - 1.0)),
						unitsPerPixel.multiply(BigDecimal.valueOf(y - 1.0)),
						fractal.getBigDecimalScale()
				)
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
	private void fillLines(final int y1, final int y2, final ComplexNumber argument) {
		for (var y = y1; y <= y2; y++) {
			fillLine(y, argument);
		}
	}
}
