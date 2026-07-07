/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.base;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link ArgumentCaptor}
 */
public interface ArgumentCaptor<S> {
  /**
   * @return the successor {@link ArgumentCaptor} of the current
   *         {@link ArgumentCaptor}
   * @throws RuntimeException if the current {@link ArgumentCaptor} does not have
   *                          a successor {@link ArgumentCaptor}
   */
  S scsArgCpt();
}
