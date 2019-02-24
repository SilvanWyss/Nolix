//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.skillAPI.Indexed;
import ch.nolix.core.validator.Validator;

//abstract class
/**
 * A {@link IndexedElement} has an index.
 * 
 * @author Silvan Wyss
 * @month 2018-09
 * @lines 40
 */
public abstract class IndexedElement implements Indexed {
	
	//attribute
	private final int index;
	
	//constructor
	/**
	 * Creates a new {@link IndexedElement} with the given index.
	 * 
	 * @param index
	 * @throws NonPositiveArgumentException if the given index is not positive.
	 */
	public IndexedElement(final int index) {
		
		//Checks if the given index is positive.
		Validator
		.suppose(index)
		.thatIsNamed(VariableNameCatalogue.INDEX)
		.isPositive();
		
		//Sets the index of the current IndexedElement.
		this.index = index;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getIndex() {
		return index;
	}
}
