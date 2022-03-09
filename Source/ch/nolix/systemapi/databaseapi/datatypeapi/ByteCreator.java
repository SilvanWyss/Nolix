//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//class
final class ByteCreator implements IValueCreator<Byte> {
	
	//method
	@Override
	public Byte createValueFromString(final String string) {
		return Byte.valueOf(string);
	}
}
