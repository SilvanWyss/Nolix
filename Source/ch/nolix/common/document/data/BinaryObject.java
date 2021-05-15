//package declaration
package ch.nolix.common.document.data;

//Java imports
import java.nio.charset.StandardCharsets;

import ch.nolix.common.errorcontrol.validator.Validator;

//class
public final class BinaryObject {
	
	//static method
	public static BinaryObject forBytes(final byte[] bytes) {
		return new BinaryObject(bytes);
	}
	
	//static method
	public static BinaryObject fromString(final String string) {
		return new BinaryObject(string.getBytes(StandardCharsets.UTF_8));
	}
	
	//multi-attribute
	private final byte[] bytes;
	
	//constructor
	private BinaryObject(final byte[] bytes) {
		
		Validator.assertThat(bytes).thatIsNamed("bytes").isNotNull();
		
		this.bytes = bytes;
	}
	
	//method
	public byte[] getRefBytes() {
		return bytes;
	}
	
	//method
	public int getSize() {
		return bytes.length;
	}
	
	//method
	public String toString() {
		return new String(bytes, StandardCharsets.UTF_8);
	}
}
