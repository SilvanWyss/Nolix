//package declaration
package ch.nolix.common.attributeAPI;

//interface
/**
 * A {@link IdentifiableByString} is a {@link IdentifiedByString} whose id can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 30
 * @param <IBS> The type of a {@link IdentifiableByString}.
 */
public interface IdentifiableByString<IBS> extends IdentifiedByString {
	
	//method declaration
	/**
	 * Removes the id of the current {@link IdentifiableByString}.
	 * 
	 * @return the current {@link IdentifiableByString}.
	 */
	public abstract IBS removeId();
	
	//method declaration
	/**
	 * Sets the id of the current {@link IdentifiableByString}.
	 * 
	 * @param id
	 * @return the current {@link IdentifiableByString}.
	 */
	public abstract IBS setId(long id);
}
