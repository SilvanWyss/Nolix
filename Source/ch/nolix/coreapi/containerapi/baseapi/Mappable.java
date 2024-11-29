package ch.nolix.coreapi.containerapi.baseapi;

import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @version 2023-10-14
 * @param <E> is the type of the elements a {@link Mappable}.
 */
public interface Mappable<E> {

  /**
   * @param mapper
   * @param <E2>   is the type of the elements the given mapper maps to.
   * @return a new {@link IContainer} with the elements the given mapper maps from
   *         the elements of the current {@link Mappable}.
   * @throws RuntimeException if the given mapper is null.
   * @throws RuntimeException if one of the elements of the current
   *                          {@link Mappable} is null.
   */
  <E2> IContainer<E2> to(Function<E, E2> mapper);

  /**
   * @param multipleMapper
   * @param <E2>           is the type of the elements of the {@link IContainer}s
   *                       the given multipleMapper maps to.
   * @return a new {@link IContainer} with the elements of the {@link IContainer}s
   *         the given multipleMapper maps from the elements of the current
   *         {@link Mappable}.
   * @throws RuntimeException if the given multipleMapper is null.
   * @throws RuntimeException if one of the elements of the current
   *                          {@link Mappable} is null.
   */
  <E2> IContainer<E2> toMultiple(Function<E, IContainer<E2>> multipleMapper);

  /**
   * @return a new {@link IContainer} with the {@link String} representations of
   *         the elements of the current {@link IContainer}.
   */
  IContainer<String> toStrings();
}
