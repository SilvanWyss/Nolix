//package declaration
package ch.nolix.business.dynamicmath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.businessapi.dynamicmathapi.IClosedInterval;
import ch.nolix.businessapi.dynamicmathapi.IComplexNumber;
import ch.nolix.businessapi.dynamicmathapi.IFractalBuilder;
import ch.nolix.businessapi.dynamicmathapi.ISequence;
import ch.nolix.core.functionapi.IElementTakerElementGetter;
import ch.nolix.core.functionapi.IIntTakerElementGetter;
import ch.nolix.system.gui.color.Color;

//class
public final class FractalBuilder implements IFractalBuilder {
	
	//constant
	public static final IClosedInterval DEFAULT_REAL_COMPONENT_INTERVAL = new ClosedInterval(-2.5, 1.0);
	
	//constant
	public static final IClosedInterval DEFAULT_IMAGINARY_COMPONENT_INTERVAL = new ClosedInterval(-1.5, 1.5);
	
	//constant
	public static final int DEFAULT_WIDHT_IN_PIXEL = 500;
	
	//constant
	public static final int DEFAULT_HEIGHT_IN_PIXEL = DEFAULT_WIDHT_IN_PIXEL;
	
	//constant
	public static final IElementTakerElementGetter<IComplexNumber, ISequence<IComplexNumber>> DEFAULT_SEQUENCE_CREATOR =
	z -> new SequenceDefinedBy1Predecessor<>(z, p -> p.getPower2().getSum(z), IComplexNumber::getSquaredMagnitude);
	
	//constant
	public static final double DEFAULT_SEQUENCES_MIN_DIVERGENCE_MAGNITUDE = 10.0;
	
	//constant
	public static final int DEFAULT_SEQUENCE_MAX_ITERATION_COUNT = 50;
	
	//constant
	public static final IIntTakerElementGetter<Color> DEFAULT_COLOR_FUNCTION =
	i ->  Color.withRedValueAndGreenValueAndBlueValue(0, 0, (10 * i) % Color.MAX_COLOR_COMPONENT);
	
	//constant
	public static final int DEFAULT_BIG_DECIMAL_SCALE = 10;
	
	//attribute
	private IClosedInterval realComponentInterval = DEFAULT_REAL_COMPONENT_INTERVAL;
	
	//attribute
	private IClosedInterval imaginaryComponentInterval = DEFAULT_IMAGINARY_COMPONENT_INTERVAL;
	
	//attribute
	private int widthInPixel = DEFAULT_WIDHT_IN_PIXEL;
	
	//attribute
	private int heightInPixel = DEFAULT_HEIGHT_IN_PIXEL;
	
	//attribute
	private IElementTakerElementGetter<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator =
	DEFAULT_SEQUENCE_CREATOR;
	
	//attribute
	private BigDecimal sequencesMinDivergenceMagnitude =
	BigDecimal.valueOf(DEFAULT_SEQUENCES_MIN_DIVERGENCE_MAGNITUDE);
	
	//attribute
	private int sequencesMaxIterationCount = DEFAULT_SEQUENCE_MAX_ITERATION_COUNT;
	
	//attribute
	private IIntTakerElementGetter<Color> colorFunction = DEFAULT_COLOR_FUNCTION;
	
	//attribute
	private int bigDecimalScale = DEFAULT_BIG_DECIMAL_SCALE;
		
	//method
	public Fractal build() {
		return
		new Fractal(
			realComponentInterval,
			imaginaryComponentInterval,
			widthInPixel,
			heightInPixel,
			sequenceCreator,
			sequencesMinDivergenceMagnitude,
			sequencesMaxIterationCount,
			colorFunction,
			bigDecimalScale
		);
	}
	
	//method
	@Override
	public IFractalBuilder setBigDecimalScale(final int bigDecumalScale) {
		
		this.bigDecimalScale = bigDecumalScale;
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setColorFunction(final IIntTakerElementGetter<Color> colorFunction) {
		
		this.colorFunction = colorFunction;
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setHeightInPixel(final int heightInPixel) {
		
		this.heightInPixel = heightInPixel;
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setImaginaryComponentInterval(final double min, final double max) {
		return setImaginaryComponentInterval(new ClosedInterval(min, max));
	}
	
	//method
	@Override
	public IFractalBuilder setImaginaryComponentInterval(final IClosedInterval imaginaryComponentInterval) {
		
		this.imaginaryComponentInterval = imaginaryComponentInterval;
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setRealComponentInterval(final double min, final double max) {
		return setRealComponentInterval(new ClosedInterval(min, max));
	}
	
	//method
	@Override
	public IFractalBuilder setRealComponentInterval(final IClosedInterval realComponentInterval) {
		
		this.realComponentInterval = realComponentInterval;
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setMaxIterationCount(final int sequencesMaxIterationCount) {
		
		this.sequencesMaxIterationCount = sequencesMaxIterationCount;
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setMinMagnitudeForDivergence(final BigDecimal sequencesMinDivergenceMagnitude) {
		
		this.sequencesMinDivergenceMagnitude = sequencesMinDivergenceMagnitude;
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setMinMagnitudeForDivergence(final double minMagnitudeForDivergence) {
		return setMinMagnitudeForDivergence(BigDecimal.valueOf(minMagnitudeForDivergence));
	}
	
	//method
	@Override
	public IFractalBuilder setSequenceCreator(
		final IElementTakerElementGetter<IComplexNumber, ISequence<IComplexNumber>> sequenceCreator
	) {
		
		this.sequenceCreator = sequenceCreator;
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setWidthInPixel(final int widthInPixel) {
		
		this.widthInPixel = widthInPixel;
		
		return this;
	}
}
