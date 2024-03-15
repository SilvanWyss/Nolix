//package declaration
package ch.nolix.coreapi.containerapi.baseapi;

//Java imports
import java.util.function.Function;
import java.util.function.Predicate;

//own imports
import ch.nolix.coreapi.generalstateapi.staterequestapi.EmptinessRequestable;
import ch.nolix.coreapi.generalstateapi.staterequestapi.MaterializationRequestable;

//interface
/**
 * A {@link IContainer} can store several elements of a certain type. A
 * {@link IContainer} stores its element in a linear order. There can exists
 * additionally other orders. A {@link IContainer} is iterable.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <E> is the type of the elements a {@link IContainer}.
 */
public interface IContainer<E>
extends
EmptinessRequestable,
IterableWithCopyableIterator<E>,
ISearchableContainer<E>,
IMappableContainer<E>,
MaterializationRequestable,
IStatisticalConainer<E>,
StoringRequestable<E> {

  //method declaration
  /**
   * @param p1BasedStartIndex
   * @return a new sub {@link IContainer} of the current {@link IContainer} from
   *         the given p1BasedStartIndex.
   * @throws RuntimeException if the given p1BasedStartIndex is not positive.
   * @throws RuntimeException if the current {@link IContainer} contains less
   *                          elements than the given p1BasedStartIndex.
   */
  IContainer<E> from1BasedStartIndex(int p1BasedStartIndex);

  //method declaration
  /**
   * @param p1BasedStartIndex
   * @param p1BasedEndIndex
   * @return a new sub {@link IContainer} of the current {@link IContainer} from
   *         the given p1BasedStartIndex to the given p1BasedEndIndex.
   * @throws RuntimeException if the given startIndex is not positive.
   * @throws RuntimeException if the given p1BasedEndIndex is smaller than the
   *                          given p1BasedStartIndex.
   * @throws RuntimeException if the given p1BasedEndIndex is bigger than the
   *                          number of elements of the current
   *                          {@link IContainer}.
   */
  IContainer<E> from1BasedStartIndexUntil1BasedEndIndex(int p1BasedStartIndex, int p1BasedEndIndex);

  //method declaration
  /**
   * @param selector
   * @return the 1 based index of the first element the given selector selects
   *         from the current {@link IContainer}.
   * @throws RuntimeException if the current {@link IContainer} does not contain
   *                          an element the given selector selects.
   */
  int get1BasedIndexOfFirst(Predicate<E> selector);

  //method declaration
  /**
   * @param element
   * @return the 1-based index of the first element of the current
   *         {@link IContainer} that equals the given element.
   * @throws RuntimeException if the current {@link IContainer} does not contain
   *                          an element that equals the given element.
   */
  int get1BasedIndexOfFirstEqualElement(E element);

  //method declaration
  /**
   * @param element
   * @return the 1-based index of the given element in the current
   *         {@link IContainer}.
   * @throws RuntimeException if the current {@link IContainer} does not contain
   *                          the given element.
   */
  int get1BasedIndexOfFirstOccuranceOf(E element);

  //method declaration
  /**
   * @param norm
   * @param <C>  is the type of the {@link Comparable}s the given norm returns.
   * @return a new {@link IContainer} with the elements of the current
   *         {@link IContainer} ordered from the smallest to the biggest element
   *         according to the given norm.
   */
  <C extends Comparable<C>> IContainer<E> toOrderedList(Function<E, C> norm);

  //method declaration
  /**
   * @return a new {@link IContainer} with the elements of the current
   *         {@link IContainer} in the reversed order.
   */
  IContainer<E> toReversedList();

  //method declaration
  /**
   * 
   * @return a new array with the Strings that represent the elements of the
   *         current {@link IContainer}.
   */
  String[] toStringArray();

  //method declaration
  /**
   * @return a new {@link IContainer} with the {@link String} representations of
   *         the elements of the current {@link IContainer}.
   */
  IContainer<String> toStrings();

  //method declaration
  /**
   * @param separator
   * @return a {@link String} representation the current {@link IContainer} with
   *         the given separator.
   */
  String toStringWithSeparator(char separator);

  //method declaration
  /**
   * @param separator
   * @return a {@link String} representation of the current {@link IContainer}
   *         with the given separator.
   * @throws RuntimeException if the given separator is null.
   */
  String toStringWithSeparator(String separator);

  //method declaration
  /**
   * @param p1BasedEndIndex
   * @return a new sub {@link IContainer} of the current {@link IContainer} with
   *         the elements to the given p1BasedEndIndex.
   * @throws RuntimeException if the given p1BasedEndIndex is not positive.
   * @throws RuntimeException if the current {@link IContainer} contains more
   *                          elements than the given p1BasedEndIndex.
   */
  IContainer<E> until1BasedIndex(int p1BasedEndIndex);

  //method declaration
  /**
   * @return a new sub {@link IContainer} of the current {@link IContainer}
   *         without the first element.
   * @throws RuntimeException if the current {@link IContainer} is empty.
   */
  IContainer<E> withoutFirst();

  //method declaration
  /**
   * @param n
   * @return a new sub {@link IContainer} of the current {@link IContainer}
   *         without the first n elements.
   * @throws RuntimeException if the given n is not positive.
   * @throws RuntimeException if the current {@link IContainer} contains less than
   *                          n elements.
   */
  IContainer<E> withoutFirst(int n);

  //method declaration
  /**
   * @return a new sub {@link IContainer} of the current {@link IContainer}
   *         without the last element.
   * @throws RuntimeException if the current {@link IContainer} is empty.
   */
  IContainer<E> withoutLast();

  //method declaration
  /**
   * @param n
   * @return a new sub {@link IContainer} of the current {@link IContainer}
   *         without the last n elements of the current {@link IContainer}.
   * @throws RuntimeException if the given n is not positive.
   * @throws RuntimeException if the current {@link IContainer} contains less than
   *                          n elements.
   */
  IContainer<E> withoutLast(int n);
}
