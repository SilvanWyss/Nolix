//package declaration
package ch.nolix.systemapi.databaseapi.datatypeapi;

//Java imports
import java.util.Base64;

import ch.nolix.core.data.BinaryObject;

//class
final class BinaryObjectCreator implements IValueCreator<BinaryObject> {
	
	//method
	@Override
	public BinaryObject createValueFromString(final String string) {
		return BinaryObject.forBytes(Base64.getDecoder().decode(string));
	}
}
