//package declaration
package ch.nolix.common.messaging;

//own imports
import ch.nolix.common.attributeapi.Indexed;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link IndexedPackage} bundles an index and a content.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 50
 * @param <C> - The type of the content of a {@link IndexedPackage}.
 */
public class IndexedPackage<C> implements Indexed {
	
	//attributes
	private final int index;
	private final C content;
	
	//constructor
	/**
	 * Creates a new {@link IndexedPackage} with the given index and content.
	 * 
	 * @param index
	 * @param content
	 * @throws ArgumentIsNullException if the given content is null.
	 */
	public IndexedPackage(final int index, final C content) {
		
		//Asserts that the given content is not null.
		Validator.assertThat(content).thatIsNamed(VariableNameCatalogue.CONTENT).isNotNull();
		
		//Sets the index of the current IndexedPackage.
		this.index = index;
		
		//Sets the content of the current IndexedPackage.
		this.content = content;
	}
	
	//method
	/**
	 * @return the content of the current {@link IndexedPackage}.
	 */
	public final C getRefContent() {
		return content;
	}
	
	//method
	/**
	 * @return the index of the current {@link IndexedPackage}.
	 */
	public final int getIndex() {
		return index;
	}
}
