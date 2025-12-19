package ch.nolix.coreapi.attribute.mutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalTokenHolder;

/**
 * A {@link IMutableOptionalTokenHolder} is a {@link IOptionalTokenHolder} whose
 * token can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableOptionalTokenHolder extends IOptionalTokenHolder {
  /**
   * Removes the token of the current {@link IMutableOptionalTokenHolder}.
   */
  void removeToken();

  /**
   * Sets the token of the current {@link IMutableOptionalTokenHolder}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  void setToken(String token);
}
