//package declaration
package ch.nolix.tech.genericMath;

//Java imports
import java.math.BigDecimal;
import java.math.RoundingMode;

import ch.nolix.common.functionAPI.I2ElementTakerElementGetter;
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.functionAPI.IIntTakerElementGetter;
import ch.nolix.common.processProperties.ProcessingMode;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.image.Image;
import ch.nolix.techAPI.genericMathAPI.IClosedInterval;
import ch.nolix.techAPI.genericMathAPI.IComplexNumber;
import ch.nolix.techAPI.genericMathAPI.IFractal;

//class
public final class Fractal implements IFractal {
	
	//attributes
	private final IClosedInterval realComponentInterval;
	private final IClosedInterval imaginaryComponentInterval;
	private final int widthInPixel;
	private final IElementTakerElementGetter<IComplexNumber, IComplexNumber[]> sequencesStartValuesFunction;
	
	//attribute
	private final I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber>
	sequencesNextValueFunction;
	
	//attributes
	private final BigDecimal sequencesMinDivergenceMagnitude;
	private final int sequencesMaxIterationCount;
	private final IIntTakerElementGetter<Color> colorFunction;
	private final int bigDecimalScale;
	
	//constructor
	public Fractal(
		final IClosedInterval realComponentInterval,
		final IClosedInterval imaginaryComponentInterval,
		final int widthInPixel,
		final IElementTakerElementGetter<IComplexNumber, IComplexNumber[]> sequencesStartValuesFunction,
		final I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber>
		sequencesNextValueFunction,
		final BigDecimal sequencesMinDivergenceMagnitude,
		final int sequencesMaxIterationCount,
		final IIntTakerElementGetter<Color> colorFunction,
		final int bigDecimalScale
	) {
		
		Validator
		.suppose(realComponentInterval)
		.thatIsNamed("real component interval")
		.isNotNull();
		
		Validator
		.suppose(imaginaryComponentInterval)
		.thatIsNamed("imaginary component interval")
		.isNotNull();
		
		Validator
		.suppose(widthInPixel)
		.thatIsNamed("width per unit")
		.isPositive();
		
		Validator
		.suppose(sequencesStartValuesFunction)
		.thatIsNamed("sequences start values function")
		.isNotNull();
		
		Validator
		.suppose(sequencesNextValueFunction)
		.thatIsNamed("sequences next value function")
		.isNotNull();
		
		Validator
		.suppose(sequencesMinDivergenceMagnitude)
		.thatIsNamed("sequences min divergence magnitude")
		.isPositive();
		
		Validator
		.suppose(sequencesMaxIterationCount)
		.thatIsNamed("sequences max iteration count")
		.isPositive();
		
		Validator
		.suppose(colorFunction)
		.thatIsNamed("color function")
		.isNotNull();
		
		Validator
		.suppose(bigDecimalScale)
		.thatIsNamed("big decimal scale")
		.isPositive();
		
		this.imaginaryComponentInterval = imaginaryComponentInterval.getInBigDecimalScale(bigDecimalScale);
		this.realComponentInterval = realComponentInterval.getInBigDecimalScale(bigDecimalScale);
		this.widthInPixel = widthInPixel;
		this.sequencesStartValuesFunction = sequencesStartValuesFunction;
		this.sequencesNextValueFunction = sequencesNextValueFunction;
		this.sequencesMinDivergenceMagnitude = sequencesMinDivergenceMagnitude;
		this.sequencesMaxIterationCount = sequencesMaxIterationCount;
		this.colorFunction = colorFunction;
		this.bigDecimalScale = bigDecimalScale;
	}	
	
	//method
	@Override
	public int getBigDecimalScale() {
		return bigDecimalScale;
	}
	
	//method
	@Override
	public Color getColor(final int index) {
		return colorFunction.getOutput(index);
	}
	
	//method
	@Override
	public int getHeightInPixel() {
		return
		imaginaryComponentInterval
		.getLength()
		.multiply(BigDecimal.valueOf(getWidthInPixel()))
		.divide(realComponentInterval.getLength(), RoundingMode.HALF_UP)
		.intValue();
	}
	
	//method
	@Override
	public IClosedInterval getImaginaryComponentInterval() {
		return imaginaryComponentInterval;
	}
	
	//method
	@Override
	public BigDecimal getMaxImaginaryComponent() {
		return imaginaryComponentInterval.getMax();
	}
	
	//method
	@Override
	public BigDecimal getMaxRealComponent() {
		return realComponentInterval.getMax();
	}
	
	//method
	@Override
	public BigDecimal getMinImaginaryComponent() {
		return imaginaryComponentInterval.getMin();
	}
	
	//method
	@Override
	public BigDecimal getMinRealComponent() {
		return realComponentInterval.getMin();
	}
	
	//method
	@Override
	public BigDecimal getPixelsPerUnit() {
		return BigDecimal.valueOf(widthInPixel).divide(realComponentInterval.getLength());
	}
	
	//method
	@Override
	public IClosedInterval getRealComponentInterval() {
		return realComponentInterval;
	}
	
	//method
	@Override
	public int getMaxIterationCount() {
		return sequencesMaxIterationCount;
	}
	
	//method
	@Override
	public BigDecimal getMinMagnitudeForConvergence() {
		return sequencesMinDivergenceMagnitude;
	}
	
	//method
	@Override
	public I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber>
	getNextValueFunction() {
		return sequencesNextValueFunction;
	}
	
	//method
	@Override
	public IComplexNumber[] getStartValues(final IComplexNumber complexNumber) {
		return sequencesStartValuesFunction.getOutput(complexNumber);
	}
	
	//method
	@Override
	public BigDecimal getUnitsPerPixel() {
		return realComponentInterval.getLength().divide(BigDecimal.valueOf(widthInPixel), RoundingMode.HALF_UP);
	}
	
	//method
	@Override
	public int getWidthInPixel() {
		return widthInPixel;
	}
	
	//method
	public ImageBuilder startImageBuild() {
		return new ImageBuilder(this);
	}
	
	//method
	@Override
	public Image toImage() {
		
		final var imageBuilder = startImageBuild();
		imageBuilder.waintUntilIsFinishedSuccessfully();
		
		return imageBuilder.getRefImage();
	}
	
	//method
	public Image toImage(final ProcessingMode processingMode) {
		switch (processingMode) {
			case SINGLE_THREADED:
				return toImageSingleThreaded();
			case MULTI_THREADED:
				return toImage();
			default:
				throw new RuntimeException();
		}
	}
	
	//method
	public Image toImageSingleThreaded() {
		
		final var widthInPixel = getWidthInPixel();
		final var heightInpixel = getHeightInPixel();		
		
		final var image = new Image(widthInPixel, heightInpixel);
		
		final var argument
		= new ComplexNumber(getMinRealComponent(), getMinImaginaryComponent(), getBigDecimalScale());
		
		final var unitsPerPixel = getUnitsPerPixel();
		
		for (var x = 1; x <= widthInPixel; x++) {
			
			for (var y = 1; y <= heightInpixel; y++) {
								
				final var c =
				argument.getSum(
					new ComplexNumber(
							unitsPerPixel.multiply(BigDecimal.valueOf(x - 1.0)),
							unitsPerPixel.multiply(BigDecimal.valueOf(y - 1.0)),
							getBigDecimalScale()
					)
				);
				
				image.setPixel(
					x,
					heightInpixel - y + 1,
					getColor(
						new ImpliciteSequence<IComplexNumber>(
							1,
							sequencesStartValuesFunction.getOutput(c),
							z -> sequencesNextValueFunction.getOutput(z, c),
							z -> z.getSquaredMagnitude()
						)
						.getConvergenceGrade(
							getMinMagnitudeForConvergence(),
							getMaxIterationCount()
						)
					)
				);
			}
		}
		
		return image;
	}
}
