package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.ITokenHolder;

/**
 * A {@link IMutableTokenHolder} is a {@link ITokenHolder} whose token can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-09
 */
public interface IMutableTokenHolder extends ITokenHolder {

  /**
   * Sets the token of the current {@link IMutableTokenHolder}.
   * 
   * @param token
   * @throws RuntimeException if the given token is null.
   * @throws RuntimeException if the given token is blank.
   */
  void setToken(String token);
}
