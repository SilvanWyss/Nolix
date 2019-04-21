//package declaration
package ch.nolix.core.license;

import ch.nolix.core.attributeAPI.Named;
//own imports
import ch.nolix.core.container.IContainer;

//abstract class
/**
 * A {@link License} contains specific {@link Permission}s.
 * 
 * @author Silvan Wyss
 * @month 2019-04
 * @lines 40
 */
public abstract class License implements Named {
	
	//method
	/**
	 * @param type
	 * @return true if the current {@link License} contains a {@link Permission} of the given type.
	 */
	public final <FP extends Permission> boolean containsPermission(final Class<FP> type) {
		return getPermissions().contains(fp -> fp.getClass().isAssignableFrom(type));
	}
	
	//abstract method
	/**
	 * @return the {@link Permission}s of the current {@link License}.
	 */
	public abstract IContainer<Permission> getPermissions();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return getClass().getName();
	}
}
