package ch.nolix.coreapi.web.url;

/**
 * @author Silvan Wyss
 */
public interface IUrlTool {
  /**
   * @param url
   * @return a display text for the given url.
   * @throws RuntimeException if the given url is null.
   */
  String getDisplayTextForUrl(String url);
}
