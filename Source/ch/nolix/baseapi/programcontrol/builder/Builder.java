/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.builder;

/**
 * A {@link Builder} can build {@link Object}s.
 * 
 * @author Silvan Wyss
 * @param <O> the type of the {@link Object}s a {@link Builder} can build.
 */
public interface Builder<O> {
  /**
   * @return a new {@link Object} from the current {@link Builder}.
   */
  O build();
}
