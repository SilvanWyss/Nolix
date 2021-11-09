//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.techapi.databaseapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedBaseParametrizedBackReferenceType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;

//class
public final class ParametrizedOptionalBackReferenceType extends BaseParametrizedBackReferenceType
implements IExtendedBaseParametrizedBackReferenceType<ParametrizedPropertyType<String>> {
	
	//constructor
	public ParametrizedOptionalBackReferenceType(final IColumn<?, ?> backReferencedColumn) {
		super(backReferencedColumn);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_BACK_REFERENCE;
	}
}
