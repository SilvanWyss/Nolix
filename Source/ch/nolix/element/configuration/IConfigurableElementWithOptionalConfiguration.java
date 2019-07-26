//package declaration
package ch.nolix.element.configuration;

//own import
import ch.nolix.element.baseAPI.IConfigurableElement;

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
	
	//abstract method
	/**
	 * @return true if the the current {@link IConfigurableElementWithOptionalConfiguration} has a configuration.
	 */
	public abstract boolean hasConfiguration();
	
	//abstract method
	/**
	 * @return the configuration of the current {@link IConfigurableElementWithOptionalConfiguration}.
	 * @throws Exception if the current {@link IConfigurableElementWithOptionalConfiguration} does not have a configuration.
	 */
	public abstract StandardConfiguration getConfiguration();
	
	//abstract method
	/**
	 * Removes the configuration of the current {@link IConfigurableElementWithOptionalConfiguration}.
	 * 
	 * @return the current {@link IConfigurableElementWithOptionalConfiguration}.
	 */
	public abstract CEWOC remmoveConfiguration();
	
	//abstract method
	/**
	 * Sets the configuration of the current {@link IConfigurableElementWithOptionalConfiguration}.
	 * Freezes the given configuration.
	 * 
	 * @param configuration
	 * @return the current {@link IConfigurableElementWithOptionalConfiguration}.
	 */
	public abstract CEWOC setConfiguration(StandardConfiguration configuration);
	
	//default method
	/**
	 * Lets the configuration of the current {@link IConfigurableElementWithOptionalConfiguration} configure
	 * the current {@link IConfigurableElementWithOptionalConfiguration}
	 * if the current {@link IConfigurableElementWithOptionalConfiguration} has a configuration.
	 */
	public default void updateFromConfiguration() {
		if (hasConfiguration()) {
			resetConfiguration();
			getConfiguration().configure(this);
		}
	}
}
