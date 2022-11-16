//package declaration
package ch.nolix.system.objectdatabase.parametrizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;

//class
public final class ParametrizedMultiBackReferenceType<
	IMPL,
	C extends IColumn<IMPL>
>
extends BaseParametrizedBackReferenceType<IMPL, C> {
	
	//constructor
	public ParametrizedMultiBackReferenceType(final C backReferencedColumn) {
		super(backReferencedColumn);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.MULTI_BACK_REFERENCE;
	}
}
