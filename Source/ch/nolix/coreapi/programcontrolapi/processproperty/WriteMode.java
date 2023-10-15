//package declaration
package ch.nolix.coreapi.programcontrolapi.processproperty;

//enum
/**
 * A {@link WriteMode} defines the behavior when a target, that should be
 * created, exists already.
 * 
 * @author Silvan Wyss
 * @date 2019-04-14
 */
public enum WriteMode {
  THROW_EXCEPTION_WHEN_TARGET_EXISTS_ALREADY,
  OVERWRITE_WHEN_TARGET_EXISTS_ALREADY,
  SKIP_WHEN_TARGET_EXISTS_ALREADY,
}
