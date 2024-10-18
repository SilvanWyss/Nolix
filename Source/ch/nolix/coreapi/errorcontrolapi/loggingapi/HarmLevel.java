package ch.nolix.coreapi.errorcontrolapi.loggingapi;

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
