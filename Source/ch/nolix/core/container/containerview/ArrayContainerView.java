package ch.nolix.core.container.containerview;

import ch.nolix.core.commontypetool.arraytool.ArrayIterator;
import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link ArrayContainerView}.
 */
public final class ArrayContainerView<E> extends AbstractExtendedContainer<E> {
  private static final ArrayContainerView<Object> EMPTY_ARRAY_CONTAINER_VIEW = new ArrayContainerView<>(new Object[0]);

  private final E[] array;

  /**
   * Creates a new {@link ArrayContainerView} for the given array.
   * 
   * @param array
   * @throws ArgumentIsNullException if the given array is null.
   */
  private ArrayContainerView(final E[] array) {
    Validator.assertThat(array).thatIsNamed(LowerCaseVariableCatalog.ARRAY).isNotNull();

    this.array = array; //NOSONAR: An ArrayContainerView operates on the original instance.
  }

  /**
   * @return an empty {@link ArrayContainerView}.
   * @param <T> is the types the elements the {@link ArrayContainerView} would
   *            have.
   */
  @SuppressWarnings("unchecked")
  public static <T> ArrayContainerView<T> createEmpty() {
    return (ArrayContainerView<T>) EMPTY_ARRAY_CONTAINER_VIEW;
  }

  /**
   * @param array
   * @param <T>   is the type of the elements of the given array.
   * @return a new {@link ArrayContainerView} for the given array.
   * @throws ArgumentIsNullException if the given array is null.
   */
  public static <T> ArrayContainerView<T> forArray(final T[] array) {
    return new ArrayContainerView<>(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return array.length;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    Validator.assertThat(oneBasedIndex).thatIsNamed("1-based index").isBetween(0, getCount());

    final var zeroBasedIndex = oneBasedIndex - 1;

    return array[zeroBasedIndex];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return ArrayIterator.forArray(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
