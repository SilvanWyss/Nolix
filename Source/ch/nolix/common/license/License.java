//package declaration
package ch.nolix.common.license;

//own imports
import ch.nolix.common.attributeapi.Named;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.invalidargumentexception.UnacceptedKeyException;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @date 2017-05-16
 * @lines 110
 */
public abstract class License implements Named {
	
	//attribute
	private boolean activated;
	
	//method
	/**
	 * Activates the current {@link License} with the given key.
	 * 
	 * @param key
	 * @throws InvalidArgumentException if the current {@link License} is already activated.
	 * @throws UnacceptedKeyException if the current {@link License} does no accepts the given key.
	 */
	public final void activate(final String key) {
		
		assertIsNotActivated();
		assertAccepts(key);
		
		activated = true;
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link License} is not activated.
	 */
	public final void assetIsActivated() {
		if (!isActivated()) {
			throw new InvalidArgumentException(this, "is not actiaved");
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return getClass().getName();
	}
	
	//method
	/**
	 * @return true if the current {@link License} is activated.
	 */
	public final boolean isActivated() {
		return activated;
	}
	
	//method declaration
	/**
	 * @param filteredKey
	 * @return true if the current {@link License} accepts the given filteredKey.
	 */
	protected abstract boolean acceptsFilteredKey(String filteredKey);
	
	//method
	/**
	 * @param key
	 * @return true if the current {@link License} accepts the given key.
	 */
	private boolean accepts(final String key) {
		return acceptsFilteredKey(getFilteredKey(key));
	}
	
	//method
	/**
	 * @throws UnacceptedKeyException if the current {@link License} does no accepts the given key.
	 */
	private void assertAccepts(final String key) {
		if (!accepts(key)) {
			throw new UnacceptedKeyException(key);
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link License} is activated.
	 */
	private void assertIsNotActivated() {
		if (isActivated()) {
			throw new InvalidArgumentException(this, "is actiaved");
		}
	}
	
	/**
	 * @param key
	 * @return a filtered key for the given key.
	 * @throws ArgumentIsNullException if the given key is null.
	 */
	private String getFilteredKey(final String key) {
		
		Validator.assertThat(key).thatIsNamed(VariableNameCatalogue.KEY).isNotNull();
		
		return
		key
		.replace(StringCatalogue.MINUS, StringCatalogue.EMPTY_STRING)
		.replace(StringCatalogue.SPACE, StringCatalogue.EMPTY_STRING)
		.replace(StringCatalogue.TABULATOR, StringCatalogue.EMPTY_STRING);
	}
}
