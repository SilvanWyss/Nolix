/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.extendediterable;

import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements a {@link IterableGroupProvider}
 */
public interface IterableGroupProvider<E> {
  /**
   * @param norm
   * @return a new {@link ExtendedIterable} with the elements of the current
   *         {@link IterableGroupProvider} grouped by the given norm, ignoring null elements
   * @throws RuntimeException if the given norm is null
   */
  ExtendedIterable<? extends ExtendedIterable<E>> getStoredInGroups(Function<E, ?> norm);
}
