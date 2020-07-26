//package declaration
package ch.nolix.element.elementAPI;

//own imports
import ch.nolix.common.attributeRequestAPI.RoleRequestable;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.mutableOptionalAttributeAPI.OptionalIdentifiableByString;
import ch.nolix.common.mutableOptionalAttributeAPI.OptionalTokenable;

//interface
/**
 * A {@link IConfigurableElement} is configurable and can contain other {@link IConfigurableElement}s.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 40
 */
public interface IConfigurableElement<C extends IConfigurableElement<C>>
extends IMutableElement<C>, OptionalIdentifiableByString<C>, OptionalTokenable<C>, RoleRequestable {
	
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
		return
		new LinkedList<IConfigurableElement<?>>(getSubConfigurables())
		.addAtEnd(getSubConfigurables().toFromMany(IConfigurableElement::getSubConfigurablesRecursively));
	}
	
	//method declaration
	/**
	 * Resets the configuration of the current {@link IConfigurableElement}.
	 * 
	 * @return the current {@link IConfigurableElement}.
	 */
	public abstract C resetConfiguration();
}
