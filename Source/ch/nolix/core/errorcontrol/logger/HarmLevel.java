//package declaration
package ch.nolix.core.errorcontrol.logger;

//enum
public enum HarmLevel {
  INFO,
  WARNING,
  ERROR,
  FATAL_ERROR;

  //method
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

  //method
  public final boolean isLowerThan(final HarmLevel harmLevel) {
    return harmLevel.isHigherThan(this);
  }
}
