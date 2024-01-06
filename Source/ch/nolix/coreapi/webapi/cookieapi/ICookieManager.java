//package declaration
package ch.nolix.coreapi.webapi.cookieapi;

//interface
public interface ICookieManager {

  //method declaration
  void deleteCookieByName(String name);

  //method declaration
  String getCookieValueByCookieNameOrNull(String cookieName);

  //method declaration
  void setOrAddCookieWithNameAndValue(String name, String value);
}