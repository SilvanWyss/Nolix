//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//class
final class FloatCreator implements IValueCreator<Float> {
	
	//method
	@Override
	public Float createValueFromString(final String string) {
		return Float.valueOf(string);
	}
}
