package ch.nolix.coreapi.web.cookie;

import java.util.Optional;

public interface ICookieManager {
  void deleteCookieByName(String name);

  Optional<String> getOptionalCookieValueByCookieName(String cookieName);

  void setOrAddCookieWithNameAndValue(String name, String value);
}
