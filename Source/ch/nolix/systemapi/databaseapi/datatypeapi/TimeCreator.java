//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//own imports
import ch.nolix.element.time.base.Time;

//class
final class TimeCreator implements IValueCreator<Time> {
	
	//method
	@Override
	public Time createValueFromString(final String string) {
		return Time.fromString(string);
	}
}
