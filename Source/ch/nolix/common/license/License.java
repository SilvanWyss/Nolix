//package declaration
package ch.nolix.common.license;

import ch.nolix.common.attributeapi.Named;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;

//class
/**
 * A {@link License} contains {@link Feature}s.
 * 
 * @author Silvan Wyss
 * @month 2019-11
 * @lines 40
 */
public abstract class License implements Named {
	
	//constructor
	/**
	 * Creates a new {@link License} using the given key.
	 * 
	 * @param key
	 * @throws InvalidArgumentException if the given key is not valid.
	 */
	public License(final String key) {
		
		//Asserts that the current License accepts the given key.
		if (!accepts(key)) {
			throw new InvalidArgumentException(VariableNameCatalogue.KEY, key, "is not valid");
		}
	}
	
	//method declaration
	/**
	 * @param key
	 * @return true if the current {@link License} accepts the given key.
	 */
	public abstract boolean accepts(String key);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return getClass().getName();
	}
}
