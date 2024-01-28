//package declaration
package ch.nolix.coreapi.webapi.cookieapi;

//Java imports
import java.util.Optional;

//interface
public interface ICookieManager {

  //method declaration
  void deleteCookieByName(String name);

  //method declaration
  Optional<String> getOptionalCookieValueByCookieName(String cookieName);

  //method declaration
  void setOrAddCookieWithNameAndValue(String name, String value);
}
