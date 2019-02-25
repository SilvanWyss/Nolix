//package declaration
package ch.nolix.tech.genericMath;

//Java imports
import java.math.BigDecimal;
import java.math.RoundingMode;

//own imports
import ch.nolix.core.functionAPI.IIntTakerElementGetter;
import ch.nolix.core.functionAPI.ITwoElementTakerElementGetter;
import ch.nolix.core.validator.Validator;
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
	private final IComplexNumber sequencesStartValue;
	private final ITwoElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber> sequencesNextValueFunction;
	private final BigDecimal sequencesMinDivergenceMagnitude;
	private final int sequencesMaxIterationCount;
	private final IIntTakerElementGetter<Color> colorFunction;
	
	//constructor
	public Fractal(
		final IClosedInterval realComponentInterval,
		final IClosedInterval imaginaryComponentInterval,
		final int widthInPixel,
		final IComplexNumber sequencesStartValue,
		final ITwoElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber> sequencesNextValueFunction,
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
		.suppose(sequencesStartValue)
		.thatIsNamed("sequences start value")
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
		this.sequencesStartValue = sequencesStartValue.getInBigDecimalScale(bigDecimalScale);
		this.sequencesNextValueFunction = sequencesNextValueFunction;
		this.sequencesMinDivergenceMagnitude = sequencesMinDivergenceMagnitude;
		this.sequencesMaxIterationCount = sequencesMaxIterationCount;
		this.colorFunction = colorFunction;
	}	
	
	//method
	@Override
	public int getBigDecimalScale() {
		return sequencesStartValue.getScale();
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
		.multiply(new BigDecimal(getWidthInPixel()))
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
		return new BigDecimal(widthInPixel).divide(realComponentInterval.getLength());
	}
	
	//method
	@Override
	public IClosedInterval getRealComponentInterval() {
		return realComponentInterval;
	}
	
	//method
	@Override
	public int getSequencesMaxIterationCount() {
		return sequencesMaxIterationCount;
	}
	
	//method
	@Override
	public BigDecimal getSequencesMinDivergenceMagnitude() {
		return sequencesMinDivergenceMagnitude;
	}
	
	//method
	@Override
	public ITwoElementTakerElementGetter<IComplexNumber, IComplexNumber, IComplexNumber> getSequencesNextValueFunction() {
		return sequencesNextValueFunction;
	}
	
	//method
	@Override
	public IComplexNumber getSequencesStartValue() {
		return sequencesStartValue;
	}
	
	//method
	@Override
	public BigDecimal getUnitsPerPixel() {
		return realComponentInterval.getLength().divide(new BigDecimal(widthInPixel), RoundingMode.HALF_UP);
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
							unitsPerPixel.multiply(new BigDecimal(x - 1)),
							unitsPerPixel.multiply(new BigDecimal(y - 1)),
							getBigDecimalScale()
					)
				);
				
				image.setPixel(
					x,
					heightInpixel - y + 1,
					getColor(
						new ImpliciteSequence<IComplexNumber>(
							1,
							getSequencesStartValue(),
							z -> sequencesNextValueFunction.getOutput(z, c),
							z -> z.getSquaredMagnitude()
						)
						.getConvergenceGrade(
							getSequencesMinDivergenceMagnitude(),
							getSequencesMaxIterationCount()
						)
					)
				);
			}
		}
		
		return image;
	}
}
