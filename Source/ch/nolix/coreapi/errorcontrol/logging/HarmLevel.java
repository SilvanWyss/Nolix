/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.errorcontrol.logging;

/**
 * @author Silvan Wyss
 */
public enum HarmLevel {
  INFO,
  WARNING,
  ERROR,
  FATAL_ERROR;

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

  public final boolean isLowerThan(final HarmLevel harmLevel) {
    return harmLevel.isHigherThan(this);
  }
}
