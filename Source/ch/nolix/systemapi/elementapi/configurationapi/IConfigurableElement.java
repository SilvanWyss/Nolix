//package declaration
package ch.nolix.systemapi.elementapi.configurationapi;

//own imports
import ch.nolix.coreapi.attributeapi.mutableoptionalattributeuniversalapi.OptionalIdentifiableByString;
import ch.nolix.coreapi.attributeapi.mutableoptionalattributeuniversalapi.OptionalTokenable;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.requestuniversalapi.ContainsElementByStringIdRequestable;
import ch.nolix.coreapi.functionapi.requestuniversalapi.TypeRequestable;
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
	 * @return the child {@link IConfigurableElement}s of the current {@link IConfigurableElement}.
	 */
	IContainer<? extends IConfigurableElement<?>> getRefChildConfigurableElements();
	
	//method declaration
	/**
	 * @param role
	 * @return true if the current {@link IConfigurableElement} has the given role.
	 */
	boolean hasRole(String role);
	
	//method declaration
	/**
	 * Resets the configuration of the current {@link IConfigurableElement} and
	 * the configuration of the child {@link IConfigurableElement}s of the current {@link IConfigurableElement}.
	 */
	void resetConfiguration();
	
	//method declaration
	/**
	 * Resets the own configuration of the current {@link IConfigurableElement}.
	 */
	void resetOwnConfiguration();
}
