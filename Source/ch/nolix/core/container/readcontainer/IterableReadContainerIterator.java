//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;

//class
final class IterableReadContainerIterator<E> implements CopyableIterator<E> {

  //attribute
  private final Iterable<E> parentIterable;

  //attribute
  private final Iterator<E> internalIterator;

  //attribute
  private int iterationCount;

  //constructor
  private IterableReadContainerIterator(final Iterable<E> parentIterable) {

    GlobalValidator.assertThat(parentIterable).thatIsNamed("parent iterable").isNotNull();

    this.parentIterable = parentIterable;
    internalIterator = parentIterable.iterator();
    iterationCount = 0;
  }

  //constructor
  private IterableReadContainerIterator(final Iterable<E> parentIterable, final int iterationCount) {

    GlobalValidator.assertThat(iterationCount).thatIsNamed("iteration count").isNotNegative();

    this.parentIterable = parentIterable;

    internalIterator = parentIterable.iterator();
    for (var i = 0; i < iterationCount; i++) {
      internalIterator.next();
    }

    this.iterationCount = iterationCount;
  }

  //static method
  public static <E2> IterableReadContainerIterator<E2> forIterable(final Iterable<E2> iterable) {
    return new IterableReadContainerIterator<>(iterable);
  }

  //method
  @Override
  public CopyableIterator<E> getCopy() {
    return new IterableReadContainerIterator<>(parentIterable, iterationCount);
  }

  //method
  @Override
  public boolean hasNext() {
    return internalIterator.hasNext();
  }

  //method
  @Override
  public E next() {

    iterationCount++;

    return internalIterator.next();
  }
}
