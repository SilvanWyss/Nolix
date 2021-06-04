//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.elementapi.IConfigurableElement;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 140
 */
public final class DeepConfiguration extends BaseConfiguration<DeepConfiguration> {
	
	//constant
	public static final String TYPE_NAME = "DeepConfiguration";
	
	//constant
	private static final String MAX_SELECTOR_LEVEL_HEADER = "MaxSelectorLevel";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link DeepConfiguration} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static DeepConfiguration fromSpecification(final BaseNode specification) {
		
		final var deepConfiguration = new DeepConfiguration();
		deepConfiguration.resetFrom(specification);
		
		return deepConfiguration;
	}
	
	//attribute
	private final MutableOptionalValue<Integer> maxSelectorLevel =
	MutableOptionalValue.forInt(MAX_SELECTOR_LEVEL_HEADER, this::setMaxSelectorLevel);
	
	//constructor
	/**
	 * Creates a new {@link DeepConfiguration}.
	 */
	public DeepConfiguration() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configure(IConfigurableElement<?> element) {
		if (!hasMaxSelectorLevel()) {

			final var elements = element.getSubConfigurables();
			
			if (selects(element)) {
				final var configurations = getRefConfigurations();
				setAttachingAttributesTo(element);
				elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
			}
				
			elements.forEach(this::configure);
		} else {
			configure(element, getMaxSelectorLevel());
		}
	}
	
	//method
	/**
	 * @return the max selector level of the current {@link DeepConfiguration}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link DeepConfiguration} does not have a max selector level.
	 */
	public int getMaxSelectorLevel() {
		return maxSelectorLevel.getValue();
	}
	
	//method
	/**
	 * @return true if the current {@link DeepConfiguration} has a max selector level.
	 */
	public boolean hasMaxSelectorLevel() {
		return maxSelectorLevel.hasValue();
	}
	
	//method
	/**
	 * Removes the max selector level of the current {@link DeepConfiguration}.
	 */
	public void removeMaxSelectorLevel() {
		maxSelectorLevel.clear();
	}
	
	//method
	/**
	 * Sets the max selector level of the current {@link DeepConfiguration}.
	 * 
	 * @param maxSelectorLevel
	 * @throws NonPositiveArgumentException if the given max selector level is not positive.
	 */
	public void setMaxSelectorLevel(final int maxSelectorLevel) {
		
		//Asserts that the given maxSelectorLevel is positive.
		Validator.assertThat(maxSelectorLevel).thatIsNamed("max selector level").isPositive();
				
		this.maxSelectorLevel.setValue(maxSelectorLevel);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBaseConfiguration() {
		removeMaxSelectorLevel();
	}
		
	//method
	/**
	 * Lets the current {@link DeepConfiguration} configure the given element recursively to the given level.
	 * 
	 * @param element
	 * @param level
	 */
	private void configure(IConfigurableElement<?> element, int level) {
		if (level > 0) {
			
			final var elements = element.getSubConfigurables();
			
			if (selects(element)) {
				
				setAttachingAttributesTo(element);
				
				final var configurations = getRefConfigurations();
				elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
			}
				
			elements.forEach(e -> configure(e, level - 1));
		}
	}
}
