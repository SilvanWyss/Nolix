//package declaration
package ch.nolix.common.mutableAttributeAPI;

//own import
import ch.nolix.common.attributeAPI.IdentifiedByString;

//interface
/**
 * A {@link IdentifiableByString} is a {@link IdentifiedByString} whose id can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 20
 * @param <IBS> The type of a {@link IdentifiableByString}.
 */
public interface IdentifiableByString<IBS> extends IdentifiedByString {
	
	//method declaration
	/**
	 * Sets the id of the current {@link IdentifiableByString}.
	 * 
	 * @param id
	 * @return the current {@link IdentifiableByString}.
	 */
	public abstract IBS setId(long id);
}
