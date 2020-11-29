//package declaration
package ch.nolix.common.commontypehelper;

//own import
import ch.nolix.common.constant.StringCatalogue;

//class
public final class BooleanHelper {
	
	//static method
	@SuppressWarnings("all")
	public static String toString(final boolean pBoolean) {
		return (pBoolean ? StringCatalogue.TRUE_HEADER : StringCatalogue.FALSE_HEADER);
	}
	
	//visibility-reduced constructor
	private BooleanHelper() {}
}
