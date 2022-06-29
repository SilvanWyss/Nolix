//package declaration
package ch.nolix.systemapi.elementapi.configurationapi;

import ch.nolix.core.skilluniversalapi.TypeRequestable;
import ch.nolix.coreapi.attributeuniversalapi.mutableoptionalattributeuniversalapi.OptionalIdentifiableByString;
import ch.nolix.coreapi.attributeuniversalapi.mutableoptionalattributeuniversalapi.OptionalTokenable;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.coreapi.requestuniversalapi.ContainsElementByStringIdRequestable;
import ch.nolix.systemapi.elementapi.mainuniversalapi.IMutableElement;

//interface
/**
 * A {@link IConfigurableElement} is configurable and can contain other {@link IConfigurableElement}s.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <C> is the type of a {@link IConfigurableElement}.
 */
public interface IConfigurableElement<C extends IConfigurableElement<C>>
extends
ContainsElementByStringIdRequestable,
IMutableElement<C>,
OptionalIdentifiableByString<C>,
OptionalTokenable<C>,
TypeRequestable {
	
	//method declaration
	/**
	 * @return the {@link IConfigurableElement}s of the current {@link IConfigurableElement}.
	 */
	IContainer<IConfigurableElement<?>> getSubConfigurables();
	
	//method declaration
	/**
	 * @param role
	 * @return true if the current {@link IConfigurableElement} has the given role.
	 */
	boolean hasRole(String role);
	
	//method declaration
	/**
	 * Resets the configuration of the current {@link IConfigurableElement} recursively.
	 */
	void resetConfiguration();
	
	//method declaration
	/**
	 * Resets the configuration of the current {@link IConfigurableElement}.
	 */
	void resetElementConfiguration();
}
