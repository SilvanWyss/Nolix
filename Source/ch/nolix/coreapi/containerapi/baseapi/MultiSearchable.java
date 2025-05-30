package ch.nolix.coreapi.containerapi.baseapi;

import java.util.function.Function;

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
}
