//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//Java imports
import java.math.BigDecimal;

//class
final class BigDecimalCreator implements IValueCreator<BigDecimal> {
	
	//method
	@Override
	public BigDecimal createValueFromString(final String string) {
		return new BigDecimal(string);
	}
}
