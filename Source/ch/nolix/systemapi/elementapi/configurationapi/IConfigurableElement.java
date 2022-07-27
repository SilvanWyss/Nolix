//package declaration
package ch.nolix.systemapi.elementapi.configurationapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
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
public interface IConfigurableElement<C extends IConfigurableElement<C>> extends IMutableElement<C>, TypeRequestable {
	
	//method declaration
	/**
	 * @return the child {@link IConfigurableElement}s of the current {@link IConfigurableElement}.
	 */
	IContainer<? extends IConfigurableElement<?>> getRefChildConfigurableElements();
	
	//method declaration
	/**
	 * @param id
	 * @return true if the current {@link IConfigurableElement} has the given id.
	 */
	boolean hasId(String id);
	
	//method declaration
	/**
	 * @param role
	 * @return true if the current {@link IConfigurableElement} has the given role.
	 */
	boolean hasRole(String role);
	
	/**
	 * @param token
	 * @return true if the current {@link IConfigurableElement} has the given token.
	 */
	boolean hasToken(String token);
	
	//method declaration
	/**
	 * Resets the configuration of the current {@link IConfigurableElement} and
	 * the configuration of the child {@link IConfigurableElement}s of the current {@link IConfigurableElement}.
	 */
	void resetConfigurationRecursively();
}
