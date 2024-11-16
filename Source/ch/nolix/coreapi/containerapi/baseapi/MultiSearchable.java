package ch.nolix.coreapi.containerapi.baseapi;

import java.util.function.Function;
import java.util.function.Predicate;

import ch.nolix.coreapi.containerapi.commoncontainerapi.SingleSearchable;

/**
 * @author Silvan Wyss
 * @version 2024-11-12
 * @param <E> is the type of the elements a {@link MultiSearchable}.
 */
public interface MultiSearchable<E> {

  /**
   * @param norm
   * @return a new {@link IContainer} with the elements of the current
   *         {@link MultiSearchable} grouped by the given norm. Ignores null
   *         elements.
   * @throws RuntimeException if the given norm is null.
   */
  IContainer<? extends IContainer<E>> getStoredInGroups(Function<E, ?> norm);

  /**
   * @param type
   * @param <E2> is the type of the elements of the returned
   *             {@link SingleSearchable}.
   * @return a new {@link IContainer} with the elements from the current
   *         {@link MultiSearchable} that are of the given type.
   * @throws RuntimeException if the given type is null.
   */
  <E2 extends E> IContainer<E2> getStoredOfType(Class<E2> type);

  /**
   * @param selector
   * @return a new {@link IContainer} with the elements from the current
   *         {@link MultiSearchable} the given selector skips. Ignores null
   *         elements.
   * @throws RuntimeException if the given selector is null.
   */
  IContainer<E> getStoredOthers(Predicate<E> selector);

  /**
   * @param selector
   * @return a new {@link IContainer} with the elements the given selector selects
   *         from the current {@link MultiSearchable}. Ignores null elements.
   * @throws RuntimeException if the given selector is null.
   */
  IContainer<E> getStoredSelected(Predicate<? super E> selector);
}
