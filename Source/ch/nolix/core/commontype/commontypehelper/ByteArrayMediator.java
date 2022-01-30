//package declaration
package ch.nolix.core.commontype.commontypehelper;

import ch.nolix.core.errorcontrol.validator.Validator;

//class
public final class ByteArrayMediator {
	
	//attribute
	private final byte[] byteArray;
	
	//constructor
	ByteArrayMediator(final byte[] byteArray) {
		
		Validator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();
		
		this.byteArray = byteArray;
	}
	
	//method
	public ByteArrayMediatorWithStartIndex fromIndex(final int index) {
		return new ByteArrayMediatorWithStartIndex(byteArray, index);
	}
}
