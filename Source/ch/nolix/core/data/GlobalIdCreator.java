//package declaration
package ch.nolix.core.data;

//Java imports
import java.util.UUID;

//own imports
import ch.nolix.core.commontype.constant.StringCatalogue;

//class
public final class GlobalIdCreator {
	
	//static method
	public static String createIdOf10HexadecimalCharacters() {
		return
		UUID.randomUUID().toString().replace(StringCatalogue.MINUS, StringCatalogue.EMPTY_STRING).substring(22, 32);
	}
	
	//constructor
	private GlobalIdCreator() {}
}
