//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

import ch.nolix.system.time.base.Time;

//class
final class TimeCreator implements IValueCreator<Time> {
	
	//method
	@Override
	public Time createValueFromString(final String string) {
		return Time.fromString(string);
	}
}
