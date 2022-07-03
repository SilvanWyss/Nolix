//package declaration
package ch.nolix.core.license;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//class
/**
 * A {@link Feature} can be required for certain functionalities.
 * 
 * @author Silvan Wyss
 * @date 2019-11-16
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
