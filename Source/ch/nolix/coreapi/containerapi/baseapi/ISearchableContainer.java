//package declaration
package ch.nolix.coreapi.containerapi.baseapi;

//Java imports
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

//interface
/**
 * @author Silvan Wyss
 * @version 2023-10-14
 * @param <E> is the type of the elements a {@link ISearchableContainer}.
 */
public interface ISearchableContainer<E> {

  //method declaration
  /**
   * @return a {@link Optional} with the first element of the current
   *         {@link ISearchableContainer} if the current
   *         {@link ISearchableContainer} contains any, an empty {@link Optional}
   *         otherwise.
   */
  Optional<E> getOptionalStoredFirst();

  //method declaration
  /**
   * @param selector
   * @return a new {@link Optional} with the first element the given selector
   *         selects from the current {@link ISearchableContainer} if the current
   *         {@link ISearchableContainer} contains an element the given selector
   *         selects, an empty {@link Optional} otherwise.
   * @throws RuntimeException if the given selector is null.
   */
  Optional<E> getOptionalStoredFirst(Predicate<? super E> selector);

  //method declaration
  /**
   * @return a randomly selected element of the current
   *         {@link ISearchableContainer}.
   * @throws RuntimeException if the current {@link ISearchableContainer} is
   *                          empty.
   */
  E getStoredAny();

  //method declaration declaration
  /**
   * @param p1BasedIndex
   * @return the element at the given p1BasedIndex.
   * @throws RuntimeException if the current {@link ISearchableContainer} does not
   *                          contain an element at the given p1BasedIndex.
   */
  E getStoredAt1BasedIndex(int p1BasedIndex);

  //method declaration
  /**
   * @param norm
   * @param <C>  is the type of the {@link Comparable}s the given norm returns.
   * @return the element with the biggest {@link Comparable} the given norm
   *         returns from the elements of the current
   *         {@link ISearchableContainer}.
   * @throws RuntimeException if the current {@link ISearchableContainer} is
   *                          empty.
   */
  <C extends Comparable<C>> E getStoredByMax(Function<E, C> norm);

  //method declaration
  /**
   * @param norm
   * @param <C>  is the type of the {@link Comparable}s the given norm returns.
   * @return the element with the smallest {@link Comparable} the given norm
   *         returns from the elements of the current
   *         {@link ISearchableContainer}.
   * @throws RuntimeException if the current {@link ISearchableContainer} is
   *                          empty.
   */
  <C extends Comparable<C>> E getStoredByMin(Function<E, C> norm);

  //method declaration
  /**
   * @return the first element of the current {@link ISearchableContainer}.
   * @throws RuntimeException if the current {@link ISearchableContainer} is
   *                          empty.
   */
  E getStoredFirst();

  //method declaration
  /**
   * @param selector
   * @return the first element the given selector selects from the current
   *         {@link ISearchableContainer}.
   * @throws RuntimeException if the current {@link ISearchableContainer} does not
   *                          contain an element the given selector selects.
   */
  E getStoredFirst(Predicate<? super E> selector);

  //method
  /**
   * @param norm
   * @return a new {@link ISearchableContainer} with groups with the elements of
   *         the current {@link ISearchableContainer} grouped by the given norm.
   */
  IContainer<? extends IContainer<E>> getStoredGroups(Function<E, ?> norm);

  //method declaration
  /**
   * @return the last element of the current {@link ISearchableContainer}.
   * @throws RuntimeException if the current {@link ISearchableContainer} is
   *                          empty.
   */
  E getStoredLast();

  //method declaration
  /**
   * @param type
   * @param <E2> is the type of the elements of the returned
   *             {@link ISearchableContainer}.
   * @return a new {@link ISearchableContainer} with the elements from the current
   *         {@link ISearchableContainer} that are of the given type.
   */
  <E2 extends E> IContainer<E2> getStoredOfType(Class<E2> type);

  //method declaration
  /**
   * @return the one element of the current {@link ISearchableContainer}.
   * @throws RuntimeException if the current {@link ISearchableContainer} is
   *                          empty.
   * @throws RuntimeException if the current {@link ISearchableContainer} contains
   *                          several elements.
   */
  E getStoredOne();

  //method declaration
  /**
   * @param selector
   * @return the one element the given selector selects from the current
   *         {@link ISearchableContainer}.
   * @throws RuntimeException if the given selector selects none or several
   *                          elements from the current
   *                          {@link ISearchableContainer}.
   */
  E getStoredOne(Predicate<? super E> selector);

  //method declaration
  /**
   * @param selector
   * @return a new {@link ISearchableContainer} with the elements from the current
   *         {@link ISearchableContainer} the given selector skips (!).
   */
  IContainer<E> getStoredOther(Predicate<E> selector);

  //method declaration
  /**
   * @param selector
   * @return a new {@link ISearchableContainer} with the elements the given
   *         selector selects from the current {@link ISearchableContainer}.
   */
  IContainer<E> getStoredSelected(Predicate<? super E> selector);
}
