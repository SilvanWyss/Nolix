//package declaration
package ch.nolix.business.dynamicmath;

//Java imports
import java.math.BigDecimal;
import java.math.RoundingMode;

//own imports
import ch.nolix.businessapi.dynamicmathapi.IClosedInterval;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.math.Calculator;

//class
public final class ClosedInterval implements IClosedInterval {
	
	//attribute
	private final BigDecimal min;
	
	//attribute
	private final BigDecimal max;
	
	//constructor
	public ClosedInterval(final BigDecimal min, final BigDecimal max) {
		
		Validator.assertThat(min).thatIsNamed(LowerCaseCatalogue.MINIMUM).isNotNull();
		Validator.assertThat(max).thatIsNamed(LowerCaseCatalogue.MAXIMUM).isNotSmallerThan(min);
		
		final var bigDecimalScale = Calculator.getMax(min.scale(), max.scale());
		
		this.min = min.setScale(bigDecimalScale, RoundingMode.HALF_UP);
		this.max = max.setScale(bigDecimalScale, RoundingMode.HALF_UP);
	}
	
	//constructor
	public ClosedInterval(final BigDecimal min, final BigDecimal max, final int bigDecimalScale) {
		
		Validator.assertThat(min).thatIsNamed(LowerCaseCatalogue.MINIMUM).isNotNull();
		Validator.assertThat(max).thatIsNamed(LowerCaseCatalogue.MAXIMUM).isNotSmallerThan(min);
		Validator.assertThat(bigDecimalScale).thatIsNamed("big decimal scale").isPositive();
		
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
	public boolean containsValue(final BigDecimal value) {
		return (value.compareTo(min) >= 0 && value.compareTo(max) <= 0);
	}
	
	//method
	@Override
	public boolean equals(final Object object) {
		
		if (!(object instanceof IClosedInterval)) {
			return false;
		}
		
		return equals((IClosedInterval)object);
	}
	
	//method
	@Override
	public int getBigDecimalScale() {
		return min.scale();
	}
	
	//method
	@Override
	public Pair<IClosedInterval, IClosedInterval> getHalfs() {
		
		final var bigDecimalScale = getBigDecimalScale();
		final var midPoint = getMidPoint();
		
		return
		new Pair<>(
			new ClosedInterval(min, midPoint, bigDecimalScale),
			new ClosedInterval(midPoint, max, bigDecimalScale)
		);
	}
	
	//method
	@Override
	public BigDecimal getLength() {
		return max.subtract(min);
	}

	//method
	@Override
	public BigDecimal getMax() {
		return max;
	}

	//method
	@Override
	public BigDecimal getMidPoint() {
		return min.add(max).divide(BigDecimal.valueOf(2.0)).setScale(min.scale());
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
	public ClosedInterval inBigDecimalScale(final int bigDecimalScale) {
		return new ClosedInterval(min, max, bigDecimalScale);
	}
	
	//method
	@Override
	public boolean intersectsWith(final IClosedInterval closedInterval) {
		return 
		max.compareTo(closedInterval.getMin()) > 0
		&& min.compareTo(closedInterval.getMax()) > 0;
	}
	
	//method
	@Override
	public String toString() {
		return ("[" + min + ", " + max + "]");
	}
	
	//method
	private boolean equals(final IClosedInterval closedInterval) {
		return 
		min.equals(closedInterval.getMin())
		&& max.equals(closedInterval.getMax());
	}
}
