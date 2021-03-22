//package declaration
package ch.nolix.common.commontype.commontypehelper;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public final class ByteArrayMediatorWithStartIndex {
	
	//attributes
	private final byte[] byteArray;
	private int index;
	
	//visibility-reduced constructor
	ByteArrayMediatorWithStartIndex(final byte[] byteArray, final int startIndex) {
		
		Validator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();
		Validator.assertThat(startIndex).thatIsNamed(LowerCaseCatalogue.START_INDEX).isBetween(0, byteArray.length);
		
		this.byteArray = byteArray;
		index = startIndex;
	}
	
	//method
	public NextIndexMediator write(final byte[] bytes) {
		
		for (var i = 0; i < bytes.length; i++) {
			byteArray[index + i] = bytes[i];
		}
		
		index += bytes.length;
		
		return new NextIndexMediator(index);
	}
}
