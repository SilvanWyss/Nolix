//package declaration
package ch.nolix.core.processProperties;

//enum
/**
 * A {@link WriteMode} defines how something has to be written down or registered
 * when there something else is probably already written down resp. registered.
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
