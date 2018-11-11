//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class OptionalBackReference<E extends Entity>
extends SingleBackReference<E> {
	
	//constructor
	public OptionalBackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	public boolean isOptional() {
		return true;
	}
}
