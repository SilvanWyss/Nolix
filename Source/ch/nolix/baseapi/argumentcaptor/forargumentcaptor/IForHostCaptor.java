/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForHostCaptor}
 */
public interface IForHostCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IForHostCaptor} capture the given host.
   * 
   * @param host
   * @return the successor of the current {@link IForHostCaptor}
   * @throws RuntimeException if the given host is null or blank
   * @throws RuntimeException if the current {@link IForHostCaptor} does not have
   *                          a successor
   */
  S forHost(String host);

  /**
   * Lets the current {@link IForHostCaptor} capture the local host.
   * 
   * @return the successor of the current {@link IForHostCaptor}
   * @throws RuntimeException if the current {@link IForHostCaptor} does not have
   *                          a successor
   */
  S forLocalHost();

  /**
   * @return the host the current {@link IForHostCaptor} has captured
   * @throws RuntimeException if the current {@link IForHostCaptor} has not
   *                          captured a host
   */
  String getHost();
}
