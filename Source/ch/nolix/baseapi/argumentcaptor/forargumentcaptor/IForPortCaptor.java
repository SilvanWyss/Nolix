/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.argumentcaptor.forargumentcaptor;

import ch.nolix.baseapi.argumentcaptor.base.ArgumentCaptor;

/**
 * @author Silvan Wyss
 * @param <S> the type of the successor of a {@link IForPortCaptor}
 */
public interface IForPortCaptor<S> extends ArgumentCaptor<S> {
  /**
   * Lets the current {@link IForPortCaptor} capture the HTTP port.
   * 
   * @return the successor of the current {@link IForPortCaptor}
   * @throws RuntimeException if the current {@link IForPortCaptor} does not have
   *                          a successor
   */
  S forHttpPort();

  /**
   * Lets the current {@link IForPortCaptor} capture the HTTPS port.
   * 
   * @return the successor of the current {@link IForPortCaptor}
   * @throws RuntimeException if the current {@link IForPortCaptor} does not have
   *                          a successor
   */
  S forHttpsPort();

  /**
   * Lets the current {@link IForPortCaptor} capture the MSSQL port.
   * 
   * @return the successor of the current {@link IForPortCaptor}
   * @throws RuntimeException if the current {@link IForPortCaptor} does not have
   *                          a successor
   */
  S forMsSqlPort();

  /**
   * Lets the current {@link IForPortCaptor} capture the given port.
   * 
   * @param port
   * @return the successor of the current {@link IForPortCaptor}
   * @throws RuntimeException if the given port is not a valid port
   * @throws RuntimeException if the current {@link IForPortCaptor} does not have
   *                          a successor
   */
  S forPort(int port);

  /**
   * @return the port the current {@link IForPortCaptor} has captured
   * @throws RuntimeException if the current {@link IForPortCaptor} has not
   *                          captured a port
   */
  int getPort();
}
