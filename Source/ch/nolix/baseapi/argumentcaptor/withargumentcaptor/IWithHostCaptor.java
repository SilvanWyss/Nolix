/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IWithHostCaptor}.
 */
public interface IWithHostCaptor<S> extends ArgumentCaptor<S> {
  /**
   * @return the host the current {@link IWithHostCaptor} has captured
   * @throws RuntimeException if the current {@link IWithHostCaptor} has not
   *                          captured a host
   */
  String getHost();

  /**
   * Lets the current {@link IWithHostCaptor} capture the given host.
   * 
   * @param host
   * @return the successor of the current {@link IWithHostCaptor}
   * @throws RuntimeException if the given host is null or blank
   * @throws RuntimeException if the current {@link IWithHostCaptor} does not have
   *                          a successor
   */
  S withHost(String host);
}
