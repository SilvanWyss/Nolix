package ch.nolix.coreapi.net.server;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
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
