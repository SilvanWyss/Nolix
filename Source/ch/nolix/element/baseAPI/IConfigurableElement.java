//package declaration
package ch.nolix.element.baseAPI;

import ch.nolix.common.attributeAPI.Tokened;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.optionalAttributeAPI.OptionalIdentifiableByString;

//interface
/**
 * A {@link IConfigurableElement}
 * is a {@link IMutableElement}, {@link OptionalIdentifiableByString} and {@link Tokened} that:
 * -Can have a role.
 * -Can contain other {@link IConfigurableElement}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 90
 */
public interface IConfigurableElement<C extends IConfigurableElement<C>>
extends IMutableElement<C>, OptionalIdentifiableByString<C>, Tokened {
	
	//method declaration
	/**
	 * @return the {@link IConfigurableElement} of the current {@link IConfigurableElement}.
	 */
	public abstract IContainer<IConfigurableElement<?>> getRefConfigurables();
	
	//method
	/**
	 * @return the {@link IConfigurableElement} of the current {@link IConfigurableElement} recursively.
	 */
	public default IContainer<IConfigurableElement<?>> getRefConfigurablesRecursively() {
		return
		new LinkedList<IConfigurableElement<?>>(getRefConfigurables())
		.addAtEnd(getRefConfigurables().toFromMany(c -> c.getRefConfigurablesRecursively()));
	}
	
	//method declaration
	/**
	 * @param role
	 * @return true if the current {@link IConfigurableElement} has the given role.
	 */
	public abstract boolean hasRole(String role);
	
	//method declaration
	/**
	 * @param token
	 * @return true if the current {@link IConfigurableElement} has the given token.
	 */
	@Override
	public abstract boolean hasToken(String token);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public default C reset() {
		
		resetConfiguration();
		
		return (C)this;
	}
	
	//method declaration
	/**
	 * Resets the configuration of the current {@link IConfigurableElement}.
	 * 
	 * @return the current {@link IConfigurableElement}.
	 */
	public abstract C resetConfiguration();
	
	//method
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
