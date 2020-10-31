//package declaration
package ch.nolix.element.elementAPI;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.mutableOptionalAttributeAPI.OptionalIdentifiableByString;
import ch.nolix.common.mutableOptionalAttributeAPI.OptionalTokenable;
import ch.nolix.common.requestAPI.ContainsElementByStringIdRequestable;

//interface
/**
 * A {@link IConfigurableElement} is configurable and can contain other {@link IConfigurableElement}s.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 60
 */
public interface IConfigurableElement<C extends IConfigurableElement<C>>
extends
ContainsElementByStringIdRequestable,
IMutableElement<C>,
OptionalIdentifiableByString<C>,
OptionalTokenable<C> {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public default boolean containsElement(final String id) {
		return getSubConfigurablesRecursively().contains(sc -> sc.hasId(id));
	}
	
	//method declaration
	/**
	 * @return the {@link IConfigurableElement}s of the current {@link IConfigurableElement}.
	 */
	public abstract IContainer<IConfigurableElement<?>> getSubConfigurables();
	
	//method
	/**
	 * @return the {@link IConfigurableElement} of the current {@link IConfigurableElement} recursively.
	 */
	public default IContainer<IConfigurableElement<?>> getSubConfigurablesRecursively() {
		
		final var subConfigurables = new LinkedList<IConfigurableElement<?>>();
		subConfigurables.addAtEnd(getSubConfigurables());
		subConfigurables.addAtEnd(getSubConfigurables().toFromMany(IConfigurableElement::getSubConfigurablesRecursively));
		
		return subConfigurables;
	}
	
	//method declaration
	/**
	 * @param role
	 * @return true if the current {@link IConfigurable} has the given role.
	 */
	public abstract boolean hasRole(String role);
	
	//method declaration
	/**
	 * Resets the configuration of the current {@link IConfigurableElement}.
	 * 
	 * @return the current {@link IConfigurableElement}.
	 */
	public abstract C resetConfiguration();
}
