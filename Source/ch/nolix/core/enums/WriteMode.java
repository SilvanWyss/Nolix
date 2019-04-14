//package declaration
package ch.nolix.core.enums;

//enum
/**
 * A {@link WriteMode} defines the behavior when something has to be written or registered
 * and there is already written resp. registered something else.
 * 
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 10
 */
public enum WriteMode {
	THROW_EXCEPTION_WHEN_EXISTS_ALREADY,
	OVERWRITE_WHEN_EXISTS_ALREADY,
	SKIP_WHEN_EXISTS_ALREADY,
}
