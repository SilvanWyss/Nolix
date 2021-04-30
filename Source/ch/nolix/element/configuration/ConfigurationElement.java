//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.element.base.MutableOptionalValue;

//class
/**
 * A {@link ConfigurationElement} is a {@link ConfigurationElement} that can have a {@link Configuration}.
 * 
 * @author Silvan Wyss
 * @date 2016-05-01
 * @lines 100
 * @param <CE> is the type of a {@link ConfigurationElement}.
 */
public abstract class ConfigurationElement<CE extends ConfigurationElement<CE>> extends ConfigurableElement<CE> {
	
	//constant
	private static final String CONFIGURATION_HEADER = PascalCaseCatalogue.CONFIGURATION;
	
	//attribute
	private final MutableOptionalValue<Configuration> configuration =
	new MutableOptionalValue<>(
		CONFIGURATION_HEADER,
		this::setConfiguration,
		Configuration::fromSpecification,
		Configuration::getSpecification
	);
	
	//method
	/**
	 * @return true if the current {@link ConfigurationElement} has a {@link Configuration}.
	 */
	public final boolean hasConfiguration() {
		return configuration.hasValue();
	}
	
	//method
	/**
	 * Removes the {@link Configuration} of the current {@link ConfigurationElement}.
	 */
	public void removeConfiguration() {
		configuration.clear();
		resetConfiguration();
	}
	
	//method
	/**
	 * Sets the {@link Configuration} of the current {@link ConfigurationElement}.
	 * 
	 * @param configuration
	 * @return the current {@link ConfigurationElement}.
	 * @throws ArgumentIsNullException if the given configuration is null.
	 */
	public CE setConfiguration(Configuration configuration) {
		
		this.configuration.setValue(configuration);
		updateFromConfiguration();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Updates the current {@link Configuration} from its {@link Configuration}.
	 */
	public void updateFromConfiguration() {
		
		//Handles the case that the current ConfigurationElement has a Configuration.
		if (hasConfiguration()) {
			resetConfiguration();
			getRefConfiguration().configure(this);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetConfigurableElement() {
		
		removeConfiguration();
		resetConfiguration();
		
		resetConfigurationElement();
	}
	
	//method
	/**
	 * Resets the current {@link ConfigurationElement}.
	 */
	protected abstract void resetConfigurationElement();
	
	//method
	/**
	 * @return the {@link Configuration} of the current {@link ConfigurationElement}.
	 */
	private Configuration getRefConfiguration() {
		return configuration.getValue();
	}
}
