//package declaration
package ch.nolix.common.license;

import ch.nolix.common.attributeapi.Named;
import ch.nolix.common.container.IContainer;

//class
/**
* A {@link Feature} can be required for certain functionalities.
* 
* @author Silvan Wyss
* @month 2019-11
* @lines 30
*/
public abstract class Feature implements Named {
	
	//method declaration
	/**
	 * @return the authorized {@link License} types of the current {@link Feature}.
	 */
	public abstract IContainer<Class<?>> getAuthorizedLicenseTypes();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return getClass().getName();
	}
}
