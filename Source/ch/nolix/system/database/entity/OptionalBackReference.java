//package declaration
package ch.nolix.system.database.entity;

import ch.nolix.businessapi.databaseapi.datatypeapi.DataType;

//class
public final class OptionalBackReference<E extends Entity> extends SingleBackReference<E> {
	
	//constructor
	public OptionalBackReference(final String referencingFieldName) {
		
		//Calls constructor of the base class.
		super(referencingFieldName);
	}
	
	//method
	@Override
	public DataType getPropertyKind() {
		return DataType.OPTIONAL_BACK_REFERENCE;
	}
	
	//method
	@Override
	public boolean isOptional() {
		return true;
	}
	
	//method
	@Override
	public void assertCanBeSaved() {}
}
