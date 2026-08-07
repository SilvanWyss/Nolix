/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.server;

/**
 * @author Silvan Wyss
 */
public interface SinglePortServer {
  /**
   * @return the ip of the current {@link SinglePortServer}.
   */
  String getIp();

  /**
   * @return the port of the current {@link SinglePortServer}.
   */
  int getPort();
}
