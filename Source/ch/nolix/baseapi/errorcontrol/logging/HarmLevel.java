/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.errorcontrol.logging;

/**
 * @author Silvan Wyss
 */
public enum HarmLevel {
  INFO,
  WARNING,
  ERROR,
  FATAL_ERROR;

  /**
   * @param harmLevel
   * @return true if the current {@link HarmLevel} is higher than the given
   *         harmLevel, false otherwise
   */
  public final boolean isHigherThan(final HarmLevel harmLevel) {
    return switch (this) {
      case INFO ->
        false;
      case WARNING ->
        (harmLevel == INFO);
      case ERROR ->
        (harmLevel == INFO || harmLevel == WARNING);
      case FATAL_ERROR ->
        (harmLevel == INFO || harmLevel == WARNING || harmLevel == ERROR);
    };
  }

  /**
   * @param harmLevel
   * @return true if the current {@link HarmLevel} is lower than the given
   *         harmLevel, false otherwise
   */
  public final boolean isLowerThan(final HarmLevel harmLevel) {
    return harmLevel.isHigherThan(this);
  }
}
