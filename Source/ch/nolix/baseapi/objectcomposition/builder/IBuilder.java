/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.objectcomposition.builder;

/**
 * A {@link IBuilder} can build {@link Object}s.
 * 
 * @author Silvan Wyss
 * @param <O> the type of the {@link Object}s a {@link IBuilder} can build.
 */
public interface IBuilder<O> {
  /**
   * @return a new {@link Object} from the current {@link IBuilder}.
   */
  O build();
}
