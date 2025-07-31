package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalTokenHolder} can have a token.
 * 
 * @author Silvan Wyss
 * @version 2020-03-29
 */
public interface IOptionalTokenHolder {

  /**
   * @return the token of the current {@link IOptionalTokenHolder}.
   */
  String getToken();

  /**
   * @return true if the current {@link IOptionalTokenHolder} has a token.
   */
  boolean hasToken();

  /**
   * @param token
   * @return true if the current {@link IOptionalTokenHolder} has the given token.
   */
  default boolean hasToken(String token) {
    return hasToken()
    && getToken().equals(token);
  }
}
