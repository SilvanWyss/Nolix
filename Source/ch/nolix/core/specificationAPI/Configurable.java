//package declaration
package ch.nolix.core.specificationAPI;

import ch.nolix.core.attributeAPI.Named;
import ch.nolix.core.attributeAPI.Tokened;
//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;

//interface
/**
 * A {@link Configurable} is a {@link Named}, {@link Specifiable} and {@link Tokened} that:
 * -Has a type and super types.
 * -Can have a role.
 * -Can contain other {@link Configurable}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 */
public interface Configurable<C extends Configurable<C>>
extends Named, Specifiable<C>, Tokened {
	
	//abstract method
	/**
	 * @return the {@link Configurable} of the current {@link Configurable}.
	 */
	public abstract IContainer<Configurable<?>> getRefConfigurables();
	
	//default method
	/**
	 * @return the {@link Configurable} of the current {@link Configurable} recursively.
	 */
	public default IContainer<Configurable<?>> getRefConfigurablesRecursively() {
		return
		new List<Configurable<?>>(getRefConfigurables())
		.addAtEnd(getRefConfigurables().toFromMany(c -> c.getRefConfigurablesRecursively()));
	}
	
	//abstract method
	/**
	 * @param role
	 * @return true if the current {@link Configurable} has the given role.
	 */
	public abstract boolean hasRole(String role);
	
	//abstract method
	/**
	 * @param token
	 * @return true if the current {@link Configurable} has the given token.
	 */
	@Override
	public abstract boolean hasToken(String token);
	
	//abstract method
	/**
	 * @param type
	 * @return true if the current {@link Configurable} has the given type or super type.
	 */
	@Override
	public abstract boolean isOfType(String type);
	
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
	 * Resets the configuration of the current {@link Configurable}.
	 * 
	 * @return the current {@link Configurable}.
	 */
	public abstract C resetConfiguration();
}
