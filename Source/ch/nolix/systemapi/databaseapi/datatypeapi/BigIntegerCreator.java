//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//own imports
import java.math.BigInteger;

//class
final class BigIntegerCreator implements IValueCreator<BigInteger> {
	
	//method
	@Override
	public BigInteger createValueFromString(final String string) {
		return new BigInteger(string);
	}
}
