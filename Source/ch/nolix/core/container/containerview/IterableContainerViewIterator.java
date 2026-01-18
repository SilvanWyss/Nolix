package ch.nolix.core.container.containerview;

import java.util.Iterator;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.iterator.CopyableIterator;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the parent {@link Iterable} of a
 *            {@link IterableContainerViewIterator}.
 */
public final class IterableContainerViewIterator<E> implements CopyableIterator<E> {
  private final Iterable<E> parentIterable;

  private final Iterator<E> internalIterator;

  private int iterationCount;

  private IterableContainerViewIterator(final Iterable<E> parentIterable) {
    Validator.assertThat(parentIterable).thatIsNamed("parent iterable").isNotNull();

    this.parentIterable = parentIterable;
    internalIterator = parentIterable.iterator();
    iterationCount = 0;
  }

  private IterableContainerViewIterator(final Iterable<E> parentIterable, final int iterationCount) {
    Validator.assertThat(iterationCount).thatIsNamed("iteration count").isNotNegative();

    this.parentIterable = parentIterable;

    internalIterator = parentIterable.iterator();
    for (var i = 0; i < iterationCount; i++) {
      internalIterator.next();
    }

    this.iterationCount = iterationCount;
  }

  public static <T> IterableContainerViewIterator<T> forIterable(final Iterable<T> iterable) {
    return new IterableContainerViewIterator<>(iterable);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return new IterableContainerViewIterator<>(parentIterable, iterationCount);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return internalIterator.hasNext();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {
    iterationCount++;

    return internalIterator.next();
  }
}
