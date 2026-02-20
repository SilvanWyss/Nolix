/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.server;

/**
 * @author Silvan Wyss
 */
public interface ISinglePortServer {
  /**
   * @return the ip of the current {@link ISinglePortServer}.
   */
  String getIp();

  /**
   * @return the port of the current {@link ISinglePortServer}.
   */
  int getPort();
}
