//package declaration
package ch.nolix.systemapi.elementapi.mainuniversalapi;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.marker.AllowDefaultMethodsAsDesignPattern;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

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
	boolean addedOrChangedAttribute(BaseNode attribute);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	default void addOrChangeAttribute(final BaseNode attribute) {
		if (!addedOrChangedAttribute(attribute)) {
			throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.ATTRIBUTE, attribute);
		}
	}
}
