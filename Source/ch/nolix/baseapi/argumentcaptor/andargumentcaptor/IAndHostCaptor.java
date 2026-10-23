/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndHostCaptor}
 */
public interface IAndHostCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IAndHostCaptor} capture the given host.
   * 
   * @param host
   * @return the successor of the current {@link IAndHostCaptor}
   * @throws RuntimeException if the given host is null or blank
   * @throws RuntimeException if the current {@link IAndHostCaptor} does not have
   *                          a successor
   */
  S andHost(String host);

  /**
   * Lets the current {@link IAndHostCaptor} capture the local host.
   * 
   * @return the successor of the current {@link IAndHostCaptor}
   * @throws RuntimeException if the current {@link IAndHostCaptor} does not have
   *                          a successor
   */
  S andLocalHost();

  /**
   * @return the host the current {@link IAndHostCaptor} has captured
   * @throws RuntimeException if the current {@link IAndHostCaptor} has not
   *                          captured a host
   */
  String getHost();
}
