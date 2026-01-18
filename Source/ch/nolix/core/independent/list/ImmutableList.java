package ch.nolix.core.independent.list;

import java.util.Arrays;
import java.util.Iterator;

import ch.nolix.core.independent.arraytool.ArrayValidator;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link ImmutableList}.
 */
public final class ImmutableList<E> implements Iterable<E> {
  private static final ArrayValidator ARRAY_VALIDATOR = new ArrayValidator();

  private final E[] elements;

  @SuppressWarnings("unchecked")
  private ImmutableList() {
    elements = (E[]) new Object[0];

    ARRAY_VALIDATOR.assertDoesNotContainNull(elements);
  }

  private ImmutableList(final E[] paramElements) {
    elements = paramElements.clone();

    ARRAY_VALIDATOR.assertDoesNotContainNull(elements);
  }

  private ImmutableList(final E element, final E[] paramElements) {
    elements = Arrays.copyOfRange(paramElements, 0, 1 + paramElements.length);
    elements[paramElements.length] = element;

    ARRAY_VALIDATOR.assertDoesNotContainNull(paramElements);
  }

  public static <T> ImmutableList<T> createEmptyList() {
    return new ImmutableList<>();
  }

  public static <T> ImmutableList<T> withElement(
    final T element,
    final @SuppressWarnings("unchecked") T... elements) {
    return new ImmutableList<>(element, elements);
  }

  public static <T> ImmutableList<T> withElements(final T[] array) {
    return new ImmutableList<>(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<E> iterator() {
    return ImmutableListIterator.forArray(elements);
  }
}
