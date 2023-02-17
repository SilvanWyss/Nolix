//package declaration
package ch.nolix.core.independent.container;

//Java imports
import java.util.Arrays;
import java.util.Iterator;

//own imports
import ch.nolix.core.independent.containervalidator.GlobalArrayValidator;

//class
public final class ImmutableList<E> implements Iterable<E> {
	
	//static method
	public static <E2> ImmutableList<E2> createEmptyList() {
		return new ImmutableList<>();
	}
	
	//static method
	@SuppressWarnings("unchecked")
	public static <E2> ImmutableList<E2> withElement(final E2 element) {
		return new ImmutableList<>((E2[])new Object[] {element});
	}
	
	//static method
	public static <E2> ImmutableList<E2> withElements(
		final E2 firstElement,
		final @SuppressWarnings("unchecked")E2... elements
	) {
		return new ImmutableList<>(firstElement, elements);
	}
	
	//static method
	public static <E2> ImmutableList<E2> withElements(final E2[] array) {
		return new ImmutableList<>(array);
	}
	
	//multi-attribute
	private final E[] elements;
	
	//constructor
	@SuppressWarnings("unchecked")
	private ImmutableList() {
		
		elements = (E[])new Object[0];
		
		GlobalArrayValidator.assertDoesNotContainNull(elements);
	}
	
	//constructor
	private ImmutableList(final E[] paramElements) {
		
		elements = paramElements.clone();
		
		GlobalArrayValidator.assertDoesNotContainNull(elements);
	}
	
	//constructor
	private ImmutableList(final E firstElement, final E[] paramElements) {
		
		elements = Arrays.copyOfRange(paramElements, 0, 1 + paramElements.length);
		elements[paramElements.length] = firstElement;
		
		GlobalArrayValidator.assertDoesNotContainNull(paramElements);
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		return ArrayIterator.forArray(elements);
	}
}
