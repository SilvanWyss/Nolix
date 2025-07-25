package ch.nolix.coreapi.container.base;

import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @version 2024-11-12
 * @param <E> is the type of the elements a {@link Groupable}.
 */
public interface Groupable<E> {

  /**
   * @param norm
   * @return a new {@link IContainer} with the elements of the current
   *         {@link Groupable} grouped by the given norm. Ignores null elements.
   * @throws RuntimeException if the given norm is null.
   */
  IContainer<? extends IContainer<E>> getStoredInGroups(Function<E, ?> norm);
}
