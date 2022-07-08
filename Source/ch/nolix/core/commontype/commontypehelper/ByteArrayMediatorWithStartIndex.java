//package declaration
package ch.nolix.core.commontype.commontypehelper;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class ByteArrayMediatorWithStartIndex {
	
	//attributes
	private final byte[] byteArray;
	private int index;
	
	//constructor
	ByteArrayMediatorWithStartIndex(final byte[] byteArray, final int startIndex) {
		
		GlobalValidator.assertThat(byteArray).thatIsNamed("byte array").isNotNull();
		GlobalValidator.assertThat(startIndex).thatIsNamed(LowerCaseCatalogue.START_INDEX).isBetween(0, byteArray.length);
		
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
