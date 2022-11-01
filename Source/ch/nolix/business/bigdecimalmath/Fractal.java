//package declaration
package ch.nolix.business.bigdecimalmath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.businessapi.bigdecimalmathapi.IClosedInterval;
import ch.nolix.businessapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.businessapi.bigdecimalmathapi.IFractal;
import ch.nolix.businessapi.bigdecimalmathapi.ISequence;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IIntTakerElementGetter;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.systemapi.graphicapi.colorapi.IColor;

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
	private final IIntTakerElementGetter<IColor> colorFunction;
	
	//attribute
	private final int bigDecimalScale;
	
	//constructor
	public Fractal( //NOSONAR
		final IClosedInterval realComponentInterval,
		final IClosedInterval imaginaryComponentInterval,
		final int widthInPixel,
		final int heightInPixel,
		final IElementTakerElementGetter<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator,
		final BigDecimal sequencesMinDivergenceMagnitude,
		final int sequencesMaxIterationCount,
		final IIntTakerElementGetter<IColor> colorFunction,
		final int bigDecimalScale
	) {
		
		GlobalValidator
		.assertThat(realComponentInterval)
		.thatIsNamed("real component interval")
		.isNotNull();
		
		GlobalValidator
		.assertThat(imaginaryComponentInterval)
		.thatIsNamed("imaginary component interval")
		.isNotNull();
		
		GlobalValidator
		.assertThat(widthInPixel)
		.thatIsNamed("width in pixel")
		.isPositive();
		
		GlobalValidator
		.assertThat(heightInPixel)
		.thatIsNamed("height in pixel")
		.isPositive();
		
		GlobalValidator
		.assertThat(sequenceCreator)
		.thatIsNamed("sequence creator")
		.isNotNull();
		
		GlobalValidator
		.assertThat(sequencesMinDivergenceMagnitude)
		.thatIsNamed("sequences min divergence magnitude")
		.isPositive();
		
		GlobalValidator
		.assertThat(sequencesMaxIterationCount)
		.thatIsNamed("sequences max iteration count")
		.isPositive();
		
		GlobalValidator
		.assertThat(colorFunction)
		.thatIsNamed("color function")
		.isNotNull();
		
		GlobalValidator
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
	public IColor getColorForIterationCountWhereValueMagnitudeExceedsMaxMagnitude(final int iterationCount) {
		
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
