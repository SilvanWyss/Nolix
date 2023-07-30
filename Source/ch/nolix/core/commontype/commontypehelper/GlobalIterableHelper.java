//package declaration
package ch.nolix.core.commontype.commontypehelper;

//class
public final class GlobalIterableHelper {
	
	//static method
	public static int getElementCount(final Iterable<?> iterable) {
		
		var elementCount = 0;
		final var iterator = iterable.iterator();
		while (iterator.hasNext()) {
			elementCount++;
			iterator.next();
		}
		
		return elementCount;
	}
	
	//constructor
	private GlobalIterableHelper() {}
}
