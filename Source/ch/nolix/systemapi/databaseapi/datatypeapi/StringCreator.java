//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//class
final class StringCreator implements IValueCreator<String> {
	
	//method
	@Override
	public String createValueFromString(final String string) {
		return string;
	}
}
