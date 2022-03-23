//package declaration
package ch.nolix.system.objectdata.parametrizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;

//class
public final class ParametrizedOptionalReferenceType<
	IMPL,
	E extends IEntity<IMPL>
>
extends BaseParametrizedReferenceType<IMPL, E> {
	
	//constructor
	public ParametrizedOptionalReferenceType(final Class<E> entityType) {
		super(entityType);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_REFERENCE;
	}
}
