//package declaration
package ch.nolix.core.commontype.commontypehelper;

//Java imports
import java.util.Objects;

//class
public final class GlobalIterableHelper {
	
	//static method
	public static boolean containsAny(final Iterable<?> iterable) {
		return
		iterable != null		
		&& iterable.iterator().hasNext();
	}
	
	//static method
	public static boolean containsEqualing(final Iterable<?> iterable, final Object object) {
		
		if (iterable == null) {
			return false;
		}
		
		for (final var e : iterable) {
			if (Objects.equals(e, object)) {
				return true;
			}
		}
		
		return false;
	}
	
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
