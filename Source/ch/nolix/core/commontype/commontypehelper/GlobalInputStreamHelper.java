//package declaration
package ch.nolix.core.commontype.commontypehelper;

//Java imports
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;

//class
public final class GlobalInputStreamHelper {
	
	//static method
	/**
	 * @param inputStream
	 * @return the next line from the given inputStream or null.
	 */
	public static String readLineFrom(final InputStream inputStream) {
		
		final var bytes = new LinkedList<Byte>();
		
		while (true) {
			try {
				
				final var lByte = inputStream.read();
				
				if (lByte == -1) {
					return null;
				}
								
				if (lByte == '\r') {
					continue;
				}
				
				if (lByte == '\n') {
					return new String(bytes.toByteArray(Byte::byteValue), StandardCharsets.UTF_8);
				}
				
				bytes.addAtEnd((byte)lByte);
			} catch (final Exception exception) { //NOSONAR
				return null;
			}
		}
	}
	
	//constructor
	private GlobalInputStreamHelper() {}
}
