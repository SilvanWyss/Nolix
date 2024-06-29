//package declaration
package ch.nolix.coreapi.programcontrolapi.processproperty;

//enum
/**
 * A {@link CreationMode} defines the behavior when a target, that should be
 * created, exist already.
 * 
 * @author Silvan Wyss
 * @version 2020-08-17
 */
public enum CreationMode {
  THROW_EXCEPTION_WHEN_TARGET_DOES_NOT_EXIST_ALREADY,
  CREATE_WHEN_TARGET_DOES_NOT_EXIST_ALREADY,
}
