/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.extendediterable;

import java.util.function.Predicate;

import ch.nolix.base.commontype.iteratorvalidator.IteratorValidator;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a
 *            {@link ExtendedIterableFilterViewIterator}
 */
public final class ExtendedIterableFilterViewIterator<E> implements CopyableIterator<E> {
  private static final IteratorValidator ITERATOR_VALIDATOR = new IteratorValidator();

  private final CopyableIterator<E> iterator;

  private final Predicate<E> selector;

  private E optionalNextElement;

  /**
   * Creates a new {@link ExtendedIterableFilterViewIterator} with the given
   * iterator and selector.
   * 
   * @param iterator
   * @param selector
   * @throws RuntimeException if the given container is null
   * @throws RuntimeException if the given selector is null
   */
  private ExtendedIterableFilterViewIterator(final CopyableIterator<E> iterator, final Predicate<E> selector) {
    Validator.assertThat(iterator).thatIsNamed(LowerCaseVariableNameCatalog.ITERATOR).isNotNull();
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableNameCatalog.SELECTOR).isNotNull();

    this.iterator = iterator;
    this.selector = selector;

    moveToOptionalNextElement();
  }

  public static <T> ExtendedIterableFilterViewIterator<T> forIteratorAndSelector(
    final CopyableIterator<T> iterator,
    final Predicate<T> selector) {
    return new ExtendedIterableFilterViewIterator<>(iterator, selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return forIteratorAndSelector(iterator.getCopy(), selector);
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
