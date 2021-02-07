//package declaration
package ch.nolix.element.elementapi;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.generalskillapi.TypeRequestable;
import ch.nolix.common.mutableoptionalattributeapi.OptionalIdentifiableByString;
import ch.nolix.common.mutableoptionalattributeapi.OptionalTokenable;
import ch.nolix.common.requestapi.ContainsElementByStringIdRequestable;

//interface
/**
 * A {@link IConfigurableElement} is configurable and can contain other {@link IConfigurableElement}s.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 80
 * @param <C> is the type of a {@link IConfigurableElement}.
 */
public interface IConfigurableElement<C extends IConfigurableElement<C>>
extends
ContainsElementByStringIdRequestable,
IMutableElement<C>,
OptionalIdentifiableByString<C>,
OptionalTokenable<C>,
TypeRequestable {
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default boolean containsElement(final String id) {
		return getSubConfigurablesRecursively().contains(sc -> sc.hasId(id));
	}
	
	//method declaration
	/**
	 * @return the {@link IConfigurableElement}s of the current {@link IConfigurableElement}.
	 */
	IContainer<IConfigurableElement<?>> getSubConfigurables();
	
	//method
	/**
	 * @return the {@link IConfigurableElement}s of the current {@link IConfigurableElement} recursively.
	 */
	default IContainer<IConfigurableElement<?>> getSubConfigurablesRecursively() {
		
		final var subConfigurables = new LinkedList<IConfigurableElement<?>>();
		subConfigurables.addAtEnd(getSubConfigurables());
		subConfigurables.addAtEnd(getSubConfigurables().toFromMany(IConfigurableElement::getSubConfigurablesRecursively));
		
		return subConfigurables;
	}
	
	//method declaration
	/**
	 * @param role
	 * @return true if the current {@link IConfigurableElement} has the given role.
	 */
	boolean hasRole(String role);
	
	//method
	/**
	 * Resets the configuration of the current {@link IConfigurableElement} recursively.
	 */
	default void resetConfiguration() {
		
		resetConfigurationOnSelf();
		
		getSubConfigurables().forEach(IConfigurableElement::resetConfiguration);
	}
	
	//method declaration
	/**
	 * Resets the configuration of the current {@link IConfigurableElement} on itself, and on itself only.
	 */
	void resetConfigurationOnSelf();
}
