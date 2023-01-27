//package declaration
package ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeuniversalapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.IdentifiedByString;

//interface
/**
 * A {@link FluentIdentifiableByString} is a {@link IdentifiedByString} whose id can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 * @param <IBS> is the type of a {@link FluentIdentifiableByString}.
 */
public interface FluentIdentifiableByString<IBS> extends IdentifiedByString {
	
	//method declaration
	/**
	 * Sets the id of the current {@link FluentIdentifiableByString}.
	 * 
	 * @param id
	 * @return the current {@link FluentIdentifiableByString}.
	 */
	IBS setId(long id);
}
