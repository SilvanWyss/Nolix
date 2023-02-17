//package declaration
package ch.nolix.core.independent.independentcontainer;

//Java imports
import java.util.Arrays;
import java.util.Iterator;

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
	public ImmutableList() {
		elements = (E[])new Object[0];
	}
	
	//constructor
	private ImmutableList(final E[] elements) {
		this.elements = elements.clone();
	}
	
	//constructor
	private ImmutableList(final E firstElement, final E[] elements) {
		this.elements = Arrays.copyOfRange(elements, 0, 1 + elements.length);
		this.elements[elements.length] = firstElement;
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		return ArrayIterator.forArray(elements);
	}
}
