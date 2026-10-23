/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.andargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IAndPortCaptor}
 */
public interface IAndPortCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IAndPortCaptor} capture the HTTP port.
   * 
   * @return the successor of the current {@link IAndPortCaptor}
   * @throws RuntimeException if the current {@link IAndPortCaptor} does not have
   *                          a successor
   */
  S andHttpPort();

  /**
   * Lets the current {@link IAndPortCaptor} capture the HTTPS port.
   * 
   * @return the successor of the current {@link IAndPortCaptor}
   * @throws RuntimeException if the current {@link IAndPortCaptor} does not have
   *                          a successor
   */
  S andHttpsPort();

  /**
   * Lets the current {@link IAndPortCaptor} capture the MSSQL port.
   * 
   * @return the successor of the current {@link IAndPortCaptor}
   * @throws RuntimeException if the current {@link IAndPortCaptor} does not have
   *                          a successor
   */
  S andMsSqlPort();

  /**
   * Lets the current {@link IAndPortCaptor} capture the given port.
   * 
   * @param port
   * @return the successor of the current {@link IAndPortCaptor}
   * @throws RuntimeException if the given port is not a valid port
   * @throws RuntimeException if the current {@link IAndPortCaptor} does not have
   *                          a successor
   */
  S andPort(int port);

  /**
   * @return the port the current {@link IAndPortCaptor} has captured
   * @throws RuntimeException if the current {@link IAndPortCaptor} has not
   *                          captured a port
   */
  int getPort();
}
