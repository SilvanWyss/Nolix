//package declaration
package ch.nolix.element.baseAPI;

import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.attributeAPI.OptionalNamable;
import ch.nolix.common.attributeAPI.Tokened;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;

//interface
/**
 * A {@link IConfigurableElement} is a {@link Named}, {@link IMutableElement} and {@link Tokened} that:
 * -Has a type and super types.
 * -Can have a role.
 * -Can contain other {@link IConfigurableElement}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 90
 */
public interface IConfigurableElement<C extends IConfigurableElement<C>> extends OptionalNamable<C>, IMutableElement<C>, Tokened {
	
	//abstract method
	/**
	 * @return the {@link IConfigurableElement} of the current {@link IConfigurableElement}.
	 */
	public abstract IContainer<IConfigurableElement<?>> getRefConfigurables();
	
	//default method
	/**
	 * @return the {@link IConfigurableElement} of the current {@link IConfigurableElement} recursively.
	 */
	public default IContainer<IConfigurableElement<?>> getRefConfigurablesRecursively() {
		return
		new List<IConfigurableElement<?>>(getRefConfigurables())
		.addAtEnd(getRefConfigurables().toFromMany(c -> c.getRefConfigurablesRecursively()));
	}
	
	//abstract method
	/**
	 * @param role
	 * @return true if the current {@link IConfigurableElement} has the given role.
	 */
	public abstract boolean hasRole(String role);
	
	//abstract method
	/**
	 * @param token
	 * @return true if the current {@link IConfigurableElement} has the given token.
	 */
	@Override
	public abstract boolean hasToken(String token);
	
	//default method
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public default C reset() {
		
		resetConfiguration();
		
		return (C)this;
	}
	
	//abstract method
	/**
	 * Resets the configuration of the current {@link IConfigurableElement}.
	 * 
	 * @return the current {@link IConfigurableElement}.
	 */
	public abstract C resetConfiguration();
	
	//default method
	/**
	 * Resets the configuration of the current {@link IConfigurableElement} recursively.
	 * 
	 * @return the current {@link IConfigurableElement}.
	 */
	@SuppressWarnings("unchecked")
	public default C resetConfigurationRecursively() {
		
		resetConfiguration();
		
		getRefConfigurables().forEach(c -> c.resetConfigurationRecursively());
		
		return (C)this;
	}
}
