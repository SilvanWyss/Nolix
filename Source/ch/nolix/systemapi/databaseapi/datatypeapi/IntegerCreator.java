//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//class
final class IntegerCreator implements IValueCreator<Integer> {
	
	//method
	@Override
	public Integer createValueFromString(final String string) {
		return Integer.valueOf(string);
	}
}
