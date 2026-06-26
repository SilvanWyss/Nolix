/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.extendediterable;

import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link Groupable}.
 */
public interface Groupable<E> {
  /**
   * @param norm
   * @return a new {@link ExtendedIterable} with the elements of the current
   *         {@link Groupable} grouped by the given norm. Ignores null elements.
   * @throws RuntimeException if the given norm is null.
   */
  ExtendedIterable<? extends ExtendedIterable<E>> getStoredInGroups(Function<E, ?> norm);
}
