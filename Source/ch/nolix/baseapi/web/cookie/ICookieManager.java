/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.web.cookie;

import java.util.Optional;

/**
 * @author Silvan Wyss
 */
public interface ICookieManager {
  /**
   * Deletes the cookie with the given name.
   * 
   * @param name
   */
  void deleteCookieByName(String name);

  /**
   * @param cookieName
   * @return a {@link Optional} with the value of the cookie with the given
   *         cookieName if there is such a cookie, an empty {@link Optional}
   *         otherwise
   */
  Optional<String> getOptionalCookieValueByCookieName(String cookieName);

  /**
   * Sets the given value to the cookie with the given name if there is such a
   * cookie, adds a new cookie with the given name and value otherwise.
   * 
   * @param name
   * @param value
   */
  void setOrAddCookieWithNameAndValue(String name, String value);
}
