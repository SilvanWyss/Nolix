//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedBaseParametrizedBackReferenceType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParametrizedBackReferenceType extends BaseParametrizedBackReferenceType
implements IExtendedBaseParametrizedBackReferenceType<ParametrizedPropertyType<String>> {
	
	//constructor
	public ParametrizedBackReferenceType(final IColumn<?, ?> backReferencedColumn) {
		super(backReferencedColumn);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.BACK_REFERENCE;
	}
}
