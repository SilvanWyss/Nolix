//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//class
final class ShortCreator implements IValueCreator<Short> {
	
	//method
	@Override
	public Short createValueFromString(final String string) {
		return Short.valueOf(string);
	}
}
