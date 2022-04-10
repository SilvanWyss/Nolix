//package declaration
package ch.nolix.business.dynamicmath;

//Java imports
import java.math.BigDecimal;
import java.math.RoundingMode;

//own imports
import ch.nolix.businessapi.dynamicmathapi.IClosedInterval;
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.businessapi.dynamicmathapi.IFractal;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.I2ElementTakerElementGetter;
import ch.nolix.core.functionapi.IElementTakerElementGetter;
import ch.nolix.core.functionapi.IIntTakerElementGetter;
import ch.nolix.core.programcontrol.processproperty.ProcessingMode;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.MutableImage;

//class
public final class Fractal implements IFractal {
	
	//attribute
	private final IClosedInterval realComponentInterval;
	
	//attribute
	private final IClosedInterval imaginaryComponentInterval;
	
	//attribute
	private final int widthInPixel;
	
	//attribute
	private final int heightInPixel;
	
	//attribute
	private final IElementTakerElementGetter<IComplexNumber, IComplexNumber[]> sequencesStartValuesFunction;
	
	//attribute
	private final I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber>
	sequencesNextValueFunction;
	
	//attribute
	private final BigDecimal sequencesMinDivergenceMagnitude;
	
	//attribute
	private final int sequencesMaxIterationCount;
	
	//attribute
	private final IIntTakerElementGetter<Color> colorFunction;
	
	//attribute
	private final int bigDecimalScale;
	
	//constructor
	public Fractal(
		final IClosedInterval realComponentInterval,
		final IClosedInterval imaginaryComponentInterval,
		final int widthInPixel,
		final int heightInPixel,
		final IElementTakerElementGetter<IComplexNumber, IComplexNumber[]> sequencesStartValuesFunction,
		final I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber> sequencesNextValueFunction,
		final BigDecimal sequencesMinDivergenceMagnitude,
		final int sequencesMaxIterationCount,
		final IIntTakerElementGetter<Color> colorFunction,
		final int bigDecimalScale
	) {
		
		Validator
		.assertThat(realComponentInterval)
		.thatIsNamed("real component interval")
		.isNotNull();
		
		Validator
		.assertThat(imaginaryComponentInterval)
		.thatIsNamed("imaginary component interval")
		.isNotNull();
		
		Validator
		.assertThat(widthInPixel)
		.thatIsNamed("width per unit")
		.isPositive();
		
		Validator
		.assertThat(heightInPixel)
		.thatIsNamed("height per unit")
		.isPositive();
		
		Validator
		.assertThat(sequencesStartValuesFunction)
		.thatIsNamed("sequences start values function")
		.isNotNull();
		
		Validator
		.assertThat(sequencesNextValueFunction)
		.thatIsNamed("sequences next value function")
		.isNotNull();
		
		Validator
		.assertThat(sequencesMinDivergenceMagnitude)
		.thatIsNamed("sequences min divergence magnitude")
		.isPositive();
		
		Validator
		.assertThat(sequencesMaxIterationCount)
		.thatIsNamed("sequences max iteration count")
		.isPositive();
		
		Validator
		.assertThat(colorFunction)
		.thatIsNamed("color function")
		.isNotNull();
		
		Validator
		.assertThat(bigDecimalScale)
		.thatIsNamed("big decimal scale")
		.isPositive();
		
		this.imaginaryComponentInterval = imaginaryComponentInterval.inBigDecimalScale(bigDecimalScale);
		this.realComponentInterval = realComponentInterval.inBigDecimalScale(bigDecimalScale);
		this.widthInPixel = widthInPixel;
		this.heightInPixel = heightInPixel;
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
		return heightInPixel;
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
	public MutableImage toImage() {
		
		final var imageBuilder = startImageBuild();
		imageBuilder.waitUntilIsFinishedSuccessfully();
		
		return imageBuilder.getRefImage();
	}
	
	//method
	public MutableImage toImage(final ProcessingMode processingMode) {
		switch (processingMode) {
			case SINGLE_THREADED:
				return toImageSingleThreaded();
			case MULTI_THREADED:
				return toImage();
			default:
				throw new InvalidArgumentException(processingMode);
		}
	}
	
	//method
	public MutableImage toImageSingleThreaded() {
				
		final var heightInpixel = getHeightInPixel();		
		
		final var image = MutableImage.withWidthAndHeight(widthInPixel, heightInpixel);
		
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
							IComplexNumber::getSquaredMagnitude
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
