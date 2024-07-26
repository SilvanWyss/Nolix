//package declaration
package ch.nolix.core.independent.container;

//Java imports
import java.util.Arrays;
import java.util.Iterator;

//own imports
import ch.nolix.core.independent.containervalidator.ArrayValidator;

//class
public final class ImmutableList<E> implements Iterable<E> {

  //constant
  private static final ArrayValidator ARRAY_VALIDATOR = new ArrayValidator();

  //multi-attribute
  private final E[] elements;

  //constructor
  @SuppressWarnings("unchecked")
  private ImmutableList() {

    elements = (E[]) new Object[0];

    ARRAY_VALIDATOR.assertDoesNotContainNull(elements);
  }

  //constructor
  private ImmutableList(final E[] paramElements) {

    elements = paramElements.clone();

    ARRAY_VALIDATOR.assertDoesNotContainNull(elements);
  }

  //constructor
  private ImmutableList(final E element, final E[] paramElements) {

    elements = Arrays.copyOfRange(paramElements, 0, 1 + paramElements.length);
    elements[paramElements.length] = element;

    ARRAY_VALIDATOR.assertDoesNotContainNull(paramElements);
  }

  //static method
  public static <E2> ImmutableList<E2> createEmptyList() {
    return new ImmutableList<>();
  }

  //static method
  public static <E2> ImmutableList<E2> withElement(
    final E2 element,
    final @SuppressWarnings("unchecked") E2... elements) {
    return new ImmutableList<>(element, elements);
  }

  //static method
  public static <E2> ImmutableList<E2> withElements(final E2[] array) {
    return new ImmutableList<>(array);
  }

  //method
  @Override
  public Iterator<E> iterator() {
    return ArrayIterator.forArray(elements);
  }
}
