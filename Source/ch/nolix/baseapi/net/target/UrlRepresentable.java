/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.net.target;

/**
 * @author Silvan Wyss
 */
public interface UrlRepresentable {
  /**
   * @return a URL representation of the current {@link UrlRepresentable}
   */
  String toUrl();
}
