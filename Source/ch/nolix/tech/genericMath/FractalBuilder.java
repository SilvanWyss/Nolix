//package declaration
package ch.nolix.tech.genericMath;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.core.functionAPI.IIntTakerElementGetter;
import ch.nolix.core.functionAPI.I2ElementTakerElementGetter;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.element.color.Color;
import ch.nolix.techAPI.genericMathAPI.IClosedInterval;
import ch.nolix.techAPI.genericMathAPI.IComplexNumber;
import ch.nolix.techAPI.genericMathAPI.IFractalBuilder;

//class
public final class FractalBuilder implements IFractalBuilder {
	
	//default values
	public static final IClosedInterval DEFAULT_REAL_COMPONENT_INTERVAL = new ClosedInterval(-2.5, 1.0);
	public static final IClosedInterval DEFAULT_IMAGINARY_COMPONENT_INTERVAL = new ClosedInterval(-1.5, 1.5);
	public static final int DEFAULT_WIDHT_IN_PIXEL = 500;
	public static final IComplexNumber DEFAULT_SEQUENCES_START_VALUE = new ComplexNumber(0.0, 0.0);
	
	//default value
	public static final I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber>
	DEFAULT_SEQUENCES_NEXT_VALUE_FUNCTION =
	(z, c) -> z[0].getPower2().getSum(c);
	
	//default values
	public static final double DEFAULT_SEQUENCES_MIN_DIVERGENCE_MAGNITUDE = 2.5;
	public static final int DEFAULT_SEQUENCE_MAX_ITERATION_COUNT = 100;
	
	//default value
	public static final IIntTakerElementGetter<Color>
	DEFAULT_COLOR_FUNCTION =
	i -> i < DEFAULT_SEQUENCE_MAX_ITERATION_COUNT ? new Color(0, 0, (10 * i) % Color.MAX_COLOR_COMPONENT) : Color.BLACK;
	
	//default value
	public static final int DEFAULT_BIG_DECIMAL_SCALE = 10;
	
	//attributes
	private IClosedInterval realComponentInterval = DEFAULT_REAL_COMPONENT_INTERVAL;
	private IClosedInterval imaginaryComponentInterval = DEFAULT_IMAGINARY_COMPONENT_INTERVAL;
	private int widthInPixel = DEFAULT_WIDHT_IN_PIXEL;
	
	//attribute
	private IElementTakerElementGetter<IComplexNumber, IComplexNumber[]> sequencesStartValuesFunction
	= c -> new IComplexNumber[] {DEFAULT_SEQUENCES_START_VALUE};
	
	//attribute
	private I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber>
	sequencesNextValueFunction =
	DEFAULT_SEQUENCES_NEXT_VALUE_FUNCTION;
	
	//attributes
	private BigDecimal sequencesMinDivergenceMagnitude = BigDecimal.valueOf(DEFAULT_SEQUENCES_MIN_DIVERGENCE_MAGNITUDE);
	private int sequencesMaxIterationCount = DEFAULT_SEQUENCE_MAX_ITERATION_COUNT;
	private IIntTakerElementGetter<Color> colorFunction = DEFAULT_COLOR_FUNCTION;
	private int bigDecimalScale = DEFAULT_BIG_DECIMAL_SCALE;
		
	//method
	public Fractal build() {
		return
		new Fractal(
			realComponentInterval,
			imaginaryComponentInterval,
			widthInPixel,
			sequencesStartValuesFunction,
			sequencesNextValueFunction,
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
	public IFractalBuilder setSequencesMaxIterationCount(final int sequencesMaxIterationCount) {
		
		this.sequencesMaxIterationCount = sequencesMaxIterationCount;
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setSequencesMinDivergenceMagnitude(final BigDecimal sequencesMinDivergenceMagnitude) {
		
		this.sequencesMinDivergenceMagnitude = sequencesMinDivergenceMagnitude;
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setSequencesMinDivergenceMagnitude(final double sequencesMinDivergenceMagnitude) {
		return setSequencesMinDivergenceMagnitude(BigDecimal.valueOf(sequencesMinDivergenceMagnitude));
	}
	
	//method
	@Override
	public IFractalBuilder setSequencesNextValueFunction(
		final I2ElementTakerElementGetter<IComplexNumber[], IComplexNumber, IComplexNumber>
		sequenceNextValueFunction
	) {
		
		this.sequencesNextValueFunction = sequenceNextValueFunction;
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setSequencesStartValues(final IComplexNumber... sequencesStartValues) {		
		return setSequencesStartValuesFunction(c -> sequencesStartValues);
	}
	
	//method
	public IFractalBuilder setSequencesStartValuesFunction(
		IElementTakerElementGetter<IComplexNumber, IComplexNumber[]> sequencesStartValuesFunction
	) {
		this.sequencesStartValuesFunction = sequencesStartValuesFunction;
		
//		this.sequencesStartValuesFunction
//		= c -> {
//			
//			final var arrayList = new ArrayList<IComplexNumber>();
//			
//			final var sequnecesStartValues = sequencesStartValuesFunction.getOutput(c);
//			for (final var ssv : sequnecesStartValues) {
//				arrayList.add(ssv);
//			}
//			 
//			return arrayList;
//		};
		
		return this;
	}
	
	//method
	@Override
	public IFractalBuilder setWidthInPixel(final int widthInPixel) {
		
		this.widthInPixel = widthInPixel;
		
		return this;
	}
}
