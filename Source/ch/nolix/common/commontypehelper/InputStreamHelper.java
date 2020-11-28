//package declaration
package ch.nolix.common.commontypehelper;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.exception.WrapperException;

//class
public final class InputStreamHelper {
	
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
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//visibility-reduced constructor
	private InputStreamHelper() {}
}
