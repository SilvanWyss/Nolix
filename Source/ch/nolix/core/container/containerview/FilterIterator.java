package ch.nolix.core.container.containerview;

import java.util.function.Predicate;

import ch.nolix.core.commontypetool.iteratorvalidator.IteratorValidator;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2025-03-09
 * @param <E> is the type of the elements of a {@link FilterIterator}.
 */
public final class FilterIterator<E> implements CopyableIterator<E> {

  private static final IteratorValidator ITERATOR_VALIDATOR = new IteratorValidator();

  private final CopyableIterator<E> iterator;

  private final Predicate<E> selector;

  private E optionalNextElement;

  /**
   * Creates a new {@link FilterIterator} with the given iterator and selector.
   * 
   * @param iterator
   * @param selector
   * @throws ArgumentIsNullException if the given container is null.
   * @throws ArgumentIsNullException if the given selector is null.
   */
  private FilterIterator(final CopyableIterator<E> iterator, final Predicate<E> selector) {

    Validator.assertThat(iterator).thatIsNamed(LowerCaseVariableCatalog.ITERATOR).isNotNull();
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    this.iterator = iterator;
    this.selector = selector;

    moveToOptionalNextElement();
  }

  public static <T> FilterIterator<T> forIteratorAndSelector(
    final CopyableIterator<T> iterator,
    final Predicate<T> selector) {
    return new FilterIterator<>(iterator, selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> createCopy() {
    return forIteratorAndSelector(iterator.createCopy(), selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (optionalNextElement != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {

    ITERATOR_VALIDATOR.assertHasNext(this);

    final var nextElement = optionalNextElement;

    moveToOptionalNextElement();

    return nextElement;
  }

  private void moveToOptionalNextElement() {

    optionalNextElement = null;

    while (iterator.hasNext()) {

      final var nextElement = iterator.next();

      if (nextElement != null && selector.test(nextElement)) {
        optionalNextElement = nextElement;
        break;
      }
    }
  }
}
