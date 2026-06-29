/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.base;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IArgumentCaptor}
 */
public interface IArgumentCaptor<S> {
  /**
   * @return the successor {@link IArgumentCaptor} of the current
   *         {@link IArgumentCaptor}
   * @throws RuntimeException if the current {@link IArgumentCaptor} does not have
   *                          a successor {@link IArgumentCaptor}
   */
  S scsArgCpt();
}
