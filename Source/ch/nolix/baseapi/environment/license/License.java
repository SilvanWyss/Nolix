/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.environment.license;

/**
 * @author Silvan Wyss
 */
public interface License {
  /**
   * Activates the current {@link License} with the given key.
   * 
   * @param key
   * @throws RuntimeException if the current {@link License} is already activated
   *                          or does not accept the given key.
   */
  void activateWithKey(String key);

  /**
   * @return the name of the current {@link License}.
   */
  String getName();

  /**
   * @return true if the current {@link License} is activated, false otherwise.
   */
  boolean isActivated();
}
