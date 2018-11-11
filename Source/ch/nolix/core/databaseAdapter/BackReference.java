//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class BackReference<E extends Entity>
extends SingleBackReference<E> {
	
	//constructor
	public BackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	public boolean isOptional() {
		return false;
	}
}
