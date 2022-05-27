//package declaration
package ch.nolix.business.dynamicmath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.businessapi.dynamicmathapi.IClosedInterval;
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.businessapi.dynamicmathapi.IFractal;
import ch.nolix.businessapi.dynamicmathapi.ISequence;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionuniversalapi.IElementTakerElementGetter;
import ch.nolix.core.functionuniversalapi.IIntTakerElementGetter;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;

//class
public final class Fractal implements IFractal {
	
	//constant
	public static final Color CONVERGENCE_COLOR = Color.BLACK;
	
	//attribute
	private final IClosedInterval realComponentInterval;
	
	//attribute
	private final IClosedInterval imaginaryComponentInterval;
	
	//attribute
	private final int widthInPixel;
	
	//attribute
	private final int heightInPixel;
	
	//attribute
	private final IElementTakerElementGetter<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator;
	
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
		final IElementTakerElementGetter<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator,
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
		.thatIsNamed("width in pixel")
		.isPositive();
		
		Validator
		.assertThat(heightInPixel)
		.thatIsNamed("height in pixel")
		.isPositive();
		
		Validator
		.assertThat(sequenceCreator)
		.thatIsNamed("sequence creator")
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
		this.sequenceCreator = sequenceCreator;
		this.sequencesMinDivergenceMagnitude = sequencesMinDivergenceMagnitude;
		this.sequencesMaxIterationCount = sequencesMaxIterationCount;
		this.colorFunction = colorFunction;
		this.bigDecimalScale = bigDecimalScale;
	}	
	
	//method
	@Override
	public ISequence<IComplexNumber> createSequenceFor(final IComplexNumber complexNumber) {
		return sequenceCreator.getOutput(complexNumber);
	}
	
	//method
	@Override
	public int getBigDecimalScale() {
		return bigDecimalScale;
	}
	
	//method
	@Override
	public Color getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(final int iterationCount) {
		
		if (iterationCount == -1) {
			return CONVERGENCE_COLOR;
		}
		
		return colorFunction.getOutput(iterationCount);
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
	public BigDecimal getMinMagnitudeForDivergence() {
		return sequencesMinDivergenceMagnitude;
	}
	
	//method
	@Override
	public int getWidthInPixel() {
		return widthInPixel;
	}
	
	//method
	public ImageGenerator startImageGeneration() {
		return new ImageGenerator(this);
	}
	
	//method
	@Override
	public MutableImage toImage() {
		
		final var imageBuilder = startImageGeneration();
		imageBuilder.waitUntilIsFinishedSuccessfully();
		
		return imageBuilder.getRefImage();
	}
}
