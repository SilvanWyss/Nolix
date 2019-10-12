//package declaration
package ch.nolix.system.databaseAdapter;

//class
public final class OptionalBackReference<E extends Entity> extends SingleBackReference<E> {
	
	//constructor
	public OptionalBackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	@Override
	public boolean isOptional() {
		return true;
	}
}
