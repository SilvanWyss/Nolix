package ch.nolix.coreapi.misc.licenseapi;

import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;

/**
 * @author Silvan Wyss
 * @version 2025-07-30
 */
public interface ILicense extends INameHolder {

  /**
   * Activates the current {@link ILicense} with the given key.
   * 
   * @param key
   * @throws RuntimeException if the current {@link ILicense} is already activated
   *                          or does not accept the given key.
   */
  void activateWithKey(String key);

  /**
   * @return true if the current {@link ILicense} is activated, false otherwise.
   */
  boolean isActivated();
}
