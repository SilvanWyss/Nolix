package ch.nolix.coreapi.web.url;

/**
 * @author Silvan Wyss
 * @version 2024-12-09
 */
public interface IUrlTool {

  /**
   * @param url
   * @return a display text for the given url.
   * @throws RuntimeException if the given url is null.
   */
  String getDisplayTextForUrl(String url);
}
