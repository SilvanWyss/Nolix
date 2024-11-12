package ch.nolix.coreapi.containerapi.baseapi;

import java.util.function.Function;

/**
 * @author Silvan Wyss
 * @version 2023-10-14
 * @param <E> is the type of the elements a {@link IMappableContainer}.
 */
public interface IMappableContainer<E> {

  /**
   * @param extractor
   * @param <E2>      is the type of the elements the given extractor returns.
   * @return a new {@link IContainer} with the elements the given extractor
   *         extracts from the elements of the current {@link IMappableContainer}.
   */
  <E2> IContainer<E2> to(Function<E, E2> extractor);

  /**
   * @param extractor
   * @param <E2>      is the type of the elements of the
   *                  {@link IMappableContainer}s the given extractor returns.
   * @return a new {@link IContainer} with the elements of the
   *         {@link IMappableContainer}s the given extractor extracts from the
   *         elements of the current {@link IMappableContainer}.
   */
  <E2> IContainer<E2> toFromGroups(Function<E, IContainer<E2>> extractor);
}
