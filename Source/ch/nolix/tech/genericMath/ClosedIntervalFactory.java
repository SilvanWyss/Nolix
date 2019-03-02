//package declaration
package ch.nolix.tech.genericMath;

//Java import
import java.math.BigDecimal;

//own import
import ch.nolix.techAPI.genericMathAPI.IClosedIntervalFactory;

//class
public final class ClosedIntervalFactory implements IClosedIntervalFactory {
	
	//method
	@Override
	public ClosedInterval create(final BigDecimal min, final BigDecimal max) {
		return new ClosedInterval(min, max);
	}
	
	//method
	@Override
	public ClosedInterval create(final BigDecimal min, final BigDecimal max, final int bigDecimalScale) {
		return new ClosedInterval(min, max, bigDecimalScale);
	}
	
	//method
	@Override
	public ClosedInterval create(final double min, final double max) {
		return new ClosedInterval(min, max);
	}
	
	//method
	@Override
	public ClosedInterval create(final double min, final double max, final int bigDecimalScale) {
		return new ClosedInterval(min, max, bigDecimalScale);
	}
}
