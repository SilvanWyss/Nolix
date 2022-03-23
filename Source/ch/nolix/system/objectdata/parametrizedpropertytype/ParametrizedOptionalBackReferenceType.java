//package declaration
package ch.nolix.system.objectdata.parametrizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;

//class
public final class ParametrizedOptionalBackReferenceType<
	IMPL,
	C extends IColumn<IMPL>
>
extends BaseParametrizedBackReferenceType<IMPL, C> {
	
	//constructor
	public ParametrizedOptionalBackReferenceType(final C backReferencedColumn) {
		super(backReferencedColumn);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_BACK_REFERENCE;
	}
}
