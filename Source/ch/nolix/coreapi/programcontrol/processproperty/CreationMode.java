package ch.nolix.coreapi.programcontrol.processproperty;

/**
 * A {@link CreationMode} defines the behavior when a target, that should be
 * created, exist already.
 * 
 * @author Silvan Wyss
 */
public enum CreationMode {
  THROW_EXCEPTION_WHEN_TARGET_DOES_NOT_EXIST_ALREADY,
  CREATE_WHEN_TARGET_DOES_NOT_EXIST_ALREADY,
}
