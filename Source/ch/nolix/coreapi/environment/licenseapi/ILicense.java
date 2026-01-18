/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.environment.licenseapi;

/**
 * @author Silvan Wyss
 */
public interface ILicense {
  /**
   * Activates the current {@link ILicense} with the given key.
   * 
   * @param key
   * @throws RuntimeException if the current {@link ILicense} is already activated
   *                          or does not accept the given key.
   */
  void activateWithKey(String key);

  /**
   * @return the name of the current {@link ILicense}.
   */
  String getName();

  /**
   * @return true if the current {@link ILicense} is activated, false otherwise.
   */
  boolean isActivated();
}
