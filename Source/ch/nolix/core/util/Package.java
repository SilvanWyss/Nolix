//package declaration
package ch.nolix.core.util;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * A package bundles an index and a context.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 60
 * @param <C> - The type of the context of a package.
 */
public class Package<C> {

	//attributes
	private final int index;
	private final C content;
	
	//constructor
	/**
	 * Creates new package with the given index and context.
	 * 
	 * @param index
	 * @param context
	 * @throws NullArgumentException if the given context is null.
	 */
	public Package(final int index, final C context) {
		
		//Checks if the given context is not null.
		Validator.suppose(context).thatIsNamed("context").isNotNull();
		
		//Sets the index of this package.
		this.index = index;
		
		//sets the context of this package.
		this.content = context;
	}
	
	//method
	/**
	 * @return the context of this package.
	 */
	public final C getRefContent() {
		return content;
	}
	
	//method
	/**
	 * @return the index of this package.
	 */
	public final int getIndex() {
		return index;
	}
	
	//method
	/**
	 * @param index
	 * @return true if this package has the given index.
	 */
	public final boolean hasIndex(final int index) {
		return (getIndex() == index);
	}
}
