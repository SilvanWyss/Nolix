//package declaration
package ch.nolix.element.configuration;

import ch.nolix.element.elementapi.IConfigurableElement;

//interface
/**
 * A {@link IConfigurableElementWithOptionalConfiguration} is a {@link IConfigurableElement}
 * that can have a configuration and configure itself.
 * 
 * @author Silvan Wyss
 * @month 2019-09
 * @lines 60
 * @param <CEWOC> The type of a {@link IConfigurableElementWithOptionalConfiguration}.
 */
public interface IConfigurableElementWithOptionalConfiguration<CEWOC extends IConfigurableElement<CEWOC>>
extends IConfigurableElement<CEWOC> {
	
	//method declaration
	/**
	 * @return true if the the current {@link IConfigurableElementWithOptionalConfiguration} has a configuration.
	 */
	boolean hasConfiguration();
	
	//method declaration
	/**
	 * @return the configuration of the current {@link IConfigurableElementWithOptionalConfiguration}.
	 * @throws Exception if the current {@link IConfigurableElementWithOptionalConfiguration} does not have a configuration.
	 */
	Configuration getConfiguration();
	
	//method declaration
	/**
	 * Removes the configuration of the current {@link IConfigurableElementWithOptionalConfiguration}.
	 * 
	 * @return the current {@link IConfigurableElementWithOptionalConfiguration}.
	 */
	CEWOC remmoveConfiguration();
	
	//method declaration
	/**
	 * Sets the configuration of the current {@link IConfigurableElementWithOptionalConfiguration}.
	 * Freezes the given configuration.
	 * 
	 * @param configuration
	 * @return the current {@link IConfigurableElementWithOptionalConfiguration}.
	 */
	CEWOC setConfiguration(Configuration configuration);
	
	//method
	/**
	 * Lets the configuration of the current {@link IConfigurableElementWithOptionalConfiguration} configure
	 * the current {@link IConfigurableElementWithOptionalConfiguration}
	 * if the current {@link IConfigurableElementWithOptionalConfiguration} has a configuration.
	 */
	default void updateFromConfiguration() {
		if (hasConfiguration()) {
			resetConfiguration();
			getConfiguration().configure(this);
		}
	}
}
