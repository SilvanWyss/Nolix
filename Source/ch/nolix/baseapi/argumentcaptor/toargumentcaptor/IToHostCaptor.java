/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.toargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IToHostCaptor}
 */
public interface IToHostCaptor<S> extends ArgumentCaptor<S> {
  /**
   * @return the host the current {@link IToHostCaptor} has captured
   * @throws RuntimeException if the current {@link IToHostCaptor} has not
   *                          captured a host
   */
  String getHost();

  /**
   * Lets the current {@link IToHostCaptor} capture the given host.
   * 
   * @param host
   * @return the successor of the current {@link IToHostCaptor}
   * @throws RuntimeException if the given host is null or blank
   * @throws RuntimeException if the current {@link IToHostCaptor} does not have a
   *                          successor
   */
  S toHost(String host);

  /**
   * Lets the current {@link IToHostCaptor} capture the local host.
   * 
   * @return the successor of the current {@link IToHostCaptor}
   * @throws RuntimeException if the current {@link IToHostCaptor} does not have a
   *                          successor
   */
  S toLocalHost();
}
