//package declaration
package ch.nolix.common.processproperty;

//enum
/**
 * A {@link AccessMode} defines the behavior when a target, that should be accessed, does not exist already.
 * 
 * @author Silvan Wyss
 * @month 2020-08
 * @lines 10
 */
public enum AccessMode {
	THROW_EXCEPTION_WHEN_TARGET_DOES_NOT_EXIST_ALREADY,
	CREATE_WHEN_TARGET_DOES_NOT_EXIST_ALREADY,
}
