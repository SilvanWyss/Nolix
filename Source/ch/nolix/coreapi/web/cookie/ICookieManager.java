/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.web.cookie;

import java.util.Optional;

/**
 * @author Silvan Wyss
 */
public interface ICookieManager {
  void deleteCookieByName(String name);

  Optional<String> getOptionalCookieValueByCookieName(String cookieName);

  void setOrAddCookieWithNameAndValue(String name, String value);
}
