//package declaration
package ch.nolix.system.objectdatabase.parametrizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public final class ParametrizedReferenceType<

	E extends IEntity
>
extends BaseParametrizedReferenceType<E> {
	
	//constructor
	public ParametrizedReferenceType(final ITable<E> referencedTable) {
		super(referencedTable);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.REFERENCE;
	}
}
