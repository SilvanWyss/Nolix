//package declaration
package ch.nolix.common.commonTypeHelpers;

//own import
import ch.nolix.common.constants.StringCatalogue;

//class
public final class BooleanHelper {
	
	//static method
	public static String toString(final boolean pBoolean) {
		return (pBoolean ? StringCatalogue.TRUE_HEADER : StringCatalogue.FALSE_HEADER);
	}
	
	//access-reducing constructor
	private BooleanHelper() {}
}
