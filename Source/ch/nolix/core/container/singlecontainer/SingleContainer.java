package ch.nolix.core.container.singlecontainer;

import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the element of a {@link SingleContainer}.
 */
public final class SingleContainer<E> extends AbstractExtendedContainer<E> {
  private final E element;

  private SingleContainer(final E element) {
    Validator.assertThat(element).thatIsNamed(LowerCaseVariableCatalog.ELEMENT).isNotNull();

    this.element = element;
  }

  public static <T> SingleContainer<T> withElement(final T element) {
    return new SingleContainer<>(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return 1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    Validator.assertThat(oneBasedIndex).thatIsNamed(LowerCaseVariableCatalog.ONE_BASED_INDEX).isEqualTo(1);

    return element;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return SingleContainerIterator.forNullableElement(element);
  }
}
