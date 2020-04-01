//package declaration
package ch.nolix.common.mutableAttributeAPI;

//own import
import ch.nolix.common.attributeAPI.Indexed;

//interface
/**
 * A {@link Indexable} is a {@link Indexed} whose index can be set programmatically.
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @lines 20
 * @param <I> The type of a {@link Indexable}.
 */
public interface Indexable<I> extends Indexed {
	
	//method declaration
	/**
	 * Sets the index of the current {@link Indexable}.
	 * 
	 * @param index
	 * @return the current {@link Indexable}.
	 */
	public abstract I setIndex(int index);
}
