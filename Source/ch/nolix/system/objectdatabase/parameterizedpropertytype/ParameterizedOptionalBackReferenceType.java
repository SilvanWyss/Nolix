//package declaration
package ch.nolix.system.objectdatabase.parameterizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;

//class
public final class ParameterizedOptionalBackReferenceType<

	C extends IColumn
>
extends BaseParameterizedBackReferenceType<C> {
	
	//constructor
	public ParameterizedOptionalBackReferenceType(final C backReferencedColumn) {
		super(backReferencedColumn);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_BACK_REFERENCE;
	}
}
