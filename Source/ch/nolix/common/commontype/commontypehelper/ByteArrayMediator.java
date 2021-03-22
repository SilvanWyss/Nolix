//package declaration
package ch.nolix.common.commontype.commontypehelper;

//own import
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public final class ByteArrayMediator {
	
	//attribute
	private final byte[] byteArray;
	
	//visibility-reduced constructor
	ByteArrayMediator(final byte[] byteArray) {
		
		Validator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();
		
		this.byteArray = byteArray;
	}
	
	//method
	public ByteArrayMediatorWithStartIndex fromIndex(final int index) {
		return new ByteArrayMediatorWithStartIndex(byteArray, index);
	}
}
