/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.extendediterable;

import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a
 *            {@link MappingContainerViewProvider}.
 */
public interface MappingContainerViewProvider<E> {
  /**
   * @param mapper
   * @param <T>    is the type of the elements the given mapper maps from the
   *               elements of the current {@link MappingContainerViewProvider}.
   * @return a new {@link ExtendedIterable} with the elements the given mapper maps from
   *         the elements of the current {@link MappingContainerViewProvider}.
   * @throws RuntimeException if the given mapper is null
   * @throws RuntimeException if one of the mapped elements of the current
   *                          {@link MappingContainerViewProvider} is null.
   */
  <T> ExtendedIterable<T> getViewOf(final Function<E, T> mapper);
}
