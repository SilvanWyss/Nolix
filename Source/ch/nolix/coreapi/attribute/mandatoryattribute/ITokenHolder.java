package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link ITokenHolder} has a token.
 * 
 * @author Silvan Wyss
 * @version 2023-02-06
 */
public interface ITokenHolder {
  /**
   * @return the token of the current {@link ITokenHolder}.
   */
  String getToken();

  /**
   * @param token
   * @return true if the current {@link ITokenHolder} has the given token.
   */
  default boolean hasToken(final String token) {
    return getToken().equals(token);
  }
}
