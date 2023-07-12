//package declaration
package ch.nolix.core.independent.containerhelper;

//class
/**
 * The {@link GlobalIterableHelper} provides methods to handle {@link Iterable}s.
 * Of the {@link GlobalIterableHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-12-16
 */
public final class GlobalIterableHelper {
	
	//static method
	/**
	 * @param container
	 * @return the number of elements of the given container.
	 */
	public static int getElementCount(final Iterable<?> container) {
		
		var elementCount = 0;
		final var iterator = container.iterator();
		while (iterator.hasNext()) {
			elementCount++;
			iterator.next();
		}
		
		return elementCount;
	}
	
	//static method
	/**
	 * @param container
	 * @return true if the given container is not empty.
	 */
	public static boolean isEmpty(final Iterable<?> container) {
		return !container.iterator().hasNext();
	}
	
	//constructor
	/**
	 * Prevents that an instance of the {@link GlobalIterableHelper} can be created.
	 */
	private GlobalIterableHelper() {}
}
