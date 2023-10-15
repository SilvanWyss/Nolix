//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//interface
/**
 * A {@link Tokened} has a token.
 * 
 * @author Silvan Wyss
 * @date 2023-02-06
 */
public interface Tokened {

  // method declaration
  /**
   * @return the token of the current {@link Tokened}.
   */
  String getToken();

  // method
  /**
   * @param token
   * @return true if the current {@link Tokened} has the given token.
   */
  boolean hasToken(String token);
}
