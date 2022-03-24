//package declaration
package ch.nolix.system.objectdata.parametrizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public final class ParametrizedReferenceType<
	IMPL,
	E extends IEntity<IMPL>
>
extends BaseParametrizedReferenceType<IMPL, E> {
	
	//constructor
	public ParametrizedReferenceType(final ITable<IMPL, E> referencedTable) {
		super(referencedTable);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.REFERENCE;
	}
}
