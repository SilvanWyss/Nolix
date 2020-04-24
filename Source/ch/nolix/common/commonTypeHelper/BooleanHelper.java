//package declaration
package ch.nolix.common.commonTypeHelper;

import ch.nolix.common.constant.StringCatalogue;

//class
public final class BooleanHelper {
	
	//static method
	public static String toString(final boolean pBoolean) {
		return (pBoolean ? StringCatalogue.TRUE_HEADER : StringCatalogue.FALSE_HEADER);
	}
	
	//visibility-reducing constructor
	private BooleanHelper() {}
}
