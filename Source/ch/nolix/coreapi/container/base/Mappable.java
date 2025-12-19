package ch.nolix.coreapi.container.base;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements a {@link Mappable}.
 */
public interface Mappable<E> {
  /**
   * @param mapper
   * @param <T>    is the type of the elements the given mapper maps from the
   *               elements of the current {@link Mappable}.
   * @return a new {@link IContainer} with the elements the given mapper maps from
   *         the elements of the current {@link Mappable}.
   * @throws RuntimeException if the given mapper is null.
   * @throws RuntimeException if one of the elements of the current
   *                          {@link Mappable} is null.
   */
  <T> IContainer<T> to(Function<E, T> mapper);

  /**
   * @param multipleMapper
   * @param <T>            is the type of the elements of the {@link IContainer}s
   *                       the given multipleMapper maps from the elements of the
   *                       current {@link Mappable}.
   * @return a new {@link IContainer} with the elements of the {@link IContainer}s
   *         the given multipleMapper maps from the elements of the current
   *         {@link Mappable}.
   * @throws RuntimeException if the given multipleMapper is null.
   * @throws RuntimeException if one of the elements of the current
   *                          {@link Mappable} is null.
   */
  <T> IContainer<T> toMultiples(Function<E, IContainer<T>> multipleMapper);

  /**
   * @param numberMapper
   * @param <N>          is the type of the {@link Number}s the given numberMapper
   *                     maps from the elements of the current {@link Mappable}.
   * @return a new {@link IContainer} with the {@link Number}s the given
   *         numberMapper maps from the elements of the current {@link Mappable}.
   *         Maps null elements to 0.0.
   * @throws RuntimeException if the given numberMapper is null.
   */
  <N extends Number> IContainer<N> toNumbers(Function<E, N> numberMapper);

  /**
   * @return a new {@link IContainer} with the {@link String} representations of
   *         the elements of the current {@link IContainer}.
   */
  IContainer<String> toStrings();

  /**
   * @param mapper
   * @param <T>    is the type of the elements the given mapper maps from the
   *               elements of the current {@link Mappable} and from the one-based
   *               index of these elements.
   * @return a new {@link IContainer} with the elements the given mapper maps from
   *         the elements of the current {@link Mappable} and from the one-based
   *         index of these elements.
   * @throws RuntimeException if the given mapper is null.
   * @throws RuntimeException if one of the elements of the current
   *                          {@link Mappable} is null.
   */
  <T> IContainer<T> toWithOneBasedIndex(BiFunction<Integer, E, T> mapper);
}
