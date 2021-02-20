//package declaration
package ch.nolix.system.dynamicmath;

//Java import
import java.math.BigDecimal;

import ch.nolix.businessapi.dynamicmathapi.IClosedIntervalFactory;

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
