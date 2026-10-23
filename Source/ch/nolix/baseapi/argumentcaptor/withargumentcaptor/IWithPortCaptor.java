/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.withargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IWithPortCaptor}
 */
public interface IWithPortCaptor<S> extends ArgumentCaptor<S> {
  /**
   * @return the port the current {@link IWithPortCaptor} has captured
   * @throws RuntimeException if the current {@link IWithPortCaptor} has not
   *                          captured a port
   */
  int getPort();

  /**
   * Lets the current {@link IWithPortCaptor} capture the HTTP port.
   * 
   * @return the successor of the current {@link IWithPortCaptor}
   * @throws RuntimeException if the current {@link IWithPortCaptor} does not have
   *                          a successor
   */
  S withHttpPort();

  /**
   * Lets the current {@link IWithPortCaptor} capture the HTTPS port.
   * 
   * @return the successor of the current {@link IWithPortCaptor}
   * @throws RuntimeException if the current {@link IWithPortCaptor} does not have
   *                          a successor
   */
  S withHttpsPort();

  /**
   * Lets the current {@link IWithPortCaptor} capture the MSSQL port.
   * 
   * @return the successor of the current {@link IWithPortCaptor}
   * @throws RuntimeException if the current {@link IWithPortCaptor} does not have
   *                          a successor
   */
  S withMsSqlPort();

  /**
   * Lets the current {@link IWithPortCaptor} capture the given port.
   * 
   * @param port
   * @return the successor of the current {@link IWithPortCaptor}
   * @throws RuntimeException if the given port is not a valid port
   * @throws RuntimeException if the current {@link IWithPortCaptor} does not have
   *                          a successor
   */
  S withPort(int port);
}
