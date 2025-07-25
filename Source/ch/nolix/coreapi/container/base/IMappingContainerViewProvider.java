package ch.nolix.coreapi.container.base;

import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @version 2025-05-14
 * @param <E> is the type of the elements of a {@link IMappingContainerViewProvider}.
 */
public interface IMappingContainerViewProvider<E> {

  /**
   * @param mapper
   * @param <T>    is the type of the elements the given mapper maps from the
   *               elements of the current {@link IMappingContainerViewProvider}.
   * @return a new {@link IContainer} with the elements the given mapper maps from
   *         the elements of the current {@link IMappingContainerViewProvider}.
   * @throws RuntimeException if the given mapper is null.
   * @throws RuntimeException if one of the mapped elements of the current
   *                          {@link IMappingContainerViewProvider} is null.
   */
  <T> IContainer<T> getViewOf(final Function<E, T> mapper);
}
