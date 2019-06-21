//package declaration
package ch.nolix.element.configuration;

//interface
/**
 * A {@link IConfigurationElement} can have a configuration and apply it to itself.
 * 
 * @author Silvan Wyss
 * @month 2019-06
 * @lines 30
 * @param <CE> The type of a {@link IConfigurationElement}.
 */
public interface IConfigurationElement<CE extends IConfigurationElement<CE>> {
	
	//abstract method
	/**
	 * Removes the configuration of the current {@link IConfigurationElement}.
	 * 
	 * @return the current {@link IConfigurationElement}.
	 */
	public abstract CE removeConfiguration();
	
	//abstract method
	/**
	 * Removes the configuration of the current {@link IConfigurationElement}.
	 * 
	 * @param configuration
	 * @return the current {@link IConfigurationElement}.
	 */
	public abstract CE setConfiguration(StandardConfiguration configuration);
}
