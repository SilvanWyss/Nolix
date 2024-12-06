package ch.nolix.core.container.containerview;

import java.util.Iterator;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;

final class IterableViewIterator<E> implements CopyableIterator<E> {

  private final Iterable<E> parentIterable;

  private final Iterator<E> internalIterator;

  private int iterationCount;

  private IterableViewIterator(final Iterable<E> parentIterable) {

    GlobalValidator.assertThat(parentIterable).thatIsNamed("parent iterable").isNotNull();

    this.parentIterable = parentIterable;
    internalIterator = parentIterable.iterator();
    iterationCount = 0;
  }

  private IterableViewIterator(final Iterable<E> parentIterable, final int iterationCount) {

    GlobalValidator.assertThat(iterationCount).thatIsNamed("iteration count").isNotNegative();

    this.parentIterable = parentIterable;

    internalIterator = parentIterable.iterator();
    for (var i = 0; i < iterationCount; i++) {
      internalIterator.next();
    }

    this.iterationCount = iterationCount;
  }

  public static <E2> IterableViewIterator<E2> forIterable(final Iterable<E2> iterable) {
    return new IterableViewIterator<>(iterable);
  }

  @Override
  public CopyableIterator<E> createCopy() {
    return new IterableViewIterator<>(parentIterable, iterationCount);
  }

  @Override
  public boolean hasNext() {
    return internalIterator.hasNext();
  }

  @Override
  public E next() {

    iterationCount++;

    return internalIterator.next();
  }
}
