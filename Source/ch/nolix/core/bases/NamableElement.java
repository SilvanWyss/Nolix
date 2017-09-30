//package declaration
package ch.nolix.core.bases;

//own imports
import ch.nolix.core.interfaces.Namable;
import ch.nolix.core.validator2.Validator;

//abstract class
/**
 * A namable element has a name.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 60
 * @parma <NE> - The type of a namable element.
 */
public abstract class NamableElement<NE extends NamableElement<NE>>
implements Namable<NE> {

	//attribute
	private String name;
	
	//constructor
	/**
	 * Creates new namable element with the given name.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	public NamableElement(final String name) {
		setName(name);
	}
	
	//method
	/**
	 * @return the name of this namable element.
	 */
	public final String getName() {
		return name;
	}
	
	//method
	/**
	 * Sets the name of this namable element.
	 * 
	 * @param name
	 * @throws NullArgumentException if the given name is null.
	 * @throws EmptyArgumentException if the given name is empty.
	 */
	@SuppressWarnings("unchecked")
	public final NE setName(final String name) {
		
		//Checks if the given name is not null or empty.
		Validator.suppose(name).thatIsNamed("name").isNotEmpty();
		
		//Sets the name of this namable element.
		this.name = name;
		
		return (NE)this;
	}
}
