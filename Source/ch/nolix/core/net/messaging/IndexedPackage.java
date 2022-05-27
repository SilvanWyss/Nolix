//package declaration
package ch.nolix.core.net.messaging;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Indexed;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
/**
 * A {@link IndexedPackage} bundles an index and a content.
 * 
 * @author Silvan Wyss
 * @date 2016-06-01
 * @param <C> is the type of the content of a {@link IndexedPackage}.
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
		Validator.assertThat(content).thatIsNamed(LowerCaseCatalogue.CONTENT).isNotNull();
		
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
