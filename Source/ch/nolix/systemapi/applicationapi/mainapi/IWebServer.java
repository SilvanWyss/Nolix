package ch.nolix.systemapi.applicationapi.mainapi;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public interface IWebServer {

  /**
   * @return the ip of the current {@link IWebServer}.
   */
  String getIp();

  /**
   * @return the port of the current {@link IWebServer}.
   */
  int getPort();
}
