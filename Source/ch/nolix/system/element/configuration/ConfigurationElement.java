//package declaration
package ch.nolix.system.element.configuration;

//own imports
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.system.element.mutableelement.MutableOptionalValue;
import ch.nolix.systemapi.elementapi.configurationapi.IConfiguration;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurationElement;

//class
/**
 * A {@link ConfigurationElement} is a {@link ConfigurationElement} that can have a {@link Configuration}.
 * 
 * @author Silvan Wyss
 * @date 2016-05-01
 * @param <CE> is the type of a {@link ConfigurationElement}.
 */
public abstract class ConfigurationElement<CE extends ConfigurationElement<CE>>
extends ConfigurableElement<CE>
implements IConfigurationElement<CE> {
	
	//constant
	private static final String CONFIGURATION_HEADER = PascalCaseCatalogue.CONFIGURATION;
	
	//attribute
	private final MutableOptionalValue<IConfiguration> configuration =
	new MutableOptionalValue<>(
		CONFIGURATION_HEADER,
		this::setConfiguration,
		Configuration::fromSpecification,
		IConfiguration::getSpecification
	);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void applyConfigurationIfHasConfiguration() {
		
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
	public final boolean hasConfiguration() {
		return configuration.hasValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void removeConfiguration() {
		configuration.clear();
		resetConfiguration();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CE setConfiguration(IConfiguration configuration) {
		
		this.configuration.setValue(configuration);
		applyConfigurationIfHasConfiguration();
		
		return asConcrete();
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
	private IConfiguration getRefConfiguration() {
		return configuration.getValue();
	}
}
