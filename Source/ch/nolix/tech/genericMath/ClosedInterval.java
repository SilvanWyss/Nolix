//package declaration
package ch.nolix.tech.genericMath;

//Java imports
import java.math.BigDecimal;
import java.math.RoundingMode;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.pair.Pair;
import ch.nolix.common.validator.Validator;
import ch.nolix.techapi.genericmathapi.IClosedInterval;

//class
public final class ClosedInterval implements IClosedInterval {
	
	//attributes
	private final BigDecimal min;
	private final BigDecimal max;
	
	//constructor
	public ClosedInterval(final BigDecimal min, final BigDecimal max) {
		
		Validator
		.assertThat(min)
		.thatIsNamed(VariableNameCatalogue.MINIMUM)
		.isNotNull();
		
		Validator
		.assertThat(max)
		.thatIsNamed(VariableNameCatalogue.MAXIMUM)
		.isNotSmallerThan(min);
		
		final var bigDecimalScale = Calculator.getMax(min.scale(), max.scale());
		
		this.min = min.setScale(bigDecimalScale, RoundingMode.HALF_UP);
		this.max = max.setScale(bigDecimalScale, RoundingMode.HALF_UP);
	}
	
	//constructor
	public ClosedInterval(final BigDecimal min, final BigDecimal max, final int bigDecimalScale) {
		
		Validator
		.assertThat(min)
		.thatIsNamed(VariableNameCatalogue.MINIMUM)
		.isNotNull();
		
		Validator
		.assertThat(max)
		.thatIsNamed(VariableNameCatalogue.MAXIMUM)
		.isNotSmallerThan(min);
		
		Validator
		.assertThat(bigDecimalScale)
		.thatIsNamed("big decimal scale")
		.isPositive();
		
		this.min = min.setScale(bigDecimalScale, RoundingMode.HALF_UP);
		this.max = max.setScale(bigDecimalScale, RoundingMode.HALF_UP);
	}
	
	//constructor
	public ClosedInterval(final double min, final double max) {
		this(BigDecimal.valueOf(min), BigDecimal.valueOf(max));
	}
	
	//constructor
	public ClosedInterval(final double min, final double max, final int bigDecimalScale) {
		this(BigDecimal.valueOf(min), BigDecimal.valueOf(max), bigDecimalScale);
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
		new Pair<>(
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
		return getMin().add(getMax()).divide(BigDecimal.valueOf(2.0)).setScale(min.scale());
	}
	
	//method
	@Override
	public BigDecimal getMin() {
		return min;
	}
	
	//method
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	//method
	@Override
	public boolean intersects(final IClosedInterval closedInterval) {
		return 
		(getMax().compareTo(closedInterval.getMin()) > 0)
		&& (getMin().compareTo(closedInterval.getMax()) > 0);
	}
	
	//method
	@Override
	public String toString() {
		return ("[" + min + ", " + max + "]");
	}
}
