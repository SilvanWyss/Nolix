//package declaration
package ch.nolix.core.commontype.commontypehelper;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;

//class
public final class GlobalInputStreamHelper {
	
	//static method
	public static String readLineFrom(final InputStream inputStream) {
		
		final var bytes = new LinkedList<Byte>();
		
		try {
			while (true) {
				
				final var lByte = inputStream.read();
				
				if (lByte == '\r') {
					continue;
				}
				
				if (lByte == -1 || lByte == '\n') {
					return new String(bytes.toByteArray(Byte::byteValue), StandardCharsets.UTF_8);
				}
				
				bytes.addAtEnd((byte)lByte);
			}
		} catch (final IOException pIOException) {
			throw WrapperException.forError(pIOException);
		}
	}
	
	//constructor
	private GlobalInputStreamHelper() {}
}
