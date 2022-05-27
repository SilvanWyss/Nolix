//package declaration
package ch.nolix.core.document.data;

//Java imports
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

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
		
		GlobalValidator.assertThat(bytes).thatIsNamed("bytes").isNotNull();
		
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
