//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//class
final class LongCreator implements IValueCreator<Long> {
	
	//method
	@Override
	public Long createValueFromString(final String string) {
		return Long.valueOf(string);
	}
}
