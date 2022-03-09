//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//class
final class DoubleCreator implements IValueCreator<Double> {
	
	//method
	@Override
	public Double createValueFromString(final String string) {
		return Double.valueOf(string);
	}
}
