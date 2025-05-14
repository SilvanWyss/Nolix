package ch.nolix.coreapi.containerapi.baseapi;

import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @version 2025-05-14
 * @param <E> is the type of the elements of a {@link ViewProvidingMappable}.
 */
public interface ViewProvidingMappable<E> {

  /**
   * @param mapper
   * @param <T>    is the type of the elements the given mapper maps from the
   *               elements of the current {@link ViewProvidingMappable}.
   * @return a new {@link IContainer} with the elements the given mapper maps from
   *         the elements of the current {@link ViewProvidingMappable}.
   * @throws RuntimeException if the given mapper is null.
   * @throws RuntimeException if one of the mapped elements of the current
   *                          {@link ViewProvidingMappable} is null.
   */
  <T> IContainer<T> getViewOf(final Function<E, T> mapper);
}
