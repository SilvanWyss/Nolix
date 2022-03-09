//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//interface
interface IValueCreator<V> {
	
	V createValueFromString(String string);
}
