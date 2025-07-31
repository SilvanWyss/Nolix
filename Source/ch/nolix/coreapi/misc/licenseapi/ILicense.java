package ch.nolix.coreapi.misc.licenseapi;

/**
 * @author Silvan Wyss
 * @version 2025-07-30
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
