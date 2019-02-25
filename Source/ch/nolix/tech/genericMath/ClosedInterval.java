//package declaration
package ch.nolix.tech.genericMath;

//Java imports
import java.math.BigDecimal;
import java.math.RoundingMode;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.tuple.Pair;
import ch.nolix.core.validator.Validator;
import ch.nolix.techAPI.genericMathAPI.IClosedInterval;

//class
public final class ClosedInterval implements IClosedInterval {
	
	//attributes
	private final BigDecimal min;
	private final BigDecimal max;
	
	//constructor
	public ClosedInterval(final BigDecimal min, final BigDecimal max) {
		
		Validator
		.suppose(min)
		.thatIsNamed(VariableNameCatalogue.MINIMUM)
		.isNotNull();
		
		Validator
		.suppose(max)
		.thatIsNamed(VariableNameCatalogue.MAXIMUM)
		.isNotSmallerThan(min);
		
		final var bigDecimalScale = Calculator.getMax(min.scale(), max.scale());
		
		this.min = min.setScale(bigDecimalScale, RoundingMode.HALF_UP);
		this.max = max.setScale(bigDecimalScale, RoundingMode.HALF_UP);
	}
	
	//constructor
	public ClosedInterval(final BigDecimal min, final BigDecimal max, final int bigDecimalScale) {
		
		Validator
		.suppose(min)
		.thatIsNamed(VariableNameCatalogue.MINIMUM)
		.isNotNull();
		
		Validator
		.suppose(max)
		.thatIsNamed(VariableNameCatalogue.MAXIMUM)
		.isNotSmallerThan(min);
		
		Validator
		.suppose(bigDecimalScale)
		.thatIsNamed("big decimal scale")
		.isPositive();
		
		this.min = min.setScale(bigDecimalScale, RoundingMode.HALF_UP);
		this.max = max.setScale(bigDecimalScale, RoundingMode.HALF_UP);
	}
	
	//constructor
	public ClosedInterval(final double min, final double max) {
		this(new BigDecimal(min), new BigDecimal(max));
	}
	
	//constructor
	public ClosedInterval(final double min, final double max, final int bigDecimalScale) {
		this(new BigDecimal(min), new BigDecimal(max), bigDecimalScale);
	}
	
	//method
	@Override
	public boolean contains(final BigDecimal value) {
		return (value.compareTo(getMin()) >= 0 && value.compareTo(getMax()) <= 0);
	}
	
	//method
	@Override
	public boolean equals(final Object object) {
		
		if (!(object instanceof IClosedInterval)) {
			return false;
		}
		
		final var closedIntervall = (IClosedInterval)object;
		
		return (min.equals(closedIntervall.getMin()) && max.equals(closedIntervall.getMax()));
	}
	
	//method
	@Override
	public int getBigDecimalScale() {
		return min.scale();
	}
	
	//method
	@Override
	public Pair<IClosedInterval, IClosedInterval> getHalfs() {
		
		final var midPoint = getMidPoint();
		
		return
		new Pair<IClosedInterval, IClosedInterval>(
			new ClosedInterval(min, midPoint, min.scale()),
			new ClosedInterval(midPoint, max, min.scale())
		);
	}
	
	//method
	@Override
	public ClosedInterval getInBigDecimalScale(final int bigDecimalScale) {
		return new ClosedInterval(min, max, bigDecimalScale);
	}
	
	//method
	@Override
	public BigDecimal getLength() {
		return getMax().subtract(getMin());
	}

	//method
	@Override
	public BigDecimal getMax() {
		return max;
	}

	//method
	@Override
	public BigDecimal getMidPoint() {
		return getMin().add(getMax()).divide(new BigDecimal(2.0)).setScale(min.scale());
	}
	
	//method
	@Override
	public BigDecimal getMin() {
		return min;
	}
	
	//method
	@Override
	public boolean intersects(final IClosedInterval closedInterval) {
		return 
		(getMax().compareTo(closedInterval.getMin()) > 0)
		&&  (getMin().compareTo(closedInterval.getMax()) > 0);
	}
	
	//method
	@Override
	public String toString() {
		return ("[" + min + "," + max + "]");
	}
}
