//package declaration
package ch.nolix.systemapi.elementapi.configurationapi;

//interface
/**
 * A {@link IConfigurationElement} can have a {@link IConfiguration} to apply to itself and to its child elements.
 * 
 * @author Silvan Wyss
 * @date 2022-07-23
 * @param <CE> is the type of a {@link IConfigurationElement}.
 */
public interface IConfigurationElement<CE extends IConfigurationElement<CE>> extends IConfigurableElement<CE> {
	
	//method declaration
	/**
	 * Applies the {@link IConfiguration} of the current {@link IConfigurationElement} to
	 * the current {@link IConfigurationElement} and its child elements if
	 * the current {@link IConfigurationElement} has a {{@link IConfiguration}
	 */
	void applyConfigurationIfHasConfiguration();
	
	//method declaration
	/**
	 * @return true if the current {@link IConfigurationElement} has a {@link IConfiguration}.
	 */
	boolean hasConfiguration();
	
	//method declaration
	/**
	 * Removes the {@link IConfiguration} of the current {@link IConfigurationElement}.
	 */
	void removeConfiguration();
	
	//method declaration
	/**
	 * Sets the given configuration to the current {@link IConfigurationElement}.
	 * 
	 * @param configuration
	 * @return the current {@link IConfigurationElement}.
	 * @throws RuntimeException if the given configuration is null.
	 */
	CE setConfiguration(IConfiguration configuration);
}
