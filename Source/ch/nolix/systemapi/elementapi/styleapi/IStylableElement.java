//package declaration
package ch.nolix.systemapi.elementapi.styleapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi.FluentOptionalIdentifiable;
import ch.nolix.coreapi.attributeapi.fluentmutableoptionalattributeuniversalapi.FluentOptionalTokenable;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.requestuniversalapi.TypeRequestable;
import ch.nolix.systemapi.elementapi.mainuniversalapi.IMutableElement;

//interface
/**
 * A {@link IStylableElement} is configurable and can contain other {@link IStylableElement}s.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <SE> is the type of a {@link IStylableElement}.
 */
public interface IStylableElement<SE extends IStylableElement<SE>>
extends
IMutableElement,
FluentOptionalIdentifiable<SE>,
FluentOptionalTokenable<SE>,
TypeRequestable {
	
	//method declaration
	/**
	 * @return the child {@link IStylableElement}s of the current {@link IStylableElement}.
	 */
	IContainer<? extends IStylableElement<?>> getOriChildStylableElements();
	
	//method declaration
	/**
	 * @param role
	 * @return true if the current {@link IStylableElement} has the given role.
	 */
	boolean hasRole(String role);
	
	//method declaration
	/**
	 * Resets the style of the current {@link IStylableElement} and
	 * the style of the child {@link IStylableElement}s of the current {@link IStylableElement}.
	 */
	void resetStyleRecursively();
}
