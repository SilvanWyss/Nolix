//package declaration
package ch.nolix.common.license;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.containers.IContainer;

//abstract class
/**
* A {@link Feature} can be required for certain functionalities.
* 
* @author Silvan Wyss
* @month 2019-11
* @lines 30
*/
public abstract class Feature implements Named {
	
	//abstract method
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
