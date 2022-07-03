//package declaration
package ch.nolix.systemapi.elementapi.mainuniversalapi;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * @author Silvan Wyss
 * @date 2021-04-01
 * @param <RME> is the type of a {@link IRespondingMutableElement}.
 */
@AllowDefaultMethodsAsDesignPattern
public interface IRespondingMutableElement<RME extends IRespondingMutableElement<RME>> extends IMutableElement<RME> {
	
	//method declaration
	/**
	 * Adds or changes the given attribute to the current {@link IRespondingMutableElement} if
	 * the given attributes matches.
	 * 
	 * @param attribute
	 * @return true if the given attribute was added or changed to the current {@link IRespondingMutableElement}.
	 */
	boolean addedOrChangedAttribute(INode<?> attribute);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default void addOrChangeAttribute(final INode<?> attribute) {
		if (!addedOrChangedAttribute(attribute)) {
			throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.ATTRIBUTE, attribute);
		}
	}
}
